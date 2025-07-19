package com.example.demo.application.product.service;

import com.example.demo.application.product.command.AddFieldCommand;
import com.example.demo.application.product.command.MakeCategoryCommand;
import com.example.demo.application.product.command.UpdateFieldCommand;
import com.example.demo.application.product.event.CategoryCreatedEvent;
import com.example.demo.application.product.event.FieldAddedEvent;
import com.example.demo.application.product.event.FieldUpdatedEvent;
import com.example.demo.application.product.exception.CategoryNotFoundException;
import com.example.demo.application.product.exception.FieldNotFoundException;
import com.example.demo.application.product.model.CategoryModel;
import com.example.demo.application.product.model.FieldModel;
import com.example.demo.application.product.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
  private final ApplicationEventPublisher publisher;
  private final CategoryRepository categoryRepository;

  public CategoryService(
      ApplicationEventPublisher publisher, CategoryRepository categoryRepository) {
    this.publisher = publisher;
    this.categoryRepository = categoryRepository;
  }

  @Transactional
  public String makeCategory(MakeCategoryCommand command) {
    // make a new category
    CategoryModel category =
        CategoryModel.builder()
            .name(command.getName())
            .image(command.getImage())
            .description(command.getDescription())
            .storeId(command.getStoreId())
            .build();

    // publish the category created event
    CategoryCreatedEvent event =
        CategoryCreatedEvent.builder()
            .id(category.getId())
            .userId(command.getUserId())
            .storeId(command.getStoreId())
            .build();

    publisher.publishEvent(event);

    // save the new category
    categoryRepository.save(category);

    // return the new category id
    return category.getId().toString();
  }

  @Transactional
  public String addField(AddFieldCommand command) {
    // find the category
    CategoryModel category =
        categoryRepository
            .findById(command.getCategoryId())
            .orElseThrow(CategoryNotFoundException::new);

    // make a new field and add it to the category
    FieldModel field = FieldModel.builder().name(command.getName()).build();

    category.getFields().add(field);

    // publish the field added event
    FieldAddedEvent event =
        FieldAddedEvent.builder()
            .id(field.getId())
            .categoryId(category.getId())
            .userId(command.getUserId())
            .storeId(category.getStoreId())
            .build();

    publisher.publishEvent(event);

    // save the category
    categoryRepository.save(category);

    // return the new field id
    return field.getId().toString();
  }

  @Transactional
  public void updateField(UpdateFieldCommand command) {
    // find the category
    CategoryModel category =
        categoryRepository
            .findById(command.getCategoryId())
            .orElseThrow(CategoryNotFoundException::new);

    // find the field and update it
    FieldModel field =
        category.getFields().stream()
            .filter(f -> f.getId().equals(command.getId()))
            .findFirst()
            .orElseThrow(FieldNotFoundException::new);

    field.update(command.getName());

    // publish the field updated event
    FieldUpdatedEvent event =
        FieldUpdatedEvent.builder()
            .id(field.getId())
            .categoryId(category.getId())
            .userId(command.getUserId())
            .storeId(category.getStoreId())
            .build();

    publisher.publishEvent(event);

    // save the category
    categoryRepository.save(category);
  }
}
