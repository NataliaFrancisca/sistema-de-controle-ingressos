package model.cliente;

import br.com.caelum.stella.ValidationMessage;
import br.com.caelum.stella.validation.CPFValidator;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CPF {

    boolean validarCPF(String cpf) {
        String regex = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cpf);


        return matcher.matches();
    }

    boolean validarCPFUsandoBiblioteca(String cpf){
        CPFValidator cpfValidator = new CPFValidator();
        List<ValidationMessage> errors = cpfValidator.invalidMessagesFor(cpf);
        return errors.isEmpty();
    }
}
