package com.spring.multipartfile.Spring_Multipart_File;

import java.util.Set;

public record UploadStatusResponse(String fileId, Set<Integer> uploadedChunks) {
}