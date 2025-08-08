package com.example.demo.application.file.controller;

import com.example.demo.application.file.dto.GenerateUploadLinkResDto;
import com.example.demo.application.file.model.UploadFileModel;
import com.example.demo.application.file.serializer.FileSerializer;
import com.example.demo.application.file.service.FileService;
import com.example.demo.platform.shared.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/files")
@Tag(name = "File", description = "File Management")
public class FileController {
  private final FileService fileService;
  private final FileSerializer fileSerializer;

  public FileController(FileService fileService, FileSerializer fileSerializer) {
    this.fileService = fileService;
    this.fileSerializer = fileSerializer;
  }

  @PreAuthorize("hasAnyRole('OWNER', 'ADMIN')")
  @PostMapping(path = "/generate-upload-link")
  @ResponseStatus(HttpStatus.OK)
  @SecurityRequirement(name = "Bearer Authentication")
  @Operation(summary = "Generate presigned upload URL")
  ResponseDto<GenerateUploadLinkResDto> generateUploadLink() {

    UploadFileModel file = fileService.generateUploadLink();

    GenerateUploadLinkResDto response = fileSerializer.serializeToGenerateUploadLinkDto(file);

    return ResponseDto.success(HttpStatus.OK, response);
  }
}
