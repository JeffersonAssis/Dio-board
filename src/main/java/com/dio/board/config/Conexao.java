package com.dio.board.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Conexao {
	private static EntityManager em = null; 
	private Conexao() {	}
	
	public static synchronized EntityManager getConn(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("BOARD-PU");
		if (em == null) {
			em = emf.createEntityManager();
		}

		return em;
	}
}