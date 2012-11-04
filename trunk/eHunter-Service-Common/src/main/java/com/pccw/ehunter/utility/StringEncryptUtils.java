package com.pccw.ehunter.utility;

import java.security.MessageDigest;

import sun.misc.BASE64Encoder;

public class StringEncryptUtils {
	
	public static final String KEY_MD5 = "MD5";
	
	public static final String KEY_SHA = "SHA";
	
	private static byte[] encryptMD5(String target) throws Exception {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		md5.update(target.getBytes());
		return md5.digest();
	}
	
	private static byte[] encryptSHA(String target) throws Exception {
        MessageDigest sha = MessageDigest.getInstance("SHA");
        sha.update(target.getBytes());
        return sha.digest();
    }
	
	public static String encryptDefault(String target) throws Exception {
		MessageDigest digest = MessageDigest.getInstance(KEY_MD5);
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(digest.digest(target.getBytes()));
	}
	
	private static String toHex(byte[] buffer) {
        StringBuffer sb = new StringBuffer(buffer.length * 3);
        for (int i = 0; i < buffer.length; i++) {
            sb.append(Character.forDigit((buffer[i] & 0xf0) >> 4, 16));
            sb.append(Character.forDigit(buffer[i] & 0x0f, 16));
        }
        return sb.toString();
    }
	
	public static String encrypt(String target , String mode) throws Exception{
		if(KEY_MD5.equals(mode)){
			return toHex(encryptMD5(target));
		}else if(KEY_SHA.equals(mode)){
			return toHex(encryptSHA(target));
		}else {
			return target;
		}
	}
}
