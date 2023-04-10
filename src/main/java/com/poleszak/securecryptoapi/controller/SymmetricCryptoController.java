package com.poleszak.securecryptoapi.controller;

import com.poleszak.securecryptoapi.service.SymmetricEncryptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/symmetric")
public class SymmetricCryptoController {

    private final SymmetricEncryptionService symmetricEncryptionService;

    @GetMapping("/key")
    public ResponseEntity<String> generateKey() throws NoSuchAlgorithmException {
        return ResponseEntity.ok(symmetricEncryptionService.generateKey());
    }

    @PostMapping("/key")
    public ResponseEntity<String> setKey(@RequestBody String key) {
        return ResponseEntity.ok(symmetricEncryptionService.setKey(key));
    }

    @PostMapping("/encode")
    public ResponseEntity<String> encrypt(@RequestBody String message) {
        return ResponseEntity.ok(symmetricEncryptionService.encrypt(message));
    }

    @PostMapping("/decode")
    public ResponseEntity<String> decrypt(@RequestBody String encryptedMessage) {
        return ResponseEntity.ok(symmetricEncryptionService.decrypt(encryptedMessage));
    }
}