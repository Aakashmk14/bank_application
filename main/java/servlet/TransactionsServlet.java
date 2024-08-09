package servlet;

import Dao.DatabaseUtil;
import Dao.serviceDao;
import Model.Transaction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/transactionServlet")
public class TransactionsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String accountNo = (String) session.getAttribute("identifier");

        if (accountNo == null) {
            response.sendRedirect("login.jsp"); // Redirect to login if account number is not available
            return;
        }

        Connection connection = DatabaseUtil.getConnection();
        serviceDao dao = new serviceDao(connection);

        try {
            List<Transaction> transactions = dao.getTransactions(accountNo);
            request.setAttribute("transactions", transactions);
            request.getRequestDispatcher("viewTransactions.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Cannot retrieve transactions from DB", e);
        }
    }
}
