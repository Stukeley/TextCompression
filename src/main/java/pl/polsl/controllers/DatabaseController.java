package pl.polsl.controllers;

import pl.polsl.entities.HistoryEntry;

import javax.persistence.*;
import java.util.List;

/**
 * Controller class responsible for communication between the application and the database.
 * @author Rafał Klinowski
 * @version 1.0
 */
public class DatabaseController {

    private final EntityManagerFactory entityManagerFactory;

    public DatabaseController() {
        entityManagerFactory = Persistence.createEntityManagerFactory("TextCompressionProd");
    }

    public void persistObject(Object object) {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        try {
            em.persist(object);
            em.getTransaction().commit();
            System.out.println("Object persisted");
        }
        catch (PersistenceException ex) {
            ex.printStackTrace();
            em.getTransaction().rollback();
        }
        finally {
            em.close();
        }
    }

    public void findObjects() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        try {
            Query query = em.createQuery("SELECT h FROM HistoryEntry h");
            List<HistoryEntry> entries = query.getResultList();
        }
        catch (PersistenceException ex) {
            ex.printStackTrace();
            em.getTransaction().rollback();
        }
        finally {
            em.close();
        }
    }

    public void updateObject(HistoryEntry newObject) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        try {
            String newResult = newObject.getResult();
            long objId = newObject.getId();

            Query query = em.createQuery("UPDATE HistoryEntry h SET h.result = ?1 WHERE h.id = ?2");
        }
        catch (PersistenceException ex) {
            ex.printStackTrace();
            em.getTransaction().rollback();
        }
        finally {
            em.close();
        }
    }

}
