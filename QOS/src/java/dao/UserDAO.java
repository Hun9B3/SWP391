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
     * get user from User table using userMail
     *
     * @param userMail is an String
     * @return <code>User</code> object.
     * @throws java.lang.Exception
     */
    public User getUserByMail(String userMail) throws Exception;

    /**
     * get user from User table using userMobile
     *
     * @param Mobile is an String
     * @return <code>User</code> object.
     */
    public User getUserByMobile(String Moblie) throws Exception;

    /**
     * Get all user
     *
     * @return
     * @throws Exception
     */
    public ArrayList<User> getUserAllUser() throws Exception;

    /**
     * add a user to User table
     *
     * @param newUser is an <code>User</code> object
     * @return a int.
     * @throws java.lang.Exception
     */
    public int addUser(User newUser) throws Exception;

    /**
     * change a user status from User table
     *
     * @param userId is an int
     * @param newStatus is a boolean object
     * @return a int.
     * @throws java.lang.Exception
     */
    public int changeStatus(int userId, boolean newStatus) throws Exception;

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
