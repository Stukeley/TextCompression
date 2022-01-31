package pl.polsl.servlets;

import pl.polsl.controllers.DatabaseController;
import pl.polsl.entities.HistoryEntry;
import pl.polsl.models.Algorithm;
import pl.polsl.models.History;
import pl.polsl.models.TextCompressionException;

import java.io.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Servlet responsible for handling the history form, representing the display of History.
 */
@WebServlet("/History")
public class HistoryServlet extends HttpServlet {

    /**
     * Single History object used in the entire Application.
     */
    private final History history = new History();

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

        // Read cookies - obsolete, we use the database now.
        /*Cookie[] cookies = request.getCookies();
        Cookie historySerializedCookie = null;
        String historySerialized = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("historySerialized")) {
                    historySerializedCookie = cookie;
                    historySerialized = cookie.getValue();
                    break;
                }
            }
        }

        if (historySerialized != null) {
            history.deserializeFromString(historySerialized);
        }

        String output = history.toString();
        
       if (output.length() == 0) {
           output = "History is empty!";
       }*/

        try {
            List<HistoryEntry> entries = databaseController.findObjects();

            StringBuilder output = new StringBuilder();

            for (HistoryEntry entry : entries) {
                output.append(entry.toString());
            }

            out.println("<html>\n<body>\n<h1>" + output.toString() + "</h1>\n");
            out.println("</body>\n</html>");
        }
        catch (TextCompressionException ex) {
            response.sendError(response.SC_BAD_REQUEST, ex.getMessage());
        }

        // Save cookies - obsolete.
        /*if (historySerializedCookie != null) {
            historySerializedCookie.setValue(history.serializeToString());
        }
        else {
            Cookie cookie = new Cookie("historySerialized", history.serializeToString());
            response.addCookie(cookie);
        }*/
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

        // Metoda POST i GET powinny mieć takie samo działanie w przypadku historii.
        doGet(request, response);
    }
}
