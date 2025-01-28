package model.cliente;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CPF {

    boolean validarCPF(String cpf) {
        String regex = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cpf);
        return matcher.matches();
    }
}
