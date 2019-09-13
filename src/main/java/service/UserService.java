package service;

import dao.UserDAO;
import dao.UserDaoFactory;
import exception.ApplicationException;
import model.User;

import java.sql.SQLException;
import java.util.List;

public class UserService implements Service {

    private static UserService userService;

    private UserDAO userDAO;

    private UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public static UserService getInstance() {
        if (userService == null) {
            try {
                userService = new UserService(UserDaoFactory.getUserDAO());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return userService;
    }

    public void createUser(User user) throws SQLException, ApplicationException {
        userDAO.createUser(user);
    }

    public List<User> getAllUsers() throws ApplicationException {
        return userDAO.getAllUsers();
    }

    public void deleteUserById(int id) throws SQLException, ApplicationException {
        userDAO.deleteUser(id);
    }

    public void updateUser(User user) throws SQLException, ApplicationException {
        userDAO.updateUser(user);
    }

    @Override
    public User getUserByLogin(String login) throws SQLException, ApplicationException {
        return userDAO.getUserByLogin(login);
    }

}
