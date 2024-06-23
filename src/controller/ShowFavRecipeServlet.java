package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/showFavRecipeServlet")
public class ShowFavRecipeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String username = (String) request.getAttribute("uname");
    	System.out.println("username in show fav recipe::"+username);
    	Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
       
        
        
        try {
            // Load and register the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Database connection
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/reciperover_db?useSSl=false?user=HP&password=root@123");

            // SQL query to fetch 'title' and 'date' from the table
            String sql = "SELECT title, saved_date ,ingredients,instructions FROM reciperover_db.recipe where userName =?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, username);
            rs = pstmt.executeQuery();

         // Process the result set
            List<String> titlesList = new ArrayList<>();
            List<String> ingredientsList = new ArrayList<>();
            List<String> instructionsList = new ArrayList<>();
            List<String> datesList = new ArrayList<>();

            while (rs.next()) {
                // Retrieve 'title' and 'date' from each row
                String title = rs.getString("title");
                String ingredients = rs.getString("ingredients");
                String instructions = rs.getString("instructions");
                String date = rs.getString("saved_date");

                // Add 'title' and 'date' to lists
                titlesList.add(title);
                datesList.add(date);
                ingredientsList.add(ingredients);
                instructionsList.add(instructions);
            }

            // Convert lists to arrays
            String[] titles = titlesList.toArray(new String[0]);
            String[] dates = datesList.toArray(new String[0]);
            String[] ingredients = ingredientsList.toArray(new String[0]);
            String[] instructions = instructionsList.toArray(new String[0]);
            
         // Set attributes to be used in the JSP
            request.setAttribute("titles", titles);
            request.setAttribute("dates", dates);
            request.setAttribute("ingredients", ingredients);
            request.setAttribute("instructions", instructions);
            
            // Forward to a JSP page to display the data
            //RequestDispatcher rd = request.getRequestDispatcher("favRecipe.jsp");
           // rd.forward(request, response);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources in reverse order of creation to avoid leaks
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
