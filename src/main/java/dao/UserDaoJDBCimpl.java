package dao;

import exception.ApplicationException;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCimpl implements UserDAO {

    private Connection connection;

    UserDaoJDBCimpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createUser(User user) throws SQLException, ApplicationException {

        String sql = "INSERT INTO user_test.users (name, age, login, password, role) Values (?, ?, ?, ?, ?)";

        connection.setAutoCommit(false);

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getName());
            statement.setInt(2, user.getAge());
            statement.setString(3, user.getLogin());
            statement.setString(4,user.getPassword());
            statement.setString(5, user.getRole());
            if (statement.executeUpdate() == 0) {
                throw new IllegalArgumentException();
            }
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
            throw new ApplicationException("Ошибка танзакции!");
        }

        connection.commit();
    }

    @Override
    public List<User> getAllUsers() throws ApplicationException {
        try (Statement statement = connection.createStatement()) {
            statement.execute("SELECT * FROM user_test.users");
            ResultSet resultSet = statement.getResultSet();
            List<User> userList = new ArrayList<>();
            while (resultSet.next()) {
                userList.add(new User(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("age"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("role")
                ));
            }
            resultSet.close();
            return userList;

        } catch (Exception e) {
            e.printStackTrace();
            throw new ApplicationException("Ошибка получения данных!");
        }
    }

    @Override
    public void updateUser(User user) throws SQLException, ApplicationException {

        connection.setAutoCommit(false);

        try(PreparedStatement statement = connection.prepareStatement(
                "UPDATE user_test.users SET name = ? , age = ?, login = ?, password = ?, role = ? WHERE id = ?")) {
            statement.setString(1, user.getName());
            statement.setInt(2, user.getAge());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getRole());
            statement.setInt(6, user.getId());
            if (statement.executeUpdate() == 0) {
                throw new IllegalArgumentException();
            }

        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
            throw new ApplicationException("Ошибка транзакции!");
        }

        connection.commit();
    }

    @Override
    public void deleteUser(int id) throws SQLException, ApplicationException {

        String sql = "DELETE FROM user_test.users WHERE id = '" + id + "'";

        connection.setAutoCommit(false);

        try (Statement statement = connection.createStatement()) {
            if (statement.executeUpdate(sql) == 0) {
                throw new IllegalArgumentException();
            }
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
            throw new ApplicationException("Ошибка транзакции!");
        }

        connection.commit();
    }

    @Override
    public User getUserByLogin(String login) throws SQLException, ApplicationException {

        String sql = "SELECT * FROM user_test.users WHERE login = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1,login);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            resultSet.next();
            User user = new User(resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("age"),
                    resultSet.getString("login"),
                    resultSet.getString("password"),
                    resultSet.getString("role")
            );
            resultSet.close();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApplicationException("Ошибка получения данных!");
        }
    }
}
