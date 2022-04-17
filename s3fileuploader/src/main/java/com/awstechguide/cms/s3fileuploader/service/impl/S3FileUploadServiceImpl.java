package com.awstechguide.cms.s3fileuploader.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Date;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.awstechguide.cms.s3fileuploader.domain.FileMetadata;
import com.awstechguide.cms.s3fileuploader.domain.UserMetadata;
import com.awstechguide.cms.s3fileuploader.service.FileMetadataService;
import com.awstechguide.cms.s3fileuploader.service.S3FileUploadService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class S3FileUploadServiceImpl implements S3FileUploadService {

	@Value("${appconfig.bucket.name}")
	private String bucketName;

	@Autowired
	private AmazonS3 s3Client;
	
	@Autowired
	private FileMetadataService fileMetadataService;

	@Override
	public String uploadFile(MultipartFile file) throws IOException {
		File fileObj = convertMultiPartFileToFile(file);
		extractFileMetadata(fileObj);
		String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename().replace(" ", "_");
		try {
			s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
			fileMetadataService.save(populateFileMetadata(fileObj));
			fileObj.delete();
			return "File Uploaded : " + fileName;
		} catch (AmazonServiceException serviceException) {
			log.info("AmazonServiceException: " + serviceException.getMessage());
			throw serviceException;
		} catch (AmazonClientException clientException) {
			log.info("AmazonClientException Message: " + clientException.getMessage());
			throw clientException;
		}
	}

	public void extractFileMetadata(File file) {
			
		Parser parser = new AutoDetectParser();
		BodyContentHandler handler = new BodyContentHandler();
		Metadata metadata = new Metadata();
		FileInputStream inputstream;
		try {
			inputstream = new FileInputStream(file);
			ParseContext context = new ParseContext();
			// Parsing the given file
			
			parser.parse(inputstream, handler, metadata, context);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TikaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		      
		
		String[] metadataNamesb4 = metadata.names();
		//list of meta data elements before adding new elements
	      log.info( " metadata elements :"  +Arrays.toString(metadata.names()));

		for (String name : metadataNamesb4) {
			log.info(name + ": " + metadata.get(name));
		}
	}

	@Override
	public byte[] downloadFile(String fileName) {
		S3Object s3Object = s3Client.getObject(bucketName, fileName);
		S3ObjectInputStream inputStream = s3Object.getObjectContent();
		try {
			byte[] content = IOUtils.toByteArray(inputStream);
			return content;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String deleteFile(String fileName) {
		s3Client.deleteObject(bucketName, fileName);
		return fileName + " removed ...";
	}

	private File convertMultiPartFileToFile(MultipartFile file) {
		File convertedFile = new File(file.getOriginalFilename());
		try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
			fos.write(file.getBytes());
		} catch (IOException e) {
			log.error("Error converting multipartFile to file", e);
		}
		return convertedFile;
	}
	
	public FileMetadata populateFileMetadata(File file) {
		FileMetadata fmd= new FileMetadata();
		UserMetadata umd = new UserMetadata();
		try {
			fmd.setFileName(file.getName());
			fmd.setFileUploadDate(new Date());
			fmd.setFileSize(file.length());
			fmd.setFileType(Files.probeContentType(file.toPath()));
			umd.setUploadedByUserEmail("som@awstechguide.com");
			umd.setUploadedByUserId("awstg");
			umd.setUploadedByUserName("som");
			fmd.setFileUserdata(umd);
			System.out.print("fmd: " +fmd.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fmd;
	}
}
