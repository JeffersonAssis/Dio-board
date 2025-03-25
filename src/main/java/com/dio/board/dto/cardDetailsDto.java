package com.dio.board.dto;

import java.time.OffsetDateTime;

public record cardDetailsDto(
		Long id,
        String titulo,
        String descricao,
        boolean bloqueado,
        OffsetDateTime blockedAt,
        String blockReason,
        int blocksAmount,
        Long columnId,
        String columnName
		) {

}
