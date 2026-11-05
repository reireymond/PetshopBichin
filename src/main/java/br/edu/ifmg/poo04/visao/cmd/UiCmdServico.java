package br.edu.ifmg.poo04.visao.cmd;

import br.edu.ifmg.poo04.controller.ServicoController;
import br.edu.ifmg.poo04.model.Servico;

import java.util.Scanner;

// tela dos servicos oferecidos
public class UiCmdServico {

    private Scanner teclado;
    private ServicoController controller;

    public UiCmdServico(ServicoController controller) {
        teclado = Leitor.getTeclado();
        this.controller = controller;
    }

    public void iniciar() {
        int opcao;

        do {
            System.out.println("\n--- SERVICOS ---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Buscar");
            System.out.println("4 - Alterar");
            System.out.println("5 - Apagar");
            System.out.println("0 - Voltar");
            opcao = Leitor.lerInteiro(teclado, "Opcao: ");

            switch (opcao) {
                case 1:
                    adicionar();
                    break;
                case 2:
                    listar();
                    break;
                case 3:
                    buscar();
                    break;
                case 4:
                    alterar();
                    break;
                case 5:
                    apagar();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("opcao invalida");
            }
        } while (opcao != 0);
    }

    private void adicionar() {
        int id = Leitor.lerInteiro(teclado, "Id do servico: ");

        // nao deixa cadastrar dois servicos com o mesmo id
        if (controller.buscar(id) != null) {
            System.out.println("ja existe um servico com esse id");
            return;
        }

        String descricao = Leitor.lerTexto(teclado, "Descricao: ");
        double valor = Leitor.lerDouble(teclado, "Valor: R$ ");

        controller.adicionar(id, descricao, valor);
        System.out.println("servico cadastrado");
    }

    private void listar() {
        if (controller.listar().isEmpty()) {
            System.out.println("nenhum servico cadastrado");
        }
        for (Servico s : controller.listar()) {
            System.out.println(s);
        }
    }

    private void buscar() {
        int id = Leitor.lerInteiro(teclado, "Id do servico: ");
        Servico servico = controller.buscar(id);

        if (servico == null) {
            System.out.println("servico nao encontrado");
        } else {
            System.out.println(servico);
        }
    }

    private void alterar() {
        int id = Leitor.lerInteiro(teclado, "Id do servico: ");
        Servico servico = controller.buscar(id);

        if (servico == null) {
            System.out.println("servico nao encontrado");
            return;
        }

        System.out.println(servico);
        servico.setDescricao(Leitor.lerTexto(teclado, "Nova descricao: "));
        servico.setValor(Leitor.lerDouble(teclado, "Novo valor: R$ "));
        System.out.println("servico alterado");
    }

    private void apagar() {
        int id = Leitor.lerInteiro(teclado, "Id do servico: ");

        if (controller.remover(id)) {
            System.out.println("servico removido");
        } else {
            System.out.println("servico nao encontrado");
        }
    }
}
