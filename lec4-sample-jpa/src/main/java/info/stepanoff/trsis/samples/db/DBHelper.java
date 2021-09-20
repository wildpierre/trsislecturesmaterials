package info.stepanoff.trsis.samples.db;

import info.stepanoff.trsis.samples.db.model.Batch;
import info.stepanoff.trsis.samples.db.model.School;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Pavel
 */
@Slf4j
public class DBHelper {

    static Connection conn;

    public static Connection getConnection() throws SQLException {
        if (conn != null) {
            return conn;
        }
        String dbUrl = "jdbc:derby:memory:demo;create=true";
        conn = DriverManager.getConnection(dbUrl);
        return conn;
    }

    public static void closeConnection() throws SQLException {
        if (conn != null) {
            if (!conn.isClosed()) {
                conn.close();
            }
        }
    }

    private static PreparedStatement schoolStatement;

    private static PreparedStatement getSchoolStatement() throws SQLException {

        if (schoolStatement == null) {
            String sql = "SELECT SCHOOL_ID,  SCHOOL_NUMBER,  SCHOOL_NAME FROM SCHOOL";
            schoolStatement = getConnection().prepareStatement(sql);
        }

        return schoolStatement;
    }

    public static List<School> getAllSchools() throws SQLException {

        List<School> result = new LinkedList<>();

        PreparedStatement stmt = getSchoolStatement();

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {

                int id = rs.getInt("SCHOOL_ID");
                int nomer = rs.getInt("SCHOOL_NUMBER");

                String imya = rs.getString("SCHOOL_NAME");

                School fakultet = new School(id, nomer, imya);

                result.add(fakultet);

            }
        }

        return result;
    }

    private static PreparedStatement deleteSchoolStatement;

    private static PreparedStatement getDeleteSchoolStatement() throws SQLException {

        if (deleteSchoolStatement == null) {
            String sql = "DELETE FROM SCHOOL WHERE SCHOOL_ID = ?";

            //Нельзя делать вот так:
            //String sql = "DELETE SCHOOL WHERE SCHOOL_ID = "+somevalue;    
            deleteSchoolStatement = getConnection().prepareStatement(sql);
        }

        return deleteSchoolStatement;
    }

    public static void deleteSchool(Integer id) throws SQLException {

        PreparedStatement stmt = getDeleteSchoolStatement();
        stmt.setInt(1, id);
        stmt.executeUpdate();
        log.info("delete executed");
        //autocommit
    }

    private static PreparedStatement addSchoolStatement;

    private static PreparedStatement getAddSchoolStatement() throws SQLException {

        if (addSchoolStatement == null) {
            String sql = "INSERT INTO SCHOOL (SCHOOL_NUMBER, SCHOOL_NAME) VALUES (?,?)";
            addSchoolStatement = getConnection().prepareStatement(sql, new String[] { "SCHOOL_ID"});
        }

        return addSchoolStatement;
    }

    public static Integer addSchool(Integer number, String name) throws SQLException {

        PreparedStatement stmt = getAddSchoolStatement();
        stmt.setInt(1, number);
        stmt.setString(2, name);
        stmt.executeUpdate();
        ResultSet rs = stmt.getGeneratedKeys();
        rs.next();
        Integer key = rs.getInt(1);
        log.info("Insert executed");
        return key;
    }

    private static PreparedStatement batchStatement;

    private static PreparedStatement getBatchStatement() throws SQLException {

        if (batchStatement == null) {
            String sql = "SELECT BATCH_ID,  BATCH_NUMBER FROM BATCH WHERE BATCH_SCHOOL_ID = ?";
            batchStatement = getConnection().prepareStatement(sql);
        }

        return batchStatement;
    }

    public static List<Batch> getBatchBySchool(Integer schoolId) throws SQLException {

        List<Batch> result = new LinkedList<>();

        PreparedStatement stmt = getBatchStatement();
        stmt.setInt(1, schoolId);

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {

                int id = rs.getInt("BATCH_ID");
                String number = rs.getString("BATCH_NUMBER");

                Batch batch = new Batch(id, number);

                result.add(batch);

            }
        }

        return result;
    }
}
