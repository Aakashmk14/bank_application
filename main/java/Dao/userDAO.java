package Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class userDAO {
    private Connection connection;
    
    public userDAO(Connection connection) {
        this.connection = connection;
    }
    
    public boolean validateUser(String identifier, String password, String role) throws SQLException {
        String query;
        
        if ("admin".equals(role)) {
            query = "SELECT * FROM admin WHERE username = ? AND password = ?";
        } else {
            query = "SELECT * FROM customer WHERE account_no = ? AND password = ?";
        }
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, identifier);
            preparedStatement.setString(2, password);
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        }
    }
}

