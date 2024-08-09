package Dao;
import Model.Customer;
import Model.Transaction;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.security.SecureRandom;

public class serviceDao {
    private Connection connection;

    public serviceDao(Connection connection) {
        this.connection = connection;
    }

    public boolean addUser(String name, String address, String mobileNo, String emailId, String accountType,
                           String balance, String dateOfBirth, String idProof, String tempPassword) throws SQLException {
        String sql = "INSERT INTO customer (username, address, mobile_no, email_id, account_type, balance, date_of_birth, id_proof, password) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, address);
            statement.setString(3, mobileNo);
            statement.setString(4, emailId);
            statement.setString(5, accountType);
            statement.setString(6, balance);
            statement.setString(7, dateOfBirth);
            statement.setString(8, idProof);
            statement.setString(9, tempPassword);
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        }
    }
    public boolean deleteCustomer(String accountNo) throws SQLException {
        String query = "DELETE FROM customer WHERE account_no = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, accountNo);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        }
    }

    // Method to update customer details
    public boolean updateCustomer(String accountNo, String username, String address, String mobileNo, 
                                  String emailId, String accountType, String dateOfBirth, String idProof) throws SQLException {
        String query = "UPDATE customer SET username = ?, address = ?, mobile_no = ?, email_id = ?, " +
                       "account_type = ?, date_of_birth = ?, id_proof = ? WHERE account_no = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, address);
            statement.setString(3, mobileNo);
            statement.setString(4, emailId);
            statement.setString(5, accountType);
            statement.setString(6, dateOfBirth);
            statement.setString(7, idProof);
            statement.setString(8, accountNo);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        }
    }


    public long getLastInsertedAccountNumber() throws SQLException {
        String sql = "SELECT MAX(account_no) FROM customer";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getLong(1);
            } else {
                throw new SQLException("Failed to retrieve the last inserted account number.");
            }
        }
    }

    public String generateTemporaryPassword() {
        final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        final int PASSWORD_LENGTH = 8;
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(PASSWORD_LENGTH);
        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            password.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return password.toString();
    }

    public List<Customer> getAllCustomers() throws SQLException {
        String query = "SELECT account_no, username, address, mobile_no, email_id, account_type, date_of_birth, id_proof FROM customer";
        List<Customer> customers = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setAccountNo(rs.getString("account_no"));
                customer.setUsername(rs.getString("username"));
                customer.setAddress(rs.getString("address"));
                customer.setMobileNo(rs.getString("mobile_no"));
                customer.setEmailId(rs.getString("email_id"));
                customer.setAccountType(rs.getString("account_type"));
                customer.setDateOfBirth(rs.getString("date_of_birth"));
                customer.setIdProof(rs.getString("id_proof"));
                customers.add(customer);
            }
        }

        return customers;
    }
    public Customer getCustomerByAccountNo(String accountNo) throws SQLException {
        String query = "SELECT * FROM customer WHERE account_no = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, accountNo);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Create a Customer object and populate it with data from the result set
                    Customer customer = new Customer();
                    customer.setAccountNo(resultSet.getString("account_no"));
                    customer.setUsername(resultSet.getString("username"));
                    customer.setAddress(resultSet.getString("address"));
                    customer.setMobileNo(resultSet.getString("mobile_no"));
                    customer.setEmailId(resultSet.getString("email_id"));
                    customer.setAccountType(resultSet.getString("account_type"));
                    customer.setDateOfBirth(resultSet.getString("date_of_birth"));
                    customer.setIdProof(resultSet.getString("id_proof"));
                    // Note: Balance and Password are not retrieved as per requirement
                    return customer;
                } else {
                    return null; // Return null if no customer found with the given account number
                }
            }
        }
    }

    public boolean changePassword(String accountNo, String currentPassword, String newPassword) throws SQLException {
        String sqlCheckPassword = "SELECT password FROM customer WHERE account_no = ?";
        String sqlUpdatePassword = "UPDATE customer SET password = ? WHERE account_no = ?";
        try (PreparedStatement checkPasswordStatement = connection.prepareStatement(sqlCheckPassword)) {
            checkPasswordStatement.setString(1, accountNo);
            try (ResultSet resultSet = checkPasswordStatement.executeQuery()) {
                if (resultSet.next()) {
                    String existingPassword = resultSet.getString("password");
                    if (existingPassword.equals(currentPassword)) {
                        try (PreparedStatement updatePasswordStatement = connection.prepareStatement(sqlUpdatePassword)) {
                            updatePasswordStatement.setString(1, newPassword);
                            updatePasswordStatement.setString(2, accountNo);
                            int rowsUpdated = updatePasswordStatement.executeUpdate();
                            return rowsUpdated > 0;
                        }
                    } else {
                        return false; // Current password does not match
                    }
                } else {
                    throw new SQLException("Account Number not found.");
                }
            }
        }
    }

    public boolean withdrawMoney(String accountNo, double amount) throws SQLException {
        // Check current balance
        String checkBalanceSql = "SELECT balance FROM customer WHERE account_no = ?";
        double currentBalance;

        try (PreparedStatement checkBalanceStmt = connection.prepareStatement(checkBalanceSql)) {
            checkBalanceStmt.setString(1, accountNo);
            try (ResultSet resultSet = checkBalanceStmt.executeQuery()) {
                if (resultSet.next()) {
                    currentBalance = resultSet.getDouble("balance");
                } else {
                    return false; // Account not found
                }
            }
        }

        // Check if the amount to withdraw is less than or equal to the current balance
        if (amount <= 0 || amount > currentBalance) {
            return false; // Invalid amount
        }

        // Perform the withdrawal
        String withdrawSql = "UPDATE customer SET balance = balance - ? WHERE account_no = ?";
        try (PreparedStatement withdrawStmt = connection.prepareStatement(withdrawSql)) {
            double newBalance = currentBalance - amount;
            withdrawStmt.setDouble(1, amount);
            withdrawStmt.setString(2, accountNo);
            int rowsAffected = withdrawStmt.executeUpdate();

            if (rowsAffected > 0) {
                // Record the transaction
                recordTransaction(accountNo, "Withdrawal", amount, newBalance);
                return true;
            } else {
                return false;
            }
        }
    }


    public boolean deposit(String accountNo, double amount) throws SQLException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero.");
        }

        // Check if account exists
        String sqlCheckAccount = "SELECT balance FROM customer WHERE account_no = ?";
        double currentBalance;

        try (PreparedStatement checkAccountStatement = connection.prepareStatement(sqlCheckAccount)) {
            checkAccountStatement.setString(1, accountNo);
            try (ResultSet resultSet = checkAccountStatement.executeQuery()) {
                if (resultSet.next()) {
                    currentBalance = resultSet.getDouble("balance");
                } else {
                    return false; // Account number not found
                }
            }
        }

        // Update balance
        String sqlUpdateBalance = "UPDATE customer SET balance = ? WHERE account_no = ?";
        try (PreparedStatement updateBalanceStatement = connection.prepareStatement(sqlUpdateBalance)) {
            double newBalance = currentBalance + amount;
            updateBalanceStatement.setDouble(1, newBalance);
            updateBalanceStatement.setString(2, accountNo);
            int rowsUpdated = updateBalanceStatement.executeUpdate();
            
            if (rowsUpdated > 0) {
                // Record the transaction
                recordTransaction(accountNo, "Deposit", amount, newBalance);
                return true;
            } else {
                return false;
            }
        }
    }


    public double getBalance(String accountNo) throws SQLException {
        String sql = "SELECT balance FROM customer WHERE account_no = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, accountNo);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getDouble("balance");
                } else {
                    throw new SQLException("Account not found.");
                }
            }
        }
    }

    public void recordTransaction(String accountNo, String type, double amount, double balanceAfter) throws SQLException {
        String sql = "INSERT INTO transactions (account_no, transaction_date, transaction_type, amount, balance_after) VALUES (?, NOW(), ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, accountNo);
            statement.setString(2, type);
            statement.setDouble(3, amount);
            statement.setDouble(4, balanceAfter);
            statement.executeUpdate();
        }
    }

    public List<Transaction> getTransactions(String accountNo) throws SQLException {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT transaction_id, account_no, transaction_date, transaction_type, amount, balance_after FROM transactions WHERE account_no = ? ORDER BY transaction_date DESC";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, accountNo);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int transactionId = resultSet.getInt("transaction_id");
                    String accNo = resultSet.getString("account_no");
                    Date transactionDate = resultSet.getDate("transaction_date");
                    String transactionType = resultSet.getString("transaction_type");
                    double amount = resultSet.getDouble("amount");
                    double balanceAfter = resultSet.getDouble("balance_after");

                    Transaction transaction = new Transaction(transactionId, accNo, transactionDate, transactionType, amount, balanceAfter);
                    transactions.add(transaction);
                }
            }
        }

        return transactions;
    }}