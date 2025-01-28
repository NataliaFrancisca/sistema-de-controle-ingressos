package utils;

import model.cliente.Cliente;

public class ClienteUtils {
    public static Cliente criarCliente(String nome, String email, String cpf){
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setEmail(email);
        cliente.setCpf(cpf);
        return cliente;
    }
}
