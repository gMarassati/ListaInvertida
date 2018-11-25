package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Diretorio {

    private String nomeLinha;
    private Map<Enum, ArrayList<String>> linhas = new HashMap<>();

    public Diretorio(String nomeLinha, Enum[] listaValores) {
        this.nomeLinha = nomeLinha;
        criarIndices(listaValores);
    }

    public void criarIndices(Enum[] listaValores){
        for (Enum valor : listaValores) {
            this.linhas.put(valor, new ArrayList<>());
        }
    }

    public String getNomeLinha() {
        return nomeLinha;
    }

    public void setNomeLinha(String nomeLinha) {
        this.nomeLinha = nomeLinha;
    }

    public Map<Enum, ArrayList<String>> getLinhas() {
        return linhas;
    }
}
