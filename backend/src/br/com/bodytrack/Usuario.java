
package br.com.bodytrack;

import java.time.LocalDate;

public class Usuario {

    private int id;
    private String nome;
    private String email;
    private String senha;
    private LocalDate dataNascimento;
    private double altura;

    // Primeiro construtor: novo usuário
    public Usuario(String nome, String email, String senha,
                   LocalDate dataNascimento, double altura) {

        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
        this.altura = altura;
    }

    // Segundo construtor: usuário recuperado do banco
    public Usuario(int id, String nome, String email, String senha,
                   LocalDate dataNascimento, double altura) {

        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
        this.altura = altura;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public double getAltura() {
        return altura;
    }
}
