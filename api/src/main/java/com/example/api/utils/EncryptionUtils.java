package com.example.api.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.util.Base64;

public class EncryptionUtils {

    public static void main(String[] args) {
        try {
            String idDigimon = "123445";
            String encryptedIdDigimon = encrypt3DES(idDigimon);
            System.out.println("ID Digimon: " + idDigimon);
            System.out.println("ID Digimon criptografado: " + encryptedIdDigimon);
            System.out.println("ID Digimon descriptografado: " + decrypt3DES(encryptedIdDigimon));

            String usuario = "usuario2";
            String encryptedUserInfo = encryptDES(usuario);
            System.out.println("Informações do usuário: " + usuario);
            System.out.println("Informações do usuário criptografadas: " + encryptedUserInfo);
            System.out.println("Informações do usuário descriptografadas: " + decryptDES(encryptedUserInfo));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Algoritmos de criptografia
    private static final String ALGORITHM_AES = "AES";
    private static final String ALGORITHM_DES = "DES";
    private static final String ALGORITHM_3DES = "DESede";
    private static final String ALGORITHM_BLOWFISH = "Blowfish";
    private static final String ALGORITHM_RSA = "RSA";
    private static final String ALGORITHM_CHACHA20 = "ChaCha20";

    // Chaves para criptografia
    private static final String AES_KEY = "1234567890123456"; // Chave AES de 16 bytes
    private static final String DES_KEY = "12345678"; // Chave DES de 8 bytes
    private static final String DES3_KEY = "123456789012345678901234"; // Chave 3DES de 24 bytes

    // Criação de uma chave AES
    private static SecretKey createAESKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM_AES);
        keyGen.init(256); // AES-256
        return keyGen.generateKey();
    }

    // Criação de uma chave RSA
    private static KeyPair createRSAKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(ALGORITHM_RSA);
        keyPairGen.initialize(2048);
        return keyPairGen.generateKeyPair();
    }

    // Criação de uma chave Blowfish
    private static SecretKey createBlowfishKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM_BLOWFISH);
        keyGen.init(128); // Blowfish de 128 bits
        return keyGen.generateKey();
    }

    // Criação de uma chave ChaCha20
    private static SecretKey createChaCha20Key() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM_CHACHA20);
        keyGen.init(256); // ChaCha20 de 256 bits
        return keyGen.generateKey();
    }

    // Métodos de criptografia e descriptografia

    // AES
    public static String encryptAES(String data, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM_AES);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedData = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    public static String decryptAES(String encryptedData, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM_AES);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedData = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedData = cipher.doFinal(decodedData);
        return new String(decryptedData);
    }

    // RSA
    public static String encryptRSA(String data, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM_RSA);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedData = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    public static String decryptRSA(String encryptedData, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM_RSA);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decodedData = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedData = cipher.doFinal(decodedData);
        return new String(decryptedData);
    }

    // DES
    public static String encryptDES(String data) throws Exception {
        Key key = new SecretKeySpec(DES_KEY.getBytes(), ALGORITHM_DES);
        Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedData = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    public static String decryptDES(String encryptedData) throws Exception {
        Key key = new SecretKeySpec(DES_KEY.getBytes(), ALGORITHM_DES);
        Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedData = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedData = cipher.doFinal(decodedData);
        return new String(decryptedData);
    }

    // 3DES
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

    // Blowfish
    public static String encryptBlowfish(String data, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM_BLOWFISH);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedData = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    public static String decryptBlowfish(String encryptedData, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM_BLOWFISH);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedData = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedData = cipher.doFinal(decodedData);
        return new String(decryptedData);
    }

    // ChaCha20
    public static String encryptChaCha20(String data, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM_CHACHA20);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedData = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    public static String decryptChaCha20(String encryptedData, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM_CHACHA20);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedData = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedData = cipher.doFinal(decodedData);
        return new String(decryptedData);
    }
}
