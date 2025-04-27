import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

//classe para criptografar usando o DES
public class Criptografar {
    private SecretKeySpec keySpec;

    // Construtor
    public Criptografar(String key) {
        byte[] keyBytes = key.substring(0, 8).getBytes();
        this.keySpec = new SecretKeySpec(keyBytes, "DES");
    }

    // Método de criptografar
    public String criptografar(String plaintext) throws Exception {
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] criptografando = cipher.doFinal(plaintext.getBytes());
        return Base64.getEncoder().encodeToString(criptografando);
    }

    public static void main(String[] args) {
        String key = "password";
        Criptografar des = new Criptografar(key);
        String textoclaro = "Hello, World!";

        
        try {
            String criptografado = des.criptografar(textoclaro);
            System.out.println("Texto Criptografado: " + criptografado);
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }
}
