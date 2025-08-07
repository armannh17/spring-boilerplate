package com.example.demo.application.store.command;

import com.example.demo.application.store.constant.Alignment;
import com.example.demo.application.store.constant.Detail;
import com.example.demo.application.store.constant.Radius;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MakeStoreCommand {
  private String name;
  private String slug;
  private String brief;
  private String description;
  private String image;
  private String color;
  private Radius radius;
  private Detail detail;
  private Alignment alignment;
  private UUID userId;
}
