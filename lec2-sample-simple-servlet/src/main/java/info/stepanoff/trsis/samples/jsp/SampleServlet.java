package info.stepanoff.trsis.samples.jsp;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
 
public class SampleServlet extends javax.servlet.http.HttpServlet {
 
    private String responseTemplate1 =
            "<html>\n" +
                    "<body>\n" +
                    "<h2>Hello from Sample Servlet</h2>\n";
 
     private String responseTemplate2 =
                    "</body>\n" +
                    "</html>";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        process(request, response);
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        process(request, response);
    }
 
    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setStatus(200);
        PrintWriter writer = response.getWriter();
        
        writer.write(responseTemplate1);
        writer.write(Instant.now().toString());
        writer.write(responseTemplate2);
        
    }
}