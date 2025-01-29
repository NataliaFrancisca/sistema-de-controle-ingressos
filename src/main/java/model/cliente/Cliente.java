package model.cliente;

import exceptions.CPFInvalidoException;
import exceptions.EmailInvalidoException;

import java.util.Objects;

public class Cliente {
    private String nome;
    private String email;
    private String cpf;

    public String getNome(){
        return nome;
    }
    public String getEmail(){
        return this.email;
    }
    public String getCpf() {return cpf;}

    public void setNome(String nome){
        if(!nome.isEmpty()){
            this.nome = nome;
        }else{
            throw new IllegalArgumentException("você deve digitar um nome válido");
        }
    }

    public void setEmail(String email){
        boolean emailValido = new Email().validarEmail(email);

        if(emailValido){
            this.email = email;
        }else{
            throw new EmailInvalidoException("digite um e-mail válido.");
        }
    }

    public void setCpf(String cpf){
        boolean cpfValido = new CPF().validarCPF(cpf);

        if(cpfValido){
            this.cpf = cpf;
        }else{
            throw new CPFInvalidoException();
        }
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", cpf='" + cpf + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Cliente  cliente = (Cliente) obj;
        return Objects.equals(cpf, cliente.cpf);
    }

    @Override
    public int hashCode(){
        return Objects.hash(cpf);
    }
}
