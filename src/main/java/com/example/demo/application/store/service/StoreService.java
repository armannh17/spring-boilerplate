package com.example.demo.application.store.service;

import com.example.demo.application.store.command.MakeStoreCommand;
import com.example.demo.application.store.command.UpdateStoreCommand;
import com.example.demo.application.store.exception.AccessToStoreDeniedException;
import com.example.demo.application.store.exception.StoreAlreadyExistsException;
import com.example.demo.application.store.exception.StoreNotFoundException;
import com.example.demo.application.store.model.StoreModel;
import com.example.demo.application.store.query.GetStoreQuery;
import com.example.demo.application.store.repository.StoreRepository;
import jakarta.transaction.Transactional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class StoreService {
  private final StoreRepository storeRepository;

  public StoreService(StoreRepository storeRepository) {
    this.storeRepository = storeRepository;
  }

  @Transactional
  public String makeStore(MakeStoreCommand command) {
    // find an existing store and throw if it exists
    storeRepository
        .findBySlug(command.getSlug())
        .ifPresent(
            (_) -> {
              throw new StoreAlreadyExistsException();
            });

    // make a new store
    StoreModel store =
        StoreModel.builder()
            .name(command.getName())
            .slug(command.getSlug())
            .brief(command.getBrief())
            .description(command.getDescription())
            .image(command.getImage())
            .verified(false)
            .radius(command.getRadius())
            .detail(command.getDetail())
            .alignment(command.getAlignment())
            .userId(command.getUserId())
            .build();

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

  @Transactional
  public void updateStore(UpdateStoreCommand command) {
    // find an existing store and throw if it does not exists
    StoreModel store =
        storeRepository
            .findByIdAndUser(command.getId(), command.getUserId())
            .orElseThrow(StoreNotFoundException::new);

    // update the store
    store.update(
        command.getName(),
        command.getBrief(),
        command.getDescription(),
        command.getImage(),
        command.getRaduis(),
        command.getDetail(),
        command.getAlignment());

    // update colors based on the passed primary color
    store.populateColorPalette(command.getColor());

    // save the updated store
    storeRepository.save(store);
  }

  public StoreModel checkStore(UUID id, UUID userId) {
    return storeRepository
        .findByIdAndUser(id, userId)
        .orElseThrow(AccessToStoreDeniedException::new);
  }
}
