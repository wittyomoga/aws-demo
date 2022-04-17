package com.awstechguide.cms.s3fileuploader.controller;

import java.io.IOException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.awstechguide.cms.s3fileuploader.service.S3FileUploadService;

@RestController
@RequestMapping("/file")
public class S3FileUploadController {

	@Autowired
	private S3FileUploadService service;

	@PostMapping("/upload")
	public ResponseEntity<String> uploadFile(@RequestParam(value = "file") MultipartFile file) throws IOException {
		return new ResponseEntity<>(service.uploadFile(file), HttpStatus.OK);
	}

	@GetMapping("/download/{fileId}")
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileId) {
		byte[] data = service.downloadFile(fileId);
		ByteArrayResource resource = new ByteArrayResource(data);
		return ResponseEntity.ok().contentLength(data.length).header("Content-type", "application/octet-stream")
				.header("Content-disposition", "attachment; fileId=\"" + fileId + "\"").body(resource);
	}

	@DeleteMapping("/delete/{fileId}")
	    public ResponseEntity<String> deleteFile(@PathVariable String fileId) {
	        return new ResponseEntity<>(service.deleteFile(fileId), HttpStatus.OK);
	    }

	@GetMapping("/getmetadata")
	    public String getMetadata(Principal principal) {
	    	return "Hi "+principal.getName()+ "--" +principal.toString();
	    }
	
	
}
