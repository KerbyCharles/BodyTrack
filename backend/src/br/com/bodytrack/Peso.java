package br.com.bodytrack;

import java.time.LocalDate;

public class Peso {

    private int id;
    private int usuarioId;
    private double peso;
    private LocalDate dataRegistro;

    // Cadastro de uma nova pesagem
    public Peso(int usuarioId, double peso, LocalDate dataRegistro) {
        this.usuarioId = usuarioId;
        this.peso = peso;
        this.dataRegistro = dataRegistro;
    }

    // Recuperação de uma pesagem do banco
    public Peso(int id, int usuarioId, double peso, LocalDate dataRegistro) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.peso = peso;
        this.dataRegistro = dataRegistro;
    }

    public int getId() {
        return id;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public double getPeso() {
        return peso;
    }

    public LocalDate getDataRegistro() {
        return dataRegistro;
    }
}