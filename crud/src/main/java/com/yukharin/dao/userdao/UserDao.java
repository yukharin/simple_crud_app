package com.yukharin.dao.userdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yukharin.dao.utils.JdbcUtils;
import com.yukharin.model.User;

public class UserDao {

    private static final String PROPERTIES_PATH = "/home/kyle/Documents/eclipse-workspace/crud/src/main/resources/DaoProperties.properties";
    private static final String TABLE = "Users";
    private static final String GET_USER = "SELECT * FROM " + TABLE + " WHERE id = ?";
    private static final String ADD_USER = "INSERT INTO " + TABLE + " (name , age) VALUES (? , ?)";
    private static final String ADD_USER_WITH_ID = "INSERT INTO " + TABLE + " (id, name , age) VALUES (?, ? , ?)";
    private static final String DELETE_USER = "DELETE FROM " + TABLE + " WHERE id = ?";
    private static final String UPDATE_USER = "UPDATE " + TABLE + " SET name = ? , age = ? WHERE id = ?";
    private static final String GET_LIST = "SELECT * FROM " + TABLE;

    private Connection connection;

    public UserDao() {
        connection = JdbcUtils.getConnection(PROPERTIES_PATH);
    }

    public Connection getConnection() {
        return connection;
    }

    public User getUser(int id) {
        User user = new User();
        try (PreparedStatement statement = connection.prepareStatement(GET_USER)) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                user.setId(result.getInt("id"));
                user.setName(result.getString("name"));
                user.setAge(result.getInt("age"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void addUser(User user) {
        if (user.getId() != 0) {
            addUserWithId(user);
        } else {
            addUserWithoutId(user);
        }
    }

    private void addUserWithId(User user) {
        try (PreparedStatement statement = connection.prepareStatement(ADD_USER_WITH_ID)) {
            statement.setLong(1, user.getId());
            statement.setString(2, user.getName());
            statement.setLong(3, user.getAge());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addUserWithoutId(User user) {
        try (PreparedStatement statement = connection.prepareStatement(ADD_USER)) {
            statement.setString(1, user.getName());
            statement.setLong(2, user.getAge());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int id) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_USER)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(int id, User user) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_USER)) {
            statement.setString(1, user.getName());
            statement.setInt(2, user.getAge());
            statement.setInt(3, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getUsersList() throws SQLException {
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_LIST)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                users.add(new User(result.getInt(1),result.getString(2), result.getInt(3)));
            }
        }
        return users;
    }

    public static void main(String[] args) throws SQLException {
        UserDao dao = new UserDao();
        System.out.println(dao.getUser(50));
        dao.deleteUser(-100);
    }

}