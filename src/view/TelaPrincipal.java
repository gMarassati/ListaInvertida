package view;

import java.util.Scanner;

public class TelaPrincipal {
    private static TelaPrincipal instance;

    public static TelaPrincipal getInstance() {
        if (instance == null)
            instance = new TelaPrincipal();

        return instance;
    }

    private TelaPrincipal(){

    }

    public void iniciaTela(){
        System.out.println("*** BEM VINDO AO SISTEMA DE LISTA INVERTIDA ***");
    }

    public int telaInicial(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\nSELECIONE UMA DAS OPÇÕES ABAIXO\n");
        System.out.println("1. Cadastrar político");
        System.out.println("2. Remover político");
        System.out.println("3. Fazer uma busca simples");
        System.out.println("4. Fazer uma busca composta (dois parâmetros)");
        System.out.println("5. Exibir políticos cadastrados");
        System.out.println("0. Sair");
        System.out.print("\nOpção escolhida: ");
        return sc.nextInt();
    }

    public void emConstrucao(){
        System.out.println("\nEm construção\n");
    }

    public void opcaoInvalida(){
        System.out.println("\nOpção inválida. Verifique número digitado.\n");
    }


    public void encerrandoPrograma() {
        System.out.println("\nEncerrando programa...");
    }

}