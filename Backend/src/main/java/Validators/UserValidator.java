
package Validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UserValidator {
    public static boolean validateEmail(String email){
        
        Pattern pattern = Pattern.compile("([a-z0-9]+(\\.?[a-z0-9])*)+@(([a-z]+)\\.([a-z]+))+");
        Matcher  matcher = pattern.matcher(email);
        return matcher.find();
    }
    
    public static boolean validatePassword(String password){
        Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,16}$");
        Matcher  matcher = pattern.matcher(password);
        return matcher.find();
    }
}
