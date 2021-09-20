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
public class JPAHelperStressTest {

    volatile static int schoolNumber = 100;
    volatile static boolean pass = true;

    @Test
    public void testAddDeleteSchool() throws Exception {
        System.out.println("addSchool and deleteSchool stress test");

        class WorkerThread implements Runnable {

            @Override
            public void run() {
                try {
                    int num = schoolNumber++;
                    String name = "test" + num;
                    Integer id = JPAHelper.addSchool(num, name);
                    assertEquals(name, JPAHelper.getSchool(id).getName());
                    assertEquals(new Integer(num), JPAHelper.getSchool(id).getNumber());
                    JPAHelper.deleteSchool(id);
                    assertEquals(null, JPAHelper.getSchool(id));
                } catch (SQLException ex) {
                    Logger.getLogger(JPAHelperStressTest.class.getName()).log(Level.SEVERE, null, ex);
                    pass = false;
                    fail("SQL error executing stress test");
                }
            }
        }

        ExecutorService executor = Executors.newFixedThreadPool(15);
        for (int i = 0; i < 10000; i++) {
            Runnable worker = new WorkerThread();
            executor.execute(worker);
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        if (!pass) {
            fail("Exception in one of worker threads");
        }
        Logger.getLogger(JPAHelperStressTest.class.getName()).log(Level.INFO, null, "Finished executing stress test");

    }

}
