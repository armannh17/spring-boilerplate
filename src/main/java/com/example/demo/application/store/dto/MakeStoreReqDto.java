package com.example.demo.application.store.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MakeStoreReqDto {
	private String name;
	private String slug;
	private String image;
	private String color;
}
