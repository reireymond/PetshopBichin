package br.edu.ifmg.poo04.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

// servico que foi feito em um pet (atendimento)
// fica ligado ao pet e nao ao cliente
public class ServicoPrestado {

    private int id;
    private Pet pet;
    private Servico servico;
    private double valorCobrado;
    private LocalDate data;

    public ServicoPrestado() {
    }

    public ServicoPrestado(int id, Pet pet, Servico servico, double valorCobrado, LocalDate data) {
        this.id = id;
        this.pet = pet;
        this.servico = servico;
        this.valorCobrado = valorCobrado;
        this.data = data;
    }

    // quantos dias passaram desde o dia do servico ate hoje
    public int getDiasDaExecucaoDoServico() {
        long dias = ChronoUnit.DAYS.between(data, LocalDate.now());
        return (int) dias;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public double getValorCobrado() {
        return valorCobrado;
    }

    public void setValorCobrado(double valorCobrado) {
        this.valorCobrado = valorCobrado;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ServicoPrestado outro = (ServicoPrestado) obj;
        return this.id == outro.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return "Atendimento " + id
                + " - pet: " + pet.getNome()
                + " - servico: " + servico.getDescricao()
                + " - cobrado: R$ " + valorCobrado
                + " - data: " + data.format(formato)
                + " (" + getDiasDaExecucaoDoServico() + " dias atras)";
    }
}
