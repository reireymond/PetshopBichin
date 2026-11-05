package br.edu.ifmg.poo04.repository;

import br.edu.ifmg.poo04.model.Cliente;
import br.edu.ifmg.poo04.model.Pet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

// guarda os pets em um arraylist
public class PetRepository {

    private ArrayList<Pet> pets;

    public PetRepository() {
        pets = new ArrayList<>();
    }

    public void adicionar(Pet pet) {
        pets.add(pet);
    }

    public ArrayList<Pet> listar() {
        return pets;
    }

    public Pet buscarPorId(int id) {
        for (Pet p : pets) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public boolean remover(int id) {
        Pet p = buscarPorId(id);
        if (p == null) {
            return false;
        }
        pets.remove(p);
        return true;
    }

    // cada linha do arquivo: id;nome;especie;raca;nascimento;idCliente
    // recebe o repositorio de clientes pra achar o dono pelo id
    // por isso os clientes tem que ser carregados antes
    public void carregarDados(String arquivo, ClienteRepository clienteRepository) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            BufferedReader leitor = new BufferedReader(new FileReader(arquivo));
            String linha = leitor.readLine();

            // se a primeira linha for o cabecalho (id;...) pula ela
            if (linha != null && linha.startsWith("id")) {
                linha = leitor.readLine();
            }

            while (linha != null) {
                String[] partes = linha.split(";");

                int id = Integer.parseInt(partes[0]);
                String nome = partes[1];
                String especie = partes[2];
                String raca = partes[3];
                LocalDate nascimento = LocalDate.parse(partes[4], formato);
                int idCliente = Integer.parseInt(partes[5]);

                Cliente dono = clienteRepository.buscarPorId(idCliente);

                // so adiciona se o dono existir
                if (dono != null) {
                    adicionar(new Pet(id, nome, especie, raca, nascimento, dono));
                }

                linha = leitor.readLine();
            }
            leitor.close();
        } catch (Exception e) {
            System.out.println("erro ao ler o arquivo " + arquivo);
        }
    }
}
