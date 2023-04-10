package com.poleszak.securecryptoapi.dto;

public record VerificationRequest(String message,
                                  String signature) {
}
