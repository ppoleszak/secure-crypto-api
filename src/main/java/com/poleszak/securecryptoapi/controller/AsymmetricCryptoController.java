package com.poleszak.securecryptoapi.controller;

import com.poleszak.securecryptoapi.dto.KeysDto;
import com.poleszak.securecryptoapi.dto.VerificationRequest;
import com.poleszak.securecryptoapi.service.AsymmetricEncryptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/asymmetric")
public class AsymmetricCryptoController {

    private final AsymmetricEncryptionService asymmetricEncryptionService;

    @GetMapping("/key")
    public ResponseEntity<String> generateKeys() throws NoSuchAlgorithmException {
        return ResponseEntity.ok(asymmetricEncryptionService.generateKeys());
    }

    @PostMapping("/key")
    public ResponseEntity<String> setKeys(@RequestBody KeysDto keys) {
        return ResponseEntity.ok(asymmetricEncryptionService.setKeys(keys.publicKey(), keys.privateKey()));
    }

    @PostMapping("/verify")
    public ResponseEntity<Boolean> verify(@RequestBody VerificationRequest verificationRequest) {
        return ResponseEntity.ok(asymmetricEncryptionService.verify(verificationRequest.message(), verificationRequest.signature()));
    }

    @PostMapping("/sign")
    public ResponseEntity<String> sign(@RequestBody String message) {
        return ResponseEntity.ok(asymmetricEncryptionService.sign(message));
    }

    @PostMapping("/encode")
    public ResponseEntity<String> encrypt(@RequestBody String message) {
        return ResponseEntity.ok(asymmetricEncryptionService.encrypt(message));
    }

    @PostMapping("/decode")
    public ResponseEntity<String> decrypt(@RequestBody String encryptedMessage) {
        return ResponseEntity.ok(asymmetricEncryptionService.decrypt(encryptedMessage));
    }
}