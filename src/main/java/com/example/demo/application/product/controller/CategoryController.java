package com.example.demo.application.product.controller;

import com.example.demo.application.product.command.AddFieldCommand;
import com.example.demo.application.product.command.MakeCategoryCommand;
import com.example.demo.application.product.command.UpdateFieldCommand;
import com.example.demo.application.product.dto.AddFieldReqDto;
import com.example.demo.application.product.dto.AddFieldResDto;
import com.example.demo.application.product.dto.MakeCategoryReqDto;
import com.example.demo.application.product.dto.MakeCategoryResDto;
import com.example.demo.application.product.dto.UpdateFieldReqDto;
import com.example.demo.application.product.serializer.CategorySerializer;
import com.example.demo.application.product.service.CategoryService;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/categories")
@Tag(name = "Category", description = "Category Management")
public class CategoryController {
  private final CategorySerializer categorySerializer;
  private final CategoryService categoryService;

  public CategoryController(
      CategorySerializer categorySerializer, CategoryService categoryService) {
    this.categorySerializer = categorySerializer;
    this.categoryService = categoryService;
  }

  @PreAuthorize("hasRole('OWNER')")
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(path = "/")
  @SecurityRequirement(name = "Bearer Authentication")
  @Operation(summary = "Make a new category")
  ResponseDto<MakeCategoryResDto> makeCategory(
      @AuthenticationPrincipal UserDetails user, @Valid @RequestBody MakeCategoryReqDto dto) {
    MakeCategoryCommand command = categorySerializer.serializeMakeCategoryCommand(user, dto);

    String id = categoryService.makeCategory(command);

    return categorySerializer.serializeMakeCategoryResponse(id);
  }

  @PreAuthorize("hasRole('OWNER')")
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(path = "/{categoryId}/fields/")
  @SecurityRequirement(name = "Bearer Authentication")
  @Operation(summary = "Add a new field")
  ResponseDto<AddFieldResDto> addField(
      @AuthenticationPrincipal UserDetails user,
      @Valid @PathVariable @UUID String categoryId,
      @Valid @RequestBody AddFieldReqDto dto) {
    AddFieldCommand command = categorySerializer.serializeAddFieldCommand(user, categoryId, dto);

    String id = categoryService.addField(command);

    return categorySerializer.serializeAddFieldResponse(id);
  }

  @PreAuthorize("hasRole('OWNER')")
  @ResponseStatus(HttpStatus.OK)
  @PutMapping(path = "/{categoryId}/fields/{fieldId}")
  @SecurityRequirement(name = "Bearer Authentication")
  @Operation(summary = "Update a field")
  ResponseDto<Void> updateField(
      @AuthenticationPrincipal UserDetails user,
      @Valid @PathVariable @UUID String categoryId,
      @Valid @PathVariable @UUID String fieldId,
      @Valid @RequestBody UpdateFieldReqDto dto) {
    UpdateFieldCommand command =
        categorySerializer.serializeUpdateFieldCommand(user, categoryId, fieldId, dto);

    categoryService.updateField(command);

    return categorySerializer.serializeUpdateFieldResponse();
  }
}
