package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import dao.StudentDAO;
import entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class StudentImpl extends UnicastRemoteObject implements StudentDAO{
	
	private EntityManager em;

	public StudentImpl() throws RemoteException  {
		em = Persistence.createEntityManagerFactory("jpa-mssql").createEntityManager();
	}

	@Override
	public List<Student> findStudentsEnrolledIn(int year)throws RemoteException {
		return em.createNamedQuery("Student.findByEnrollmentYear", Student.class)
				.setParameter("year", year)
				.getResultList();
	}

	@Override
	public List<Student> findStudentsEnrolledInCourse(String title)throws RemoteException {
		return em.createNamedQuery("Student.findByEnrollmentCourse", Student.class)
				.setParameter("title", "%"+title+"%")
				.getResultList();
	}

	@Override
	public boolean add(Student student) throws RemoteException {
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			em.persist(student);
			
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Student> findAll() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}


}
