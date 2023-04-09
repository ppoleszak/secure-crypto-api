package com.poleszak.securecryptoapi.service;

@Service
public class AsymmetricEncryptionService {

    private KeyPair keyPair;

    public Map<String, String> generateKeyPair() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048);
            keyPair = keyGen.generateKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            Map<String, String> keys = new HashMap<>();
            keys.put("publicKey", Base64.getEncoder().encodeToString(publicKey.getEncoded()));
            keys.put("privateKey", Base64.getEncoder().encodeToString(privateKey.getEncoded()));

            return keys;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating key pair", e);
        }
    }

    public void setKeyPair(Map<String, String> keyPair) {
        try {
            byte[] publicKeyBytes = Base64.getDecoder().decode(keyPair.get("publicKey"));
            byte[] privateKeyBytes = Base64.getDecoder().decode(keyPair.get("privateKey"));

            PublicKey publicKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(publicKeyBytes));
            PrivateKey privateKey = KeyFactory.getInstance("RSA").generatePrivate(new X509EncodedKeySpec(privateKeyBytes));

            this.keyPair = new KeyPair(publicKey, privateKey);
        } catch (Exception e) {
            throw new RuntimeException("Error setting key pair", e);
        }
    }

    public String sign(String message) {
        try {
            Signature privateSignature = Signature.getInstance("SHA256")
