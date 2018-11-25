package controller;

import model.*;
import view.TelaPrincipal;

public class ControladorPrincipal {
    private static ControladorPrincipal instance;

    public static ControladorPrincipal getInstance() {
        if (instance == null)
            instance = new ControladorPrincipal();

        return instance;
    }

    private ControladorPrincipal () {
        testCode();
        boolean executaPrograma = true;
        TelaPrincipal.getInstance().iniciaTela();
        do {
            switch (TelaPrincipal.getInstance().telaInicial()) {
                case 1:
                    ControladorDados.getInstance().cadastraPolitico(ControladorDados.getInstance().telaCadastraPolitico());
                    break;
                case 2:
                    ControladorDados.getInstance().removePolitico(ControladorDados.getInstance().telaRemovePolitico());
                    break;
                case 3:
                    TelaPrincipal.getInstance().emConstrucao();
                    break;
                case 4:
                    TelaPrincipal.getInstance().emConstrucao();
                    break;
                case 5:
                    TelaPrincipal.getInstance().emConstrucao();
                    break;
                case 0:
                    TelaPrincipal.getInstance().encerrandoPrograma();
                    executaPrograma = false;
                    break;
                default:
                    TelaPrincipal.getInstance().opcaoInvalida();
            }
        } while(executaPrograma == true);

    }

    public void testCode(){
        Politico politicoTeste1 = new Politico("123","Politico Teste", PartidosEnum.PDT, CargosEnum.GOVERNADOR, 210);
        Politico politicoTeste2 = new Politico("354","Outro Politico", PartidosEnum.PT, CargosEnum.PRESIDENTE, 650);
        Politico politicoTeste3 = new Politico("453","Mais Um Politico", PartidosEnum.MDB, CargosEnum.SENADOR, 1400);
        ControladorDados.getInstance().cadastraPolitico(politicoTeste1);
        ControladorDados.getInstance().cadastraPolitico(politicoTeste2);
        ControladorDados.getInstance().cadastraPolitico(politicoTeste3);
//        System.out.println("ANTES DA REMOÇÃO:");
        System.out.println("Partidos: " + ControladorDados.getInstance().partidos.getLinhas());
        System.out.println("Cargos: " + ControladorDados.getInstance().cargos.getLinhas());
        System.out.println("Patrimonio: " + ControladorDados.getInstance().patrimonio.getLinhas());
//        ControladorDados.getInstance().removePolitico("354");
//        System.out.println("\nDEPOIS DA REMOÇÃO:");
//        System.out.println("Partidos: " + ControladorDados.getInstance().partidos.getLinhas());
//        System.out.println("Cargos: " + ControladorDados.getInstance().cargos.getLinhas());
//        System.out.println("Patrimonio: " + ControladorDados.getInstance().patrimonio.getLinhas());
    }


}