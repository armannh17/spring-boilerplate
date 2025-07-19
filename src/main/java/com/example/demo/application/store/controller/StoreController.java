package com.example.demo.application.store.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.application.store.command.MakeStoreCommand;
import com.example.demo.application.store.command.UpdateStoreCommand;
import com.example.demo.application.store.dto.GetStoreResDto;
import com.example.demo.application.store.dto.MakeStoreReqDto;
import com.example.demo.application.store.dto.MakeStoreResDto;
import com.example.demo.application.store.dto.UpdateStoreReqDto;
import com.example.demo.application.store.model.StoreModel;
import com.example.demo.application.store.query.GetStoreQuery;
import com.example.demo.application.store.serializer.StoreSerializer;
import com.example.demo.application.store.service.StoreService;
import com.example.demo.platform.shared.dto.ResponseDto;
import com.example.demo.platform.shared.validator.SlugValidator;

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

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(path = "/{slug}")
	@Operation(summary = "Get a store info")
	ResponseDto<GetStoreResDto> getStore(@Valid @PathVariable @SlugValidator String slug) {
		GetStoreQuery query = storeSerializer.serializeGetStoreQuery(slug);

		StoreModel store = storeService.getStore(query);

		return storeSerializer.serializeGetStoreResponse(store);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(path = "/{id}")
	@SecurityRequirement(name = "Bearer Authentication")
	@Operation(summary = "Update a store")
	ResponseDto<Void> updateStore(@Valid @PathVariable UUID id, @Valid @RequestBody UpdateStoreReqDto dto) {
		UpdateStoreCommand command = storeSerializer.serializeUpdateStoreCommand(id, dto);

		storeService.updateStore(command);

		return storeSerializer.serializeUpdateStoreResponse();
	}
}
