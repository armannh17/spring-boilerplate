package com.example.demo.application.store.entity;

import com.example.demo.application.store.constant.Alignment;
import com.example.demo.application.store.constant.Detail;
import com.example.demo.application.store.constant.Radius;
import com.example.demo.platform.shared.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "store")
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class StoreEntity extends BaseEntity {
  @Column(name = "name", nullable = false, updatable = true, length = 200)
  private String name;

  @Column(name = "slug", nullable = false, updatable = false, length = 200, unique = true)
  private String slug;

  @Column(name = "brief", nullable = false, updatable = true, length = 200)
  private String brief;

  @Column(name = "description", nullable = false, updatable = true, length = 200)
  private String description;

  @Column(name = "image", nullable = false, updatable = true, length = 200)
  private String image;

  @Column(name = "color_primary", nullable = false, updatable = true, length = 100)
  private String colorPrimary;

  @Column(name = "color_accent", nullable = false, updatable = true, length = 100)
  private String colorAccent;

  @Column(name = "color_neutral", nullable = false, updatable = true, length = 100)
  private String colorNeutral;

  @Column(name = "color_dark", nullable = false, updatable = true, length = 100)
  private String colorDark;

  @Column(name = "verified", nullable = false, updatable = true)
  private Boolean verified;

  @Column(name = "radius", nullable = false, updatable = true, length = 100)
  @JdbcType(PostgreSQLEnumJdbcType.class)
  private Radius raduis;

  @Column(name = "detail", nullable = false, updatable = true, length = 100)
  @JdbcType(PostgreSQLEnumJdbcType.class)
  private Detail detail;

  @Column(name = "alignment", nullable = false, updatable = true, length = 100)
  @JdbcType(PostgreSQLEnumJdbcType.class)
  private Alignment alignment;

  @Column(name = "user_id", nullable = false, updatable = true, columnDefinition = "UUID")
  private UUID userId;
}
