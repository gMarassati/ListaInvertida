package controller;

import view.TelaPrincipal;

public class ControladorPrincipal {
    private static ControladorPrincipal instance;

    public static ControladorPrincipal getInstance() {
        if (instance == null)
            instance = new ControladorPrincipal();

        return instance;
    }

    private ControladorPrincipal () {
        boolean executaPrograma = true;
        TelaPrincipal.getInstance().iniciaTela();
        do {
            switch (TelaPrincipal.getInstance().telaInicial()) {
                case 1:
                    ControladorDados.getInstance().cadastraPolitico();
                    break;
                case 2:
                    ControladorDados.getInstance().removePolitico();
                    break;
                case 3:
                    ControladorBusca.getInstance().buscaSimples();
                    break;
                case 4:
                    ControladorBusca.getInstance().buscaComposta();
                    break;
                case 5:
                    ControladorDados.getInstance().exibeCadastrados();
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



}