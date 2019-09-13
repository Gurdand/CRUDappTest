package service;

import exception.ApplicationException;
import model.User;

import java.sql.SQLException;
import java.util.List;

public interface Service {

    void createUser(User user) throws SQLException, ApplicationException;

    List<User> getAllUsers() throws ApplicationException;

    void deleteUserById(int id) throws SQLException, ApplicationException;

    void updateUser(User user) throws SQLException, ApplicationException;

    User getUserByLogin(String login) throws SQLException, ApplicationException;
}
