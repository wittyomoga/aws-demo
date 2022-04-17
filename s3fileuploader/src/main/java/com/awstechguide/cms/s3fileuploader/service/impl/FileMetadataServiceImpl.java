package com.awstechguide.cms.s3fileuploader.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.awstechguide.cms.s3fileuploader.domain.FileMetadata;
import com.awstechguide.cms.s3fileuploader.service.FileMetadataService;

import lombok.extern.java.Log;

@Log
@Service
public class FileMetadataServiceImpl implements FileMetadataService{

	@Autowired
	DynamoDBMapper mapper;
	
	@Override
	public String save(FileMetadata filemd) {
		mapper.save(filemd);
		log.info("Record saved successfully "+ filemd);
		return "SUCCESS";
	}

	@Override
	public FileMetadata load(String fileId) {
		FileMetadata filemd = new FileMetadata();
		filemd.setFileId(fileId);	
		FileMetadata tranc =mapper.load(filemd);
		return tranc;
	}

	@Override
	public FileMetadata update(FileMetadata updatedfilemd, String fileId) {
		FileMetadata t = new FileMetadata();
		t.setFileId(fileId);
		FileMetadata newfilemd = mapper.load(t);
		newfilemd=updatedfilemd;
		mapper.save(newfilemd);
		return null;
	}

	@Override
	public String delete(String fileId) {
		FileMetadata t = new FileMetadata();
		t.setFileId(fileId);
		FileMetadata result = mapper.load(t);
		mapper.delete(result);
		return "SUCCESS";
	}

	@Override
	public List<FileMetadata> query(String fileId) {
		FileMetadata t = new FileMetadata();
		t.setFileId(fileId);		
		DynamoDBQueryExpression<FileMetadata> exp = new DynamoDBQueryExpression<FileMetadata>()
				.withHashKeyValues(t)
				.withLimit(10);
		
		List<FileMetadata> res = mapper.query(FileMetadata.class, exp);
		
		return res;
	}
}
