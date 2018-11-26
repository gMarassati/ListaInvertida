package view;

import java.util.InputMismatchException;
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

    public int telaInicial() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nSELECIONE UMA DAS OPÇÕES ABAIXO\n");
        System.out.println("1. Cadastrar político");
        System.out.println("2. Remover político");
        System.out.println("3. Fazer uma busca simples");
        System.out.println("4. Fazer uma busca composta (dois parâmetros)");
        System.out.println("5. Exibir políticos cadastrados");
        System.out.println("0. Sair");
        System.out.print("\nOpção escolhida: ");
        return verificaInput(sc.nextLine());
    }

    public void encerrandoPrograma() {
        System.out.println("\nEncerrando programa...");
    }

    // se o input não for um inteiro válido (sem espaços), retorna exceção (tratada em outras funções)
    private int verificaInput(String input){
        try{
            int numero = Integer.parseInt(input);
            return numero;
        } catch(NumberFormatException e){
            throw new InputMismatchException("Deve digitar apenas números (sem espaços ou letras)");
        }
    }

}