package com.example.demo.platform.shared.object;

import java.awt.Color;

public class ColorObject {
  private final Color color;
  private final float[] hsb;

  public ColorObject(String hex) {
    this.color = Color.decode(hex);
    this.hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
  }

  public String getHex() {
    return toHex(color);
  }

  public boolean isGrayscale() {
    int red = color.getRed();
    int green = color.getGreen();
    int blue = color.getBlue();

    int maxChannel = Math.max(red, Math.max(green, blue));
    int minChannel = Math.min(red, Math.min(green, blue));

    return (maxChannel - minChannel) <= 16;
  }

  public String generateAccentColor() {
    float hue = (hsb[0] + 30f / 360f) % 1.0f;
    float saturation = Math.max(hsb[1] * 1.1f, Math.min(0f, 1f));
    float brightness = Math.max(hsb[2] * 1.1f, Math.min(0f, 1f));

    return toHex(Color.getHSBColor(hue, saturation, brightness));
  }

  public String generateNeutralColor() {
    float hue = hsb[0];
    float saturation = 0.05f;
    float brightness = Math.max(0.9f, 1f - hsb[2] * 0.1f);

    return toHex(Color.getHSBColor(hue, saturation, brightness));
  }

  public String generateDarkColor() {
    float hue = hsb[0];
    float saturation = 0.05f;
    float brightness = Math.min(0.15f, hsb[2] * 0.2f);

    return toHex(Color.getHSBColor(hue, saturation, brightness));
  }

  private String toHex(Color color) {
    return String.format("#%02X%02X%02X", color.getRed(), color.getGreen(), color.getBlue())
        .toUpperCase();
  }
}
