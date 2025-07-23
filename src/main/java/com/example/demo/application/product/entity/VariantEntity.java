package com.example.demo.application.product.entity;

import com.example.demo.platform.shared.entity.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "variant")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class VariantEntity extends BaseEntity {
  @Column(name = "name", nullable = false, updatable = true, length = 200)
  private String name;

  @Column(name = "overview", nullable = false, updatable = true, length = 200)
  private String overview;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  @JoinColumn(name = "variant_id", nullable = false, updatable = false)
  private List<VarietyEntity> varieties;
}
