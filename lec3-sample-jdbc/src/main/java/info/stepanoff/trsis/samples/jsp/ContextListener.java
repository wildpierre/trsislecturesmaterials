package info.stepanoff.trsis.samples.jsp;

import org.apache.ibatis.jdbc.ScriptRunner;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import info.stepanoff.trsis.samples.db.DBHelper;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.ibatis.io.Resources;

@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        try {
            
            //start database
            Connection conn = DBHelper.getConnection();
            //ibatis
            ScriptRunner sr = new ScriptRunner(conn);
            Reader reader = Resources.getResourceAsReader("init.sql");
            
            // Exctute script
            sr.runScript(reader);
            
            
            reader = Resources.getResourceAsReader("fill.sql");
            
            // Exctute script
            sr.runScript(reader);
        } catch (SQLException ex) {
            Logger.getLogger(ContextListener.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ContextListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
        try {
            //stop database
            DBHelper.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ContextListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
