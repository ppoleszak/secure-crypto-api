package com.poleszak.securecryptoapi.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.NoSuchAlgorithmException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class AsymmetricEncryptionServiceTest {

    @InjectMocks
    private AsymmetricEncryptionService asymmetricEncryptionService;

    @Test
    void generateKeysTest() throws NoSuchAlgorithmException {
        //given //when
        var keys = asymmetricEncryptionService.generateKeys();

        //then
        assertThat(keys).isNotEmpty();
    }

    @Test
    void setKeysTest() {
        //given
        var publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAj84lZjgjDF/SIoz7X6VyApWKd/kwkafkrZjq8MtBC/ygEqEi" +
                "8vSykBuJbkHrSnqj33EnxLrtiV6dkfq1jwdKBUNGHTjk0qOHEpOHgEYT+1QMMcdi6TEeaHoYi9QJAqpkNAUZUrOzd8KRHwyduc2xGUVr" +
                "pMpe/jEPD/aVzluKXzYcC967LP9ohBqd3CfxelgdOs3JIR+t7RUwIxqZFhcACUrHVP4yBMrbX1sulFWmppFehWGzrKOUeN1FNfbS3RB" +
                "220Lyhhor/A18ucZUMmYYJQgujsOsbgoMyGCmb9NYmujmrXSTX5o5PN1tJXXJg6o4kAG0dN0j8sWbtg6GonJi3QIDAQAB";
        var privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCPziVmOCMMX9IijPtfpXIClYp3+TCRp+StmOrwy0EL" +
                "/KASoSLy9LKQG4luQetKeqPfcSfEuu2JXp2R+rWPB0oFQ0YdOOTSo4cSk4eARhP7VAwxx2LpMR5oehiL1AkCqmQ0BRlSs7N3wpEfDJ25" +
                "zbEZRWukyl7+MQ8P9pXOW4pfNhwL3rss/2iEGp3cJ/F6WB06zckhH63tFTAjGpkWFwAJSsdU/jIEyttfWy6UVaamkV6FYbOso5R43UU" +
                "19tLdEHbbQvKGGiv8DXy5xlQyZhglCC6Ow6xuCgzIYKZv01ia6OatdJNfmjk83W0ldcmDqjiQAbR03SPyxZu2DoaicmLdAgMBAAECggE" +
                "AAgNjT6aKixJOWVB69nBVA8c/LIXPEcL1dx60b1GRJA9R2GDsIdWeKFlSV7pRhnXoFGk7osvxJmKuNwx4/TsfZKp38VHKFk7WxcJN+9Q" +
                "/VF3tW8cMUfJvmAvZix+ZTrG+/GFz5M4CPbN6H6KoFKFWEOIBa0mwAcQDD3imyUBL1CBi7Mru8YyyuqJBahv5io/WCnz+GAJwRN+7SiaI" +
                "WRyeWRuRNJEd2pH4tfAPoYzK+RBm2MMBK7KO6u/sc6l2FO8E6Lhifd2mENsCBxiELtSG+0uiTgLl/5nGckWuuxpZs44pvoZKO3QHVpbps" +
                "rWRBXWWJro7THUj2ccAhvtG6eXfgQKBgQDHZs+9tMmFngCKXh72Oo8W/CHc278VpZ+4s3aUNEnXHJ5Be0RM1+0+9TiXm+bVVAhivJUUoE" +
                "hhsom/EQS41uOU+zb4z+MOwR/mMLatGQZt8yqJwGtXI+Ke0DysNCUfgerljWoIyfNtVHO7jAtGWcffNpNwBUGpFobliJ6bDt+WwQKBgQC" +
                "4n4SH3icFQ9tIiy4MicJwUjGM2I0FSdSQ/827mWe/ZGWQjiliroTN9z4xhBpoc36LkrYKIp0necegfNxo4FcO3rWPVeofvX0JUqPfpK9gl" +
                "U3ID7fk/BeDKVn3gLcRFa6KNe9D7MWvsaex+9YCh1NSJZDbwELOK6q/HFUGLIkPHQKBgQDG2Q0zxnTsrZsmYSQJMm59YmQ2hmExKiB0pWs" +
                "L8nTscpqS/GUpKG9ryEluT0dL1+gg5qTYk9p6qvvYsRT/azQ63qZ8S3vHu+meuVeCmSEjRkpWYq/oj12wOEM5Cys2F23zCyoBp7r8fSqIj" +
                "ei0eFXsj9VJ8cP/5foSLkLUqGlqQQKBgEuWg9xcx/0P2yfIVAIuJL136XGQ96vCnpT/Jmw4UpG5uRuGGMueSCFLqNr9CxLYtmbOmllr8ovHA" +
                "ERk2pjVwM8Fv6//rOtx2U+H1jAqbONOPZo7VT8bN0UsGPmMaWdFSUCs9FkLB6T3R4FHakd+wE5Rsw1FmhRvLGkyYF02vqbBAoGBAMGsOvNe" +
                "rLaJnlm9Tf1O/kZG6JrRUFmiObCIucik3jOi/D/fQmexkyBB1CzFbqyhG3pfwTJATaZFKBnMOjN97v2HlMy6PouXLvD3uskde95Qs7DF+bKx" +
                "LLrdQEVVjPCUpFcEW8HE1j75BUK7r2rPenEMyaboE5XfpYJ0HSivkXeS";

        //when
        var result = asymmetricEncryptionService.setKeys(publicKey, privateKey);

        //then
        assertThat(result).isEqualTo("Keys updated successfully");
    }

    @Test
    void setKeysErrorTest() {
        //given
        var publicKey = "invalid_public_key";
        var privateKey = "invalid_private_key";

        //when //then
        assertThatThrownBy(() -> asymmetricEncryptionService.setKeys(publicKey, privateKey))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Error setting keys");
    }

    @Test
    void encryptDecryptTest() {
        //given
        var message = "test_message";
        //when
        var encryptedMessage = asymmetricEncryptionService.encrypt(message);
        //then
        assertThat(encryptedMessage).isNotEmpty();

        //given
        var encryptedMessageJson = String.format("{\"message\": \"%s\"}", encryptedMessage);
        //when
        var decryptedMessage = asymmetricEncryptionService.decrypt(encryptedMessageJson);
        //then
        assertThat(decryptedMessage).isEqualTo(message);
    }

    @Test
    void decryptErrorTest() {
        //given
        var encryptedMessageJson = "{\"message\": \"invalid_encrypted_message\"}";

        //when //then
        assertThatThrownBy(() -> asymmetricEncryptionService.decrypt(encryptedMessageJson))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Error decrypting message");
    }

    @Test
    void signVerifyTest() {
        //given
        var message = "test_message";
        var signature = asymmetricEncryptionService.sign(message);
        assertThat(signature).isNotEmpty();

        //when
        var isVerified = asymmetricEncryptionService.verify(message, signature);

        //then
        assertThat(isVerified).isTrue();
    }

    @Test
    void verifyErrorTest() {
        //given
        var message = "test_message";
        var signature = "invalid_signature";

        //when //then
        assertThatThrownBy(() -> asymmetricEncryptionService.verify(message, signature))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Error verifying signature");
    }
}