package com.example.demo.application.store.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.application.store.command.MakeStoreCommand;
import com.example.demo.application.store.dto.MakeStoreReqDto;
import com.example.demo.application.store.dto.MakeStoreResDto;
import com.example.demo.application.store.serializer.StoreSerializer;
import com.example.demo.application.store.service.StoreService;
import com.example.demo.platform.shared.dto.ResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/stores")
@Tag(name = "Store", description = "Store Management")
public class StoreController {
	private final StoreSerializer storeSerializer;
	private final StoreService storeService;

	public StoreController(StoreSerializer storeSerializer, StoreService storeService) {
		this.storeSerializer = storeSerializer;
		this.storeService = storeService;
	}

	@PreAuthorize("hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(path = "/")
	@SecurityRequirement(name = "Bearer Authentication")
	@Operation(summary = "Make a new store")
	ResponseDto<MakeStoreResDto> makeStore(@Valid @RequestBody MakeStoreReqDto dto) {
		MakeStoreCommand command = storeSerializer.serializeMakeStoreCommand(dto);

		String id = storeService.makeStore(command);

		return storeSerializer.serializeMakeStoreResponse(id);
	}
}
