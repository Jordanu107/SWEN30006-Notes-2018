package com.unimelb.swen30006.workshop3;

import java.util.HashMap;

public class EffectLibrary {
	private static HashMap<String, Effect> EFFECTS;
	
	public EffectLibrary() {
		EFFECTS = new HashMap<>();
	}
	
	public static EffectLibrary initialise() {
		return new EffectLibrary();
	}
	
	public boolean registerEffect(String name, Effect effect) {
		if (EFFECTS.get(name) == null) {
			EFFECTS.put(name, effect);
			return true;
		}
		return false;
	}
	
	public static boolean deregisterEffect(String name, Effect effect) {
		if (EFFECTS.get(name) != null) {
			EFFECTS.remove(name, effect);
			return true;
		}
		return false;
	}
	
	public Effect getEffect(String name) {
		return EFFECTS.get(name);
	}
	
	public static String[] availableEffects() {
		String[] list = new String[EFFECTS.size()];
		int i = 0;
		for (String key : EFFECTS.keySet()) {
			list[i] = key;
			i++;
		}
		return list;
	}
}
