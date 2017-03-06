/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.Base64;
/**
 *
 * @author sid
 */
public class PasswordUtil {
    public static String hashPassword(String password)
            throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.reset();
            md.update(password.getBytes());
            byte[] mdArray = md.digest();
            StringBuilder sb = new StringBuilder(mdArray.length * 2);
            for (byte b : mdArray) {
                int v = b & 0xff;
                        if (v < 16) {
                            sb.append('0');
                        }
            
            sb.append(Integer.toHexString(v));
        }
    return sb.toString();
    }
     public static String getSalt() {
         Random r = new SecureRandom();
         byte[] saltBytes = new byte[32];
         r.nextBytes(saltBytes);
         return Base64.getEncoder().encodeToString(saltBytes);
     }
     public static String hashAndSaltPassword(String password)
             throws NoSuchAlgorithmException {
         String salt = getSalt();
         return hashPassword(password + salt);
     }
     
 /*  This code tests the functionality of this class.    */
     public static void main(String[] args) {
         try {
             System.out.println("Hash for 'sesame'       : "
                     + hashPassword("sesame"));
             System.out.println("Random salt             : "
                     + getSalt());
             System.out.println("Salted hash for 'sesame': "
                     + hashAndSaltPassword("sesame"));
             
         } catch (NoSuchAlgorithmException ex) {
             System.out.println(ex);
         }
     }
}