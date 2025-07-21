package com.example.demo.application.store.handler;

import com.example.demo.application.product.event.CategoryCreatedEvent;
import com.example.demo.application.product.event.CategoryDeletedEvent;
import com.example.demo.application.product.event.CategoryUpdatedEvent;
import com.example.demo.application.product.event.FieldAddedEvent;
import com.example.demo.application.product.event.FieldDeletedEvent;
import com.example.demo.application.product.event.FieldUpdatedEvent;
import com.example.demo.application.product.event.ProductCreatedEvent;
import com.example.demo.application.product.event.VariantAddedEvent;
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
  private void on(CategoryUpdatedEvent event) {
    storeService.checkStore(event.getStoreId(), event.getUserId());
  }

  @EventListener
  private void on(CategoryDeletedEvent event) {
    storeService.checkStore(event.getStoreId(), event.getUserId());
  }

  @EventListener
  private void on(FieldAddedEvent event) {
    storeService.checkStore(event.getStoreId(), event.getUserId());
  }

  @EventListener
  private void on(FieldUpdatedEvent event) {
    storeService.checkStore(event.getStoreId(), event.getUserId());
  }

  @EventListener
  private void on(FieldDeletedEvent event) {
    storeService.checkStore(event.getStoreId(), event.getUserId());
  }

  @EventListener
  private void on(ProductCreatedEvent event) {
    storeService.checkStore(event.getStoreId(), event.getUserId());
  }

  @EventListener
  private void on(VariantAddedEvent event) {
    storeService.checkStore(event.getStoreId(), event.getUserId());
  }
}
