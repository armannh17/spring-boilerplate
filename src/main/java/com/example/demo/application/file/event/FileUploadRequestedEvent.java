package com.example.demo.application.file.event;

import com.example.demo.application.file.view.UploadFileView;
import com.example.demo.platform.shared.event.BaseEvent;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class FileUploadRequestedEvent extends BaseEvent<UploadFileView> {
	
}
