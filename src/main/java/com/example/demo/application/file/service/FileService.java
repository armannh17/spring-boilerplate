package com.example.demo.application.file.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.demo.application.file.client.AmazonClient;
import com.example.demo.application.file.client.StorageClient;
import com.example.demo.application.file.model.UploadFileModel;

@Service
public class FileService {
	private StorageClient storageClient;

	public FileService(AmazonClient amazonClient) {
		this.storageClient = amazonClient;
	}

	public UploadFileModel generateUploadLink() {
		String key = UUID.randomUUID().toString();

		String url = storageClient.generateUploadLink(key);

		return UploadFileModel.builder().key(key).url(url).build();
	}
}
