package com.poleszak.securecryptoapi.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SymmetricCryptoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private String generatedKey;

    @BeforeEach
    public void setUp() throws Exception {
        generatedKey = mockMvc.perform(get("/symmetric/key"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    @Test
    public void generateKey_success() throws Exception {
        mockMvc.perform(get("/symmetric/key"))
                .andExpect(status().isOk())
                .andExpect(content().string(not(Matchers.emptyString())));
    }

    @Test
    public void setKey_success() throws Exception {
        mockMvc.perform(post("/symmetric/key")
                        .contentType("text/plain")
                        .content(generatedKey))
                .andExpect(status().isOk())
                .andExpect(content().string("Key updated successfully"));
    }

    @Test
    public void encrypt_decrypt_success() throws Exception {
        var testMessage = "This is a test message.";

        // Encrypt the message
        var encryptedMessage = mockMvc.perform(post("/symmetric/encode")
                        .contentType(APPLICATION_JSON)
                        .content(testMessage))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        // Decrypt the message
        var decryptedMessage = mockMvc.perform(post("/symmetric/decode")
                        .contentType(APPLICATION_JSON)
                        .content("{\"message\": \"" + encryptedMessage + "\"}"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertEquals(testMessage, decryptedMessage);
    }
}