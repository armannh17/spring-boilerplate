package com.example.demo.application.product.service;

import com.example.demo.application.product.command.AddVariantCommand;
import com.example.demo.application.product.command.DeleteProductCommand;
import com.example.demo.application.product.command.DeleteVariantCommand;
import com.example.demo.application.product.command.MakeProductCommand;
import com.example.demo.application.product.command.UpdateProductCommand;
import com.example.demo.application.product.event.ProductCreatedEvent;
import com.example.demo.application.product.event.ProductDeletedEvent;
import com.example.demo.application.product.event.ProductUpdatedEvent;
import com.example.demo.application.product.event.VariantAddedEvent;
import com.example.demo.application.product.event.VariantDeletedEvent;
import com.example.demo.application.product.exception.ProductNotFoundException;
import com.example.demo.application.product.model.ProductModel;
import com.example.demo.application.product.model.VariantModel;
import com.example.demo.application.product.model.VarietyModel;
import com.example.demo.application.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
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

  @Transactional
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
            .storeId(command.getStoreId())
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

  @Transactional
  public void updateProduct(UpdateProductCommand command) {
    // find a product and throw if it does not exist
    ProductModel product =
        productRepository.findById(command.getId()).orElseThrow(ProductNotFoundException::new);

    // update the product
    product.update(command.getName(), command.getImage(), command.getDescription());

    // publish the product updated event
    ProductUpdatedEvent event =
        ProductUpdatedEvent.builder()
            .id(product.getId())
            .storeId(product.getStoreId())
            .userId(command.getUserId())
            .build();

    publisher.publishEvent(event);

    // save the changes
    productRepository.save(product);
  }

  @Transactional
  public void deleteProduct(DeleteProductCommand command) {
    // find a product and throw if it does not exist
    ProductModel product =
        productRepository.findById(command.getId()).orElseThrow(ProductNotFoundException::new);

    // delete the product
    product.delete();

    // publish the product deleted event
    ProductDeletedEvent event =
        ProductDeletedEvent.builder()
            .id(product.getId())
            .storeId(product.getStoreId())
            .userId(command.getUserId())
            .build();

    publisher.publishEvent(event);

    // save the changes
    productRepository.delete(product);
  }

  @Transactional
  public String addVariant(AddVariantCommand command) {
    // find a product and throw if it does not exist
    ProductModel product =
        productRepository
            .findById(command.getProductId())
            .orElseThrow(ProductNotFoundException::new);

    // make a variant and add it to the product
    List<VarietyModel> varieties =
        command.getVarieties().stream()
            .map(v -> VarietyModel.builder().name(v.getName()).color(v.getColor()).build())
            .collect(Collectors.toList());

    VariantModel variant =
        VariantModel.builder()
            .name(command.getName())
            .overview(command.getOverview())
            .varieties(varieties)
            .build();

    product.addVariant(variant);

    // publish the variant added event
    VariantAddedEvent event =
        VariantAddedEvent.builder()
            .id(variant.getId())
            .productId(product.getId())
            .storeId(product.getStoreId())
            .userId(command.getUserId())
            .build();

    publisher.publishEvent(event);

    // save the variant
    productRepository.save(product);

    // return the new variant id
    return variant.getId().toString();
  }

  @Transactional
  public void deleleVariant(DeleteVariantCommand command) {
    // find a product and throw if it does not exist
    ProductModel product =
        productRepository
            .findById(command.getProductId())
            .orElseThrow(ProductNotFoundException::new);

    // remove the variant
    VariantModel variant = product.deleteVariant(command.getId());

    // publish the variant deleted event
    VariantDeletedEvent event =
        VariantDeletedEvent.builder()
            .id(variant.getId())
            .productId(product.getId())
            .storeId(product.getStoreId())
            .userId(command.getUserId())
            .build();

    publisher.publishEvent(event);

    // save the changes
    productRepository.save(product);
  }
}
