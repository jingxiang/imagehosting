package com.bushangbuxia.imagehosting.utils;

import java.util.Random;

public class RandomUtils{

	private static char[] numbersAndLetters = ("abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();

	public static final String randomString(int length) {
		if (length < 1) {
			return null;
		}
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[new Random().nextInt(61)];
		}
		return new String(randBuffer);
	}
	
	public static final int randomNumber(int min, int max) {
		return new Random().ints(min, (max + 1)).limit(1).findFirst().getAsInt();
	}
}