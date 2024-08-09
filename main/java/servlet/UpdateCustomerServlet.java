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

@WebServlet("/updateCustomer")
public class UpdateCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountNo = request.getParameter("accountNo");

        if (accountNo == null || accountNo.isEmpty()) {
            response.sendRedirect("viewCustomers.jsp"); // Redirect to view customers page if accountNo is not provided
            return;
        }

        Connection connection = DatabaseUtil.getConnection();
        serviceDao dao = new serviceDao(connection);

        try {
            // Fetch the current details of the customer to pre-fill the form
            Customer customer = dao.getCustomerByAccountNo(accountNo); // This method needs to be implemented
            if (customer != null) {
                request.setAttribute("customer", customer);
                request.getRequestDispatcher("updateCustomer.jsp").forward(request, response);
            } else {
                response.sendRedirect("viewCustomers.jsp"); // Redirect if customer is not found
            }
        } catch (SQLException e) {
            throw new ServletException("Cannot fetch customer details from DB", e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountNo = request.getParameter("accountNo");
        String username = request.getParameter("username");
        String address = request.getParameter("address");
        String mobileNo = request.getParameter("mobileNo");
        String emailId = request.getParameter("emailId");
        String accountType = request.getParameter("accountType");
        String dateOfBirth = request.getParameter("dateOfBirth");
        String idProof = request.getParameter("idProof");

        Connection connection = DatabaseUtil.getConnection();
        serviceDao dao = new serviceDao(connection);

        try {
            boolean success = dao.updateCustomer(accountNo, username, address, mobileNo, emailId, accountType, dateOfBirth, idProof);
            if (success) {
                response.sendRedirect("viewCustomers?message=Customer details updated successfully");
            } else {
                response.sendRedirect("viewCustomers?message=Failed to update customer details");
            }
        } catch (SQLException e) {
            throw new ServletException("Cannot update customer details in DB", e);
        }
    }
}
