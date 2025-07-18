package com.example.demo.application.store.dto;

import com.example.demo.application.store.constant.Alignment;
import com.example.demo.application.store.constant.Detail;
import com.example.demo.application.store.constant.Radius;

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
	private Radius radius;
	private Detail detail;
	private Alignment alignment;
}
