package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Diretorio {
    private Map<Enum, ArrayList<String>> linhas = new HashMap<>();

    public Diretorio(Enum[] listaValores) {
        criarIndices(listaValores);
    }

    public void criarIndices(Enum[] listaValores){
        for (Enum valor : listaValores) {
            this.linhas.put(valor, new ArrayList<>());
        }
    }

    public Map<Enum, ArrayList<String>> getLinhas() {
        return linhas;
    }

}
