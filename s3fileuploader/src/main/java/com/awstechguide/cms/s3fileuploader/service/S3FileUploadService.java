package com.awstechguide.cms.s3fileuploader.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface S3FileUploadService {
	
	String uploadFile(MultipartFile file) throws IOException;

	byte[] downloadFile(String fileName);

	String deleteFile(String fileName);

}
