package com.example.demo.application.store.model;

import java.awt.Color;

import com.example.demo.platform.shared.model.BaseModel;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class StoreModel extends BaseModel {
	private String name;
	private String slug;
	private String image;
	private String colorPrimary;
	private String colorAccent;
	private String colorNeutral;
	private Boolean verified;

	public void PopulateColors(String primary) {
		Color colorPrimary = Color.decode(primary);

		float[] hsb = Color.RGBtoHSB(colorPrimary.getRed(), colorPrimary.getGreen(), colorPrimary.getBlue(), null);

		Color colorAccent = Color.getHSBColor((hsb[0] + 0.58f) % 1.0f, clamp(hsb[1] * 0.8f, 0.4f, 1.0f),
				clamp(hsb[2] * 1.2f, 0.6f, 1.0f));

		Color colorNeutral = Color.getHSBColor(hsb[0], 0.05f, clamp(hsb[2] * 1.1f, 0.6f, 0.95f));

		this.colorPrimary = toHex(colorPrimary);
		this.colorAccent = toHex(colorAccent);
		this.colorNeutral = toHex(colorNeutral);
	}

	private float clamp(float value, float min, float max) {
		return Math.max(min, Math.min(max, value));
	}

	private String toHex(Color color) {
		return String.format("#%02X%02X%02X", color.getRed(), color.getGreen(), color.getBlue());
	}
}
