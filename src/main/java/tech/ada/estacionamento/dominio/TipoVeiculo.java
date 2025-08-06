package tech.ada.estacionamento.dominio;

public enum TipoVeiculo {
    CARRO, MOTO, CAMINHAO;

    public static TipoVeiculo getFromOrdinal(int ordinal) {
        for (TipoVeiculo tipoVeiculo : TipoVeiculo.values()){
            if (tipoVeiculo.ordinal() == ordinal){
                return tipoVeiculo;
            }
        }
        throw new RuntimeException("Não há o tipo de veiculo informado");
    }
}
