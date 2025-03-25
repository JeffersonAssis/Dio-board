package com.dio.board.dao;

import java.util.List;

import com.dio.board.config.Conexao;
import com.dio.board.model.Card;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class CardDao {
	
	static final EntityManager em = Conexao.getConn();

	 public void salvar(Card card) {
	   
	        EntityTransaction tx = em.getTransaction();
	        try {
	            tx.begin();
	            em.persist(card);
	            tx.commit();
	        } catch (Exception e) {
	            if (tx.isActive()) tx.rollback();
	            e.printStackTrace();
	        }
	    }

	    public Card buscarPorId(Long id) {
	        return em.find(Card.class, id);
	    }

	    public List<Card> listarTodos() {
	        return em.createQuery("FROM Card", Card.class).getResultList();
	    }

	    public void atualizar(Card card) {
	      
	        EntityTransaction tx = em.getTransaction();
	        try {
	            tx.begin();
	            em.merge(card);
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
	            Card card = em.find(Card.class, id);
	            if (card != null) {
	                em.remove(card);
	                tx.commit();
	            }
	        } catch (Exception e) {
	            if (tx.isActive()) tx.rollback();
	            e.printStackTrace();
	        }
	    }
}
