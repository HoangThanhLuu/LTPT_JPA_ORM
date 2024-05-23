package dao.impl;

import dao.PersonDAO;
import entity.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.util.List;

public class PersonDAOImpl implements PersonDAO {

    private EntityManager em;

    public PersonDAOImpl() {
        em = Persistence.createEntityManagerFactory("jpa-mssql").createEntityManager();
    }

    @Override
    public boolean add(Person person) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(person);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Person person) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(person);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Person findByID(int id) {
        return em.find(Person.class, id);
    }

    @Override
    public List<Person> findAll() {
        return em.createQuery("SELECT p FROM Person p", Person.class).getResultList();
    }

    @Override
    public List<Person> findByTitle(String title) {
        return em.createQuery("SELECT p FROM Person p WHERE p.title LIKE :title", Person.class)
                .setParameter("title", "%" + title + "%")
                .getResultList();
    }

    @Override
    public Person findByTitle2(String title) {
        return em.createQuery("SELECT p FROM Person p WHERE p.title = :title", Person.class)
                .setParameter("title", title)
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean delete(int id) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Person person = em.find(Person.class, id);
            if (person != null) {
                em.remove(person);
                tx.commit();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
}
