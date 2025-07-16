package com.example.demo.application.store.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.demo.platform.shared.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "store")
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class StoreEntity extends BaseEntity {
	@Column(name = "name", nullable = false, updatable = false, length = 200)
	private String name;

	@Column(name = "slug", nullable = false, updatable = false, length = 200, unique = true)
	private String slug;

	@Column(name = "image", nullable = false, updatable = false, length = 200)
	private String image;

	@Column(name = "color_primary", nullable = false, updatable = false, length = 100)
	private String colorPrimary;

	@Column(name = "color_accent", nullable = false, updatable = false, length = 100)
	private String colorAccent;

	@Column(name = "color_neutral", nullable = false, updatable = false, length = 100)
	private String colorNeutral;

	@Column(name = "verified", nullable = false, updatable = true)
	private Boolean verified;
}
