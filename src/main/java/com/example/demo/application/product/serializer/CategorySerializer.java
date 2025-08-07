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
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategorySerializer {

  MakeCategoryCommand serializeToMakeCategoryCommand(String userId, MakeCategoryReqDto dto);

  MakeCategoryResDto serializeToMakeCategoryDto(String id);

  UpdateCategoryCommand serializeToUpdateCategoryCommand(
      String userId, String id, UpdateCategoryReqDto dto);

  DeleteCategoryCommand serializeToDeleteCategoryCommand(String userId, String id);

  GetCategoryQuery serializeToGetCategoryQuery(String storeId);

  List<GetCategoryResDto> serializeToGetCategoryDto(List<CategoryModel> categories);

  AddFieldCommand serializeToAddFieldCommand(String userId, String categoryId, AddFieldReqDto dto);

  AddFieldResDto serializeToAddFieldDto(String id);

  UpdateFieldCommand serializeToUpdateFieldCommand(
      String userId, String categoryId, String id, UpdateFieldReqDto dto);

  DeleteFieldCommand serializeToDeleteFieldCommand(String userId, String categoryId, String id);
}
