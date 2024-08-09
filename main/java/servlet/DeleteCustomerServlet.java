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

@WebServlet("/deleteCustomer")
public class DeleteCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountNo = request.getParameter("accountNo");

        if (accountNo == null || accountNo.isEmpty()) {
            response.sendRedirect("viewCustomers.jsp"); // Redirect if no account number is provided
            return;
        }

        Connection connection = DatabaseUtil.getConnection();
        serviceDao dao = new serviceDao(connection);

        try {
            boolean isDeleted = dao.deleteCustomer(accountNo);
            if (isDeleted) {
                response.sendRedirect("viewCustomers?message=Customer+deleted+successfully");
            } else {
                response.sendRedirect("viewCustomers?message=Failed+to+delete+customer");
            }
        } catch (SQLException e) {
            throw new ServletException("Cannot delete customer from DB", e);
        } finally {
            DatabaseUtil.closeConnection(connection);
        }
    }
}
