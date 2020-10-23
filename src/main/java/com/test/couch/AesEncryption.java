package com.test.couch;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Map;

//add logic to test commit
public class AesEncryption {

  private static AesEncryption aesEncryption = null;
  private final Map<String, Object> properties;
  private final static String SECRET_KEY = "auth.secret_key";
  private final static String CHIPER_INSTANCE = "AES/CBC/PKCS5Padding";
  private final static String SECRET_KEY_FACTORY_INSTANCE = "PBKDF2WithHmacSHA256";
  private final static byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
  private final static IvParameterSpec ivspec = new IvParameterSpec(iv);

  private static String secretKey;
  private static String salt;
  private static int iterations = 65536;
  private static int keySize = 256;
  private static KeySpec spec;
  private static SecretKey tmp;
  private static SecretKeySpec secretKeySpec;
  private static SecretKeyFactory factory;

  private AesEncryption ( Map<String, Object> properties) {
    this.properties = properties;
    this.salt = properties.get(SECRET_KEY).toString();
    this.secretKey = properties.get(SECRET_KEY).toString();
    try {
      factory = SecretKeyFactory.getInstance(SECRET_KEY_FACTORY_INSTANCE);
      spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), iterations, keySize);
      tmp = factory.generateSecret(spec);
      secretKeySpec = new SecretKeySpec(tmp.getEncoded(), "AES");
    }
    catch ( Exception e ) {
      e.printStackTrace();
    }
  }

  public static AesEncryption getInstance(Map<String, Object> properties) {
    if ( aesEncryption == null) {
      aesEncryption = new AesEncryption(properties);
    }
    return aesEncryption;
  }

  public static AesEncryption getInstance() {
    return aesEncryption;
  }

  public String encrypt(String strToEncrypt)
  {
    try
    {
      Cipher cipher = Cipher.getInstance(CHIPER_INSTANCE);
      cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivspec);
      return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
    }
    catch (Exception e)
    {
      System.out.println("Error while encrypting: " + e.toString());
    }
    return null;
  }

  public String decrypt(String strToDecrypt) {
    try
    {
      Cipher cipher = Cipher.getInstance(CHIPER_INSTANCE);
      cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivspec);
      return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
    }
    catch (Exception e) {
      System.out.println("Error while decrypting: " + e.toString());
    }
    return null;
  }

}