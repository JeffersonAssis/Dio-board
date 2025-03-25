package com.dio.board.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

import com.dio.board.dao.CardDao;
import com.dio.board.dto.BoardColumnInfoDTO;
import com.dio.board.model.Card;
import static com.dio.board.model.TipoColunaEnum.CANCELADO;
import static com.dio.board.model.TipoColunaEnum.FECHADO;


public class CardService {

	 private CardDao cardDAO = new CardDao();

	    public void salvar(Card card) {
	    	cardDAO.salvar(card);
	    }

	    public Card buscarPorId(Long id) {
	        return cardDAO.buscarPorId(id);
	    }

	    public List<Card> listarTodos() {
	        return cardDAO.listarTodos();
	    }

	    public void atualizar(Long id,Card card) {
	    	cardDAO.atualizar(card);
	    }

	    public void deletar(Long id) {
	    	cardDAO.deletar(id);
	    }

			 public void moveToNextColumn(final Long cardId, final List<BoardColumnInfoDTO> boardColumnsInfo) throws SQLException{
                 
            var card = buscarPorId(cardId);
						int tam = card.getBlocks().size()-1;
            if(card.getBlocks().get(tam).getMotivoDesbloqueio().isBlank()){
								System.out.println("O card está bloqueado ");
						}else if(Objects.isNull(card)){
								System.out.printf("O card de id %s não foi encontrado".formatted(cardId)+"/n");
						}
						
            BoardColumnInfoDTO currentColumn = boardColumnsInfo.stream()
                    .filter(bc -> bc.id().equals(card.getId()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("O card informado pertence a outro board"));
            if (currentColumn.tipo().equals(FECHADO)){
                System.out.println("O card já foi finalizado");
            }
            var nextColumn = boardColumnsInfo.stream()
                    .filter(bc -> bc.ordem() == currentColumn.ordem() + 1)
                    .findFirst().orElseThrow(() -> new IllegalStateException("O card está cancelado"));
            //card.atualizar(nextColumn.id(), cardId);
       
        }
}
