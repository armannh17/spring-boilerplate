package com.example.demo.application.catalog.entity;

import java.math.BigInteger;

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
@Table(name = "product")
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class ProductEntity extends BaseEntity {
	@Column(name = "name", nullable = false, updatable = true, length = 200)
	private String name;

	@Column(name = "description", nullable = false, updatable = true, columnDefinition = "text")
	private String Description;

	@Column(name = "price", nullable = false, updatable = true, columnDefinition = "bigint")
	private Long price;
}
