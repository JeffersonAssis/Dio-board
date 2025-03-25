package com.dio.board.dto;

import com.dio.board.model.TipoColunaEnum;

public record BoardColumnInfoDTO(Long id, int ordem, TipoColunaEnum tipo) {} 
