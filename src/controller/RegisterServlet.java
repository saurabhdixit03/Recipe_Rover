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

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/rs")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID1 = 1L;
       


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String name = request.getParameter("name");
        String email = request.getParameter("email");
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
        
       
        
      //Persistence Logic
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = "insert into reciperover_db.signup values(?,?,?,?)";
        try {
       	Class.forName("com.mysql.jdbc.Driver");
       	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/reciperover_db?useSSL=true", "root", "admin");
       	 		
       	 pstmt = con.prepareStatement(sql);
       	 //Set the values for place holder
       	 
       	 pstmt.setString(1,email);
       	 pstmt.setString(2,name);
       	 pstmt.setString(3,password);
       	 pstmt.setString(4,contact);
       	 
       	 pstmt.executeUpdate();
       	 
       	 
       	 
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
        response.sendRedirect("login.jsp"); // Replace with your actual success page.
    }
}
