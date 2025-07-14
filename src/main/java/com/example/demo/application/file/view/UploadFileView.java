package com.example.demo.application.file.view;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UploadFileView {
	private String key;
	private String url;
}
