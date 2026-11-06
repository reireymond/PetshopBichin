package br.edu.ifmg.poo04.model;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

// classe do animal
// todo pet tem um dono, por isso o pet cria um cliente no construtor vazio
public class Pet {

    private int id;
    private String nome;
    private String especie;
    private String raca;
    private Cliente cliente;
    private LocalDate nascimento;

    // cria um cliente vazio pro pet nao ficar sem dono (<<create>> do diagrama)
    public Pet() {
        cliente = new Cliente();
    }

    public Pet(int id, String nome, String especie, String raca, LocalDate nascimento, Cliente cliente) {
        this.id = id;
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.nascimento = nascimento;
        this.cliente = cliente;
    }

    // calcula a idade em anos usando a data de nascimento e a data de hoje
    public int getIdade() {
        Period periodo = Period.between(nascimento, LocalDate.now());
        return periodo.getYears();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Pet)) {
            return false;
        }
        Pet outro = (Pet) obj;
        return this.id == outro.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return "Pet " + id + " - " + nome
                + " - especie: " + especie
                + " - raca: " + raca
                + " - nascimento: " + nascimento.format(formato)
                + " (" + getIdade() + " anos)"
                + " - dono: " + cliente.getNome();
    }
}
