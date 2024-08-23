package main.java.com.example.encryption;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionUtils {

    // Convert SecretKey to a Base64 encoded string
    public static String encodeKey(SecretKey key) {
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }

    // Convert a Base64 encoded string back to SecretKey
    public static SecretKey decodeKey(String keyString) {
        byte[] decodedKey = Base64.getDecoder().decode(keyString);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    }

    public static SecretKey generateKey() throws Exception {
        // TODO Auto-generated method stub
        KeyGenerator keyGen  = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        return keyGen.generateKey();
    }
}