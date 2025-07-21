package com.example.demo.application.product.service;

import com.example.demo.application.product.command.MakeProductCommand;
import com.example.demo.application.product.event.ProductCreatedEvent;
import com.example.demo.application.product.model.ProductModel;
import com.example.demo.application.product.repository.ProductRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
  private final ApplicationEventPublisher publisher;
  private final ProductRepository productRepository;

  public ProductService(ApplicationEventPublisher publisher, ProductRepository productRepository) {
    this.publisher = publisher;
    this.productRepository = productRepository;
  }

  public String makeProduct(MakeProductCommand command) {
    // make a new product
    ProductModel product =
        ProductModel.builder()
            .name(command.getName())
            .image(command.getImage())
            .description(command.getDescription())
            .published(false)
            .archived(false)
            .fieldId(command.getFieldId())
            .build();

    // publish the product created event
    ProductCreatedEvent event =
        ProductCreatedEvent.builder()
            .id(product.getId())
            .fieldId(command.getFieldId())
            .storeId(command.getStoreId())
            .userId(command.getUserdId())
            .build();

    publisher.publishEvent(event);

    // save the new product
    productRepository.save(product);

    // return the id of the new product
    return product.getId().toString();
  }
}
