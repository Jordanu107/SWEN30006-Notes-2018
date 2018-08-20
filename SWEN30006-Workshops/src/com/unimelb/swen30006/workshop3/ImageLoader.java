package com.unimelb.swen30006.workshop3;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {
	private String filename;
	private BufferedImage img;
	
	public ImageLoader(String filename) {
		this.filename = filename;
	}
	
	public ImageDocument loadImage() {
		try {
			img = ImageIO.read(new File(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ImageDocument doc = new ImageDocument(img);
		return doc;
	} 
	
	public void writeImage(BufferedImage img, String outputFile) {
		try {
				ImageIO.write(img, "jpg", new File(outputFile));
			} catch(IOException e) {
				System.out.println("Error");
			}
	}
}
