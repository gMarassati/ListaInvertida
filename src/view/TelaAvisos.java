package view;

public class TelaAvisos {
    private static TelaAvisos instance;

    public static TelaAvisos getInstance() {
        if (instance == null)
            instance = new TelaAvisos();

        return instance;
    }

    private TelaAvisos(){

    }

    // AVISOS GERAIS :

    public void opcaoInvalida(){
        System.out.println("\nOpção inválida.");
    }

    public void exibeErro(Exception msg){
        System.out.println("\nErro: " + msg.getMessage());
    }

}
