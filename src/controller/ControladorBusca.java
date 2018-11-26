package controller;

import model.CargosEnum;
import model.PartidosEnum;
import model.PatrimonioEnum;
import view.TelaAvisos;
import view.TelaBusca;

import java.util.ArrayList;
import java.util.InputMismatchException;
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

    // faz a busca simples dos dados, de acordo com o atributo escolhido
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
            case 0:
                break;
            default:
                TelaAvisos.getInstance().opcaoInvalida();
        }
    }

    // retorna lista referente ao partido buscado
    public List<String> buscaPartido(){
        int numeroPartido = TelaBusca.getInstance().buscaPoliticoPartido();
        List<String> dados = ControladorDados.getInstance().partidos.getLinhas().get(PartidosEnum.porNumero(numeroPartido));
        return dados;
    }

    // retorna lista referente ao cargo buscado
    public List<String> buscaCargo(){
        int valorCargo = TelaBusca.getInstance().buscaPoliticoCargo();
        List<String> dados = ControladorDados.getInstance().cargos.getLinhas().get(CargosEnum.porNumero(valorCargo));
        return dados;
    }

    // retorna lista referente a faixa de patrimonio escolhida
    public List<String> buscaPatrimonio(){
        int valorPatrimonio = TelaBusca.getInstance().buscaPoliticoPatrimonio();
        List<String> dados = ControladorDados.getInstance().patrimonio.getLinhas().get(PatrimonioEnum.porNumero(valorPatrimonio));
        return dados;
    }

    // faz a busca com dois parâmetros escolhidos
    public void buscaComposta(){
        try { // verifica se opções digitadas em buscaComposta() são válidas. Se forem, continua o processo
            int primeiraOpcao = TelaBusca.getInstance().buscaComposta("primeira");
            if (primeiraOpcao > 0 && primeiraOpcao <= 3) {
                int segundaOpcao = TelaBusca.getInstance().buscaComposta("segunda");
                if (segundaOpcao > 0 && segundaOpcao <= 3) {
                    List<String> primeiraLista;
                    List<String> segundaLista;
                    List<String> listaFinal = new ArrayList<>(); // armazenara os dados em comum nas duas listas
                    if (primeiraOpcao != segundaOpcao) { // faz a intersecção dos dados
                        primeiraLista = this.escolhaBuscaComposta(primeiraOpcao);
                        segundaLista = this.escolhaBuscaComposta(segundaOpcao);
                        for (String opcao : primeiraLista) {
                            if (segundaLista.contains(opcao)) {
                                listaFinal.add(opcao);
                            }
                        }
                    }
                    ControladorDados.getInstance().exibeBusca(listaFinal);
                } else {
                    TelaAvisos.getInstance().opcaoInvalida();
                }
            } else {
                TelaAvisos.getInstance().opcaoInvalida();
            }
        } catch (IllegalArgumentException e){
            TelaAvisos.getInstance().exibeErro(e);
        } catch (InputMismatchException e){
            TelaAvisos.getInstance().exibeErro(e);
        }
    }

    // retorna as listas referentes as consultas na busca composta
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