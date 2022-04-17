package com.awstechguide.aws.springbootdemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DemoRESTController {

	@GetMapping("/welcome")
	public String getMessage() {		
		return "Welcome to AWS tech Guide";
	}
}
