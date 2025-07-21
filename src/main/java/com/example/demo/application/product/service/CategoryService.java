package com.example.demo.application.product.service;

import com.example.demo.application.product.command.AddFieldCommand;
import com.example.demo.application.product.command.DeleteCategoryCommand;
import com.example.demo.application.product.command.DeleteFieldCommand;
import com.example.demo.application.product.command.MakeCategoryCommand;
import com.example.demo.application.product.command.UpdateCategoryCommand;
import com.example.demo.application.product.command.UpdateFieldCommand;
import com.example.demo.application.product.event.CategoryCreatedEvent;
import com.example.demo.application.product.event.CategoryDeletedEvent;
import com.example.demo.application.product.event.CategoryUpdatedEvent;
import com.example.demo.application.product.event.FieldAddedEvent;
import com.example.demo.application.product.event.FieldDeletedEvent;
import com.example.demo.application.product.event.FieldUpdatedEvent;
import com.example.demo.application.product.exception.CategoryNotFoundException;
import com.example.demo.application.product.model.CategoryModel;
import com.example.demo.application.product.model.FieldModel;
import com.example.demo.application.product.query.GetCategoryQuery;
import com.example.demo.application.product.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;
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
  public void updateCategory(UpdateCategoryCommand command) {
    // find the category
    CategoryModel category =
        categoryRepository.findById(command.getId()).orElseThrow(CategoryNotFoundException::new);

    // update the category
    category.update(command.getName(), command.getImage(), command.getDescription());

    // publish the category updated event
    CategoryUpdatedEvent event =
        CategoryUpdatedEvent.builder()
            .id(category.getId())
            .userId(command.getUserId())
            .storeId(category.getStoreId())
            .build();

    publisher.publishEvent(event);

    // save the category
    categoryRepository.save(category);
  }

  @Transactional
  public void deleteCategory(DeleteCategoryCommand command) {
    // find the category and delete it
    CategoryModel category =
        categoryRepository.findById(command.getId()).orElseThrow(CategoryNotFoundException::new);

    category.delete();

    // publish the category deleted event
    CategoryDeletedEvent event =
        CategoryDeletedEvent.builder()
            .id(category.getId())
            .userId(command.getUserId())
            .storeId(category.getStoreId())
            .build();

    publisher.publishEvent(event);

    // delete the category
    categoryRepository.delete(category);
  }

  public List<CategoryModel> getCategoryList(GetCategoryQuery query) {
    return categoryRepository.findAllByStore(query.getStoreId());
  }

  @Transactional
  public String addField(AddFieldCommand command) {
    // find the category
    CategoryModel category =
        categoryRepository
            .findById(command.getCategoryId())
            .orElseThrow(CategoryNotFoundException::new);

    // make a new field and add it to the category
    FieldModel field = category.addField(command.getName());

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
    FieldModel field = category.updateField(command.getId(), command.getName());

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

  @Transactional
  public void deleteField(DeleteFieldCommand command) {
    // find the category
    CategoryModel category =
        categoryRepository
            .findById(command.getCategoryId())
            .orElseThrow(CategoryNotFoundException::new);

    // find the field and delete it
    FieldModel field = category.deleteField(command.getId());

    // publish the field deleted event
    FieldDeletedEvent event =
        FieldDeletedEvent.builder()
            .id(field.getId())
            .categoryId(category.getId())
            .userId(command.getUserId())
            .storeId(category.getStoreId())
            .build();

    publisher.publishEvent(event);

    // save the category
    categoryRepository.save(category);
  }

  public void checkCategory(UUID fieldId) {
    categoryRepository.findByField(fieldId).orElseThrow(CategoryNotFoundException::new);
  }
}
