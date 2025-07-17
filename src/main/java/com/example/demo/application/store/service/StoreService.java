package com.example.demo.application.store.service;

import org.springframework.stereotype.Service;

import com.example.demo.application.store.command.MakeStoreCommand;
import com.example.demo.application.store.exception.StoreAlreadyExistsException;
import com.example.demo.application.store.exception.StoreNotFoundException;
import com.example.demo.application.store.model.StoreModel;
import com.example.demo.application.store.query.GetStoreQuery;
import com.example.demo.application.store.repository.StoreRepository;

@Service
public class StoreService {
	private final StoreRepository storeRepository;

	public StoreService(StoreRepository storeRepository) {
		this.storeRepository = storeRepository;
	}

	public String makeStore(MakeStoreCommand command) {
		// find an existing store and throw if it exists
		storeRepository.findBySlug(command.getSlug()).ifPresent((_) -> {
			throw new StoreAlreadyExistsException();
		});

		// make a new store
		StoreModel store = StoreModel.builder().name(command.getName()).slug(command.getSlug())
				.brief(command.getBrief()).description(command.getDescription()).image(command.getImage())
				.verified(false).build();

		// make colors based on the passed primary color
		store.populateColorPalette(command.getColor());

		// save the new store
		storeRepository.save(store);

		// return the new stores id
		return store.getId().toString();
	}

	public StoreModel getStore(GetStoreQuery query) {
		// find an existing store and throw if it does not exist
		return storeRepository.findBySlug(query.getSlug()).orElseThrow(StoreNotFoundException::new);
	}
}
