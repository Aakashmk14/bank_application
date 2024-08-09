package servlet;

import Dao.DatabaseUtil;
import Dao.serviceDao;
import Model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/viewCustomers")
public class ViewCustomersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = DatabaseUtil.getConnection();
        serviceDao dao = new serviceDao(connection);

        try {
            List<Customer> customers = dao.getAllCustomers();
            request.setAttribute("customers", customers);
            request.getRequestDispatcher("viewCustomers.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Cannot retrieve customer data from DB", e);
        }
    }
}
