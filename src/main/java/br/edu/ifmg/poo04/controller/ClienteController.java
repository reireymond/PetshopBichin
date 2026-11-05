package br.edu.ifmg.poo04.controller;

import br.edu.ifmg.poo04.model.Cliente;
import br.edu.ifmg.poo04.repository.ClienteRepository;

import java.util.ArrayList;

// faz a ponte entre a tela e o repositorio de clientes
// a tela nao sabe como os dados sao guardados, so chama o controller
public class ClienteController {

    private ClienteRepository repository;

    // o repositorio vem de fora (injecao de dependencia), criado no main
    public ClienteController(ClienteRepository repository) {
        this.repository = repository;
    }

    public void adicionar(int id, String nome, String telefone) {
        repository.adicionar(new Cliente(id, nome, telefone));
    }

    public Cliente buscar(int id) {
        return repository.buscarPorId(id);
    }

    public ArrayList<Cliente> listar() {
        return repository.listar();
    }

    public boolean remover(int id) {
        return repository.remover(id);
    }
}
