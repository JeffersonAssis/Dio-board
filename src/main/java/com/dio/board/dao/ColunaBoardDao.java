package com.dio.board.dao;

import java.util.List;

import com.dio.board.config.Conexao;
import com.dio.board.model.ColunaBoard;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class ColunaBoardDao {
	
	static final EntityManager em = Conexao.getConn();
	
	public void salvar(ColunaBoard coluna) {
 
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(coluna);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        }
    }

    public ColunaBoard buscarPorId(Long id) {
        return em.find(ColunaBoard.class, id);
    }

    public List<ColunaBoard> listarTodos() {
        return em.createQuery("FROM ColunaBoard", ColunaBoard.class).getResultList();
    }

    public void atualizar(ColunaBoard coluna) {
       
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(coluna);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        }
    }

    public void deletar(Long id) {
        
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            ColunaBoard coluna = em.find(ColunaBoard.class, id);
            if (coluna != null) {
                em.remove(coluna);
                tx.commit();
            }
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        }
    }
}
