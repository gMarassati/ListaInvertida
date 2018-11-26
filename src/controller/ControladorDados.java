package controller;

import model.*;
import view.TelaAvisos;
import view.TelaBusca;
import view.TelaCadastro;

import java.util.*;

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
        String cpf = TelaCadastro.getInstance().inputCpf();
        // Se CPF ainda não estiver cadastrado, continuara com as telas de cadastros e criará um objeto Politico
        if(!this.politicos.containsKey(cpf)){
            try {
                String nome = TelaCadastro.getInstance().inputNome();
                Enum partido = PartidosEnum.porNumero(TelaCadastro.getInstance().inputPartido());
                Enum cargo = CargosEnum.porNumero(TelaCadastro.getInstance().inputCargo());
                int patrimonio = TelaCadastro.getInstance().inputPatrimonio();

                Politico politico = new Politico(cpf, nome, partido, cargo, patrimonio);

                // Cadastro do politico. Utiliza CPF como as chave

                String chavePolitico = politico.getCpf();
                this.politicos.put(chavePolitico, politico);
                // armazena chave correspondente nos diretórios
                List<String> tempPartido = this.partidos.getLinhas().get(politico.getPartido());
                tempPartido.add(chavePolitico);
                List<String> tempCargo = this.cargos.getLinhas().get(politico.getCargo());
                tempCargo.add(chavePolitico);
                List<String> tempPatrimonio = this.patrimonio.getLinhas().get(verificaValorEnum(politico));
                tempPatrimonio.add(chavePolitico);

                TelaCadastro.getInstance().politicoAddOuRem("cadastrado");
            } catch (IllegalArgumentException e){
                TelaAvisos.getInstance().exibeErro(e);
            } catch (InputMismatchException e){
                TelaAvisos.getInstance().exibeErro(e);
            }
        } else {
            TelaCadastro.getInstance().politicoExistente();
        }

    }

    public void removePolitico(){
        String chave = TelaCadastro.getInstance().removerPolitico();
        // Se a busca não encontrar o político, o objeto será nulo, e não continuará com o processo
        Politico politicoARemover = this.politicos.get(chave);
        if(politicoARemover != null) {
            this.politicos.remove(chave);
            // Remove a chave correspondente dos diretórios
            this.partidos.getLinhas().get(politicoARemover.getPartido()).remove(chave);
            this.cargos.getLinhas().get(politicoARemover.getCargo()).remove(chave);
            this.patrimonio.getLinhas().get(verificaValorEnum(politicoARemover)).remove(chave);

            TelaCadastro.getInstance().politicoAddOuRem("removido");
        } else {
            TelaCadastro.getInstance().politicoInexistente();
        }
    }

    // verifica em qual índice no diretório o político ficará, de acordo com o seu patrimônio
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

    // utilizado para todas as exibições de busca
    public void exibeBusca(List<String> lista){
        if (!lista.isEmpty()) {
            for (String chave : lista) {
                Politico politico = this.politicos.get(chave);
                TelaBusca.getInstance().exibePolitico(politico);
            }
        } else {
            TelaCadastro.getInstance().listaVazia();
        }
    }
    // exibe todos os politicos
    public void exibeCadastrados(){
        List<String> chaves = new ArrayList<>(this.politicos.keySet());
        if (!chaves.isEmpty())
            this.exibeBusca(chaves);
        else
            TelaCadastro.getInstance().listaVazia();
    }

//    public boolean verificaCPF(String cpf){
//        if(cpf.length() != 11){
//            System.out.println("CPF com tamanho invalido");
//            return false;
//        }
//        try {
//            for(int i = 0; i < 11; i++){
//                Integer.parseInt(String.valueOf(cpf.charAt(i)));
//            }
//        } catch(NumberFormatException nfe) {
//            return false;//verifica se todos os dados são numericos
//        }
//        return calculaCPF(cpf);
//    }

//    public boolean calculaCPF(String cpf){
//        int soma = 0; //variavel que faz a soma dos termos do cpf
//        int k = 10; // variavel que multiplica os termos do cpf antes da soma
//        for(int i = 0; i < 9; i++){
//            soma = soma + Character.getNumericValue(cpf.charAt(i)) * k;
//            k--;
//        }
//        int digitoVerificador = 11 - (soma%11);
//        if (digitoVerificador > 9){
//            if(Character.getNumericValue(cpf.charAt(9)) != 0){
//                return false;
//            }
//        }
//        else if(digitoVerificador <= 9){
//            if(Character.getNumericValue(cpf.charAt(9)) != digitoVerificador){
//                return false;
//            }
//        }
//        soma = 0;
//        k = 11;
//        for(int i = 0; i < 10; i++){
//            soma = soma + Character.getNumericValue(cpf.charAt(i)) * k;
//            k--;
//        }
//        digitoVerificador = 11 - (soma%11);
//        if (digitoVerificador > 9){
//            if(Character.getNumericValue(cpf.charAt(10)) != 0){
//                return false;
//            }
//        }
//        else if(digitoVerificador <= 9){
//            if(Character.getNumericValue(cpf.charAt(10)) != digitoVerificador){
//                return false;
//            }
//        }
//        return true;
//    }

    // FUNÇÕES PARA INICIAR PROGRAMA COM DADOS:

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