package servlet;
import Dao.serviceDao;
import Dao.DatabaseUtil;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/registerCustomerServlet")
public class RegisterCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String mobileNo = request.getParameter("mobile_no");
        String emailId = request.getParameter("email_id");
        String accountType = request.getParameter("account_type");
        String balance = request.getParameter("balance");
        String dateOfBirth = request.getParameter("date_of_birth");
        String idProof = request.getParameter("id_proof");

        try (Connection connection = DatabaseUtil.getConnection()) {
            serviceDao serviceDao = new serviceDao(connection);

            // Generate the temporary password before adding the user
            String tempPassword = serviceDao.generateTemporaryPassword();
            boolean success = serviceDao.addUser(name, address, mobileNo, emailId, accountType, balance, dateOfBirth, idProof, tempPassword);

            if (success) {
                // Retrieve the last inserted account number
                long accountNumber = serviceDao.getLastInsertedAccountNumber();
                request.setAttribute("message", "Customer registered successfully.");
                request.setAttribute("accountNumber", accountNumber);
                request.setAttribute("temporaryPassword", tempPassword);
                request.getRequestDispatcher("registrationSuccess.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Failed to register customer.");
                request.getRequestDispatcher("adminDashboard.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred during registration.");
            request.getRequestDispatcher("adminDashboard.jsp").forward(request, response);
        }
    }
}
