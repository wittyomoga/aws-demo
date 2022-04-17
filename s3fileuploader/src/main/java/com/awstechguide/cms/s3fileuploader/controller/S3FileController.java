package com.awstechguide.cms.s3fileuploader.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.awstechguide.cms.s3fileuploader.domain.FileMetadata;
import com.awstechguide.cms.s3fileuploader.service.FileMetadataService;

import lombok.extern.java.Log;

@Log
@RestController
@RequestMapping(FileMetadataService.SERVICE_NAME)
public class S3FileController {

	@Autowired
	FileMetadataService fmdService;
	
	@GetMapping("/{s3FileId}")
	public ResponseEntity<FileMetadata> getS3FileMetaData(@PathVariable String s3FileId){
		log.info("******"+s3FileId);
		FileMetadata fmd = fmdService.load(s3FileId);
		return new ResponseEntity<FileMetadata>(fmd, HttpStatus.OK);
	}
}
