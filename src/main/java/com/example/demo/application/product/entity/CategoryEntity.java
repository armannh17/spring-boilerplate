package com.example.demo.application.product.entity;

import com.example.demo.platform.shared.entity.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "category")
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class CategoryEntity extends BaseEntity {
  @Column(name = "name", nullable = false, updatable = true, length = 200)
  private String name;

  @Column(name = "image", nullable = false, updatable = true, length = 200)
  private String image;

  @Column(name = "description", nullable = false, updatable = true, columnDefinition = "text")
  private String description;

  @Column(name = "store_id", nullable = false, updatable = true, columnDefinition = "UUID")
  private UUID storeId;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  @JoinColumn(name = "category_id", nullable = false, updatable = false)
  private List<FieldEntity> fields;
}
