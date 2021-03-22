package be.dog.d.steven.exam1Z0_816and1Z0_817.chapter11;

import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidParameterSpecException;

public class Extra {
    private static final String password = "Secure123!";

    public static void main(String[] args) throws
            NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, BadPaddingException,
            IllegalBlockSizeException, InvalidAlgorithmParameterException,
            InvalidParameterSpecException {

        SecretKey key = KeyGenerator.getInstance("AES").generateKey();
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] value = password.getBytes();
        byte[] encryptedValue = cipher.doFinal(value);
        GCMParameterSpec ps = cipher.getParameters().getParameterSpec(GCMParameterSpec.class);
        cipher.init(Cipher.DECRYPT_MODE, key, ps);
        byte[] decryptedValue = cipher.doFinal(encryptedValue);

        System.out.println(new String(decryptedValue, StandardCharsets.UTF_8));
    }
}
