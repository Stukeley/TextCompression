package pl.polsl.servlets;

import pl.polsl.controllers.DatabaseController;
import pl.polsl.entities.BonusInfo;
import pl.polsl.entities.HistoryEntry;
import pl.polsl.models.Algorithm;
import pl.polsl.models.History;
import pl.polsl.models.TextCompressionException;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Servlet responsible for handling the main form, representing the algorithm invocation.
 */
@WebServlet("/Form")
public class MainServlet extends HttpServlet {

    /**
     * Single Algorithm object used in the entire Application.
     */
    private final Algorithm algorithm = new Algorithm();

    /**
     * Single DatabaseController object used in the entire Application.
     */
    private final DatabaseController databaseController = new DatabaseController();

    /**
     * Handles the HTTP <code>GET</code> method.
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

        if (request == null) {
            out.println("<html>\n<body>\n<h1>" + "Incorrect access - request was null." + "</h1>\n");
            out.println("</body>\n</html>");
            return;
        }

        // Get parameter value - input
        String input = request.getParameter("input");

        // input was not given
        if (input.length() == 0) {
            response.sendError(response.SC_BAD_REQUEST, "Input parameter cannot be empty!");
        } else {

            String output = "";

            try {

                boolean isInputCompressed = algorithm.isStringCompressed(input);

                if (isInputCompressed) {
                    output = algorithm.decompress(input);
                }
                else {
                    output = algorithm.compress(input);
                }

                // Obsolete - we use the database now.
                // HistoryServlet.history.add(input, output);

                // NEW - add to database.
                BonusInfo bonusInfo = new BonusInfo(LocalDateTime.now(), isInputCompressed);
                HistoryEntry entry = new HistoryEntry(output, bonusInfo);

                databaseController.persistObject(entry);
            }
            catch (TextCompressionException ex) {
                response.sendError(response.SC_BAD_REQUEST, ex.getMessage());
            }

            out.println("<html>\n<body>\n<h1>" + output + "</h1>\n");
            out.println("</body>\n</html>");
        }
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
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        doGet(request, response);
    }
}
