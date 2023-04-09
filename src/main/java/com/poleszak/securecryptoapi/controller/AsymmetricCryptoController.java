package com.poleszak.securecryptoapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/crypto/asymmetric")
public class AsymmetricCryptoController {

    private AsymmetricEncryptionService asymmetricEncryptionService;

    @GetMapping("/key")
    public ResponseEntity<Map<String, String>> getAsymmetricKey() {
        Map<String, String> keyPair = asymmetricEncryptionService.generateKeyPair();
        return ResponseEntity.ok(keyPair);
    }

    @PostMapping("/key")
    public ResponseEntity<Void> setAsymmetricKey(@RequestBody Map<String, String> keyPair) {
        asymmetricEncryptionService.setKeyPair(keyPair);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifyAsymmetric(@RequestBody String message) {
        String signedMessage = asymmetricEncryptionService.sign(message);
        return ResponseEntity.ok(signedMessage);
    }

    @PostMapping("/sign")
    public ResponseEntity<Boolean> signAsymmetric(@RequestBody Map<String, String> request) {
        String message = request.get("message");
        String signature = request.get("signature");
        boolean isVerified = asymmetricEncryptionService.verify(message, signature);
        return ResponseEntity.ok(isVerified);
    }

    @PostMapping("/encode")
    public ResponseEntity<String> encodeAsymmetric(@RequestBody String message) {
        String encoded = asymmetricEncryptionService.encode(message);
        return ResponseEntity.ok(encoded);
    }

    @PostMapping("/decode")
    public ResponseEntity<String> decodeAsymmetric(@RequestBody String message) {
        String decoded = asymmetricEncryptionService.decode(message);
        return ResponseEntity.ok(decoded);
    }
}
