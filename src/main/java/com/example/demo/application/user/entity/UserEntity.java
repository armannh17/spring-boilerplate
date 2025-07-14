package com.example.demo.application.user.entity;

import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.demo.application.user.type.Role;
import com.example.demo.platform.shared.entity.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "\"user\"")
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class UserEntity extends BaseEntity {
	@Column(name = "phone", nullable = false, updatable = false, length = 200, unique = true)
	private String phone;

	@Column(name = "locked", nullable = false, updatable = false)
	private Boolean locked;

	@Column(name = "role", nullable = false, updatable = true, length = 100)
	@JdbcType(PostgreSQLEnumJdbcType.class)
	private Role role;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, optional = false)
	@JoinColumn(name = "credential_id", unique = true)
	private CredentialEntity credential;
}
