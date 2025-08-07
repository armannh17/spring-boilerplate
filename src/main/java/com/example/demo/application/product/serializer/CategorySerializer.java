package com.example.demo.application.product.serializer;

import com.example.demo.application.product.command.AddFieldCommand;
import com.example.demo.application.product.command.DeleteCategoryCommand;
import com.example.demo.application.product.command.DeleteFieldCommand;
import com.example.demo.application.product.command.MakeCategoryCommand;
import com.example.demo.application.product.command.UpdateCategoryCommand;
import com.example.demo.application.product.command.UpdateFieldCommand;
import com.example.demo.application.product.dto.AddFieldReqDto;
import com.example.demo.application.product.dto.AddFieldResDto;
import com.example.demo.application.product.dto.GetCategoryResDto;
import com.example.demo.application.product.dto.MakeCategoryReqDto;
import com.example.demo.application.product.dto.MakeCategoryResDto;
import com.example.demo.application.product.dto.UpdateCategoryReqDto;
import com.example.demo.application.product.dto.UpdateFieldReqDto;
import com.example.demo.application.product.model.CategoryModel;
import com.example.demo.application.product.query.GetCategoryQuery;
import com.example.demo.platform.shared.serializer.BaseSerializer;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.core.userdetails.UserDetails;

@Mapper(componentModel = "spring")
public interface CategorySerializer extends BaseSerializer {

  @Mapping(target = "userId", source = "user", qualifiedByName = "mapId")
  MakeCategoryCommand serializeToMakeCategoryCommand(UserDetails user, MakeCategoryReqDto dto);

  MakeCategoryResDto serializeToMakeCategoryDto(String id);

  @Mapping(target = "userId", source = "user", qualifiedByName = "mapId")
  UpdateCategoryCommand serializeToUpdateCategoryCommand(
      UserDetails user, String id, UpdateCategoryReqDto dto);

  @Mapping(target = "userId", source = "user", qualifiedByName = "mapId")
  DeleteCategoryCommand serializeToDeleteCategoryCommand(UserDetails user, String id);

  GetCategoryQuery serializeToGetCategoryQuery(String storeId);

  List<GetCategoryResDto> serializeToGetCategoryDto(List<CategoryModel> categories);

  @Mapping(target = "userId", source = "user", qualifiedByName = "mapId")
  AddFieldCommand serializeToAddFieldCommand(
      UserDetails user, String categoryId, AddFieldReqDto dto);

  AddFieldResDto serializeToAddFieldDto(String id);

  @Mapping(target = "id", source = "fieldId", qualifiedByName = "mapId")
  @Mapping(target = "userId", source = "user", qualifiedByName = "mapId")
  UpdateFieldCommand serializeToUpdateFieldCommand(
      UserDetails user, String categoryId, String fieldId, UpdateFieldReqDto dto);

  @Mapping(target = "id", source = "fieldId", qualifiedByName = "mapId")
  @Mapping(target = "userId", source = "user", qualifiedByName = "mapId")
  DeleteFieldCommand serializeToDeleteFieldCommand(
      UserDetails user, String categoryId, String fieldId);
}
