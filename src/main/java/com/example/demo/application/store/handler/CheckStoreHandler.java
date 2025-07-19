package com.example.demo.application.store.handler;

import com.example.demo.application.product.event.CategoryCreatedEvent;
import com.example.demo.application.product.event.FieldAddedEvent;
import com.example.demo.application.store.service.StoreService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CheckStoreHandler {
  private final StoreService storeService;

  public CheckStoreHandler(StoreService storeService) {
    this.storeService = storeService;
  }

  @EventListener
  private void on(CategoryCreatedEvent event) {
    storeService.checkStore(event.getStoreId(), event.getUserId());
  }

  @EventListener
  private void on(FieldAddedEvent event) {
    storeService.checkStore(event.getStoreId(), event.getUserId());
  }
}
