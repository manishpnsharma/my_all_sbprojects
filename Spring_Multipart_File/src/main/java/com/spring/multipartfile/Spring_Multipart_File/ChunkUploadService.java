package com.spring.multipartfile.Spring_Multipart_File;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.*;
import java.util.Comparator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
public class ChunkUploadService {

    @Value("${app.upload.temp-dir}")
    private String tempDir;

    @Value("${app.upload.final-dir}")
    private String finalDir;

    private Path tempRoot;
    private Path finalRoot;

    private final ConcurrentMap<String, ReentrantLock> locks = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() throws Exception {
        tempRoot = Paths.get(tempDir).toAbsolutePath().normalize();
        finalRoot = Paths.get(finalDir).toAbsolutePath().normalize();

        Files.createDirectories(tempRoot);
        Files.createDirectories(finalRoot);
    }

    public ChunkUploadResponse saveChunk(
            String fileId,
            String fileName,
            int chunkIndex,
            int totalChunks,
            MultipartFile file
    ) throws Exception {

        validateInput(fileId, fileName, chunkIndex, totalChunks, file);

        Path chunkDir = getChunkDirectory(fileId);
        Files.createDirectories(chunkDir);

        Path chunkPath = chunkDir.resolve(chunkIndex + ".part");

        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, chunkPath, StandardCopyOption.REPLACE_EXISTING);
        }

        int uploadedChunks = countUploadedChunks(chunkDir);

        boolean completed = false;
        String finalFileName = null;

        if (uploadedChunks == totalChunks) {
            ReentrantLock lock = locks.computeIfAbsent(fileId, id -> new ReentrantLock());

            lock.lock();
            try {
                if (allChunksAvailable(chunkDir, totalChunks)) {
                    finalFileName = mergeChunks(fileId, fileName, chunkDir, totalChunks);
                    completed = true;
                    deleteDirectory(chunkDir);
                }
            } finally {
                lock.unlock();
                locks.remove(fileId);
            }
        }

        return new ChunkUploadResponse(
                fileId,
                chunkIndex,
                uploadedChunks,
                totalChunks,
                completed,
                finalFileName
        );
    }

    public UploadStatusResponse getUploadStatus(String fileId) throws Exception {
        Path chunkDir = getChunkDirectory(fileId);

        if (!Files.exists(chunkDir)) {
            return new UploadStatusResponse(fileId, Set.of());
        }

        try (Stream<Path> stream = Files.list(chunkDir)) {
            Set<Integer> uploadedChunks = stream
                    .filter(path -> path.getFileName().toString().endsWith(".part"))
                    .map(path -> path.getFileName().toString().replace(".part", ""))
                    .map(Integer::parseInt)
                    .collect(Collectors.toSet());

            return new UploadStatusResponse(fileId, uploadedChunks);
        }
    }

    private String mergeChunks(
            String fileId,
            String originalFileName,
            Path chunkDir,
            int totalChunks
    ) throws Exception {

        String safeFileName = Paths.get(originalFileName).getFileName().toString();
        String finalFileName = fileId + "_" + safeFileName;

        Path tempFinalFile = finalRoot.resolve(finalFileName + ".uploading");
        Path finalFile = finalRoot.resolve(finalFileName);

        try (OutputStream outputStream = Files.newOutputStream(
                tempFinalFile,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING
        )) {
            for (int i = 0; i < totalChunks; i++) {
                Path chunkPath = chunkDir.resolve(i + ".part");
                Files.copy(chunkPath, outputStream);
            }
        }

        Files.move(tempFinalFile, finalFile, StandardCopyOption.REPLACE_EXISTING);

        return finalFileName;
    }

    private boolean allChunksAvailable(Path chunkDir, int totalChunks) {
        return IntStream.range(0, totalChunks)
                .allMatch(i -> Files.exists(chunkDir.resolve(i + ".part")));
    }

    private int countUploadedChunks(Path chunkDir) throws Exception {
        try (Stream<Path> stream = Files.list(chunkDir)) {
            return (int) stream
                    .filter(path -> path.getFileName().toString().endsWith(".part"))
                    .count();
        }
    }

    private Path getChunkDirectory(String fileId) {
        Path chunkDir = tempRoot.resolve(fileId).normalize();

        if (!chunkDir.startsWith(tempRoot)) {
            throw new IllegalArgumentException("Invalid fileId");
        }

        return chunkDir;
    }

    private void validateInput(
            String fileId,
            String fileName,
            int chunkIndex,
            int totalChunks,
            MultipartFile file
    ) {
        if (fileId == null || fileId.isBlank()) {
            throw new IllegalArgumentException("fileId is required");
        }

        if (fileName == null || fileName.isBlank()) {
            throw new IllegalArgumentException("fileName is required");
        }

        if (totalChunks <= 0) {
            throw new IllegalArgumentException("totalChunks must be greater than zero");
        }

        if (chunkIndex < 0 || chunkIndex >= totalChunks) {
            throw new IllegalArgumentException("Invalid chunkIndex");
        }

        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("Chunk file is empty");
        }
    }

    private void deleteDirectory(Path directory) throws Exception {
        if (!Files.exists(directory)) {
            return;
        }

        try (Stream<Path> walk = Files.walk(directory)) {
            walk.sorted(Comparator.reverseOrder())
                    .forEach(path -> {
                        try {
                            Files.deleteIfExists(path);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    });
        }
    }
}