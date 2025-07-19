package com.example.demo.application.user.entity;

import com.example.demo.platform.shared.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "credential")
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class CredentialEntity extends BaseEntity {
  @Column(name = "otp_code", nullable = true, updatable = true, length = 100)
  private String otpCode;

  @Column(name = "otp_expiry", nullable = true, updatable = true)
  @Temporal(TemporalType.TIMESTAMP)
  private Date otpExpiry;
}
