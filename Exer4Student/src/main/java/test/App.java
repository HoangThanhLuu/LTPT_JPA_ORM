package test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class App {
public static void main(String[] args) {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-mssql");
	EntityManager em = emf.createEntityManager();
	EntityTransaction tx = em.getTransaction();
	try {
		tx.begin();
		
		tx.commit();
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	
	emf.close();
}
}
