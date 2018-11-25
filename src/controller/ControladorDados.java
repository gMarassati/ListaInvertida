package controller;

import model.*;
import view.TelaCadastro;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControladorDados {
    private Map<String, Politico> politicos = new HashMap<>();
    Diretorio partidos = new Diretorio("Partido", PartidosEnum.values());
    Diretorio cargos = new Diretorio("Cargo", CargosEnum.values());
    Diretorio patrimonio = new Diretorio("Patrimonio", PatrimonioEnum.values());

    private static ControladorDados instance;

    public static ControladorDados getInstance() {
        if (instance == null)
            instance = new ControladorDados();

        return instance;
    }

    private ControladorDados() {

    }

    public Politico telaCadastraPolitico(){
        String cpf = TelaCadastro.getInstance().inputCpf();
        String nome = TelaCadastro.getInstance().inputNome();
        Enum partido = PartidosEnum.porNumero(TelaCadastro.getInstance().inputPartido());
        Enum cargo = CargosEnum.porNumero(TelaCadastro.getInstance().inputCargo());
        int patrimonio = TelaCadastro.getInstance().inputPatrimonio();

        return new Politico(cpf, nome, partido, cargo, patrimonio);
    }

    public void cadastraPolitico(Politico politico){
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

            TelaCadastro.getInstance().politicoCadastrado();
        } else {
            // Criar exceção para tal
            System.out.println("Político com CPF já cadastrado");
        }

        // Código Teste
//        this.testCode();

    }

    public String telaRemovePolitico(){
        return TelaCadastro.getInstance().removerPolitico();
    }

    public void removePolitico(String chave){
        Politico politicoARemover = this.politicos.get(chave);
        this.politicos.remove(chave);
        this.partidos.getLinhas().get(politicoARemover.getPartido()).remove(chave);
        this.cargos.getLinhas().get(politicoARemover.getCargo()).remove(chave);
        this.patrimonio.getLinhas().get(verificaValorEnum(politicoARemover)).remove(chave);

        // Código Teste
        this.testCode();

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

    public void testCode(){
        System.out.println("Partidos: " + partidos.getLinhas());
        System.out.println("Cargos: " + cargos.getLinhas());
        System.out.println("Patrimonio: " + patrimonio.getLinhas());
    }



}