package exceptions;

public class IngressoEsgotadoException extends IllegalArgumentException{
    public IngressoEsgotadoException(String mensagem){
        super(mensagem);
    }
}
