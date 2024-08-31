package com.example.api.infra.helper;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

public class Criptografia {

    private static final String ALGORITHM_3DES = "DESede";
    private static final String DES3_KEY = "123456789012345678901234"; // Chave 3DES de 24 bytes

    public static String encrypt3DES(String data) throws Exception {
        Key key = new SecretKeySpec(DES3_KEY.getBytes(), ALGORITHM_3DES);
        Cipher cipher = Cipher.getInstance(ALGORITHM_3DES);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedData = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    public static String decrypt3DES(String encryptedData) throws Exception {
        Key key = new SecretKeySpec(DES3_KEY.getBytes(), ALGORITHM_3DES);
        Cipher cipher = Cipher.getInstance(ALGORITHM_3DES);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedData = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedData = cipher.doFinal(decodedData);
        return new String(decryptedData);
    }
}
