package com.example.demo.platform.shared.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public abstract class BaseEntity implements Serializable {
	@Id
	@Builder.Default
	@Column(name = "id", nullable = false, updatable = false, columnDefinition = "UUID")
	protected UUID id = UUID.randomUUID();

	@CreatedDate
	@Builder.Default
	@Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
	protected Date createdAt = new Date();

	@LastModifiedDate
	@Builder.Default
	@Column(name = "updated_at", nullable = false, updatable = true, columnDefinition = "TIMESTAMP WITH TIME ZONE")
	protected Date updatedAt = new Date();
}
