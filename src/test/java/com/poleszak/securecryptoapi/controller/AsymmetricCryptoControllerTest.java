package com.poleszak.securecryptoapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poleszak.securecryptoapi.dto.KeysDto;
import com.poleszak.securecryptoapi.dto.VerificationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AsymmetricCryptoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void generateKeysTest() throws Exception {
        mockMvc.perform(get("/asymmetric/key"))
                .andExpect(status().isOk());
    }

    @Test
    public void setKeysTest() throws Exception {
        KeysDto keys = new KeysDto("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAj84lZjgjDF/SIoz7X6VyApWKd/kwkafkrZjq8MtBC/ygEqEi8vSykBuJbkHrSnqj33EnxLrtiV6dkfq1jwdKBUNGHTjk0qOHEpOHgEYT+1QMMcdi6TEeaHoYi9QJAqpkNAUZUrOzd8KRHwyduc2xGUVrpMpe/jEPD/aVzluKXzYcC967LP9ohBqd3CfxelgdOs3JIR+t7RUwIxqZFhcACUrHVP4yBMrbX1sulFWmppFehWGzrKOUeN1FNfbS3RB220Lyhhor/A18ucZUMmYYJQgujsOsbgoMyGCmb9NYmujmrXSTX5o5PN1tJXXJg6o4kAG0dN0j8sWbtg6GonJi3QIDAQAB",
                "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCPziVmOCMMX9IijPtfpXIClYp3+TCRp+StmOrwy0EL/KASoSLy9LKQG4luQetKeqPfcSfEuu2JXp2R+rWPB0oFQ0YdOOTSo4cSk4eARhP7VAwxx2LpMR5oehiL1AkCqmQ0BRlSs7N3wpEfDJ25zbEZRWukyl7+MQ8P9pXOW4pfNhwL3rss/2iEGp3cJ/F6WB06zckhH63tFTAjGpkWFwAJSsdU/jIEyttfWy6UVaamkV6FYbOso5R43UU19tLdEHbbQvKGGiv8DXy5xlQyZhglCC6Ow6xuCgzIYKZv01ia6OatdJNfmjk83W0ldcmDqjiQAbR03SPyxZu2DoaicmLdAgMBAAECggEAAgNjT6aKixJOWVB69nBVA8c/LIXPEcL1dx60b1GRJA9R2GDsIdWeKFlSV7pRhnXoFGk7osvxJmKuNwx4/TsfZKp38VHKFk7WxcJN+9Q/VF3tW8cMUfJvmAvZix+ZTrG+/GFz5M4CPbN6H6KoFKFWEOIBa0mwAcQDD3imyUBL1CBi7Mru8YyyuqJBahv5io/WCnz+GAJwRN+7SiaIWRyeWRuRNJEd2pH4tfAPoYzK+RBm2MMBK7KO6u/sc6l2FO8E6Lhifd2mENsCBxiELtSG+0uiTgLl/5nGckWuuxpZs44pvoZKO3QHVpbpsrWRBXWWJro7THUj2ccAhvtG6eXfgQKBgQDHZs+9tMmFngCKXh72Oo8W/CHc278VpZ+4s3aUNEnXHJ5Be0RM1+0+9TiXm+bVVAhivJUUoEhhsom/EQS41uOU+zb4z+MOwR/mMLatGQZt8yqJwGtXI+Ke0DysNCUfgerljWoIyfNtVHO7jAtGWcffNpNwBUGpFobliJ6bDt+WwQKBgQC4n4SH3icFQ9tIiy4MicJwUjGM2I0FSdSQ/827mWe/ZGWQjiliroTN9z4xhBpoc36LkrYKIp0necegfNxo4FcO3rWPVeofvX0JUqPfpK9glU3ID7fk/BeDKVn3gLcRFa6KNe9D7MWvsaex+9YCh1NSJZDbwELOK6q/HFUGLIkPHQKBgQDG2Q0zxnTsrZsmYSQJMm59YmQ2hmExKiB0pWsL8nTscpqS/GUpKG9ryEluT0dL1+gg5qTYk9p6qvvYsRT/azQ63qZ8S3vHu+meuVeCmSEjRkpWYq/oj12wOEM5Cys2F23zCyoBp7r8fSqIjei0eFXsj9VJ8cP/5foSLkLUqGlqQQKBgEuWg9xcx/0P2yfIVAIuJL136XGQ96vCnpT/Jmw4UpG5uRuGGMueSCFLqNr9CxLYtmbOmllr8ovHAERk2pjVwM8Fv6//rOtx2U+H1jAqbONOPZo7VT8bN0UsGPmMaWdFSUCs9FkLB6T3R4FHakd+wE5Rsw1FmhRvLGkyYF02vqbBAoGBAMGsOvNerLaJnlm9Tf1O/kZG6JrRUFmiObCIucik3jOi/D/fQmexkyBB1CzFbqyhG3pfwTJATaZFKBnMOjN97v2HlMy6PouXLvD3uskde95Qs7DF+bKxLLrdQEVVjPCUpFcEW8HE1j75BUK7r2rPenEMyaboE5XfpYJ0HSivkXeS");


        mockMvc.perform(post("/asymmetric/key")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(keys)))
                .andExpect(status().isOk());
    }

    @Test
    public void verifyTest() throws Exception {
        var validSignature = "3082010a0282010100b6fb4b6c4d6d4f6a3590ed693a8b176aee6d7589351e2bb601fb38ff1f31c603bc245d6c40b95e9ac" +
                "9e93a1fde660b5c5e5e5a618f5a7d5e38a5a2b9e5e5d55a5a5a5a5a55a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5" +
                "a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5a5";
        VerificationRequest verificationRequest = new VerificationRequest("exampleMessage", validSignature);

        mockMvc.perform(post("/asymmetric/verify")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(verificationRequest)))
                .andExpect(status().isOk());
    }


    @Test
    public void encryptTest() throws Exception {
        String message = "exampleMessage";

        mockMvc.perform(post("/asymmetric/encode")
                        .contentType("application/json")
                        .content(message))
                .andExpect(status().isOk());
    }
}