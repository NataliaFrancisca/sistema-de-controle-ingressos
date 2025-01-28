import model.cliente.Cliente;
import model.evento.Evento;
import model.ingresso.Ingresso;
import model.ingresso.IngressoPrinter;
import utils.ClienteUtils;
import utils.EventoUtils;

import java.time.LocalDateTime;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        IngressoPrinter ingressoPrinter = new IngressoPrinter();
        Cliente cliente = null;
        Evento evento = null;

        try{
            cliente = ClienteUtils.criarCliente("Natalia", "nat@mail.com", "222.333.444-33");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        try{
            evento = EventoUtils.criarEvento(
                    "Copa Vôlei Feminino - Final",
                    "Escola Vôlei Fernanda Garay",
                    "Rua Carlos Almeida Fernandes, n 120, Ibirapuera - São Paulo, SP",
                    LocalDateTime.of(2025,2, 25, 21, 0,0)
            );

            evento.ativarIngressos(2, LocalDateTime.of(2025, 1, 28, 0,0,0), 2);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        if(!Objects.isNull(cliente) && !Objects.isNull(evento)){
            Ingresso ingresso = evento.emitirIngresso(cliente);
            ingressoPrinter.imprimir(ingresso);
        }


    }
}
