package com.uncodigo.vwallet.dao.impl;

import com.uncodigo.vwallet.dao.IUserDao;
import com.uncodigo.vwallet.dto.UserLoginDto;
import com.uncodigo.vwallet.models.database.ConnectionDb;
import com.uncodigo.vwallet.models.users.User;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements IUserDao {
    ConnectionDb conn;
    // Initialize the UserDaoImpl class
    public UserDaoImpl(){
        this.conn = ConnectionDb.getInstance();
    }

    @Override
    public User login(UserLoginDto userLoginDto) {
        try {
            User user = getUserByEmail(userLoginDto.getEmail());

            if (user != null && BCrypt.checkpw(userLoginDto.getPassword(), user.getPassword())) {
                return user;
            } else {
                System.out.println("Invalid email or password");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public User getUserById(int id) {
        try {
            Connection connection = conn.getConnection();
            // Get user with roles
            String query = "SELECT * FROM users WHERE id = ? AND active = true";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            return getUser(preparedStatement);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public User getUserByEmail(String email) {
        try {
            Connection connection = conn.getConnection();
            // Get user with roles
            String query = "SELECT * FROM users WHERE email = ? AND active = true";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);

            return getUser(preparedStatement);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    private User getUser(PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery();

        User user = null;
        if (resultSet.next()) {
            user = new User();
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            user.setActive(resultSet.getBoolean("active"));
        }

        return user;
    }
}
