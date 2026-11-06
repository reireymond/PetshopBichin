package br.edu.ifmg.poo04.model;

// servico oferecido pelo petshop (banho, tosa, consulta...)
// o valor aqui e o preco de tabela
public class Servico {

    private int id;
    private String descricao;
    private double valor;

    public Servico() {
    }

    public Servico(int id, String descricao, double valor) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Servico)) {
            return false;
        }
        Servico outro = (Servico) obj;
        return this.id == outro.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Servico " + id + " - " + descricao + " - R$ " + valor;
    }
}
