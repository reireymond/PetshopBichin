package br.edu.ifmg.poo04.controller;

import br.edu.ifmg.poo04.model.Cliente;
import br.edu.ifmg.poo04.model.Pet;
import br.edu.ifmg.poo04.repository.ClienteRepository;
import br.edu.ifmg.poo04.repository.PetRepository;

import java.time.LocalDate;
import java.util.ArrayList;

// controller dos pets
// recebe tambem o repositorio de clientes porque precisa achar o dono do bicho
public class PetController {

    private PetRepository repository;
    private ClienteRepository repositoryCliente;

    public PetController(PetRepository repository, ClienteRepository repositoryCliente) {
        this.repository = repository;
        this.repositoryCliente = repositoryCliente;
    }

    // a tela manda so o id do cliente, aqui a gente busca o objeto e monta o pet
    // o cliente ja tem que existir nesse ponto (a tela cria antes se precisar)
    public void adicionar(int id, String nome, String especie, String raca, LocalDate nascimento, int idCliente) {
        Cliente dono = repositoryCliente.buscarPorId(idCliente);
        repository.adicionar(new Pet(id, nome, especie, raca, nascimento, dono));
    }

    public Pet buscar(int id) {
        return repository.buscarPorId(id);
    }

    public ArrayList<Pet> listar() {
        return repository.listar();
    }

    public boolean remover(int id) {
        return repository.remover(id);
    }
}
