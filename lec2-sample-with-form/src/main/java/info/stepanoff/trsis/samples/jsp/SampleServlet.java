package info.stepanoff.trsis.samples.jsp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SampleServlet extends javax.servlet.http.HttpServlet {

    private String responseTemplate1
            = "<html>\n"
            + "<body>\n"
            + "<h2>";

    private String responseTemplate2
            = "</h2>\n"
            + "<form method='post' action='sample' target='_self'><input name='data'><input type='submit'></form></body>\n"
            + "</html>";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        process(request, response, request.getParameter("data"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        process(request, response, request.getParameter("data"));
    }

    private void process(HttpServletRequest request, HttpServletResponse response, String data) throws IOException {
        response.setStatus(200);
        response.getWriter().write(responseTemplate1);
        if (data != null) {
            response.getWriter().write(data);
        } else {
            response.getWriter().write("data is not provided");
        }
        response.getWriter().write(responseTemplate2);
    }
}
