package com.example.demo.application.product.serializer;

import com.example.demo.application.product.command.AddVariantCommand;
import com.example.demo.application.product.command.DeleteProductCommand;
import com.example.demo.application.product.command.DeleteVariantCommand;
import com.example.demo.application.product.command.MakeProductCommand;
import com.example.demo.application.product.command.UpdateProductCommand;
import com.example.demo.application.product.dto.AddVariantReqDto;
import com.example.demo.application.product.dto.AddVariantResDto;
import com.example.demo.application.product.dto.MakeProductReqDto;
import com.example.demo.application.product.dto.MakeProductResDto;
import com.example.demo.application.product.dto.UpdateProductReqDto;
import com.example.demo.platform.shared.serializer.BaseSerializer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.core.userdetails.UserDetails;

@Mapper(componentModel = "spring")
public interface ProductSerializer extends BaseSerializer {

  @Mapping(target = "userdId", source = "user", qualifiedByName = "mapId")
  MakeProductCommand serializeToMakeProductCommand(UserDetails user, MakeProductReqDto dto);

  MakeProductResDto serializeToMakeProductDto(String id);

  @Mapping(target = "userId", source = "user", qualifiedByName = "mapId")
  UpdateProductCommand serializeToUpdateProductCommand(
      UserDetails user, String id, UpdateProductReqDto dto);

  @Mapping(target = "userId", source = "user", qualifiedByName = "mapId")
  DeleteProductCommand serializeToDeleteProductCommand(UserDetails user, String id);

  @Mapping(target = "userId", source = "user", qualifiedByName = "mapId")
  AddVariantCommand serializeToAddVariantCommand(
      UserDetails user, String productId, AddVariantReqDto dto);

  AddVariantResDto serializeToAddVariantDto(String id);

  @Mapping(target = "userId", source = "user", qualifiedByName = "mapId")
  DeleteVariantCommand serializeToDeleteVariantCommand(
      UserDetails user, String productId, String id);
}
