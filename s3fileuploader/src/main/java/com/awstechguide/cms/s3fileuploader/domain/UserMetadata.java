package com.awstechguide.cms.s3fileuploader.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserMetadata {
	private String uploadedByUserId;
	private String uploadedByUserName;
	private String uploadedByUserEmail;
}
