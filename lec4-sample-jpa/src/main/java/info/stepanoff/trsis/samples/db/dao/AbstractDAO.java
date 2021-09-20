package info.stepanoff.trsis.samples.db.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Pavel
 */
@Slf4j
public abstract class AbstractDAO {
    
    protected final EntityManager entityManager;
    protected final EntityTransaction entityTransaction;
    
    public AbstractDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.entityTransaction = this.entityManager.getTransaction();
    }
    
    protected void beginTransaction() {
        try {
            entityTransaction.begin();
        } catch (IllegalStateException e) {
            log.error("Error while beginning transaction; attempting rolling back just in case", e);
            rollbackTransaction();
        }
    }
    
    protected void commitTransaction() {
        try {
            entityTransaction.commit();
        } catch (IllegalStateException e) {
            log.error("Error while commiting transaction; rolling back ", e);
            rollbackTransaction();
        }
    }
    
    protected void rollbackTransaction() {
        try {
            entityTransaction.rollback();
        } catch (IllegalStateException e) {
            log.error("Error while rolling back transaction: ", e);
        }
    }
    
}
