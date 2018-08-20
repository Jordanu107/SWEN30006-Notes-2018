package com.unimelb.swen30006.workshop3;

import java.awt.image.BufferedImage;

public class GreyScaleEffect implements Effect {

	@Override
	public BufferedImage applyEffect(BufferedImage img, Effect effect) {
		// Code attributed to: https://www.dyclassroom.com/image-processing-project/how-to-convert-a-color-image-into-grayscale-image-in-java
		int width = img.getWidth();
		int height = img.getHeight();
		
		for (int y = 0; y < height; y++) {
			for (int x=0; x < width; x++) {
				int p = img.getRGB(x,y);
				int a = (p>>24)&0xff;
				int r = (p>>16)&0xff;
				int g = (p>>8)&0xff;
				int b = p&0xff;
				
				int avg = (r+g+b)/3;
				
				p = (a<<24) | (avg<<16) | (avg<<8) | avg;
				img.setRGB(x, y, p);
			}
		}
		return img;
	}

}
