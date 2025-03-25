package com.dio.board.service;

import java.util.List;

import com.dio.board.dao.BlockDao;
import com.dio.board.model.Block;


public class BlockService {
	
	 private BlockDao blockDao = new BlockDao();

	    public void salvar(Block block) {
	        blockDao.salvar(block);
	    }

	    public Block buscarPorId(Long id) {
	        return blockDao.buscarPorId(id);
	    }

	    public List<Block> listarTodos() {
	        return blockDao.listarTodos();
	    }

	    public void atualizar(Block block) {
	        blockDao.atualizar(block);
	    }

	    public void deletar(Long id) {
	        blockDao.deletar(id);
	    }
}
