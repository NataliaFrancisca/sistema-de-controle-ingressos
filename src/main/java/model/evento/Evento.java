package model.evento;

import exceptions.EventoInvalidoException;
import model.cliente.Cliente;
import model.ingresso.Ingresso;
import model.ingresso.Ingressos;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Evento {
    private final UUID id;
    private String titulo;
    private String organizador;
    private String endereco;
    private LocalDateTime dataEvento;
    private Ingressos ingressos;
    private boolean statusDistribuicaoIngressos;

    public Evento(String titulo, String organizador, String endereco, LocalDateTime dataEvento) {
        Objects.requireNonNull(titulo, "título não pode ser nulo.");
        Objects.requireNonNull(organizador, "organizador não pode ser nulo.");
        Objects.requireNonNull(endereco, "endereço não pode ser nulo.");
        Objects.requireNonNull(dataEvento, "data do evento não pode ser nula.");


        if(titulo.isEmpty()){
            throw new EventoInvalidoException("título do evento não pode ser vazio.");
        }

        if(organizador.isEmpty()){
            throw new EventoInvalidoException("organizador do evento não pode ser vazio.");
        }

        if(endereco.isEmpty()){
            throw new EventoInvalidoException("endereço do evento não pode ser vazio");
        }

        if(dataEvento.isBefore(LocalDateTime.now())){
            throw new EventoInvalidoException("data do evento deve ser no futuro.");
        }

        this.id = UUID.randomUUID();
        this.titulo = titulo;
        this.organizador = organizador;
        this.endereco = endereco;
        this.dataEvento = dataEvento;
        this.statusDistribuicaoIngressos = false;
    }

    public void ativarIngressos(int numeroDeIngressosDisponiveis, LocalDateTime dataAbertura, int numeroDeDiasParaEncerrar){
        Objects.requireNonNull(dataAbertura, "data de abertura não pode ser nula.");

        if(dataAbertura.isAfter(this.dataEvento)){
            throw new EventoInvalidoException("data de abertura de ingressos não pode ser depois do evento.");
        }

        this.ingressos = new Ingressos(numeroDeIngressosDisponiveis);
        this.ingressos.setDataAberturaParaAdquirirIngresso(dataAbertura);
        this.ingressos.setDataEncerramentoParaAdquirirIngresso(numeroDeDiasParaEncerrar);
        this.statusDistribuicaoIngressos = true;
    }

    public Ingresso emitirIngresso(Cliente cliente){
        Objects.requireNonNull(cliente, "cliente não pode ser nulo.");

        if(!this.statusDistribuicaoIngressos){
            throw new RuntimeException("não é possível emitir ingressos no momento.");
        };

        if(this.ingressos == null){
            throw new RuntimeException("ingressos não foram configurados para este evento.");
        }

        return this.ingressos.emitirIngresso(cliente, this);
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDateTime getDataEvento() {
        return dataEvento;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", organizador='" + organizador + '\'' +
                ", endereco='" + endereco + '\'' +
                ", dataEvento=" + dataEvento +
                ", ingressos=" + ingressos +
                ", statusDistribuicaoIngressos=" + statusDistribuicaoIngressos +
                '}';
    }
}
