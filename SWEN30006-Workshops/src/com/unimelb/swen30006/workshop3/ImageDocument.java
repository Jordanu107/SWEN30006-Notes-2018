package com.unimelb.swen30006.workshop3;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageDocument implements Effect {
	public BufferedImage image;
	public Effect[] transforms;
	public String outputFile;
	
	public ImageDocument(BufferedImage image) {
		this.image = image;
	}
	
	@Override
	public BufferedImage applyEffect(BufferedImage img, Effect effect) {
		return effect.applyEffect(img, effect);
	}
	
	public void renderImage(String outputFile) {
		
	}
	
	public boolean addTransform(Effect t) {
		for (Effect effect : transforms) {
			if (effect.equals(t)) {
				return false;
			}
		}
		return true;
	}
	
	public boolean addTransformations(Effect[] t) {
		return true;
	}
	
	public void setOutputFile(String outputFile) {
		this.outputFile = outputFile;
	}
}
