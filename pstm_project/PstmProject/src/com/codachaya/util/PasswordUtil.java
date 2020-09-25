package com.codachaya.util;

import java.util.Random;

import sun.misc.BASE64Encoder;

public class PasswordUtil {
	
	private final static int KEY_LENGTH = 64;
	
	public static String getRandomKey() {
		
		String result = null;
		
		Random random = new Random();
		
		byte[] value = new byte[KEY_LENGTH];
		
		random.nextBytes(value);
		
		BASE64Encoder encoder = new BASE64Encoder();
		
		result = encoder.encode(value);
		
		return result;
	}

	public static String pbkdf2() {
		String result = null;
		
		return result;
	}
	
}
