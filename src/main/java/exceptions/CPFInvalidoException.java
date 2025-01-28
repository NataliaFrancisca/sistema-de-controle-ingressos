package exceptions;

public class CPFInvalidoException extends IllegalArgumentException{
    public CPFInvalidoException(){
        super("digite um cpf válido (xxx.xxx.xxx-xx)");
    }
}
