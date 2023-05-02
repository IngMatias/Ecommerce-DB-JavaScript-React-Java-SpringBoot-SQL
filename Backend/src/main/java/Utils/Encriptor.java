/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author giova
 */
public class Encriptor {
    public static String encrypt(String text){
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("MD5");
            byte[] hash = digest.digest(text.getBytes(StandardCharsets.UTF_8));
            text = new String(hash, StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Password Could Not Be Encrypted");
        }
        return text;
    }
}
