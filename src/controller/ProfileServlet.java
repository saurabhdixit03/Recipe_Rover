package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ProfileServlet
 */
@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	 HttpSession session = request.getSession();
    	 String email = (String) session.getAttribute("uname");
        // Retrieve form data
        String name = request.getParameter("name");
        //String email = request.getParameter("email");
        String password = request.getParameter("pass");
        String confirmPassword = request.getParameter("re_pass");
        String contact = request.getParameter("contact");

        // You can add validation and processing logic here.
        // For simplicity, we will just print the data for demonstration purposes.
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);
        System.out.println("Confirm Password: " + confirmPassword);
        System.out.println("Contact: " + contact);
        
       
        StringBuilder queryBuilder = new StringBuilder("UPDATE reciperover_db.signup SET ");

        boolean hasValuesToUpdate = false;

        if (name != null && !name.isEmpty()) {
            queryBuilder.append("name = ?, ");
            hasValuesToUpdate = true;
        }

        if (password != null && !password.isEmpty()) {
            queryBuilder.append("password = ?, ");
            hasValuesToUpdate = true;
        }

        if (contact != null && !contact.isEmpty()) {
            queryBuilder.append("contact = ?, ");
            hasValuesToUpdate = true;
        }

        // Remove the trailing comma and space from the query
        int lastIndex = queryBuilder.length() - 2;
        if (lastIndex > 0) {
            queryBuilder.delete(lastIndex, lastIndex + 2);
        }

        queryBuilder.append(" WHERE email = ?");

      //Persistence Logic
        Connection con = null;
        PreparedStatement pstmt = null;
       
      
        try {
        	
       	Class.forName("com.mysql.jdbc.Driver");
       	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/reciperover_db?useSSL=true", "HP", "root@123");
         pstmt = con.prepareStatement(queryBuilder.toString()); 		
        
       	 
       	 
       	int parameterIndex = 1;

        if (name != null && !name.isEmpty()) {
            pstmt.setString(parameterIndex++, name);
        }

        if (password != null && !password.isEmpty()) {
            pstmt.setString(parameterIndex++, password);
        }

        if (contact != null && !contact.isEmpty()) {
            pstmt.setString(parameterIndex++, contact);
        }

        // Set the email in the last parameter for WHERE clause
        pstmt.setString(parameterIndex, email);

        // Execute the update query
        int rowsAffected = pstmt.executeUpdate();

        if (rowsAffected > 0) {
            // Updated successfully
            // Add your success logic here
        } else {
            // No rows affected or update failed
            // Add your failure logic here
        }

        pstmt.close();
       	 
        }
        catch(ClassNotFoundException | SQLException e) {
       	 e.printStackTrace();
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
        // You can then redirect to a success page or perform further actions.
        response.sendRedirect("index.jsp"); // Replace with your actual success page.
    }
}
