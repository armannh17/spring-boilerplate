package com.example.demo.application.store.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetStoreResDto {
	private String id;
	private String name;
	private String slug;
	private String brief;
	private String description;
	private String image;
	private String colorPrimary;
	private String colorAccent;
	private String colorNeutral;
	private String colorDark;
	private Boolean verified;
}
