package br.edu.ifmg.poo04.visao.cmd;

import br.edu.ifmg.poo04.controller.ClienteController;
import br.edu.ifmg.poo04.controller.PetController;
import br.edu.ifmg.poo04.model.Cliente;
import br.edu.ifmg.poo04.model.Pet;

import java.time.LocalDate;
import java.util.Scanner;

// tela dos pets
// recebe o controller de cliente tambem porque o cliente e cadastrado junto com o pet
public class UiCmdPet {

    private Scanner teclado;
    private PetController controller;
    private ClienteController controllerCliente;

    public UiCmdPet(PetController controller, ClienteController controllerCliente) {
        teclado = Leitor.getTeclado();
        this.controller = controller;
        this.controllerCliente = controllerCliente;
    }

    public void iniciar() {
        int opcao;

        do {
            System.out.println("\n--- PETS ---");
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

    // pede o codigo do dono: se ele existe so liga ao pet,
    // se nao existe cadastra o cliente na hora
    private void adicionar() {
        int id = Leitor.lerInteiro(teclado, "Id do pet: ");

        if (controller.buscar(id) != null) {
            System.out.println("ja existe um pet com esse id");
            return;
        }

        String nome = Leitor.lerTexto(teclado, "Nome: ");
        String especie = Leitor.lerTexto(teclado, "Especie: ");
        String raca = Leitor.lerTexto(teclado, "Raca: ");
        LocalDate nascimento = Leitor.lerData(teclado, "Nascimento (dd/MM/aaaa): ");

        int idCliente = Leitor.lerInteiro(teclado, "Codigo do dono: ");
        Cliente dono = controllerCliente.buscar(idCliente);

        if (dono != null) {
            System.out.println("dono: " + dono.getNome());
        } else {
            System.out.println("cliente nao cadastrado, informe os dados dele");
            String nomeCliente = Leitor.lerTexto(teclado, "Nome do cliente: ");
            String telefone = Leitor.lerTelefone(teclado, "Telefone (dd) ddddd-dddd: ");
            controllerCliente.adicionar(idCliente, nomeCliente, telefone);
        }

        controller.adicionar(id, nome, especie, raca, nascimento, idCliente);
        System.out.println("pet cadastrado");
    }

    private void listar() {
        if (controller.listar().isEmpty()) {
            System.out.println("nenhum pet cadastrado");
        }
        for (Pet p : controller.listar()) {
            System.out.println(p);
        }
    }

    private void buscar() {
        int id = Leitor.lerInteiro(teclado, "Id do pet: ");
        Pet pet = controller.buscar(id);

        if (pet == null) {
            System.out.println("pet nao encontrado");
        } else {
            System.out.println(pet);
        }
    }

    // altera so os dados do animal, o dono continua o mesmo
    private void alterar() {
        int id = Leitor.lerInteiro(teclado, "Id do pet: ");
        Pet pet = controller.buscar(id);

        if (pet == null) {
            System.out.println("pet nao encontrado");
            return;
        }

        System.out.println(pet);
        pet.setNome(Leitor.lerTexto(teclado, "Novo nome: "));
        pet.setEspecie(Leitor.lerTexto(teclado, "Nova especie: "));
        pet.setRaca(Leitor.lerTexto(teclado, "Nova raca: "));
        pet.setNascimento(Leitor.lerData(teclado, "Novo nascimento (dd/MM/aaaa): "));
        System.out.println("pet alterado");
    }

    private void apagar() {
        int id = Leitor.lerInteiro(teclado, "Id do pet: ");

        if (controller.remover(id)) {
            System.out.println("pet removido");
        } else {
            System.out.println("pet nao encontrado");
        }
    }
}
