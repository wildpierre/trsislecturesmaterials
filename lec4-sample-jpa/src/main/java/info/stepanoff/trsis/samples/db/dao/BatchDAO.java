package info.stepanoff.trsis.samples.db.dao;

import info.stepanoff.trsis.samples.db.model.Batch;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import lombok.extern.slf4j.Slf4j;

/**
 * CRUD ActiveRecord
 *
 * @author Pavel
 */
@Slf4j
public class BatchDAO extends AbstractDAO {

    public BatchDAO(EntityManager entityManager) {
        super(entityManager);
    }

    public void persist(Integer number, String name) {
        beginTransaction();
        Batch user = new Batch(number, name);
        entityManager.persist(user);
        commitTransaction();
    }

    public Batch find(int id) {
        return entityManager.find(Batch.class, id);
    }

    public Collection<Batch> findAll(Integer id) {

        Query query = entityManager.createQuery("SELECT e FROM Batch e where batch_school_id = :school");
        query.setParameter("school", id);
        return (Collection<Batch>) query.getResultList();

    }

    public void update(int id, String number, Integer schoolId) {
        beginTransaction();
        Batch batch = entityManager.find(Batch.class, id);
        batch.setNumber(number);
        batch.setSchool(schoolId);
        entityManager.merge(batch);
        commitTransaction();
    }

    public void remove(int id) {
        beginTransaction();
        Batch batch = entityManager.find(Batch.class, id);
        entityManager.remove(batch);
        commitTransaction();
    }

}
