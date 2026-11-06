package br.edu.ifmg.poo04.model;

// classe do dono do pet
// o cliente so existe se tiver um pet (composicao)
public class Cliente {

    private int id;
    private String nome;
    private String telefone;

    public Cliente() {
    }

    public Cliente(int id, String nome, String telefone) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    // dois clientes sao iguais se tiverem o mesmo id
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Cliente)) {
            return false;
        }
        Cliente outro = (Cliente) obj;
        return this.id == outro.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Cliente " + id + " - " + nome + " - telefone: " + telefone;
    }
}
