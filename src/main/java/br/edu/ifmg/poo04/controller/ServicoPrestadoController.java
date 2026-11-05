package br.edu.ifmg.poo04.controller;

import br.edu.ifmg.poo04.model.Cliente;
import br.edu.ifmg.poo04.model.Pet;
import br.edu.ifmg.poo04.model.Servico;
import br.edu.ifmg.poo04.model.ServicoPrestado;
import br.edu.ifmg.poo04.repository.PetRepository;
import br.edu.ifmg.poo04.repository.ServicoPrestadoRepository;
import br.edu.ifmg.poo04.repository.ServicoRepository;

import java.time.LocalDate;
import java.util.ArrayList;

// controller dos atendimentos
// usa tres repositorios: o dos atendimentos, o dos pets e o dos servicos
public class ServicoPrestadoController {

    private ServicoPrestadoRepository repository;
    private PetRepository repositoryPet;
    private ServicoRepository repositoryServico;

    public ServicoPrestadoController(ServicoPrestadoRepository repository,
                                     PetRepository repositoryPet,
                                     ServicoRepository repositoryServico) {
        this.repository = repository;
        this.repositoryPet = repositoryPet;
        this.repositoryServico = repositoryServico;
    }

    // a tela manda os ids, aqui busca o pet e o servico e cria o atendimento
    public void adicionar(int id, int idPet, int idServico, double valorCobrado, LocalDate data) {
        Pet pet = repositoryPet.buscarPorId(idPet);
        Servico servico = repositoryServico.buscarPorId(idServico);

        repository.adicionar(new ServicoPrestado(id, pet, servico, valorCobrado, data));
    }

    public ServicoPrestado buscar(int id) {
        return repository.buscarPorId(id);
    }

    public ArrayList<ServicoPrestado> listar() {
        return repository.listar();
    }

    public boolean removerServicoPrestado(int id) {
        return repository.remover(id);
    }

    public ArrayList<ServicoPrestado> buscarPorPeriodo(LocalDate inicio, LocalDate fim) {
        return repository.buscarPorPeriodo(inicio, fim);
    }

    public ArrayList<ServicoPrestado> buscarPorCliente(Cliente cliente) {
        return repository.buscarPorCliente(cliente);
    }

    public ArrayList<Pet> buscarBichosPorNomeDono(String nome) {
        return repository.buscarBichosPorNomeDono(nome);
    }

    public double maiorValorPorCliente(Cliente cliente) {
        return repository.maiorValorPorCliente(cliente);
    }

    // esses dois nao estao no diagrama, a tela usa pra conferir o pet
    // e pra mostrar o valor do servico antes de digitar o valor cobrado
    public Pet buscarPet(int idPet) {
        return repositoryPet.buscarPorId(idPet);
    }

    public Servico buscarServico(int idServico) {
        return repositoryServico.buscarPorId(idServico);
    }
}
