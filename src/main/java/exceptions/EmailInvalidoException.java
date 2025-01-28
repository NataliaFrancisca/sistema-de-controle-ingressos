package exceptions;

public class EmailInvalidoException extends IllegalArgumentException{
    public EmailInvalidoException(String mensagem){
        super(mensagem);
    }
}
