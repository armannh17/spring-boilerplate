package com.example.demo.application.store.command;

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
}
