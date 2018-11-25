package view;

public class TelaBusca {
    private static TelaBusca instance;

    public static TelaBusca getInstance() {
        if (instance == null)
            instance = new TelaBusca();

        return instance;
    }

    private TelaBusca(){

    }

}