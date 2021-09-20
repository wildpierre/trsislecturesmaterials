package info.stepanoff.trsis.samples.db;


import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pavel
 */
public class DBHelperStressTest {

    volatile static int schoolNumber = 100;
    volatile static boolean pass = true;

//    @Test
    public void testAddDeleteSchool() throws Exception {
        System.out.println("addSchool and deleteSchool stress test");

        class WorkerThread implements Runnable {

            @Override
            public void run() {
                try {
                    int num = schoolNumber++;
                    String name = "test" + num;
                    Integer id = DBHelper.addSchool(num, name);
                    //assertEquals(name, JPAHelper.getSchool(id).getName());
                    //assertEquals(new Integer(num), JPAHelper.getSchool(id).getNumber());
                    DBHelper.deleteSchool(id);
                    //assertEquals(null, JPAHelper.getSchool(id));
                } catch (SQLException ex) {
                    Logger.getLogger(DBHelperStressTest.class.getName()).log(Level.SEVERE, null, ex);
                    pass = false;
                    fail("SQL error executing stress test");
                }
            }
        }
        
        JPAHelper.getAllSchools();//Чтобы инициализировать базу данных на случай, если этот тест выполнится первым
        
        ExecutorService executor = Executors.newFixedThreadPool(15);
        for (int i = 0; i < 10000; i++) {
            Runnable worker = new WorkerThread();
            executor.execute(worker);
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        if (!pass) fail("Exception in one of worker threads");
        Logger.getLogger(DBHelperStressTest.class.getName()).log(Level.INFO, null, "Finished executing stress test");

    }

}
