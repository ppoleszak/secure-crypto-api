package com.poleszak.securecryptoapi.service;

import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;

@Service
public class SymmetricEncryptionService {

    private SecretKey key;

    public String generateKey() {
        KeyGenerator keyGen;
        try {
            keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128);
            key = keyGen.generateKey();
            return DatatypeConverter.printHexBinary(key.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating key", e);
        }
    }

    public void setKey(String hexKey) {
        byte[] decodedKey = DatatypeConverter.parseHexBinary(hexKey);
        key = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    }

    public String encode(String message) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encrypted = cipher.doFinal(message.getBytes());
            return DatatypeConverter.printHexBinary(encrypted);
        } catch (Exception e) {
            throw new RuntimeException("Error encoding message", e);
        }
    }

    public String decode(String encryptedMessage) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decodedMessage = cipher.doFinal(DatatypeConverter.parseHexBinary(encryptedMessage));
            return new String(decodedMessage);
        } catch (Exception e) {
            throw new RuntimeException("Error decoding message", e);
        }
    }
}
