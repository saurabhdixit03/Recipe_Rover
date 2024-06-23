package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;




//@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("email");
        String password = request.getParameter("password"); 
        
        boolean isValidUser = false;
		try {
			
			isValidUser = validateUser(userId, password);
			//Setting Session 
			HttpSession session=request.getSession();  
	        session.setAttribute("uname",userId);  
	        
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        if (isValidUser) {
            HttpSession session = request.getSession();
            session.setAttribute("loggedInUser", userId);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            //response.sendRedirect("index.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("login.jsp?error=invalid");
        }
    }

    private boolean validateUser(String userId, String password) throws ClassNotFoundException {
    	
   
    	Connection con = null;
        PreparedStatement pstmt = null;
        String sql = "SELECT * FROM reciperover_db.signup WHERE email = ? AND password = ?";
        try {
       	Class.forName("com.mysql.cj.jdbc.Driver");
       	 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/reciperover_db?useSSl=false?user=HP&password=root@123");
       	

       	 pstmt = con.prepareStatement(sql);
       	 //Set the values for place holder
       	 
       	 pstmt.setString(1,userId);
       	 pstmt.setString(2,password);
       	 
       	try (ResultSet resultSet = pstmt.executeQuery()) {
            return resultSet.next(); // Returns true if a row is found
        }
       	}
        catch( SQLException e) {
       	 e.printStackTrace();
       	return false;
        }
		
    }  
    
}
