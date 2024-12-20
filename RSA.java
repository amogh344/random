import java.security.*;
import javax.crypto.Cipher;
import java.math.BigInteger;

public class RSA
{
    public static void main(String[] args) throws Exception
    {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair pair = keyGen.generateKeyPair();

        String msg = "Hello, this is a test message for RSA Encryption";
        System.out.println("Message: " + msg);
        Cipher cipher = Cipher.getInstance("RSA");

        // Encrypt the message
        cipher.init(Cipher.ENCRYPT_MODE, pair.getPublic());
        byte[] encrypted = cipher.doFinal(msg.getBytes());

        // Convert encrypted byte array to hex using BigInteger
        String encryptedHex = new BigInteger(1, encrypted).toString(16);
        System.out.println("Encrypted: " + encryptedHex);

        // Decrypt the message
        cipher.init(Cipher.DECRYPT_MODE, pair.getPrivate());
        byte[] decrypted = cipher.doFinal(encrypted);
        System.out.println("Decrypted: " + new String(decrypted));
    }
}
