package com.fline.datasecurity.zj.util;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SymmetricEncoder {
	private static Logger LOG = LogManager.getLogger(SymmetricEncoder.class);

	// 加密
	public static String AESEncode(String encodeRules, String content) {
		try {
			KeyGenerator keygen = KeyGenerator.getInstance("AES");

			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(encodeRules.getBytes());
			keygen.init(128, secureRandom);
			SecretKey original_key = keygen.generateKey();
			byte[] raw = original_key.getEncoded();
			SecretKey key = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] byte_encode = content.getBytes("utf-8");
			byte[] byte_AES = cipher.doFinal(byte_encode);
			String AES_encode = Base64.encodeBase64String(byte_AES);
			return AES_encode;
		} catch (Exception e) {
			LOG.error("aesEncode failed.", e);
			return null;
		}
	}

	/*
	 * 解密
	 */
	public static String AESDncode(String encodeRules, String content) {
		try {
			KeyGenerator keygen = KeyGenerator.getInstance("AES");
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(encodeRules.getBytes());
			keygen.init(128, secureRandom);
			SecretKey original_key = keygen.generateKey();
			byte[] raw = original_key.getEncoded();
			SecretKey key = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, key);
//			byte [] byte_content= new BASE64Decoder().decodeBuffer(content);
			byte[] byte_content = Base64.decodeBase64(content);
			byte[] byte_decode = cipher.doFinal(byte_content);
			String AES_decode = new String(byte_decode, "utf-8");
			return AES_decode;
		} catch (Exception e) {
			LOG.error("aesDecode failed.", e);
			return null;
		}
	}

}
