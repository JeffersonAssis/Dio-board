package com.dio.board.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name = "BLOCKS")
@Data
public class Block {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_bloqueio", nullable = false)
    private LocalDateTime dataBloqueio = LocalDateTime.now();

    @Column(name = "motivo_bloqueio", nullable = false)
    private String motivoBloqueio;

    @Column(name = "data_desbloqueio")
    private LocalDateTime dataDesbloqueio;

    @Column(name = "motivo_desbloqueio")
    private String motivoDesbloqueio;

    @ManyToOne
    @JoinColumn(name = "card_id", nullable = false)
    private Card card;
    
}