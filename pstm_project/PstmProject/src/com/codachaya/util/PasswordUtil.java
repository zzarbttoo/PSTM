package com.codachaya.util;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import sun.misc.BASE64Encoder;

public class PasswordUtil {
	
	private final static int KEY_LENGTH = 64;
	private final static int ITER_COUNT = 1293;

	public static String[] encrypt(String password) {
		String[] result = new String[2];

        try {
			SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			
	        char[] chars = password.toCharArray();
	        String salt = getRandomKey();

	        PBEKeySpec spec = new PBEKeySpec(chars, salt.getBytes(), ITER_COUNT, KEY_LENGTH * 8);
	        byte[] hash = skf.generateSecret(spec).getEncoded();

	        result[0] = byteToBase64(hash);
	        result[1] = salt;
	        
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
        
		return result;
	}
	
	public static boolean checkPassword(String password, String salt, String resultPassword) {

		boolean result = false;
		
		try {
			SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			
	        char[] chars = password.toCharArray();

	        PBEKeySpec spec = new PBEKeySpec(chars, salt.getBytes(), ITER_COUNT, KEY_LENGTH * 8);
	        byte[] hash = skf.generateSecret(spec).getEncoded();
	        
	        String encResult = byteToBase64(hash);

	        if(encResult.equals(resultPassword)) {
	        	result = true;
	        }
	        
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static String getRandomKey() {
		Random random = new Random();
		
		byte[] value = new byte[KEY_LENGTH];
		
		random.nextBytes(value);
		
		return byteToBase64(value);
	}
	
	public static String byteToBase64(byte[] bytes) {
		String result = null;
		
		BASE64Encoder encoder = new BASE64Encoder();
		
		result = encoder.encode(bytes);
		
		return result;
	}

    private static String toHex(byte[] array)
    {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if(paddingLength > 0)
        {
            return String.format("%0"  +paddingLength + "d", 0) + hex;
        } else{
            return hex;
        }
    }

    private static byte[] fromHex(String hex)
    {
        byte[] bytes = new byte[hex.length() / 2];
        for(int i = 0; i<bytes.length ;i++)
        {
            bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }
}
