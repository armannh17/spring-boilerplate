package com.example.demo.application.file.client;

import java.time.Duration;

import org.springframework.stereotype.Component;

import com.example.demo.application.file.config.FileConfig;

import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

@Component
public class AmazonClient implements StorageClient {
	private final S3Presigner s3Presigner;
	private final FileConfig fileConfig;

	public AmazonClient(S3Presigner s3Presigner, FileConfig fileConfig) {
		this.s3Presigner = s3Presigner;
		this.fileConfig = fileConfig;
	}

	public String generateUploadLink(String key) {
		String bucket = fileConfig.getBucketName();
		Duration expiry = fileConfig.getBucketExpiry();

		PutObjectRequest command = PutObjectRequest.builder().bucket(bucket).key(bucket).build();

		PutObjectPresignRequest request = PutObjectPresignRequest.builder().putObjectRequest(command)
				.signatureDuration(expiry).build();

		return s3Presigner.presignPutObject(request).url().toString();
	}
}
