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

@WebServlet("/WithdrawServlet")
public class WithdrawServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = DatabaseUtil.getConnection();
        serviceDao dao = new serviceDao(connection);
        

        String accountNo = (String) request.getSession().getAttribute("identifier");
        double amount = Double.parseDouble(request.getParameter("amount"));

        try {
            boolean success = dao.withdrawMoney(accountNo, amount);
            if (success) {
                request.setAttribute("message", "Withdrawal successful!");
            } else {
                request.setAttribute("message", "Withdrawal failed. Please check the account number or amount.");
            }
            request.getRequestDispatcher("withdraw.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Cannot withdraw money", e);
        }
    }
}
