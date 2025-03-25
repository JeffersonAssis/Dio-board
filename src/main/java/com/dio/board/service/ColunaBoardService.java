package com.dio.board.service;

import java.util.List;

import com.dio.board.dao.ColunaBoardDao;
import com.dio.board.model.ColunaBoard;

public class ColunaBoardService {
	
	 private ColunaBoardDao colunaBoardDao = new ColunaBoardDao();

	    public void salvar(ColunaBoard block) {
	        colunaBoardDao.salvar(block);
	    }

	    public ColunaBoard buscarPorId(Long id) {
	        return colunaBoardDao.buscarPorId(id);
	    }

	    public List<ColunaBoard> listarTodos() {
	        return colunaBoardDao.listarTodos();
	    }

	    public void atualizar(ColunaBoard block) {
	        colunaBoardDao.atualizar(block);
	    }

	    public void deletar(Long id) {
	        colunaBoardDao.deletar(id);
	    }

}
