package br.edu.ifmg.poo04.visao.cmd;

import br.edu.ifmg.poo04.controller.ClienteController;
import br.edu.ifmg.poo04.controller.ServicoPrestadoController;
import br.edu.ifmg.poo04.model.Cliente;
import br.edu.ifmg.poo04.model.Pet;
import br.edu.ifmg.poo04.model.Servico;
import br.edu.ifmg.poo04.model.ServicoPrestado;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

// tela dos servicos prestados (atendimentos)
// usa o controller de cliente nas buscas por cliente
public class UiCmdServicoPrestado {

    private Scanner teclado;
    private ServicoPrestadoController controller;
    private ClienteController controllerCliente;

    public UiCmdServicoPrestado(ServicoPrestadoController controller, ClienteController controllerCliente) {
        teclado = Leitor.getTeclado();
        this.controller = controller;
        this.controllerCliente = controllerCliente;
    }

    public void iniciar() {
        int opcao;

        do {
            System.out.println("\n--- SERVICOS PRESTADOS ---");
            System.out.println("1 - Registrar atendimento");
            System.out.println("2 - Listar");
            System.out.println("3 - Buscar");
            System.out.println("4 - Apagar");
            System.out.println("5 - Atendimentos por periodo");
            System.out.println("6 - Atendimentos de um cliente");
            System.out.println("7 - Animais atendidos pelo nome do dono");
            System.out.println("8 - Maior valor pago por um cliente");
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
                    apagar();
                    break;
                case 5:
                    servicosPorPeriodo();
                    break;
                case 6:
                    servicosDoCliente();
                    break;
                case 7:
                    animaisDoDono();
                    break;
                case 8:
                    maiorValor();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("opcao invalida");
            }
        } while (opcao != 0);
    }

    private void adicionar() {
        int id = Leitor.lerInteiro(teclado, "Id do atendimento: ");

        if (controller.buscar(id) != null) {
            System.out.println("ja existe um atendimento com esse id");
            return;
        }

        int idPet = Leitor.lerInteiro(teclado, "Id do pet: ");
        Pet pet = controller.buscarPet(idPet);
        if (pet == null) {
            System.out.println("pet nao encontrado");
            return;
        }
        System.out.println("pet: " + pet.getNome() + " - dono: " + pet.getCliente().getNome());

        int idServico = Leitor.lerInteiro(teclado, "Id do servico: ");
        Servico servico = controller.buscarServico(idServico);
        if (servico == null) {
            System.out.println("servico nao encontrado");
            return;
        }

        // mostra o servico e o valor antes de digitar o valor cobrado (regra do trabalho)
        System.out.println("servico: " + servico.getDescricao() + " - valor: R$ " + servico.getValor());

        double valorCobrado = Leitor.lerDouble(teclado, "Valor cobrado: R$ ");
        LocalDate data = Leitor.lerData(teclado, "Data (dd/MM/aaaa): ");

        controller.adicionar(id, idPet, idServico, valorCobrado, data);
        System.out.println("atendimento registrado");
    }

    private void listar() {
        mostrarLista(controller.listar());
    }

    private void buscar() {
        int id = Leitor.lerInteiro(teclado, "Id do atendimento: ");
        ServicoPrestado sp = controller.buscar(id);

        if (sp == null) {
            System.out.println("atendimento nao encontrado");
        } else {
            System.out.println(sp);
        }
    }

    private void apagar() {
        int id = Leitor.lerInteiro(teclado, "Id do atendimento: ");

        if (controller.removerServicoPrestado(id)) {
            System.out.println("atendimento removido");
        } else {
            System.out.println("atendimento nao encontrado");
        }
    }

    private void servicosPorPeriodo() {
        LocalDate inicio = Leitor.lerData(teclado, "Data inicial (dd/MM/aaaa): ");
        LocalDate fim = Leitor.lerData(teclado, "Data final (dd/MM/aaaa): ");

        mostrarLista(controller.buscarPorPeriodo(inicio, fim));
    }

    private void servicosDoCliente() {
        int id = Leitor.lerInteiro(teclado, "Id do cliente: ");
        Cliente cliente = controllerCliente.buscar(id);

        if (cliente == null) {
            System.out.println("cliente nao encontrado");
            return;
        }
        mostrarLista(controller.buscarPorCliente(cliente));
    }

    private void animaisDoDono() {
        String nome = Leitor.lerTexto(teclado, "Nome do dono: ");
        ArrayList<Pet> pets = controller.buscarBichosPorNomeDono(nome);

        if (pets.isEmpty()) {
            System.out.println("nenhum animal encontrado");
        }
        for (Pet p : pets) {
            System.out.println(p);
        }
    }

    private void maiorValor() {
        int id = Leitor.lerInteiro(teclado, "Id do cliente: ");
        Cliente cliente = controllerCliente.buscar(id);

        if (cliente == null) {
            System.out.println("cliente nao encontrado");
            return;
        }
        double maior = controller.maiorValorPorCliente(cliente);
        System.out.println("maior valor pago por " + cliente.getNome() + ": R$ " + maior);
    }

    // mostra uma lista de atendimentos, usado em varias opcoes
    private void mostrarLista(ArrayList<ServicoPrestado> lista) {
        if (lista.isEmpty()) {
            System.out.println("nenhum atendimento encontrado");
        }
        for (ServicoPrestado sp : lista) {
            System.out.println(sp);
        }
    }
}
