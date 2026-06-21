package com.spring.multipartfile.Spring_Multipart_File;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/files")
public class ChunkUploadController {

    private final ChunkUploadService chunkUploadService;

    public ChunkUploadController(ChunkUploadService chunkUploadService) {
        this.chunkUploadService = chunkUploadService;
    }

    @PostMapping(
            value = "/chunk",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<ChunkUploadResponse> uploadChunk(
            @RequestParam String fileId,
            @RequestParam String fileName,
            @RequestParam int chunkIndex,
            @RequestParam int totalChunks,
            @RequestPart("file") MultipartFile file
    ) throws Exception {

        ChunkUploadResponse response = chunkUploadService.saveChunk(
                fileId,
                fileName,
                chunkIndex,
                totalChunks,
                file
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{fileId}/status")
    public ResponseEntity<UploadStatusResponse> getStatus(
            @PathVariable String fileId
    ) throws Exception {

        return ResponseEntity.ok(chunkUploadService.getUploadStatus(fileId));
    }
}