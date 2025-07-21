package com.example.demo.application.product.command;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddVarietyCommand {
  private String name;
  private String color;
}
