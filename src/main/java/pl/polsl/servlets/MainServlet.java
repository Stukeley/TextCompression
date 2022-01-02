package pl.polsl.servlets;

import pl.polsl.models.Algorithm;
import pl.polsl.models.History;
import pl.polsl.models.TextCompressionException;

import java.io.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/Form")
public class MainServlet extends HttpServlet {
    /**
     * Collection of statistics
     */
    private final History history;

    /**
     * Constructor initiating statistics collection
     */
    public MainServlet() {
        history = new History();
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/html; charset=ISO-8859-2");
        PrintWriter out = response.getWriter();

        // Get parameter values - firstName i lastName

        String input = request.getParameter("input");

        // FirstName or lastName was not given - send error message
        if (input.length() == 0) {
            response.sendError(response.SC_BAD_REQUEST, "Input parameter cannot be empty!");
        } else {

            Algorithm algorithm = new Algorithm();
            String output = "";

            try {

                boolean isInputCompressed = algorithm.isStringCompressed(input);

                if (isInputCompressed) {
                    output = algorithm.decompress(input);
                }
                else {
                    output = algorithm.compress(input);
                }

                history.add(input, output);
            }
            catch (TextCompressionException ex) {
                response.sendError(response.SC_BAD_REQUEST, ex.getMessage());
            }

            out.println("<html>\n<body>\n<h1>" + output + "</h1>\n");
            out.println("</body>\n</html>");
        }
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/plain; charset=ISO-8859-2");
        PrintWriter out = response.getWriter();

        out.println("Passed parameters:");

        Enumeration enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String name = (String) enumeration.nextElement();
            out.println(name + " = " + request.getParameter(name));
        }
    }
}
