package br.edu.ifmg.poo04;

import br.edu.ifmg.poo04.controller.*;
import br.edu.ifmg.poo04.repository.*;
import br.edu.ifmg.poo04.visao.cmd.*;

// aqui o sistema e montado: cria as camadas e liga uma na outra
// ordem: repository -> controller -> telas -> menu principal
public class Main {

    public static void main(String[] args) {

        // 1) repositorios, e onde os dados ficam guardados (arraylist por enquanto)
        ClienteRepository clienteRepository = new ClienteRepository();
        PetRepository petRepository = new PetRepository();
        ServicoRepository servicoRepository = new ServicoRepository();
        ServicoPrestadoRepository servicoPrestadoRepository = new ServicoPrestadoRepository();

        // 2) carrega os dados iniciais dos arquivos texto
        // a ordem importa: pet precisa do cliente e o atendimento precisa do pet e do servico
        clienteRepository.carregarDados("dados/clientes.txt");
        servicoRepository.carregarDados("dados/servicos.txt");
        petRepository.carregarDados("dados/pets.txt", clienteRepository);
        servicoPrestadoRepository.carregarDados("dados/servicos_prestados.txt", petRepository, servicoRepository);

        // 3) controllers, recebem os repositorios no construtor
        ClienteController clienteController = new ClienteController(clienteRepository);
        PetController petController = new PetController(petRepository, clienteRepository);
        ServicoController servicoController = new ServicoController(servicoRepository);
        ServicoPrestadoController servicoPrestadoController =
                new ServicoPrestadoController(servicoPrestadoRepository, petRepository, servicoRepository);

        // 4) telas de cada opcao do menu, recebem os controllers
        UiCmdCliente uiCmdCliente = new UiCmdCliente(clienteController);
        UiCmdPet uiCmdPet = new UiCmdPet(petController, clienteController);
        UiCmdServico uiCmdServico = new UiCmdServico(servicoController);
        UiCmdServicoPrestado menuServicoPrestado =
                new UiCmdServicoPrestado(servicoPrestadoController, clienteController);

        // 5) menu principal recebe as telas e comeca o programa
        UI_Cmd uiCmd = new UI_Cmd(uiCmdCliente, uiCmdPet, uiCmdServico, menuServicoPrestado);
        uiCmd.iniciar();
    }
}
