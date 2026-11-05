package br.edu.ifmg.poo04.repository;

import br.edu.ifmg.poo04.model.Cliente;
import br.edu.ifmg.poo04.model.Pet;
import br.edu.ifmg.poo04.model.Servico;
import br.edu.ifmg.poo04.model.ServicoPrestado;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

// guarda os servicos prestados (atendimentos) em um arraylist
// tambem tem as buscas por periodo, por cliente e pelo nome do dono
public class ServicoPrestadoRepository {

    private ArrayList<ServicoPrestado> servicoPrestado;

    public ServicoPrestadoRepository() {
        servicoPrestado = new ArrayList<>();
    }

    public void adicionar(ServicoPrestado sp) {
        servicoPrestado.add(sp);
    }

    public ArrayList<ServicoPrestado> listar() {
        return servicoPrestado;
    }

    public ServicoPrestado buscarPorId(int id) {
        for (ServicoPrestado sp : servicoPrestado) {
            if (sp.getId() == id) {
                return sp;
            }
        }
        return null;
    }

    public boolean remover(int id) {
        ServicoPrestado sp = buscarPorId(id);
        if (sp == null) {
            return false;
        }
        servicoPrestado.remove(sp);
        return true;
    }

    // atendimentos entre a data inicial e a final (contando as duas)
    public ArrayList<ServicoPrestado> buscarPorPeriodo(LocalDate inicio, LocalDate fim) {
        ArrayList<ServicoPrestado> resultado = new ArrayList<>();

        for (ServicoPrestado sp : servicoPrestado) {
            LocalDate data = sp.getData();
            if (data.isBefore(inicio) == false && data.isAfter(fim) == false) {
                resultado.add(sp);
            }
        }
        return resultado;
    }

    // atendimentos dos pets de um cliente
    public ArrayList<ServicoPrestado> buscarPorCliente(Cliente cliente) {
        ArrayList<ServicoPrestado> resultado = new ArrayList<>();

        for (ServicoPrestado sp : servicoPrestado) {
            if (sp.getPet().getCliente().equals(cliente)) {
                resultado.add(sp);
            }
        }
        return resultado;
    }

    // pets ja atendidos, procurando pelo nome do dono
    // usa toLowerCase pra nao ter diferenca entre maiuscula e minuscula
    public ArrayList<Pet> buscarBichosPorNomeDono(String nome) {
        ArrayList<Pet> resultado = new ArrayList<>();

        for (ServicoPrestado sp : servicoPrestado) {
            Pet pet = sp.getPet();
            String nomeDono = pet.getCliente().getNome().toLowerCase();

            // o segundo teste e pra nao repetir o mesmo pet na lista
            if (nomeDono.contains(nome.toLowerCase()) && !resultado.contains(pet)) {
                resultado.add(pet);
            }
        }
        return resultado;
    }

    // maior valor que o cliente ja pagou em um atendimento
    public double maiorValorPorCliente(Cliente cliente) {
        double maior = 0;

        for (ServicoPrestado sp : buscarPorCliente(cliente)) {
            if (sp.getValorCobrado() > maior) {
                maior = sp.getValorCobrado();
            }
        }
        return maior;
    }

    // cada linha do arquivo: id;idPet;idServico;valorCobrado;data
    public void carregarDados(String arquivo, PetRepository petRepository, ServicoRepository servicoRepository) {
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
                int idPet = Integer.parseInt(partes[1]);
                int idServico = Integer.parseInt(partes[2]);
                double valor = Double.parseDouble(partes[3]);
                LocalDate data = LocalDate.parse(partes[4], formato);

                Pet pet = petRepository.buscarPorId(idPet);
                Servico servico = servicoRepository.buscarPorId(idServico);

                // so adiciona se o pet e o servico existirem
                if (pet != null && servico != null) {
                    adicionar(new ServicoPrestado(id, pet, servico, valor, data));
                }

                linha = leitor.readLine();
            }
            leitor.close();
        } catch (Exception e) {
            System.out.println("erro ao ler o arquivo " + arquivo);
        }
    }
}
