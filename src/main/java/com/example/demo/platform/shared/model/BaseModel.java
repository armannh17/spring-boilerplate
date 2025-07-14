package com.example.demo.platform.shared.model;

import java.util.Date;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public abstract class BaseModel {
	@Builder.Default
	protected UUID id = UUID.randomUUID();

	@Builder.Default
	protected Date createdAt = new Date();

	@Builder.Default
	protected Date updatedAt = new Date();
}
