package com.dio.board.service;

import java.util.List;

import com.dio.board.dao.BoardDao;
import com.dio.board.model.Board;

public class BoardService {
    
    private BoardDao boardDAO = new BoardDao();

    public void salvar(Board board) {
        boardDAO.salvar(board);
    }

    public Board buscarPorId(Long id) {
        return boardDAO.buscarPorId(id);
    }

    public List<Board> listarTodos() {
        return boardDAO.listarTodos();
    }

    public void atualizar(Board board) {
        boardDAO.atualizar(board);
    }

    public boolean deletar(Long id) {
    	return boardDAO.deletar(id);
        
    }
}