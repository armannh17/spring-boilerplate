package com.example.demo.application.file.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UploadFileModel {
	private String key;
	private String url;
}
