package com.unimelb.swen30006.workshop3;

import java.awt.image.BufferedImage;

public interface Effect {
	public BufferedImage applyEffect(BufferedImage img, Effect effect);
}
