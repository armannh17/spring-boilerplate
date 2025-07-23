package com.example.demo.application.product.entity;

import com.example.demo.platform.shared.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "variety")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class VarietyEntity extends BaseEntity {
  @Column(name = "name", nullable = false, updatable = true, length = 100)
  private String name;

  @Column(name = "color", nullable = false, updatable = true, length = 100)
  private String color;
}
