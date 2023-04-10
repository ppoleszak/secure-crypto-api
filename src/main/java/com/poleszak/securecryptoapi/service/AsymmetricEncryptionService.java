package com.poleszak.securecryptoapi.service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import static java.util.Base64.getDecoder;
import static java.util.Base64.getEncoder;
import static javax.crypto.Cipher.getInstance;

@Service
public class AsymmetricEncryptionService {
    private KeyPair keyPair;

    public AsymmetricEncryptionService() throws NoSuchAlgorithmException {
        generateKeys();
    }

    public String generateKeys() throws NoSuchAlgorithmException {
        var keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        keyPair = keyPairGenerator.generateKeyPair();

        return exportKeys();
    }

    public String exportKeys() {
        var publicKey = getEncoder().encodeToString(keyPair.getPublic().getEncoded());
        var privateKey = getEncoder().encodeToString(keyPair.getPrivate().getEncoded());

        return "Public Key: " + publicKey + "\nPrivate Key: " + privateKey;
    }

    public String setKeys(String publicKeyStr, String privateKeyStr) {
        try {
            var publicKeyBytes = getDecoder().decode(publicKeyStr);
            var privateKeyBytes = getDecoder().decode(privateKeyStr);
            var publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
            var privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
            var keyFactory = KeyFactory.getInstance("RSA");
            var publicKey = keyFactory.generatePublic(publicKeySpec);
            var privateKey = keyFactory.generatePrivate(privateKeySpec);
            keyPair = new KeyPair(publicKey, privateKey);

            return "Keys updated successfully";
        } catch (Exception e) {
            throw new RuntimeException("Error setting keys", e);
        }
    }

    public String encrypt(String message) {
        try {
            var cipher = getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());
            var encryptedMessageBytes = cipher.doFinal(message.getBytes());

            return getEncoder().encodeToString(encryptedMessageBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting message", e);
        }
    }

    public String decrypt(String encryptedMessageJson) {
        try {
            var encryptedMessage = new JSONObject(encryptedMessageJson).getString("message");
            var cipher = getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());
            var encryptedMessageBytes = getDecoder().decode(encryptedMessage);
            var decryptedMessageBytes = cipher.doFinal(encryptedMessageBytes);

            return new String(decryptedMessageBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error decrypting message", e);
        }
    }


    public String sign(String message) {
        try {
            var signature = Signature.getInstance("SHA256withRSA");
            signature.initSign(keyPair.getPrivate());
            signature.update(message.getBytes());
            var signatureBytes = signature.sign();

            return getEncoder().encodeToString(signatureBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error signing message", e);
        }
    }

    public boolean verify(String message, String signatureStr) {
        try {
            var signature = Signature.getInstance("SHA256withRSA");
            signature.initVerify(keyPair.getPublic());
            signature.update(message.getBytes());
            var signatureBytes = getDecoder().decode(signatureStr);

            return signature.verify(signatureBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error verifying signature", e);
        }
    }
}