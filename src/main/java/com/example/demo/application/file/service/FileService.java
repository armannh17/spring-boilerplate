package com.example.demo.application.file.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.demo.application.file.client.AmazonClient;
import com.example.demo.application.file.client.StorageClient;
import com.example.demo.application.file.view.UploadFileView;

@Service
public class FileService {
	private StorageClient storageClient;

	public FileService(AmazonClient amazonClient) {
		this.storageClient = amazonClient;
	}

	public UploadFileView generateUploadURL() {
		String key = UUID.randomUUID().toString();

		String url = storageClient.generateUploadURL(key);

		return UploadFileView.builder().key(key).url(url).build();
	}
}
