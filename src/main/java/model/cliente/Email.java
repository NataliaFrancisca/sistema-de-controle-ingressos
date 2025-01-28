package model.cliente;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email {
    public boolean validarEmail(String email){
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
