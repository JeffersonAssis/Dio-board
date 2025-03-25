package com.dio.board.dao;

import java.util.List;

import com.dio.board.config.Conexao;
import com.dio.board.model.Board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class BoardDao {
	
	static final EntityManager em = Conexao.getConn();

	public void salvar(Board board) {
		
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(board);
			tx.commit();
		} catch (Exception e) {
			if (tx.isActive())
				tx.rollback();
			e.printStackTrace();
		}
	}

	public Board buscarPorId(Long id) {
		return em.find(Board.class, id);
	}

	public List<Board> listarTodos() {
		return em.createQuery("FROM Board", Board.class).getResultList();
	}

	public void atualizar(Board board) {
		
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.merge(board);
			tx.commit();
		} catch (Exception e) {
			if (tx.isActive())
				tx.rollback();
			e.printStackTrace();
		}
	}

	public boolean deletar(Long id) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			Board board = em.find(Board.class, id);
			if (board != null) {
				em.remove(board);
				tx.commit();
			}
		} catch (Exception e) {
			if (tx.isActive())
				tx.rollback();
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
