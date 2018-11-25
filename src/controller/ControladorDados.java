package controller;

import model.*;
import view.TelaBusca;
import view.TelaCadastro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControladorDados {
    private Map<String, Politico> politicos = new HashMap<>();
    Diretorio partidos = new Diretorio(PartidosEnum.values());
    Diretorio cargos = new Diretorio(CargosEnum.values());
    Diretorio patrimonio = new Diretorio(PatrimonioEnum.values());

    private static ControladorDados instance;

    public static ControladorDados getInstance() {
        if (instance == null)
            instance = new ControladorDados();

        return instance;
    }

    private ControladorDados() {
        this.cargaDeDadosInicial();
    }

    public void cadastraPolitico(){
        // Exibe tela de cadastro e cria um objeto Politico
        String cpf = TelaCadastro.getInstance().inputCpf();
        String nome = TelaCadastro.getInstance().inputNome();
        Enum partido = PartidosEnum.porNumero(TelaCadastro.getInstance().inputPartido());
        Enum cargo = CargosEnum.porNumero(TelaCadastro.getInstance().inputCargo());
        int patrimonio = TelaCadastro.getInstance().inputPatrimonio();

        Politico politico = new Politico(cpf, nome, partido, cargo, patrimonio);

        // Algoritmo para cadastro do politico

        String chavePolitico = politico.getCpf();
        //Antes de inserir, deve verificar se chave já não existe
        if (!this.politicos.containsKey(chavePolitico)) {
            this.politicos.put(chavePolitico, politico);
            // armazena chave no diretório partidos
            List<String> tempPartido = this.partidos.getLinhas().get(politico.getPartido());
            tempPartido.add(chavePolitico);
            // armazena chave no diretório cargo
            List<String> tempCargo = this.cargos.getLinhas().get(politico.getCargo());
            tempCargo.add(chavePolitico);
            // armazena chave no diretório patrimonio
            List<String> tempPatrimonio = this.patrimonio.getLinhas().get(verificaValorEnum(politico));
            tempPatrimonio.add(chavePolitico);

            TelaCadastro.getInstance().politicoAddOuRem("cadastrado");
        } else {
            // Criar exceção para tal
            System.out.println("Político com CPF já cadastrado");
        }

    }

    public void removePolitico(){
        String chave = TelaCadastro.getInstance().removerPolitico();
        Politico politicoARemover = this.politicos.get(chave);
        this.politicos.remove(chave);
        this.partidos.getLinhas().get(politicoARemover.getPartido()).remove(chave);
        this.cargos.getLinhas().get(politicoARemover.getCargo()).remove(chave);
        this.patrimonio.getLinhas().get(verificaValorEnum(politicoARemover)).remove(chave);

        TelaCadastro.getInstance().politicoAddOuRem("removido");
    }

    public Enum verificaValorEnum(Politico politico){
        int tempPatrimonio = politico.getPatrimonio();
        if (tempPatrimonio < 301)
            return PatrimonioEnum.ABAIXO_DE_300;
        if (tempPatrimonio < 601)
            return PatrimonioEnum.ENTRE_301_E_600;
        if (tempPatrimonio < 901)
            return PatrimonioEnum.ENTRE_601_E_900;
        if (tempPatrimonio < 1201)
            return PatrimonioEnum.ENTRE_901_E_1200;
        else
            return PatrimonioEnum.ACIMA_DE_1200;
    }

    public void exibeBusca(List<String> lista){
        for (String chave : lista){
            Politico politico = this.politicos.get(chave);
            TelaBusca.getInstance().exibePolitico(politico);
        }
    }

    public void exibeCadastrados(){
        List<String> chaves = new ArrayList<>(this.politicos.keySet());
        this.exibeBusca(chaves);
    }

    private void testCode(){
        System.out.println("Partidos: " + partidos.getLinhas());
        System.out.println("Cargos: " + cargos.getLinhas());
        System.out.println("Patrimonio: " + patrimonio.getLinhas());
    }

    private void cargaDeDadosInicial(){
        Politico politicoTeste1 = new Politico("123","Politico Teste", PartidosEnum.PDT, CargosEnum.GOVERNADOR, 210);
        Politico politicoTeste2 = new Politico("354","Outro Politico", PartidosEnum.PT, CargosEnum.PRESIDENTE, 650);
        Politico politicoTeste3 = new Politico("453","Mais Um Politico", PartidosEnum.MDB, CargosEnum.SENADOR, 1400);
        Politico politicoTeste4 = new Politico("386","Penúltimo Politico", PartidosEnum.PP, CargosEnum.GOVERNADOR, 1123);
        Politico politicoTeste5 = new Politico("313","Último Politico", PartidosEnum.PSDB, CargosEnum.DEP_ESTADUAL, 153);
        this.adicionaCargaDeDadosInicial(politicoTeste1);
        this.adicionaCargaDeDadosInicial(politicoTeste2);
        this.adicionaCargaDeDadosInicial(politicoTeste3);
        this.adicionaCargaDeDadosInicial(politicoTeste4);
        this.adicionaCargaDeDadosInicial(politicoTeste5);
    }

    private void adicionaCargaDeDadosInicial(Politico politico){
        String chavePolitico = politico.getCpf();
        this.politicos.put(chavePolitico, politico);
        List<String> tempPartido = this.partidos.getLinhas().get(politico.getPartido());
        tempPartido.add(chavePolitico);
        List<String> tempCargo = this.cargos.getLinhas().get(politico.getCargo());
        tempCargo.add(chavePolitico);
        List<String> tempPatrimonio = this.patrimonio.getLinhas().get(verificaValorEnum(politico));
        tempPatrimonio.add(chavePolitico);
    }

}