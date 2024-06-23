package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/setSessionVariableServlet")
public class SetSessionVariableServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] selectedValues = request.getParameterValues("selectedSpices");
        if (selectedValues != null) {
            HttpSession session = request.getSession();
            session.setAttribute("selectedSpices", selectedValues);
        }
        // Redirect or forward to another page if needed
    }
}
