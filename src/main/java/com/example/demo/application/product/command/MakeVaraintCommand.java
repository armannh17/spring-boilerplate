package com.example.demo.application.product.command;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MakeVaraintCommand {
  private String name;
  private String overview;
  private List<MakeVarietyCommand> varieties;
}
