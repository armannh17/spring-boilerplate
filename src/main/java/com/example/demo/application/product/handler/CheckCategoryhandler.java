package com.example.demo.application.product.handler;

import com.example.demo.application.product.event.ProductCreatedEvent;
import com.example.demo.application.product.service.CategoryService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CheckCategoryhandler {
  private final CategoryService categoryService;

  public CheckCategoryhandler(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @EventListener
  public void on(ProductCreatedEvent event) {
    categoryService.checkCategory(event.getFieldId());
  }
}
