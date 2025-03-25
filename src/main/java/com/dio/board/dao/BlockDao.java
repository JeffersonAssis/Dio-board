package com.dio.board.dao;

import java.util.List;

import com.dio.board.config.Conexao;
import com.dio.board.model.Block;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class BlockDao {
	
	static final EntityManager em = Conexao.getConn();
	
	public void salvar(Block block) {
       
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(block);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        }
    }

    public Block buscarPorId(Long id) {
        return em.find(Block.class, id);
    }

    public List<Block> listarTodos() {
        return em.createQuery("FROM Block", Block.class).getResultList();
    }

    public void atualizar(Block block) {
        
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(block);
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
            Block block = em.find(Block.class, id);
            if (block != null) {
                em.remove(block);
                tx.commit();
            }
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        }
    }

}
