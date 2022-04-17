package com.awstechguide.cms.s3fileuploader.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
	@GetMapping("/hello")
	public String welcome2User(Principal principal) {
		return "Hello! "+principal.getName()+ " "+principal.toString();
	}
}
