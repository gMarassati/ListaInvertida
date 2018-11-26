package view;

import model.Politico;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TelaBusca {
    private static TelaBusca instance;

    public static TelaBusca getInstance() {
        if (instance == null)
            instance = new TelaBusca();

        return instance;
    }

    private TelaBusca(){

    }

    public int telaBuscaSimples(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\nSELECIONE COMO DESEJA FAZER A BUSCA");
        System.out.println("1. Por Partido");
        System.out.println("2. Por Cargo");
        System.out.println("3. Por Patrimonio");
        System.out.println("0. Voltar");
        System.out.print("\nOpção escolhida: ");
        return verificaInput(sc.nextLine());
    }

    public int buscaPoliticoPartido(){
        Scanner sc = new Scanner(System.in);
        System.out.print("\nDigite o número do partido a ser buscado: ");
        return verificaInput(sc.nextLine());
    }

    public int buscaPoliticoCargo(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEscolha qual o cargo desejado:");
        System.out.println("1. Presidente");
        System.out.println("2. Governador");
        System.out.println("3. Senador");
        System.out.println("4. Deputado Federal");
        System.out.println("5. Deputado Estadual");
        System.out.print("Digite a opção numérica de acordo com o cargo: ");
        return verificaInput(sc.nextLine());
    }

    public int buscaPoliticoPatrimonio(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEscolha qual a faixa desejada (em mil reais):");
        System.out.println("1. Menor que 300");
        System.out.println("2. Entre 301 e 600");
        System.out.println("3. Entre 601 e 900");
        System.out.println("4. Entre 901 e 1200");
        System.out.println("5. Maior que 1200");
        System.out.print("Opção digitada: ");
        return verificaInput(sc.nextLine());
    }

    public int buscaComposta(String busca){
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEscolha a " + busca + " opção de busca:");
        System.out.println("1. Por Partido");
        System.out.println("2. Por Cargo");
        System.out.println("3. Por Patrimonio");
        return verificaInput(sc.nextLine());
    }

    public void exibePolitico(Politico politico){
        System.out.println("\nNome: " + politico.getNome());
        System.out.println("CPF: " + politico.getCpf());
        System.out.println("Partido: " + politico.getPartido());
        System.out.println("Cargo: " + politico.getCargo());
        System.out.println("Patrimonio (em mil): " + politico.getPatrimonio() + "\n");
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