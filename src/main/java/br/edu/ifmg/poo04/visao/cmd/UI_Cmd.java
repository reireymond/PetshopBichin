package br.edu.ifmg.poo04.visao.cmd;

import java.util.Scanner;

// menu principal
// so mostra as opcoes e chama o iniciar() da tela escolhida
public class UI_Cmd {

    private Scanner teclado;

    private UiCmdCliente uiCmdCliente;
    private UiCmdPet uiCmdPet;
    private UiCmdServico uiCmdServico;
    private UiCmdServicoPrestado menuServicoPrestado;

    public UI_Cmd(UiCmdCliente uiCmdCliente, UiCmdPet uiCmdPet,
                  UiCmdServico uiCmdServico, UiCmdServicoPrestado menuServicoPrestado) {
        teclado = Leitor.getTeclado();
        this.uiCmdCliente = uiCmdCliente;
        this.uiCmdPet = uiCmdPet;
        this.uiCmdServico = uiCmdServico;
        this.menuServicoPrestado = menuServicoPrestado;
    }

    public void iniciar() {
        int opcao;

        do {
            System.out.println("\n===== PETSHOP BICHIN =====");
            System.out.println("1 - Clientes");
            System.out.println("2 - Pets");
            System.out.println("3 - Servicos");
            System.out.println("4 - Servicos prestados");
            System.out.println("0 - Sair");
            opcao = Leitor.lerInteiro(teclado, "Opcao: ");

            switch (opcao) {
                case 1:
                    uiCmdCliente.iniciar();
                    break;
                case 2:
                    uiCmdPet.iniciar();
                    break;
                case 3:
                    uiCmdServico.iniciar();
                    break;
                case 4:
                    menuServicoPrestado.iniciar();
                    break;
                case 0:
                    System.out.println("saindo...");
                    break;
                default:
                    System.out.println("opcao invalida");
            }
        } while (opcao != 0);
    }
}
