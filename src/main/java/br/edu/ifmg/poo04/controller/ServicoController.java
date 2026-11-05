package br.edu.ifmg.poo04.controller;

import br.edu.ifmg.poo04.model.Servico;
import br.edu.ifmg.poo04.repository.ServicoRepository;

import java.util.ArrayList;

// controller dos servicos oferecidos, so repassa as chamadas pro repositorio
public class ServicoController {

    private ServicoRepository repository;

    public ServicoController(ServicoRepository repository) {
        this.repository = repository;
    }

    public void adicionar(int id, String descricao, double valor) {
        repository.adicionar(new Servico(id, descricao, valor));
    }

    public Servico buscar(int id) {
        return repository.buscarPorId(id);
    }

    public ArrayList<Servico> listar() {
        return repository.listar();
    }

    public boolean remover(int id) {
        return repository.remover(id);
    }
}
