package com.universe.common.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 支持AES、DES、RSA加密、数字签名以及生成对称密钥和非对称密钥对
 */
public abstract class EncryptUtils {

	private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
	private static final Encoder BASE64_ENCODER = Base64.getEncoder();
	private static final Decoder BASE64_DECODER = Base64.getDecoder();

	private static final Map<Algorithm, KeyFactory> KEY_FACTORY_CACHE = new ConcurrentHashMap<>();
	private static final Map<Algorithm, Cipher> CIPHER_CACHE = new HashMap<>();

	/**
	 * 生成对称密钥，目前支持的算法有AES、DES
	 * @param algorithm
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String generateSymmetricKey(Algorithm algorithm) throws NoSuchAlgorithmException {
		KeyGenerator generator = KeyGenerator.getInstance(algorithm.getName());
		generator.init(algorithm.getKeySize());
		SecretKey secretKey = generator.generateKey();
		return BASE64_ENCODER.encodeToString(secretKey.getEncoded());
	}

	/**
	 * 生成非对称密钥对，目前支持的算法有RSA、DSA
	 * @param algorithm
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static AsymmetricKeyPair generateAsymmetricKeyPair(Algorithm algorithm) throws NoSuchAlgorithmException {
		KeyPairGenerator generator = KeyPairGenerator.getInstance(algorithm.getName());
		generator.initialize(algorithm.getKeySize());
		KeyPair keyPair = generator.generateKeyPair();
		String publicKey = BASE64_ENCODER.encodeToString(keyPair.getPublic().getEncoded());
		String privateKey = BASE64_ENCODER.encodeToString(keyPair.getPrivate().getEncoded());
		return new AsymmetricKeyPair(publicKey, privateKey);
	}

	public static String encryptByAES(String key, String cleartext) throws Exception {
		return encryptSymmetrically(key, cleartext, Algorithm.AES);
	}

	public static String decryptyByAES(String key, String ciphertext) throws Exception {
		return decryptSymmetrically(key, ciphertext, Algorithm.AES);
	}

	public static String encryptByDES(String key, String cleartext) throws Exception {
		return encryptSymmetrically(key, cleartext, Algorithm.DES);
	}

	public static String decryptByDES(String key, String ciphertext) throws Exception {
		return decryptSymmetrically(key, ciphertext, Algorithm.DES);
	}

	public static String encryptByRSA(String publicKeyText, String cleartext) throws Exception {
		return encryptAsymmetrically(publicKeyText, cleartext, Algorithm.RSA);
	}

	public static String decryptByRSA(String privateKeyText, String ciphertext) throws Exception {
		return decryptAsymmetrically(privateKeyText, ciphertext, Algorithm.RSA);
	}

	/**
	 * SHA1签名算法和DSA加密算法结合使用生成数字签名
	 * @param privateKeyText
	 * @param msg
	 * @return 数字签名
	 * @throws Exception
	 */
	public static String signBySHA1WithDSA(String privateKeyText, String msg) throws Exception {
		return doSign(privateKeyText, msg, Algorithm.DSA, Algorithm.SHA1WithDSA);
	}

	/**
	 * SHA1签名算法和RSA加密算法结合使用生成数字签名
	 * @param privateKeyText
	 * @param msg
	 * @return 数字签名
	 * @throws Exception
	 */
	public static String signBySHA1WithRSA(String privateKeyText, String msg) throws Exception {
		return doSign(privateKeyText, msg, Algorithm.RSA, Algorithm.SHA1WithRSA);
	}

	/**
	 * SHA256签名算法和RSA加密算法结合使用生成数字签名
	 * @param privateKeyText
	 * @param msg
	 * @return 数字签名
	 * @throws Exception
	 */
	public static String signBySHA256WithRSA(String privateKeyText, String msg) throws Exception {
		return doSign(privateKeyText, msg, Algorithm.RSA, Algorithm.SHA256WithRSA);
	}

	/**
	 * SHA1签名算法和DSA加密算法检验数字签名
	 * @param publicKeyText
	 * @param msg
	 * @param signatureText 数字
	 * @return 检验是否成功
	 * @throws Exception
	 */
	public static boolean verifyBySHA1WithDSA(String publicKeyText, String msg, String signatureText) throws Exception {
		return doVerify(publicKeyText, msg, signatureText, Algorithm.DSA, Algorithm.SHA1WithDSA);
	}

	/**
	 * SHA1签名算法和RSA加密算法检验数字签名
	 * @param publicKeyText
	 * @param msg
	 * @param signatureText
	 * @return 校验是否成功
	 * @throws Exception
	 */
	public static boolean verifyBySHA1WithRSA(String publicKeyText, String msg, String signatureText) throws Exception {
		return doVerify(publicKeyText, msg, signatureText, Algorithm.RSA, Algorithm.SHA1WithRSA);
	}

	/**
	 * SHA256签名算法和RSA加密算法检验数字签名
	 * @param publicKeyText
	 * @param msg
	 * @param signatureText
	 * @return 校验是否成功
	 * @throws Exception
	 */
	public static boolean verifyBySHA256WithRSA(String publicKeyText, String msg, String signatureText) throws Exception {
		return doVerify(publicKeyText, msg, signatureText, Algorithm.RSA, Algorithm.SHA256WithRSA);
	}

	/**
	 * 对称加密
	 * @param secretKey 密钥
	 * @param cleartext 明文
	 * @param algorithm 对称加密算法，如AES、DES
	 * @return
	 * @throws Exception
	 */
	public static String encryptSymmetrically(String secretKey, String cleartext, Algorithm algorithm) throws Exception {
		SecretKey key = decodeSymmetricKey(secretKey, algorithm);
		byte[] cleartextInBytes = cleartext.getBytes(DEFAULT_CHARSET);
		byte[] ciphertextInBytes = transform(algorithm, Cipher.ENCRYPT_MODE, key, cleartextInBytes);

		return BASE64_ENCODER.encodeToString(ciphertextInBytes);
	}

	/**
	 * 对称解密
	 * @param secretKey 密钥
	 * @param ciphertext 密文
	 * @param algorithm 对称加密算法，如AES、DES
	 * @return
	 * @throws Exception
	 */
	public static String decryptSymmetrically(String secretKey, String ciphertext, Algorithm algorithm) throws Exception {
		SecretKey key = decodeSymmetricKey(secretKey, algorithm);
		byte[] ciphertextInBytes = BASE64_DECODER.decode(ciphertext);

		byte[] cleartextInBytes = transform(algorithm, Cipher.DECRYPT_MODE, key, ciphertextInBytes);
		return new String(cleartextInBytes, DEFAULT_CHARSET);
	}

	/**
	 * 非对称加密
	 * @param publicKeyText 公钥
	 * @param cleartext 明文
	 * @param algorithm 非对称加密算法
	 * @return
	 * @throws Exception
	 */
	public static String encryptAsymmetrically(String publicKeyText, String cleartext, Algorithm algorithm) throws Exception {
		PublicKey publicKey = regeneratePublicKey(publicKeyText, algorithm);
		byte[] cleartextInBytes = cleartext.getBytes(DEFAULT_CHARSET);
		byte[] ciphertextInBytes = transform(algorithm, Cipher.ENCRYPT_MODE, publicKey, cleartextInBytes);
		return BASE64_ENCODER.encodeToString(ciphertextInBytes);
	}

	/**
	 * 非对称解密
	 * @param privateKeyText 私钥
	 * @param ciphertext 密文
	 * @param algorithm 非对称加密算法
	 * @return
	 * @throws Exception
	 */
	public static String decryptAsymmetrically(String privateKeyText, String ciphertext, Algorithm algorithm) throws Exception {
		PrivateKey privateKey = regeneratePrivateKey(privateKeyText, algorithm);
		byte[] ciphertextInBytes = BASE64_DECODER.decode(ciphertext);
		byte[] cleartextInBytes = transform(algorithm, Cipher.DECRYPT_MODE, privateKey, ciphertextInBytes);
		return new String(cleartextInBytes, DEFAULT_CHARSET);
	}

	/**
	 * 生成数字签名
	 * @param privateKeyText 私钥
	 * @param msg 传输的数据
	 * @param encryptionAlgorithm 加密算法，见Algorithm中的加密算法
	 * @param signatureAlgorithm 签名算法，见Algorithm中的签名算法
	 * @return 数字签名
	 * @throws Exception
	 */
	public static String doSign(String privateKeyText, String msg, Algorithm encryptionAlgorithm, Algorithm signatureAlgorithm)
		throws Exception {
		PrivateKey privateKey = regeneratePrivateKey(privateKeyText, encryptionAlgorithm);
		// Signature只支持签名算法
		Signature signature = Signature.getInstance(signatureAlgorithm.getName());
		signature.initSign(privateKey);
		signature.update(msg.getBytes(DEFAULT_CHARSET));
		byte[] signatureInBytes = signature.sign();
		return BASE64_ENCODER.encodeToString(signatureInBytes);
	}

	/**
	 * 数字签名验证
	 * @param publicKeyText 公钥
	 * @param msg 传输的数据
	 * @param signatureText 数字签名
	 * @param encryptionAlgorithm 加密算法，见Algorithm中的加密算法
	 * @param signatureAlgorithm 签名算法，见Algorithm中的签名算法
	 * @return 校验是否成功
	 * @throws Exception
	 */
	public static boolean doVerify(String publicKeyText, String msg, String signatureText, Algorithm encryptionAlgorithm,
																 Algorithm signatureAlgorithm) throws Exception {
		PublicKey publicKey = regeneratePublicKey(publicKeyText, encryptionAlgorithm);
		Signature signature = Signature.getInstance(signatureAlgorithm.getName());
		signature.initVerify(publicKey);
		signature.update(msg.getBytes(DEFAULT_CHARSET));
		return signature.verify(BASE64_DECODER.decode(signatureText));
	}

	/**
	 * 将密钥进行Base64位解码，重新生成SecretKey实例
	 * @param secretKey
	 * @param algorithm
	 * @return
	 */
	private static SecretKey decodeSymmetricKey(String secretKey, Algorithm algorithm) {
		byte[] key = BASE64_DECODER.decode(secretKey);
		return new SecretKeySpec(key, algorithm.getName());
	}

	private static PublicKey regeneratePublicKey(String publicKeyText, Algorithm algorithm)
		throws NoSuchAlgorithmException, InvalidKeySpecException {
		byte[] keyInBytes = BASE64_DECODER.decode(publicKeyText);
		KeyFactory keyFactory = getKeyFactory(algorithm);
		// 公钥必须使用RSAPublicKeySpec或者X509EncodedKeySpec
		KeySpec publicKeySpec = new X509EncodedKeySpec(keyInBytes);
		PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
		return publicKey;
	}

	private static PrivateKey regeneratePrivateKey(String key, Algorithm algorithm) throws Exception {
		byte[] keyInBytes = BASE64_DECODER.decode(key);
		KeyFactory keyFactory = getKeyFactory(algorithm);
		// 私钥必须使用RSAPrivateCrtKeySpec或者PKCS8EncodedKeySpec
		KeySpec privateKeySpec = new PKCS8EncodedKeySpec(keyInBytes);
		PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);
		return privateKey;
	}

	private static KeyFactory getKeyFactory(Algorithm algorithm) throws NoSuchAlgorithmException {
		KeyFactory keyFactory = KEY_FACTORY_CACHE.get(algorithm);
		if (keyFactory == null) {
			keyFactory = KeyFactory.getInstance(algorithm.getName());
			KEY_FACTORY_CACHE.put(algorithm, keyFactory);
		}

		return keyFactory;
	}

	private static byte[] transform(Algorithm algorithm, int mode, Key key, byte[] msg) throws Exception {
		Cipher cipher = CIPHER_CACHE.get(algorithm);
		// double check，减少上下文切换
		if (cipher == null) {
			synchronized (EncryptUtils.class) {
				if ((cipher = CIPHER_CACHE.get(algorithm)) == null) {
					cipher = determineWhichCipherToUse(algorithm);
					CIPHER_CACHE.put(algorithm, cipher);
				}

				cipher.init(mode, key);
				return cipher.doFinal(msg);
			}
		}

		synchronized (EncryptUtils.class) {
			cipher.init(mode, key);
			return cipher.doFinal(msg);
		}
	}

	private static Cipher determineWhichCipherToUse(Algorithm algorithm) throws NoSuchAlgorithmException, NoSuchPaddingException {
		Cipher cipher = null;
		String transformation = algorithm.getTransformation();
		// 官方推荐的transformation使用algorithm/mode/padding组合，SunJCE使用ECB作为默认模式，使用PKCS5Padding作为默认填充
		if (StringUtils.isNotEmpty(transformation)) {
			cipher = Cipher.getInstance(transformation);
		} else {
			cipher = Cipher.getInstance(algorithm.getName());
		}

		return cipher;
	}

	/**
	 * 算法分为加密算法和签名算法，更多算法实现见：<br/>
	 * <a href="https://docs.oracle.com/javase/8/docs/technotes/guides/security/StandardNames.html#impl">jdk8中的标准算法</a>
	 */
	public static class Algorithm {

		/**
		 * 以下为加密算法，加密算法transformation采用algorithm/mode/padding的形式
		 */
		public static final Algorithm AES = new Algorithm("AES", "AES/ECB/PKCS5Padding", 128);
		public static final Algorithm DES = new Algorithm("DES", "DES/ECB/PKCS5Padding", 56);
		public static final Algorithm RSA = new Algorithm("RSA", "RSA/ECB/PKCS1Padding", 1024);

		/**
		 * 以下为签名算法
		 */
		public static final Algorithm DSA = new Algorithm("DSA", 1024);
		public static final Algorithm SHA1WithDSA = new Algorithm("SHA256WithRSA", 1024);
		public static final Algorithm SHA1WithRSA = new Algorithm("SHA1WithRSA", 2048);
		public static final Algorithm SHA256WithRSA = new Algorithm("SHA256WithRSA", 2048);

		private String name;
		private String transformation;
		private int keySize;

		public Algorithm(String name, int keySize) {
			this(name, null, keySize);
		}

		public Algorithm(String name, String transformation, int keySize) {
			this.name = name;
			this.transformation = transformation;
			this.keySize = keySize;
		}

		public String getName() {
			return name;
		}

		public String getTransformation() {
			return transformation;
		}

		public int getKeySize() {
			return keySize;
		}

		@Override
		public String toString() {
			return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
		}

	}

	public static class AsymmetricKeyPair {

		private String publicKey;
		private String privateKey;

		public AsymmetricKeyPair(String publicKey, String privateKey) {
			this.publicKey = publicKey;
			this.privateKey = privateKey;
		}

		public String getPublicKey() {
			return publicKey;
		}

		public String getPrivateKey() {
			return privateKey;
		}

		@Override
		public String toString() {
			return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
		}
	}

}
