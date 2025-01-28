package utils;

import model.evento.Evento;

import java.time.LocalDateTime;

public class EventoUtils {
    public static Evento criarEvento(String titulo, String organizador, String endereco, LocalDateTime dataEvento){
        return new Evento(titulo, organizador, endereco, dataEvento);
    }
}
