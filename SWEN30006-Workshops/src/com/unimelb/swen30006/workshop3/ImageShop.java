package com.unimelb.swen30006.workshop3;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageShop {
	private static ImageDocument image;
	private static EffectLibrary effects;
	
	public static void main(String args[]) {
		ImageLoader loader = new ImageLoader("assets/LECGroup.jpg");
		image = loader.loadImage();
		effects = EffectLibrary.initialise();
		effects.registerEffect("greyscale", image);
		Effect effect = effects.getEffect("greyscale");
		
		image.setOutputFile("assets/altered_LECGroup.jpg");
		image.applyEffect(image.image, new GreyScaleEffect());
		
		loader.writeImage(image.image, image.outputFile);
	}
	
	private Effect[] processTransforms(String args[]) {
		return null;
	}
	
	private void processImage(String image, Effect[] transforms) {
		
	}
}
