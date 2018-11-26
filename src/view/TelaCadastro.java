package view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TelaCadastro {
    private static TelaCadastro instance;

    public static TelaCadastro getInstance() {
        if (instance == null)
            instance = new TelaCadastro();

        return instance;
    }

    private TelaCadastro(){

    }

    public String inputCpf(){
        Scanner sc = new Scanner(System.in);
        System.out.print("\nDigite o CPF: ");
        return sc.nextLine();
    }

    public String inputNome(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o Nome: ");
        return sc.nextLine();
    }

    public int inputPartido(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o partido: ");
        return this.verificaInput(sc.nextLine());
    }

    public int inputCargo(){
        Scanner sc = new Scanner(System.in);
        System.out.println("CARGOS:");
        System.out.println("1. Presidente");
        System.out.println("2. Governador");
        System.out.println("3. Senador");
        System.out.println("4. Deputado Federal");
        System.out.println("5. Deputado Estadual");
        System.out.print("Digite a opção numérica de acordo com o cargo: ");
        return this.verificaInput(sc.nextLine());
    }

    public int inputPatrimonio(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o valor de patrimonio do político (em mil reais): ");
        return this.verificaInput(sc.nextLine());
    }

    public String removerPolitico(){
        Scanner sc = new Scanner(System.in);
        System.out.print("\nDigite o CPF do politico a ser removido: ");
        return sc.nextLine();
    }

    public void politicoAddOuRem(String acao){
        System.out.println("\nPolítico " + acao + " com sucesso");
    }

    public void politicoExistente(){
        System.out.println("\nPolítico já está cadastrado");
    }

    public void politicoInexistente(){
        System.out.println("\nNão existe político com esse CPF");
    }

    public void listaVazia(){
        System.out.println("\nSem resultados disponíveis");
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