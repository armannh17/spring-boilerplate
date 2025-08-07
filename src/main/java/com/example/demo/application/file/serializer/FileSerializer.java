package com.example.demo.application.file.serializer;

import com.example.demo.application.file.dto.GenerateUploadLinkResDto;
import com.example.demo.application.file.model.UploadFileModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FileSerializer {
  GenerateUploadLinkResDto serializeToGenerateUploadLinkDto(UploadFileModel model);
}
