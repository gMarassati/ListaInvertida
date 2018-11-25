package model;

public enum PatrimonioEnum {

    ABAIXO_DE_300(1),
    ENTRE_301_E_600(2),
    ENTRE_601_E_900(3),
    ENTRE_901_E_1200(4),
    ACIMA_DE_1200(5);

    private final int valor;

    PatrimonioEnum(int indice){
        valor = indice;
    }

    public int getValor(){
        return valor;
    }

    public static PatrimonioEnum porNumero(int numero) {
        for (PatrimonioEnum patrimonio : PatrimonioEnum.values()) {
            if (numero == patrimonio.getValor())
                return patrimonio;
        }
        throw new IllegalArgumentException("Valor inválido");
    }

}