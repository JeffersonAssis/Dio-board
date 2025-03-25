package com.dio.board.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Entity
@Table(name = "COLUNABORDS", uniqueConstraints = {
        @UniqueConstraint(name = "id_tipo_uk", columnNames = {"board_id", "ordem"})
})
@Data
public class ColunaBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private int ordem;

  
    private TipoColunaEnum tipo;

    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;
    

  
    @OneToMany(mappedBy = "coluna", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Card> cards = new ArrayList<>();
    
}
