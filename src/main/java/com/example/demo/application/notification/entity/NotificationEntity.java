package com.example.demo.application.notification.entity;

import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.demo.application.notification.constant.NotificationType;
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
@Table(name = "notification")
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class NotificationEntity extends BaseEntity {
	@Column(name = "template", nullable = false, updatable = true, length = 100)
	private String template;

	@Column(name = "type", nullable = false, updatable = true, length = 100)
	@JdbcType(PostgreSQLEnumJdbcType.class)
	private NotificationType type;
}
