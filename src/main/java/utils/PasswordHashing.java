package utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;

public class PasswordHashing {

    private static final String SECRET_KEY
            = "my_super_secret_key_for_facebook";

    private static final String SALT = "ssshhhhhhhhhhh!!!!";

    public static String encryptPassword(String password) {
        try {
            byte[] iv = new byte[16];
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

            KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);

            return Base64.getEncoder().encodeToString(cipher.doFinal(password.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.getMessage());
        }
        return null;
    }

    public static String decryptPassword(String encryptedString) {
        try {
            byte[] iv = new byte[16];
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(),65536, 256);

            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);

            return new String(cipher.doFinal( Base64.getDecoder().decode(encryptedString)));
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.getMessage());
        }
        return null;
    }
}
