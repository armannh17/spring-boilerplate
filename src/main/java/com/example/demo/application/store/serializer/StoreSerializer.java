package com.example.demo.application.store.serializer;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.example.demo.application.store.command.MakeStoreCommand;
import com.example.demo.application.store.dto.MakeStoreReqDto;
import com.example.demo.application.store.dto.MakeStoreResDto;
import com.example.demo.platform.shared.dto.ResponseDto;

@Component
public class StoreSerializer {
	public MakeStoreCommand serializeMakeStoreCommand(MakeStoreReqDto dto) {
		return MakeStoreCommand.builder().name(dto.getName()).slug(dto.getSlug()).image(dto.getImage())
				.color(dto.getColor()).build();
	}

	public ResponseDto<MakeStoreResDto> serializeMakeStoreResponse(String id) {
		return ResponseDto.<MakeStoreResDto>builder().status(HttpStatus.CREATED.value()).message("successful")
				.data(MakeStoreResDto.builder().id(id).build()).build();
	}
}
