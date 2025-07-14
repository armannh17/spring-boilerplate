package com.example.demo.application.file.handler;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.example.demo.application.file.event.FileUploadRequestedEvent;
import com.example.demo.application.file.service.FileService;
import com.example.demo.application.file.view.UploadFileView;

@Component
public class FileUploadRequestedHandler {
	private final FileService fileService;

	public FileUploadRequestedHandler(FileService fileService) {
		this.fileService = fileService;
	}

	@EventListener
	public void on(FileUploadRequestedEvent event) {
		UploadFileView file = fileService.generateUploadURL();

		event.setResult(file);
	}
}
