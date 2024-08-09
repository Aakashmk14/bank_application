package servlet;

import Dao.DatabaseUtil;
import Dao.serviceDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/balanceServlet")
public class CheckBalanceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String accountNo = (String) session.getAttribute("identifier");
        if (accountNo == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        Connection connection = DatabaseUtil.getConnection();
        serviceDao dao = new serviceDao(connection);
        try {
            double balance = dao.getBalance(accountNo);
            request.setAttribute("balance", balance);
            request.getRequestDispatcher("checkBalance.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Cannot retrieve balance from DB", e);
        }
    }
}
