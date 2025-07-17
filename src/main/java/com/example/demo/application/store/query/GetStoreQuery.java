package com.example.demo.application.store.query;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetStoreQuery {
	private String slug;
}
