package model;

public enum CargosEnum {
    PRESIDENTE(1),
    GOVERNADOR(2),
    SENADOR(3),
    DEP_FEDERAL(4),
    DEP_ESTADUAL(5);

    private final int cargo;

    CargosEnum(int numeroCargo){
        cargo = numeroCargo;
    }

    public int getValor(){
        return cargo;
    }

    public static CargosEnum porNumero(int numero) {
        for (CargosEnum cargo : CargosEnum.values()) {
            if (numero == cargo.getValor())
                return cargo;
        }
        throw new IllegalArgumentException("Opção inválida");
    }

}