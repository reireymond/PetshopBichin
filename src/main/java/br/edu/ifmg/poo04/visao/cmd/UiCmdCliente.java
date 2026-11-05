package br.edu.ifmg.poo04.visao.cmd;

import br.edu.ifmg.poo04.controller.ClienteController;
import br.edu.ifmg.poo04.model.Cliente;

import java.util.Scanner;

// tela dos clientes
// nao tem cadastrar aqui porque o cliente e criado junto com o pet
public class UiCmdCliente {

    private Scanner teclado;
    private ClienteController controller;

    public UiCmdCliente(ClienteController controller) {
        teclado = Leitor.getTeclado();
        this.controller = controller;
    }

    public void iniciar() {
        int opcao;

        do {
            System.out.println("\n--- CLIENTES ---");
            System.out.println("1 - Listar");
            System.out.println("2 - Buscar");
            System.out.println("3 - Alterar");
            System.out.println("4 - Apagar");
            System.out.println("0 - Voltar");
            opcao = Leitor.lerInteiro(teclado, "Opcao: ");

            switch (opcao) {
                case 1:
                    listar();
                    break;
                case 2:
                    buscar();
                    break;
                case 3:
                    alterar();
                    break;
                case 4:
                    apagar();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("opcao invalida");
            }
        } while (opcao != 0);
    }

    private void listar() {
        if (controller.listar().isEmpty()) {
            System.out.println("nenhum cliente cadastrado");
        }
        for (Cliente c : controller.listar()) {
            System.out.println(c);
        }
    }

    private void buscar() {
        int id = Leitor.lerInteiro(teclado, "Id do cliente: ");
        Cliente cliente = controller.buscar(id);

        if (cliente == null) {
            System.out.println("cliente nao encontrado");
        } else {
            System.out.println(cliente);
        }
    }

    // busca o cliente e muda pelos setters
    // como a lista guarda o mesmo objeto, a mudanca ja fica salva
    private void alterar() {
        int id = Leitor.lerInteiro(teclado, "Id do cliente: ");
        Cliente cliente = controller.buscar(id);

        if (cliente == null) {
            System.out.println("cliente nao encontrado");
            return;
        }

        System.out.println(cliente);
        cliente.setNome(Leitor.lerTexto(teclado, "Novo nome: "));
        cliente.setTelefone(Leitor.lerTelefone(teclado, "Novo telefone (dd) ddddd-dddd: "));
        System.out.println("cliente alterado");
    }

    private void apagar() {
        int id = Leitor.lerInteiro(teclado, "Id do cliente: ");

        if (controller.remover(id)) {
            System.out.println("cliente removido");
        } else {
            System.out.println("cliente nao encontrado");
        }
    }
}
