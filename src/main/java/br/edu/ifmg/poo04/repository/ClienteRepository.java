package br.edu.ifmg.poo04.repository;

import br.edu.ifmg.poo04.model.Cliente;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

// guarda os clientes em um arraylist
public class ClienteRepository {

    private ArrayList<Cliente> clientes;

    public ClienteRepository() {
        clientes = new ArrayList<>();
    }

    public void adicionar(Cliente cliente) {
        clientes.add(cliente);
    }

    public ArrayList<Cliente> listar() {
        return clientes;
    }

    // procura o cliente pelo id, se nao achar retorna null
    public Cliente buscarPorId(int id) {
        for (Cliente c : clientes) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    // remove o cliente pelo id, retorna true se conseguiu
    public boolean remover(int id) {
        Cliente c = buscarPorId(id);
        if (c == null) {
            return false;
        }
        clientes.remove(c);
        return true;
    }

    // le o arquivo texto e adiciona os clientes na lista
    // cada linha do arquivo: id;nome;telefone
    public void carregarDados(String arquivo) {
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
                String telefone = partes[2];

                adicionar(new Cliente(id, nome, telefone));

                linha = leitor.readLine();
            }
            leitor.close();
        } catch (Exception e) {
            System.out.println("erro ao ler o arquivo " + arquivo);
        }
    }
}
