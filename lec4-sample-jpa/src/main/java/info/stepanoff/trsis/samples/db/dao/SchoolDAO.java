package info.stepanoff.trsis.samples.db.dao;

import info.stepanoff.trsis.samples.db.model.School;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import lombok.extern.slf4j.Slf4j;

/**
 * CRUD ActiveRecord pattern implementation
 *
 * @author Pavel
 */
@Slf4j
public class SchoolDAO extends AbstractDAO {

    public SchoolDAO(EntityManager entityManager) {
        super(entityManager);
    }

    public School persist(Integer number, String name) {
        beginTransaction();
        School school = new School(number, name);
        entityManager.persist(school);
        commitTransaction();
        return school;
    }

    public School find(int id) {
        return entityManager.find(School.class, id);
    }

    public Collection<School> findAll() {

        Query query = entityManager.createQuery("SELECT e FROM School e");
        return (Collection<School>) query.getResultList();

    }

    public void update(int id, Integer number, String name) {
        beginTransaction();
        School user = entityManager.find(School.class, id);
        user.setNumber(number);
        user.setName(name);
        entityManager.merge(user);
        commitTransaction();
    }

    public void remove(int id) {
        beginTransaction();
        School user = entityManager.find(School.class, id);
        entityManager.remove(user);
        commitTransaction();
    }

}
