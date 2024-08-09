package servlet;

import Dao.DatabaseUtil;
import Dao.serviceDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/SetPasswordServlet")
public class SetPasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        String accountno = (String) request.getSession().getAttribute("identifier"); // Assuming the email ID is stored in session

        if (newPassword.equals(confirmPassword)) {
            Connection connection = DatabaseUtil.getConnection();
            serviceDao dao = new serviceDao(connection);

            try {
                boolean isPasswordChanged = dao.changePassword(accountno, currentPassword, newPassword);
                if (isPasswordChanged) {
                    request.setAttribute("message", "Password changed successfully.");
                } else {
                    request.setAttribute("message", "Current password is incorrect.");
                }
            } catch (SQLException e) {
                throw new ServletException("Cannot change password", e);
            } finally {
                DatabaseUtil.closeConnection(connection);
            }
        } else {
            request.setAttribute("message", "New passwords do not match.");
        }

        request.getRequestDispatcher("setPassword.jsp").forward(request, response);
    }
}
