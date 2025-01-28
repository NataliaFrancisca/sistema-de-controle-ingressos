package model.ingresso;

public class IngressoPrinter {
    public void imprimir(Ingresso ingresso){
        System.out.println("*** [ingresso: nº" + ingresso.getId() + "] ***");
        System.out.println("-----------------------------------------");
        System.out.println("[nome]: " + ingresso.getCliente().getNome());
        System.out.println("[cpf]: " + ingresso.getCliente().getCpf());
        System.out.println("-----------------------------------------");
        System.out.println("[evento]: " + ingresso.getEvento().getTitulo() + " | " + ingresso.getEvento().getDataEvento());
    }
}
