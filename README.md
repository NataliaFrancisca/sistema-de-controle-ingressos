# 🎟️🖥️ Sistema Gerenciador de Ingressos

> Este projeto tem como objetivo simular um sistema de gerenciamento de ingressos para eventos, utilizando conceitos de Java, como `Collections` e tratamento de exceções. O sistema permite a criação de eventos, ativação de vendas de ingressos, cadastro de clientes e emissão de ingressos, seguindo regras de negócio específicas.
 
## **1. Como Funciona:**

### Criando um novo `Evento`
```java
Evento evento = criarEvento(
        "Copa Vôlei Feminino - Final",
        "Escola Vôlei Fernanda Garay",
        "Rua Carlos Almeida Fernandes, n 120, Ibirapuera - São Paulo, SP",
        LocalDateTime.of(2025, 2, 25, 21, 0, 0)
);
```
- Este método cria um novo `Evento` com um nome, local, endereço e data. 
- É necessário passar uma data que esteja no **futuro**. 

### Ativando acesso aos `Ingressos`
```java
// int numeroDeIngressosDisponiveis,
// LocalDateTime dataAbertura,
// int numeroDeDiasParaEncerrar

evento.ativarIngressos(2, LocalDateTime.of(2025, 1, 28, 0, 0, 0), 2);
```
- Este método ativa os ingressos.
- O número de ingressos disponíveis deve ser maior que 0.
- A data de abertura deve ser no futuro, e deve ser antes da data de realização do evento.


### Criando um novo `Cliente`
```java
Cliente cliente = criarCliente("Natalia", "nat@mail.com", "222.444.666-88");
Cliente cliente1 = criarCliente("Luiz", "luiz@mail.com", "222.333.444-55");
```
- para criar um novo `Cliente` é preciso passar um e-mail(xxxx@xxx.xxx) e cpf válido (xxx.xxx.xxx-xx).

### Emitindo um `Ingresso` para o `Cliente`
```java
Ingresso ingressoCliente = evento.emitirIngresso(cliente);
```

#### Regras para emitir um `Ingresso`:
- O cliente deve ser válido.
- O evento deve estar ativo e com acesso aberto aos ingressos.
- Cada cliente pode emitir apenas **1 ingresso** por evento.
- A data de aquisição do ingresso deve estar dentro do período.
  
## 2. Stacks:
- **Java**
- **Maven**
- **caelum-stella-core**: biblioteca para validação do CPF. (Obs.: a validação foi desativada para testes, mas pode ser reativada conforme necessário.).
  

## 3. O que aprendi com esse projeto:
- Utilizei `HashSet` para armazenar clientes, garantindo que cada cliente só possa adquirir **1 ingresso** por evento. Para isso, implementei os métodos `equals` e `hashCode` na classe `Cliente`, utilizando o CPF como identificador único.
- Implementei exceções personalizadas para tratar casos específicos, como datas inválidas ou tentativas de emitir ingressos fora do período.

   
## 4. Exemplo de Uso:
```java
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
```
  
