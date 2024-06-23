package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SavedRecipeServlet
 */
@WebServlet("/AddToFavServlet")
public class AddToFavServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		  HttpSession session = request.getSession();
		  String uname = (String) session.getAttribute("uname");
		  System.out.println("Username in add to fav: " + uname);
		  
		  
		String sqlGetLastRecipeId = "SELECT MAX(recipe_id) FROM reciperover_db.recipe"; // Query to get the last inserted recipe ID
        // Retrieve form data
        String recipeName = request.getParameter("recipeName");
        String ingredients = request.getParameter("ingredients");
        String instructions = request.getParameter("instructions");
        // Get the current date
        LocalDate currentDate = LocalDate.now();
        System.out.println("currentDate" + currentDate);
        
        
       
        // You can add validation and processing logic here.
        // For simplicity, we will just print the data for demonstration purposes.
        //System.out.println("title: " + recipeName);
        //System.out.println("ingredients: " + ingredients);
       // System.out.println("instructions: " + instructions);
       
       
        
      //Persistence Logic
        int responseCode = HttpServletResponse.SC_OK; // Default to 200 (OK) status code
        Connection con = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt1 = null;
        ResultSet rs = null;
        String sql =  "INSERT INTO reciperover_db.recipe (recipe_id, title, ingredients, instructions, saved_date,userName) VALUES (?, ?, ?, ?, ?, ?)";
        
        try {
           	Class.forName("com.mysql.cj.jdbc.Driver");
           	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/reciperover_db?useSSL=true", "root", "admin");
       	 		
           	pstmt1 = con.prepareStatement(sqlGetLastRecipeId);
            rs = pstmt1.executeQuery();
            int lastRecipeId = 0;
            if (rs.next()) {
                lastRecipeId = rs.getInt(1); // Retrieve the last recipe ID
            }
            int nextRecipeId = lastRecipeId + 1; // Increment to get the next recipe ID
       	 pstmt = con.prepareStatement(sql);
       	 
       	 //Set the values for place holder
       	 
       	pstmt.setLong(1, nextRecipeId);
        pstmt.setString(2, recipeName);
        pstmt.setString(3, ingredients);
        pstmt.setString(4, instructions);
        pstmt.setString(5, currentDate.toString());
        pstmt.setString(6, uname);
       	 
       	pstmt.executeUpdate();
       	
       	 
        }
        catch(ClassNotFoundException | SQLException e) {
       	 e.printStackTrace();
       	responseCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR; // Set 500 (Internal Server Error) status code on exception
        }
        finally {
       		if(pstmt!=null) {
       			try {
       				pstmt.close();
       			} catch (SQLException e) {
       				// TODO Auto-generated catch block
       				e.printStackTrace();
       			}
       		}
       		if(con!=null) {
       			try {
       				pstmt.close();
       			} catch (SQLException e) {
       				// TODO Auto-generated catch block
       				e.printStackTrace();
       			}

       		}
       		
       	}
        if(responseCode==200) {
        	session.setAttribute("AddToFavSuccessFlag",1 );  
        }
        else {
        	session.setAttribute("AddToFavSuccessFlag",0 );  
        }
             // You can then redirect to a success page or perform further actions.
        response.sendRedirect("favRecipe.jsp");// Replace with your actual success page.
        response.setStatus(responseCode); // Set the response status code
		
		
		 
        
    }
}
