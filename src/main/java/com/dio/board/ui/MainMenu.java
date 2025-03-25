package com.dio.board.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.dio.board.model.Board;
import com.dio.board.model.ColunaBoard;
import com.dio.board.model.TipoColunaEnum;
import com.dio.board.service.BoardService;
import static com.dio.board.model.TipoColunaEnum.CANCELADO;
import static com.dio.board.model.TipoColunaEnum.FECHADO;
import static com.dio.board.model.TipoColunaEnum.INICIAL;
import static com.dio.board.model.TipoColunaEnum.PENDENTE;

public class MainMenu {

    private final Scanner scanner = new Scanner(System.in);

    public void execute() throws SQLException {
        System.out.println("Bem vindo ao gerenciador de boards, escolha a opção desejada");
        var option = -1;
        while (true){
            System.out.println("1 - Criar um novo board");
            System.out.println("2 - Selecionar um board existente");
            System.out.println("3 - Excluir um board");
            System.out.println("4 - Sair");
            option = scanner.nextInt();
            switch (option){
                case 1 -> createBoard();
                case 2 -> selectBoard();
                case 3 -> deleteBoard();
                case 4 -> System.exit(0);
                default -> System.out.println("Opção inválida, informe uma opção do menu");
            }
        }
    }

    private void createBoard() throws SQLException {
        Board entity = new Board();
        var service = new BoardService();
        System.out.println("Informe o nome do seu board");
        entity.setNome(scanner.next());

        System.out.println("Seu board terá colunas além das 3 padrões? Se sim informe quantas, senão digite '0'");
        var addColuna = scanner.nextInt();

        List<ColunaBoard> coluna = new ArrayList<>();

        System.out.println("Informe o nome da coluna inicial do board");
        var initialColumnName = scanner.next();
        var initialColumn = criarColuna(initialColumnName, INICIAL , 0);
        coluna.add(initialColumn);

        for (int i = 0; i < addColuna; i++) {
            System.out.println("Informe o nome da coluna de tarefa pendente do board");
            var pendingColumnName = scanner.next();
            var pendingColumn = criarColuna(pendingColumnName, PENDENTE, i + 1);
            coluna.add(pendingColumn);
        }

        System.out.println("Informe o nome da coluna final");
        var finalColumnName = scanner.next();
        var finalColumn = criarColuna(finalColumnName, FECHADO, addColuna + 1);
        coluna.add(finalColumn);

        System.out.println("Informe o nome da coluna de cancelamento do baord");
        var cancelColumnName = scanner.next();
        var cancelColumn = criarColuna(cancelColumnName, CANCELADO , addColuna + 2);
        coluna.add(cancelColumn);

        entity.setColunas(coluna);
            for (ColunaBoard c : coluna) {
                c.setBoard(entity);
            }


        service.salvar(entity);
        
        System.out.println(entity.getNome()+ " -  " + entity.getColunas().stream().map(ColunaBoard::getNome).collect(Collectors.joining(", ")));
        }

    

    private void selectBoard() throws SQLException {
        System.out.println("Informe o id do board que deseja selecionar");
        var id = scanner.nextLong();

            var queryService = new BoardService();
            var optional = queryService.buscarPorId(id);
            if(optional.getColunas().size() > 0) {
            	new BoardMenu(optional).execute();
            }
            System.out.printf("Não foi encontrado um board com id %s\n", id);
    }

    private void deleteBoard() throws SQLException {
        System.out.println("Informe o id do board que será excluido");
        var id = scanner.nextLong();
           var service = new BoardService();
            if (service.deletar(id)){
                System.out.printf("O board %s foi excluido\n", id);
            } else {
                System.out.printf("Não foi encontrado um board com id %s\n", id);
            }
        
    }

    private ColunaBoard criarColuna(final String name, final TipoColunaEnum tipo, final int ordem){
        var boardColumn = new ColunaBoard();
        boardColumn.setNome(name);
        boardColumn.setTipo(tipo);
        boardColumn.setOrdem(ordem);
        return boardColumn;
    }

}