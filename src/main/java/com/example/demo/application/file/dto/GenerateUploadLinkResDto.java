package com.example.demo.application.file.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GenerateUploadLinkResDto {
	private String key;
	private String url;
}
