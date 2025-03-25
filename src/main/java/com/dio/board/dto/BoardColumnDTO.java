package com.dio.board.dto;

import com.dio.board.model.TipoColunaEnum;

public record BoardColumnDTO(Long id,
                             String nome,
                             TipoColunaEnum tipo,
                             int cardsAmount) {
}
