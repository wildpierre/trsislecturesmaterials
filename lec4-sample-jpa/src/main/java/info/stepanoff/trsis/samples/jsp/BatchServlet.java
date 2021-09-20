package info.stepanoff.trsis.samples.jsp;

import info.stepanoff.trsis.samples.db.JPAHelper;
import info.stepanoff.trsis.samples.db.model.Batch;
import info.stepanoff.trsis.samples.db.model.School;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BatchServlet extends javax.servlet.http.HttpServlet {

    private static final long serialVersionUID = 1L;

    private final String responseTemplate1
            = "<html>\n"
            + "<body>\n"
            + "<h2>Список групп</h2>\n"
            + "<a href='school'>Вернуться назад</a>\n"
            + "<br>\n"
            + "<table> \n"
            + "<thead><th>Номер</th></thead>\n"
            + "<tbody>\n";


    private final String responseTemplate2
            = "</tbody></table>\n"
            + "</body>\n"
            + "</html>";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            process(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(BatchServlet.class.getName()).log(Level.SEVERE, null, ex);
            throw new IOException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            process(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(BatchServlet.class.getName()).log(Level.SEVERE, null, ex);
            throw new IOException(ex);
        }
    }

    
    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {

        response.setStatus(200);
        response.setContentType("text/html;charset=UTF-8");
        

        response.getWriter().write(responseTemplate1);

        String schoolId = request.getParameter("school");

        for (Batch batch : JPAHelper.getBatchBySchool(Integer.valueOf(schoolId))) {
            response.getWriter().write("<tr>");
            response.getWriter().write("<td>");
            response.getWriter().write(batch.getNumber());
            response.getWriter().write("</td>");
            response.getWriter().write("</tr>");
        }
        response.getWriter().write(responseTemplate2);
    }
    
}
