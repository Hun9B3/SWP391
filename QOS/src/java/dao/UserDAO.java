package dao;

import bean.User;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Lớp này chứa các interface của UserDAOImpl
 *
 */
public interface UserDAO {

    public User getUserLogin(String userMail, String password) throws Exception;

    /**
     * get user from User table using userId
     *
     * @param userId is an <code>int</code>
     * @return <code>User</code> object.
     * @throws java.lang.Exception
     */
    public User getUserById(int userId) throws Exception;

    /**
     * Get all user
     *
     * @return
     * @throws Exception
     */
    public ArrayList<User> getUserAllUser() throws Exception;

    /**
     * get new users
     *
     * @return <code>ArrayList<Use>r</code> object.
     * @throws java.lang.Exception
     */
    public ArrayList<User> get10NewUser() throws Exception;

    /**
     * Get user count by user role
     *
     * @return <code>HashMap</code>
     * @throws Exception
     */
    public HashMap<String, Integer> getUserCountByRole() throws Exception;
}
