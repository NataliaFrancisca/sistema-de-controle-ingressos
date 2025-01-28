package model.ingresso;

import model.cliente.Cliente;
import model.evento.Evento;

import java.util.Objects;
import java.util.UUID;

public class Ingresso {
    private final UUID id;
    private final Evento evento;
    private final Cliente cliente;

    public Ingresso(Evento evento, Cliente cliente){
        Objects.requireNonNull(evento, "evento não pode ser nulo.");
        Objects.requireNonNull(cliente, "cliente não pode ser nulo.");

        this.id = UUID.randomUUID();
        this.evento = evento;
        this.cliente = cliente;
    }

    public UUID getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Evento getEvento() {
        return evento;
    }
}
