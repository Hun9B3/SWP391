package dao;

import bean.User;

/**
 * Lớp này chứa các interface của UserDAOImpl
 *
 */
public interface UserDAO {
 public User getUserLogin(String userMail, String password) throws Exception;
}
