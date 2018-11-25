package model;

public enum PartidosEnum {
    PP(11), PDT(12), PT(13), MDB(15), PSTU(16), PSL(17), REDE(18), DEM(25), NOVO(30), PSDB(45), PV(43), PSOL(50);

    private final int partido;

    PartidosEnum(int numeroPartido){
        partido = numeroPartido;
    }

    public int getValor(){
        return partido;
    }

    public static PartidosEnum porNumero(int numero) {
        for (PartidosEnum partido : PartidosEnum.values()) {
            if (numero == partido.getValor())
                return partido;
        }
        throw new IllegalArgumentException("Partido não cadastrado no sistema");
    }

}
