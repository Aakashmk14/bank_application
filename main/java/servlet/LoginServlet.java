package servlet;

import Dao.userDAO;
import Dao.DatabaseUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String identifier = request.getParameter("identifier");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        
        Connection connection = null;
        try {
            connection = DatabaseUtil.getConnection();
            userDAO userDAO = new userDAO(connection);
            
            boolean isValidUser = userDAO.validateUser(identifier, password, role);
            
            if (isValidUser) {
                HttpSession session = request.getSession();
                session.setAttribute("identifier", identifier);
                session.setAttribute("role", role);
                
                if ("admin".equals(role)) {
                    response.sendRedirect("admindashboard.jsp");
                } else if ("customer".equals(role)) {
                    response.sendRedirect("customerDashboard.jsp");
                }
            } else {
                response.sendRedirect("login.jsp?error=Invalid username or password");
            }
        } catch (SQLException e) {
            throw new ServletException("Database connection problem", e);
        } finally {
            DatabaseUtil.closeConnection(connection);
        }
    }
}
