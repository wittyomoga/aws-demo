package com.awstechguide.cms.s3fileuploader.service;

import java.util.List;

import com.awstechguide.cms.s3fileuploader.domain.FileMetadata;

public interface FileMetadataService {

	String SERVICE_NAME ="/s3file";
	String save(FileMetadata filemd);

	FileMetadata load(String fileId);

	FileMetadata update(FileMetadata updatedfilemd, String fileId);

	String delete(String fileId);

	List<FileMetadata> query(String fileId);

}
