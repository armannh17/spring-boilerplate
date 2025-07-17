package com.example.demo.application.store.serializer;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.example.demo.application.store.command.MakeStoreCommand;
import com.example.demo.application.store.dto.GetStoreReqDto;
import com.example.demo.application.store.dto.GetStoreResDto;
import com.example.demo.application.store.dto.MakeStoreReqDto;
import com.example.demo.application.store.dto.MakeStoreResDto;
import com.example.demo.application.store.model.StoreModel;
import com.example.demo.application.store.query.GetStoreQuery;
import com.example.demo.platform.shared.dto.ResponseDto;

@Component
public class StoreSerializer {
	public MakeStoreCommand serializeMakeStoreCommand(MakeStoreReqDto dto) {
		return MakeStoreCommand.builder().name(dto.getName()).slug(dto.getSlug()).image(dto.getImage())
				.brief(dto.getBrief()).description(dto.getDescription()).color(dto.getColor()).build();
	}

	public ResponseDto<MakeStoreResDto> serializeMakeStoreResponse(String id) {
		return ResponseDto.<MakeStoreResDto>builder().status(HttpStatus.CREATED.value()).message("successful")
				.data(MakeStoreResDto.builder().id(id).build()).build();
	}

	public GetStoreQuery serializeGetStoreQuery(GetStoreReqDto dto) {
		return GetStoreQuery.builder().slug(dto.getSlug()).build();
	}

	public ResponseDto<GetStoreResDto> serializeGetStoreResponse(StoreModel store) {
		return ResponseDto.<GetStoreResDto>builder().status(HttpStatus.OK.value()).message("successful")
				.data(GetStoreResDto.builder().id(store.getId().toString()).name(store.getName()).slug(store.getSlug())
						.brief(store.getBrief()).description(store.getDescription()).image(store.getImage())
						.colorPrimary(store.getColorPrimary()).colorAccent(store.getColorAccent())
						.colorNeutral(store.getColorNeutral()).colorDark(store.getColorDark())
						.verified(store.getVerified()).build())
				.build();
	}
}
