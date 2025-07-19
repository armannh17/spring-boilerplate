package com.example.demo.application.file.serializer;

import com.example.demo.application.file.dto.GenerateUploadLinkResDto;
import com.example.demo.application.file.model.UploadFileModel;
import com.example.demo.platform.shared.constant.ErrorCode;
import com.example.demo.platform.shared.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class FileSerializer {
  public ResponseDto<GenerateUploadLinkResDto> serializeGenerateUploadLinkResponse(
      UploadFileModel file) {
    return ResponseDto.<GenerateUploadLinkResDto>builder()
        .code(ErrorCode.NO_ERROR.getCode())
        .status(HttpStatus.OK.value())
        .message("successful")
        .data(GenerateUploadLinkResDto.builder().key(file.getKey()).url(file.getUrl()).build())
        .build();
  }
}
