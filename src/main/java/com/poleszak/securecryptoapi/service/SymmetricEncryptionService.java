package com.poleszak.securecryptoapi.service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;

import static java.util.Base64.getDecoder;
import static java.util.Base64.getEncoder;
import static javax.crypto.Cipher.DECRYPT_MODE;
import static javax.crypto.Cipher.ENCRYPT_MODE;
import static javax.crypto.KeyGenerator.getInstance;

@Service
public class SymmetricEncryptionService {
    private SecretKey secretKey;

    public SymmetricEncryptionService() throws NoSuchAlgorithmException {
        generateKey();
    }

    public String generateKey() throws NoSuchAlgorithmException {
        var keyGenerator = getInstance("AES");
        keyGenerator.init(128);
        secretKey = keyGenerator.generateKey();

        return exportKey();
    }

    public String exportKey() {
        return getEncoder().encodeToString(secretKey.getEncoded());
    }

    public String setKey(String keyStr) {
        var decodedKey = getDecoder().decode(keyStr);
        secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");

        return "Key updated successfully";
    }

    public String encrypt(String message) {
        try {
            var cipher = Cipher.getInstance("AES");
            cipher.init(ENCRYPT_MODE, secretKey);
            var encryptedMessageBytes = cipher.doFinal(message.getBytes());

            return getEncoder().encodeToString(encryptedMessageBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting message", e);
        }
    }

    public String decrypt(String encryptedMessageJson) {
        try {
            var encryptedMessage = new JSONObject(encryptedMessageJson).getString("message");
            var cipher = Cipher.getInstance("AES");
            cipher.init(DECRYPT_MODE, secretKey);
            var encryptedMessageBytes = getDecoder().decode(encryptedMessage);
            var decryptedMessageBytes = cipher.doFinal(encryptedMessageBytes);

            return new String(decryptedMessageBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error decrypting message", e);
        }
    }
}