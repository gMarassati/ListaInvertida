package model;

public class Politico {

    private String cpf;
    private String nome;
    private Enum partido;
    private Enum cargo;
    private int patrimonio;

    public Politico(String cpf, String nome, Enum partido, Enum cargo, int patrimonio) {
        this.cpf = cpf;
        this.nome = nome;
        this.partido = partido;
        this.cargo = cargo;
        this.patrimonio = patrimonio;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public Enum getPartido() {
        return partido;
    }

    public Enum getCargo() {
        return cargo;
    }

    public int getPatrimonio() {
        return patrimonio;
    }

}