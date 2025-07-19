package com.example.demo.application.store.command;

import java.util.UUID;

import com.example.demo.application.store.constant.Alignment;
import com.example.demo.application.store.constant.Detail;
import com.example.demo.application.store.constant.Radius;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateStoreCommand {
	private UUID id;
	private String name;
	private String brief;
	private String description;
	private String image;
	private String color;
	private Radius raduis;
	private Detail detail;
	private Alignment alignment;
}
