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
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductSerializer {

  MakeProductCommand serializeToMakeProductCommand(String userId, MakeProductReqDto dto);

  MakeProductResDto serializeToMakeProductDto(String id);

  UpdateProductCommand serializeToUpdateProductCommand(
      String userId, String id, UpdateProductReqDto dto);

  DeleteProductCommand serializeToDeleteProductCommand(String userId, String id);

  AddVariantCommand serializeToAddVariantCommand(
      String userId, String productId, AddVariantReqDto dto);

  AddVariantResDto serializeToAddVariantDto(String id);

  DeleteVariantCommand serializeToDeleteVariantCommand(String userId, String productId, String id);
}
