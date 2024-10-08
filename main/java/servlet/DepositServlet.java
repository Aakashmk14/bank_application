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

@WebServlet("/DepositServlet")
public class DepositServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountNo = request.getParameter("accountNo");
        double amount = Double.parseDouble(request.getParameter("amount"));

        Connection connection = DatabaseUtil.getConnection();
        serviceDao dao = new serviceDao(connection);

        try {
            boolean success = dao.deposit(accountNo, amount);
            if (success) {
                request.setAttribute("message", "Deposit successful.");
            } else {
                request.setAttribute("message", "Deposit failed. Please check the account number and try again.");
            }
        } catch (SQLException e) {
            throw new ServletException("Cannot process deposit", e);
        } finally {
            DatabaseUtil.closeConnection(connection);
        }

        request.getRequestDispatcher("deposit.jsp").forward(request, response);
    }
}
