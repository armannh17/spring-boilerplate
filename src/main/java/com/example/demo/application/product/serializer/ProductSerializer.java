package com.example.demo.application.product.serializer;

import com.example.demo.application.product.command.AddVariantCommand;
import com.example.demo.application.product.command.AddVarietyCommand;
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
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.core.userdetails.UserDetails;

@Mapper(componentModel = "spring")
public interface ProductSerializer extends BaseSerializer {

  @Mapping(target = "storeId", source = "dto.storeId", qualifiedByName = "mapId")
  @Mapping(target = "fieldId", source = "dto.fieldId", qualifiedByName = "mapId")
  @Mapping(target = "userdId", source = "user", qualifiedByName = "mapId")
  MakeProductCommand serializeToMakeProductCommand(UserDetails user, MakeProductReqDto dto);

  MakeProductResDto serializeToMakeProductDto(String id);

  @Mapping(target = "id", source = "productId", qualifiedByName = "mapId")
  @Mapping(target = "userId", source = "user", qualifiedByName = "mapId")
  UpdateProductCommand serializeToUpdateProductCommand(
      UserDetails user, String productId, UpdateProductReqDto dto);

  @Mapping(target = "id", source = "productId", qualifiedByName = "mapId")
  @Mapping(target = "userId", source = "user", qualifiedByName = "mapId")
  DeleteProductCommand serializeToDeleteProductCommand(UserDetails user, String productId);

  @Mapping(target = "productId", source = "productId", qualifiedByName = "mapId")
  @Mapping(target = "userId", source = "user", qualifiedByName = "mapId")
  @Mapping(target = "varieties", source = "dto.varieties")
  AddVariantCommand serializeToAddVariantCommand(
      UserDetails user, String productId, AddVariantReqDto dto);

  AddVariantResDto serializeToAddVariantDto(String id);

  @Mapping(target = "id", source = "variantId", qualifiedByName = "mapId")
  @Mapping(target = "productId", source = "productId", qualifiedByName = "mapId")
  @Mapping(target = "userId", source = "user", qualifiedByName = "mapId")
  DeleteVariantCommand serializeToDeleteVariantCommand(
      UserDetails user, String productId, String variantId);

  List<AddVarietyCommand> mapVarieties(List<AddVariantReqDto> varieties);
}
