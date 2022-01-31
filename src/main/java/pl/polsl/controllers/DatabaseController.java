package pl.polsl.controllers;

import pl.polsl.entities.HistoryEntry;
import pl.polsl.models.TextCompressionException;

import javax.persistence.*;
import java.util.List;

/**
 * Controller class responsible for communication between the application and the database.
 * @author Rafał Klinowski
 * @version 1.1
 */
public class DatabaseController {

    /**
     * Single EntityManagerFactory object used in the entire application.
     */
    private final EntityManagerFactory entityManagerFactory;

    /**
     * Single EntityManager object used in the entire application.
     */
    private final EntityManager em;

    /**
     * Constructor initializing the objects.
     */
    public DatabaseController() {
        entityManagerFactory = Persistence.createEntityManagerFactory("TextCompressionProd");
        em = entityManagerFactory.createEntityManager();
    }

    /**
     * Function used to save an object in the database.
     * @param object Object to be saved in the database.
     * @throws TextCompressionException Exception thrown when the object cannot be saved.
     */
    public void persistObject(Object object) throws TextCompressionException {
        em.getTransaction().begin();

        try {
            em.persist(object);
            em.getTransaction().commit();
        }
        catch (PersistenceException ex) {
            ex.printStackTrace();
            em.getTransaction().rollback();
            throw new TextCompressionException("Exception - object not persisted! " + ex.getMessage());
        }
        finally {
            em.close();
        }
    }

    /**
     * Function used to get a list of all objects in the database.
     * @return A list of all objects of type HistoryEntry in the database.
     * @throws TextCompressionException Exception thrown when the objects cannot be selected.
     */
    public List<HistoryEntry> findObjects() throws TextCompressionException {
        em.getTransaction().begin();

        try {
            Query query = em.createQuery("SELECT h FROM HistoryEntry h");
            List<HistoryEntry> entries = query.getResultList();

            return entries;
        }
        catch (PersistenceException ex) {
            ex.printStackTrace();
            em.getTransaction().rollback();

            throw new TextCompressionException("Exception - objects not returned! " + ex.getMessage());
        }
        finally {
            em.close();
        }
    }

    /**
     * Function used to get an object from the database by id.
     * @param id ID of the object to retrieve.
     * @return The retrieved object.
     * @throws TextCompressionException Exception thrown when the object cannot be selected.
     */
    public HistoryEntry findObject(long id) throws TextCompressionException {
        em.getTransaction().begin();

        try {
            HistoryEntry entry = em.find(HistoryEntry.class, id);

            if (entry == null) {
                throw new TextCompressionException("Object of id " + id + " not found in the database!");
            }
            else {
                return entry;
            }
        }
        catch (PersistenceException ex) {
            ex.printStackTrace();
            em.getTransaction().rollback();
            throw new TextCompressionException("Exception - object not returned! " + ex.getMessage());
        }
        finally {
            em.close();
        }
    }

    /**
     * Function used to update an object in the database.
     * @param newObject New, updated object to be replaced in the database by ID.
     * @throws TextCompressionException Exception thrown when the object cannot be updated.
     */
    public void updateObject(HistoryEntry newObject) throws TextCompressionException {
        em.getTransaction().begin();

        try {
            HistoryEntry entry = em.find(HistoryEntry.class, newObject.getId());

            if (entry == null) {
                return;
            }

            entry.setResult(newObject.getResult());
            entry.setBonusInfo(newObject.getBonusInfo());

            em.getTransaction().commit();
        }
        catch (PersistenceException ex) {
            ex.printStackTrace();
            em.getTransaction().rollback();
            throw new TextCompressionException("Exception - object not updated! " + ex.getMessage());
        }
        finally {
            em.close();
        }
    }

    /**
     * Function used to remove an object from the database
     * @param objectToRemove Object to remove from the database.
     * @throws TextCompressionException Exception thrown when the object cannot be removed.
     */
    public void removeObject(HistoryEntry objectToRemove) throws TextCompressionException {
        em.getTransaction().begin();

        try {
            HistoryEntry entry = em.find(HistoryEntry.class, objectToRemove.getId());

            if (entry == null) {
                return;
            }

            em.remove(entry);

            em.getTransaction().commit();
        }
        catch (PersistenceException ex) {
            ex.printStackTrace();
            em.getTransaction().rollback();
            throw new TextCompressionException("Exception - object not removed! " + ex.getMessage());
        }
        finally {
            em.close();
        }
    }

}
