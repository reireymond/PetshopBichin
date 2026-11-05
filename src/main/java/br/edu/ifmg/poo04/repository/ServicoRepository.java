package br.edu.ifmg.poo04.repository;

import br.edu.ifmg.poo04.model.Servico;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

// guarda os servicos em um arraylist
public class ServicoRepository {

    private ArrayList<Servico> servicos;

    public ServicoRepository() {
        servicos = new ArrayList<>();
    }

    public void adicionar(Servico servico) {
        servicos.add(servico);
    }

    public ArrayList<Servico> listar() {
        return servicos;
    }

    public Servico buscarPorId(int id) {
        for (Servico s : servicos) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }

    public boolean remover(int id) {
        Servico s = buscarPorId(id);
        if (s == null) {
            return false;
        }
        servicos.remove(s);
        return true;
    }

    // cada linha do arquivo: id;descricao;valor
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
                String descricao = partes[1];
                double valor = Double.parseDouble(partes[2]);

                adicionar(new Servico(id, descricao, valor));

                linha = leitor.readLine();
            }
            leitor.close();
        } catch (Exception e) {
            System.out.println("erro ao ler o arquivo " + arquivo);
        }
    }
}
