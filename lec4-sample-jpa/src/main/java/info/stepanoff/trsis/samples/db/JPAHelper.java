package info.stepanoff.trsis.samples.db;

import info.stepanoff.trsis.samples.db.dao.SchoolDAO;
import info.stepanoff.trsis.samples.db.model.Batch;
import info.stepanoff.trsis.samples.db.model.School;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Pavel
 */
@Slf4j
public class JPAHelper {

    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("school-unit");

    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }

    public static List<School> getAllSchools() throws SQLException {
        SchoolDAO dao = new SchoolDAO(getEntityManager());
        return new ArrayList<>(dao.findAll());
    }

    public static School getSchool(Integer id) throws SQLException {
        SchoolDAO dao = new SchoolDAO(getEntityManager());
        return dao.find(id);
    }

    public static void deleteSchool(Integer id) throws SQLException {
        SchoolDAO dao = new SchoolDAO(getEntityManager());
        dao.remove(id);
    }

    public static Integer addSchool(Integer number, String name) throws SQLException {
        SchoolDAO dao = new SchoolDAO(getEntityManager());
        return dao.persist(number, name).getId();

    }

    public static List<Batch> getBatchBySchool(Integer id) throws SQLException {
        SchoolDAO dao = new SchoolDAO(getEntityManager());
        School school = dao.find(id);
        return school.getBatchesList();
    }

}
