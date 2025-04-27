import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

//classe para descriptografar usando DES
public class DES_Descriptografando{
    private SecretKeySpec keySpec;

    //construtor
    public DES_Descriptografando(String key){
        byte[] keyBytes = key.substring(0, 8).getBytes();
        this.keySpec = new SecretKeySpec(keyBytes, "DES");
    }

    public String DesCriptografar(String textocriptografado) throws Exception{
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        byte[] decodificandoBytes = Base64.getDecoder().decode(textocriptografado);
        byte[] decodificaBytes = cipher.doFinal(decodificandoBytes);
        return new String(decodificaBytes);
    }

    public static void main(String[] args) {
        String key = "password";
        DES_Descriptografando des = new DES_Descriptografando(key);

        String criptografado = "oZrq7d/d7uyIDioPY1JJsw==";

        try {
            String resultado = des.DesCriptografar(criptografado);
            System.out.println("O texto claro é: " + resultado);
        } catch (Exception e) {
            e.printStackTrace(); 
        }

    }

}
