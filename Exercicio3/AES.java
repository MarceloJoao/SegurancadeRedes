import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

public class AES{
     
    //gerar a chave
    public static byte[] gerarchave(String chave) throws Exception{
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        return digest.digest(chave.getBytes("UTF-8"));
    }

    //completar a palavra
    public static String pad(String texto){
        int padding = 16 - (texto.length() % 16);
        return texto + String.valueOf((char) padding).repeat(padding);
    }

    public static String unpad(String texto) throws Exception {
        int padding = texto.charAt(texto.length() - 1);
        return texto.substring(0, texto.length() - padding);
    }
    
    //metodo para criptografar
    public static String Criptografar(String texto, String senha) throws Exception{
        //gerando a chave
         byte [] chave = gerarchave(senha);
         byte [] iv = new byte[16];
         new SecureRandom().nextBytes(iv);
         //selecionar o metodo de criptografia
         Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
         SecretKeySpec chaveprivada = new SecretKeySpec(chave, "AES");
         IvParameterSpec ivSpec = new IvParameterSpec(iv);
         //encriptar
         cipher.init(Cipher.ENCRYPT_MODE, chaveprivada, ivSpec);
         byte [] text = pad(texto).getBytes("UTF-8");
         byte [] criptografado = cipher.doFinal(text);

        byte[] ivMaisCriptografado = new byte[iv.length + criptografado.length];
        System.arraycopy(iv, 0, ivMaisCriptografado, 0, iv.length);
        System.arraycopy(criptografado, 0, ivMaisCriptografado, iv.length, criptografado.length);

        //convertendo para base64
        return Base64.getEncoder().encodeToString(ivMaisCriptografado);
    
    }

    public static String Descriptografar(String textocriptografado, String senha) throws Exception{
        byte [] dados = Base64.getDecoder().decode(textocriptografado);
        byte [] iv = new byte[16];
        byte [] criptografado = new byte[dados.length - 16];

        System.arraycopy(dados, 0, iv, 0, 16);
        System.arraycopy(dados, 16, criptografado, 0, criptografado.length);
        
        byte [] chave = gerarchave(senha);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec chaveprivada = new SecretKeySpec(chave, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
       
        //configurar para decriptar
        cipher.init(Cipher.DECRYPT_MODE, chaveprivada, ivSpec);
        //descriptogrfando 
        byte [] text = cipher.doFinal(criptografado);

        return unpad(new String(text, "UTF-8"));

    }


    public static void main(String[] args) {
        //usando os metodos na main
           
        try {
        String senha = "password";
        String mensagem = "Mensagem Original";

        System.out.println("Mensagem original: " + mensagem);

        String criptografado = Criptografar(mensagem, senha);
        System.out.println("Texto criptografado: " + criptografado);

        String descriptografado = Descriptografar(criptografado, senha);
        System.out.println("Texto descriptografado: " + descriptografado);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        

    }
}