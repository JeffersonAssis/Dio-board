package com.dio.board.ui;

import java.sql.SQLException;
import java.util.Scanner;

import com.dio.board.dto.BoardColumnInfoDTO;
import com.dio.board.model.Board;
import com.dio.board.model.Card;
import com.dio.board.model.ColunaBoard;
import com.dio.board.service.BoardService;
import com.dio.board.service.CardService;
import com.dio.board.service.ColunaBoardService;

import static com.dio.board.model.TipoColunaEnum.CANCELADO;
import static com.dio.board.model.TipoColunaEnum.FECHADO;
import static com.dio.board.model.TipoColunaEnum.INICIAL;
import static com.dio.board.model.TipoColunaEnum.PENDENTE;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BoardMenu {

	private final Scanner scanner = new Scanner(System.in);
	

	private final Board entity;

	public void execute() {
		try {
			System.out.printf("Bem vindo ao board %s, selecione a operação desejada\n", entity.getId());
			var option = -1;
			while (option != 9) {
				System.out.println("1 - Criar um card");
				System.out.println("*2 - Mover um card");
				System.out.println("*3 - Bloquear um card");
				System.out.println("*4 - Desbloquear um card");
				System.out.println("*5 - Cancelar um card");
				System.out.println("6 - Ver board");
				System.out.println("7 - Ver coluna com cards");
				System.out.println("8 - Ver card");
				System.out.println("9 - Voltar para o menu anterior um card");
				System.out.println("10 - Sair");
				option = scanner.nextInt();
				switch (option) {
				case 1 -> createCard();
				case 2 -> moveCardToNextColumn();
				case 3 -> blockCard();
				case 4 -> unblockCard();
				case 5 -> cancelCard();
				case 6 -> showBoard();
				case 7 -> showColumn();
				case 8 -> showCard();
				case 9 -> System.out.println("Voltando para o menu anterior");
				case 10 -> System.exit(0);
				default -> System.out.println("Opção inválida, informe uma opção do menu");
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.exit(0);
		}
	}

	private void createCard() throws SQLException {
		var card = new Card();
		System.out.println("Informe o título do card");
		card.setTitulo(scanner.next());
		System.out.println("Informe a descrição do card");
		card.setDescricao(scanner.next());
		for (var c : entity.getColunas()) {
			if (c.getTipo().equals(INICIAL)) {
				card.setColuna(c);
			}
		}

		var cardService = new CardService();

		cardService.salvar(card);

	}

	private void moveCardToNextColumn() throws SQLException {
		System.out.println("Informe o id do card que deseja mover para a próxima coluna");
		var cardId = scanner.nextLong();
		var boardColumnsInfo = entity.getColunas().stream()
				.map(bc -> new BoardColumnInfoDTO(bc.getId(), bc.getOrdem(), bc.getTipo())).toList();
		
				new CardService().moveToNextColumn(cardId, boardColumnsInfo);
		
	}

	private void blockCard() throws SQLException {
		System.out.println("Informe o id do card que será bloqueado");
		var cardId = scanner.nextLong();
		System.out.println("Informe o motivo do bloqueio do card");
		var reason = scanner.next();
	
		var boardColumnsInfo = entity.getColunas().stream()
				.map(bc -> new BoardColumnInfoDTO(bc.getId(), bc.getOrdem(), bc.getTipo())).toList();

			//new CardService().block(cardId, reason, boardColumnsInfo);
		
	}

	private void unblockCard() throws SQLException {
		System.out.println("Informe o id do card que será desbloqueado");
		var cardId = scanner.nextLong();
		System.out.println("Informe o motivo do desbloqueio do card");
		var reason = scanner.next();
		
			//new CardService(connection).unblock(cardId, reason);
	
	}

	private void cancelCard() throws SQLException {
		System.out.println("Informe o id do card que deseja mover para a coluna de cancelamento");
		var cardId = scanner.nextLong();
		var cancelColumn = entity.getColunas();
		var boardColumnsInfo = entity.getColunas().stream()
				.map(bc -> new BoardColumnInfoDTO(bc.getId(), bc.getOrdem(), bc.getTipo())).toList();
		
			//new CardService().cancel(cardId, cancelColumn.getId(), boardColumnsInfo);
	
	}

	private void showBoard() throws SQLException {
		var b = new BoardService().buscarPorId(entity.getId());
		
				System.out.printf("Board [%s,%s]\n", b.getId(), b.getNome());
				b.getColunas().forEach(c -> System.out.printf("Coluna [%s] tipo: [%s] tem %s cards\n", c.getNome(), c.getTipo(),
						c.getNome()));
			
	}

	private void showColumn() throws SQLException {
		var columnsIds = entity.getColunas().stream().map(ColunaBoard::getId).toList();
		var selectedColumnId = -1L;
		while (!columnsIds.contains(selectedColumnId)) {
			System.out.printf("Escolha uma coluna do board %s pelo id\n", entity.getNome());
			entity.getColunas()
					.forEach(c -> System.out.printf("%s - %s [%s]\n", c.getId(), c.getNome(), c.getTipo()));
			selectedColumnId = scanner.nextLong();
		}


			var co = new ColunaBoardService().buscarPorId(selectedColumnId);
			
				System.out.printf("Coluna %s tipo %s\n", co.getNome(), co.getTipo());
				co.getCards().forEach(ca -> System.out.printf("Card %s - %s\nDescrição: %s", ca.getId(), ca.getTitulo(),
						ca.getDescricao()+"\n"));
			
		
	}

	private void showCard() throws SQLException {
		System.out.println("Informe o id do card que deseja visualizar");
		var selectedCardId = scanner.nextLong();
			var c = new CardService().buscarPorId(selectedCardId);
			
			if(c.getId()>0) {
				
				System.out.printf("Card %s - %s.\n", c.getId(), c.getTitulo());
				System.out.printf("Descrição: %s\n", c.getDescricao());
				int i = c.getBlocks().size()-1;
				System.out.println(c.getBlocks().get(i).getMotivoDesbloqueio().isEmpty() ? "Está bloqueado. Motivo: " + c.getBlocks().get(i).getMotivoBloqueio() : "Não está bloqueado");
				System.out.printf("Já foi bloqueado %s vezes\n", c.getBlocks().size());
				System.out.printf("Está no momento na coluna %s - %s\n", c.getColuna().getId(), c.getColuna().getNome());
			}else {
			
			System.out.printf("Não existe um card com o id %s\n", selectedCardId);
			}
		}
	

}
