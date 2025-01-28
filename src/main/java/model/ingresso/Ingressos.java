package model.ingresso;

import exceptions.ClienteJaPossuiIngressoException;
import exceptions.IngressoEsgotadoException;
import exceptions.PeriodoVendaIngressosException;
import model.cliente.Cliente;
import model.evento.Evento;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;

public class Ingressos {
    private HashSet<Cliente> listaDeIngressosAdquiridos = new HashSet<>();
    private int numeroDeIngressosDisponiveis;
    private LocalDateTime dataAberturaParaAdquirirIngresso;
    private LocalDateTime dataEncerramentoParaAdquirirIngresso;

    public Ingressos(int numeroDeIngressosDisponiveis){
        if(numeroDeIngressosDisponiveis <= 0){
            throw new IllegalArgumentException("número de ingressos disponíveis deve ser maior que zero.");
        }

        this.numeroDeIngressosDisponiveis = numeroDeIngressosDisponiveis;
    }

    public Ingresso emitirIngresso(Cliente cliente, Evento evento) throws IngressoEsgotadoException, PeriodoVendaIngressosException, ClienteJaPossuiIngressoException{
        Objects.requireNonNull(cliente, "cliente não pode ser nulo.");
        Objects.requireNonNull(evento, "evento não pode ser nulo.");

        if(listaDeIngressosAdquiridos.contains(cliente)){
            throw new ClienteJaPossuiIngressoException("cliente já emitiu ingresso.");
        }

        verificarPeriodoParaAdquirirIngressos();
        verificarDisponibilidadeIngressos();

        listaDeIngressosAdquiridos.add(cliente);
        System.out.println("[aviso]: Olá, " + cliente.getNome().toLowerCase() + ". Seu ingresso foi emitido com sucesso.");
        return new Ingresso(evento, cliente);
    }

    public void verificarPeriodoParaAdquirirIngressos() throws PeriodoVendaIngressosException {
        LocalDateTime dataAtual = LocalDateTime.now().plusDays(2);

        if(this.dataAberturaParaAdquirirIngresso == null || this.dataEncerramentoParaAdquirirIngresso == null){
            throw new PeriodoVendaIngressosException("perído de venda de ingressos não foi definido.");
        }

        if(dataAtual.isBefore(dataAberturaParaAdquirirIngresso)){
            throw new PeriodoVendaIngressosException("venda de ingressos ainda não iniciou.");
        }

        if(dataAtual.isAfter(dataEncerramentoParaAdquirirIngresso)){
            throw new PeriodoVendaIngressosException("venda de ingressos já encerrou.");
        }
    }

    private void verificarDisponibilidadeIngressos() throws IngressoEsgotadoException {

        if(this.listaDeIngressosAdquiridos.size() >= this.numeroDeIngressosDisponiveis){
            throw new IngressoEsgotadoException("ingressos esgotados.");
        }
    }

    public void setDataAberturaParaAdquirirIngresso(LocalDateTime dataAberturaParaAdquirirIngressos){
        Objects.requireNonNull(dataAberturaParaAdquirirIngressos, "data abertura não pode ser nula.");

        LocalDateTime dataAtual = LocalDateTime.now();
        if(dataAberturaParaAdquirirIngressos.isBefore(dataAtual)){
            throw new IllegalArgumentException("data de abertura deve ser no futuro.");
        }

        this.dataAberturaParaAdquirirIngresso = dataAberturaParaAdquirirIngressos;
    }

    public void setDataEncerramentoParaAdquirirIngresso(int numeroDeDiasParaEncerramento){
        if(this.dataAberturaParaAdquirirIngresso == null){
            throw new IllegalArgumentException("defina a data de abertura antes de definir a data de encerramento.");
        }

        if(numeroDeDiasParaEncerramento <= 0){
            throw new IllegalArgumentException("número de dias para encerramento deve ser maior que zero.");
        }

        this.dataEncerramentoParaAdquirirIngresso = this.dataAberturaParaAdquirirIngresso.plusDays(numeroDeDiasParaEncerramento);
    }
}
