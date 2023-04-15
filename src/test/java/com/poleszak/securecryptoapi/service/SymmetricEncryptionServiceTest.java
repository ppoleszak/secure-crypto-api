package com.poleszak.securecryptoapi.service;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class SymmetricEncryptionServiceTest {

    @InjectMocks
    private SymmetricEncryptionService symmetricEncryptionService;

    @BeforeEach
    public void setUp() throws NoSuchAlgorithmException {
        symmetricEncryptionService.generateKey();
    }

    @Test
    public void generateKey_success() throws NoSuchAlgorithmException {
        //given //when
        var key = symmetricEncryptionService.generateKey();
        //then
        assertNotNull(key);
    }

    @Test
    public void exportKey_success() {
        //given //when
        var key = symmetricEncryptionService.exportKey();
        //then
        assertNotNull(key);
    }

    @Test
    public void setKey_success() {
        //given
        var key = symmetricEncryptionService.exportKey();
        //when
        var response = symmetricEncryptionService.setKey(key);
        //then
        assertEquals("Key updated successfully", response);
    }

    @Test
    public void encrypt_decrypt_success() throws JSONException {
        //given
        var message = "Test message";
        //when //then
        var encryptedMessage = symmetricEncryptionService.encrypt(message);

        //given
        var encryptedMessageJson = new JSONObject();
        encryptedMessageJson.put("message", encryptedMessage);
        //when
        var decryptedMessage = symmetricEncryptionService.decrypt(encryptedMessageJson.toString());
        //then
        assertEquals(message, decryptedMessage);
    }
}
