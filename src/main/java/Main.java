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
        // instância da classe para imprimir ingressos
        IngressoPrinter impressoraDeIngressos = new IngressoPrinter();

        // criação dos clientes
        Cliente cliente = criarCliente("Natalia", "nat@mail.com", "222.444.666-88");
        Cliente cliente1 = criarCliente("Luiz", "luiz@mail.com", "222.333.444-55");

        // criação do evento
        Evento evento = criarEvento(
                "Copa Vôlei Feminino - Final",
                "Escola Vôlei Fernanda Garay",
                "Rua Carlos Almeida Fernandes, n 120, Ibirapuera - São Paulo, SP",
                LocalDateTime.of(2025, 2, 25, 21, 0, 0)
        );

        // ativação dos ingressos
        if (!Objects.isNull(evento)) {
            try {
                evento.ativarIngressos(2, LocalDateTime.of(2025, 1, 29, 0, 0, 0), 2);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        // emissão e impressão dos ingressos
        if (!Objects.isNull(cliente) && !Objects.isNull(evento)) {
            try {
                Ingresso ingressoCliente1 = evento.emitirIngresso(cliente);
                Ingresso ingressoCliente2 = evento.emitirIngresso(cliente1);

                impressoraDeIngressos.imprimir(ingressoCliente1);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static Cliente criarCliente(String nome, String email, String cpf){
        try{
            return ClienteUtils.criarCliente(nome, email, cpf);
        }catch (Exception e){
            System.out.println("Erro ao criar cliente: " + e.getMessage());
            return null;
        }
    }

    private static Evento criarEvento(String nome, String local, String endereco, LocalDateTime data){
        try{
            return EventoUtils.criarEvento(nome, local, endereco, data);
        }catch (Exception e){
            System.out.println("Erro ao criar evento: " + e.getMessage());
            return null;
        }
    }
}
