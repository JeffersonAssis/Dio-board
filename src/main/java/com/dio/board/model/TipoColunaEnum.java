package com.dio.board.model;

import java.util.stream.Stream;

public enum TipoColunaEnum {
	
	 INICIAL, FECHADO, CANCELADO, PENDENTE;

    public static TipoColunaEnum buscarNome(final String name){
        return Stream.of(TipoColunaEnum.values())
                .filter(b -> b.name().equals(name))
                .findFirst().orElseThrow();
    }

}
