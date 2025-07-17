package com.example.demo.application.store.model;

import com.example.demo.application.store.exception.GreyScaleColorAreNotAllowedException;
import com.example.demo.platform.shared.model.BaseModel;
import com.example.demo.platform.shared.object.ColorObject;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class StoreModel extends BaseModel {
	private String name;
	private String slug;
	private String brief;
	private String description;
	private String image;
	private String colorPrimary;
	private String colorAccent;
	private String colorNeutral;
	private String colorDark;
	private Boolean verified;

	public void populateColorPalette(String hex) {
		ColorObject baseColor = new ColorObject(hex);

		if (baseColor.isGrayscale()) {
			throw new GreyScaleColorAreNotAllowedException();
		}

		this.colorPrimary = baseColor.getHex();
		this.colorAccent = baseColor.generateAccentColor();
		this.colorNeutral = baseColor.generateNeutralColor();
		this.colorDark = baseColor.generateDarkColor();
	}
}
