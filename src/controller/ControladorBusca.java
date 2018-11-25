package controller;

import model.CargosEnum;
import model.PartidosEnum;
import model.PatrimonioEnum;
import view.TelaBusca;

import java.util.ArrayList;
import java.util.List;

public class ControladorBusca {
    private static ControladorBusca instance;

    public static ControladorBusca getInstance() {
        if (instance == null)
            instance = new ControladorBusca();

        return instance;
    }

    private ControladorBusca () {

    }

    public void buscaSimples(){
        int opcao = TelaBusca.getInstance().telaBuscaSimples();
        switch (opcao){
            case 1:
                ControladorDados.getInstance().exibeBusca(this.buscaPartido());
                break;
            case 2:
                ControladorDados.getInstance().exibeBusca(this.buscaCargo());
                break;
            case 3:
                ControladorDados.getInstance().exibeBusca(this.buscaPatrimonio());
                break;
            default:
                // Criar exceção
                System.out.println("Selecione uma opção válida");
        }
    }

    public List<String> buscaPartido(){
        int numeroPartido = TelaBusca.getInstance().buscaPoliticoPartido();
        List<String> dados = ControladorDados.getInstance().partidos.getLinhas().get(PartidosEnum.porNumero(numeroPartido));
        return dados;
    }

    public List<String> buscaCargo(){
        int valorCargo = TelaBusca.getInstance().buscaPoliticoCargo();
        List<String> dados = ControladorDados.getInstance().cargos.getLinhas().get(CargosEnum.porNumero(valorCargo));
        return dados;
    }

    public List<String> buscaPatrimonio(){
        int valorPatrimonio = TelaBusca.getInstance().buscaPoliticoPatrimonio();
        List<String> dados = ControladorDados.getInstance().patrimonio.getLinhas().get(PatrimonioEnum.porNumero(valorPatrimonio));
        return dados;
    }

    public void buscaComposta(){
        int primeiraOpcao = TelaBusca.getInstance().buscaComposta("primeira");
        int segundaOpcao = TelaBusca.getInstance().buscaComposta("segunda");
        List<String> primeiraLista;
        List<String> segundaLista;
        List<String> listaFinal = new ArrayList<>();
        if (primeiraOpcao != segundaOpcao){
            primeiraLista = this.escolhaBuscaComposta(primeiraOpcao);
            segundaLista = this.escolhaBuscaComposta(segundaOpcao);
            for (String opcao : primeiraLista) {
                if(segundaLista.contains(opcao)){
                    listaFinal.add(opcao);
                }
            }
        }
        ControladorDados.getInstance().exibeBusca(listaFinal);

    }

    public List<String> escolhaBuscaComposta(int opcao){
        if(opcao == 1)
            return this.buscaPartido();
        if(opcao == 2)
            return this.buscaCargo();
        if (opcao == 3)
            return this.buscaPatrimonio();
        else
            return null;
    }

}
