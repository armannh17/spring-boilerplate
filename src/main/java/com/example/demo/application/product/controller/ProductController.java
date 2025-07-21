package com.example.demo.application.product.controller;

import com.example.demo.application.product.command.AddVariantCommand;
import com.example.demo.application.product.command.DeleteProductCommand;
import com.example.demo.application.product.command.DeleteVariantCommand;
import com.example.demo.application.product.command.MakeProductCommand;
import com.example.demo.application.product.dto.AddVariantReqDto;
import com.example.demo.application.product.dto.AddVariantResDto;
import com.example.demo.application.product.dto.MakeProductReqDto;
import com.example.demo.application.product.dto.MakeProductResDto;
import com.example.demo.application.product.serializer.ProductSerializer;
import com.example.demo.application.product.service.ProductService;
import com.example.demo.platform.shared.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/products")
@Tag(name = "Product", description = "Product Management")
public class ProductController {
  private final ProductSerializer productSerializer;
  private final ProductService productService;

  public ProductController(ProductSerializer productSerializer, ProductService productService) {
    this.productSerializer = productSerializer;
    this.productService = productService;
  }

  @PreAuthorize("hasRole('OWNER')")
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(path = "")
  @SecurityRequirement(name = "Bearer Authentication")
  @Operation(summary = "Make a new product")
  ResponseDto<MakeProductResDto> makeProduct(
      @AuthenticationPrincipal UserDetails user, @Valid @RequestBody MakeProductReqDto dto) {
    MakeProductCommand command = productSerializer.serializeMakeProductCommand(user, dto);

    String id = productService.makeProduct(command);

    return productSerializer.serializeMakeProductResponse(id);
  }

  @PreAuthorize("hasRole('OWNER')")
  @ResponseStatus(HttpStatus.CREATED)
  @DeleteMapping(path = "/{productId}")
  @SecurityRequirement(name = "Bearer Authentication")
  @Operation(summary = "Delete a product")
  ResponseDto<Void> deleteProduct(
      @AuthenticationPrincipal UserDetails user, @Valid @PathVariable @UUID String productId) {
    DeleteProductCommand command = productSerializer.serializeDeleteProductCommand(user, productId);

    productService.deleteProduct(command);

    return productSerializer.serializeDeleteProductResponse();
  }

  @PreAuthorize("hasRole('OWNER')")
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(path = "/{productId}/variant")
  @SecurityRequirement(name = "Bearer Authentication")
  @Operation(summary = "Add a new variant")
  ResponseDto<AddVariantResDto> addvariant(
      @AuthenticationPrincipal UserDetails user,
      @Valid @PathVariable @UUID String productId,
      @Valid @RequestBody AddVariantReqDto dto) {
    AddVariantCommand command = productSerializer.serializeAddVariantCommand(user, productId, dto);

    String id = productService.addVariant(command);

    return productSerializer.serializeAddVariantResponse(id);
  }

  @PreAuthorize("hasRole('OWNER')")
  @ResponseStatus(HttpStatus.CREATED)
  @DeleteMapping(path = "/{productId}/variant/{variantId}")
  @SecurityRequirement(name = "Bearer Authentication")
  @Operation(summary = "Add a new variant")
  ResponseDto<Void> addvariant(
      @AuthenticationPrincipal UserDetails user,
      @Valid @PathVariable @UUID String productId,
      @Valid @PathVariable @UUID String variantId) {
    DeleteVariantCommand command =
        productSerializer.serializeDeleteVariantCommand(user, productId, variantId);

    productService.deleleVariant(command);

    return productSerializer.serializeDeleteVariantResponse();
  }
}
