package com.spring.multipartfile.Spring_Multipart_File;

public record ChunkUploadResponse(
        String fileId,
        int chunkIndex,
        int uploadedChunks,
        int totalChunks,
        boolean completed,
        String finalFileName
) {
}