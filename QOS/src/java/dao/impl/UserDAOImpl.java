
package dao.impl;

import bean.User;
import dao.DBConnection;
import dao.UserDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UserDAOImpl extends DBConnection implements UserDAO {

    @Override
    public User getUserLogin(String userMail, String password) throws Exception {
        Connection conn = null;
        /* Result set returned by the sqlserver */
        ResultSet rs = null;
        /* Prepared statement for executing sql queries */
        PreparedStatement pre = null;

        String sql = "SELECT * FROM [User] WHERE userMail = ? and password = ? and status = 1";
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, userMail);
            pre.setString(2, password);
            rs = pre.executeQuery();
            if (rs.next()) {
                User loginUser = new User(rs.getInt("userId"),
                        rs.getString("userName"),
                        rs.getString("password"),
                        rs.getInt("roleId"),
                        rs.getString("profilePic"),
                        rs.getString("userMail"),
                        rs.getBoolean("gender"),
                        rs.getString("userMobile"),
                        rs.getBoolean("status"));
                return loginUser;
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
        return null;
    }

    /**
     * get user from User table using userId
     *
     * @param userId is an int
     * @return <code>User</code> object.
     * @throws java.lang.Exception
     */
    @Override
    public User getUserById(int userId) throws Exception {
        Connection conn = null;
        /* Result set returned by the sqlserver */
        ResultSet rs = null;
        /* Prepared statement for executing sql queries */
        PreparedStatement pre = null;

        String sql = "SELECT * FROM [User] WHERE userId = ?";
        User user = null;
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            pre.setInt(1, userId);
            rs = pre.executeQuery();
            while (rs.next()) {
                user = new User(rs.getInt("userId"),
                        rs.getString("userName"),
                        rs.getString("password"),
                        rs.getInt("roleId"),
                        rs.getString("profilePic"),
                        rs.getString("userMail"),
                        rs.getBoolean("gender"),
                        rs.getString("userMobile"),
                        rs.getBoolean("status"));
                return user;
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
        return null;
    }

     /**
     * Get all user
     *
     * @return
     * @throws Exception
     */
    @Override
    public ArrayList<User> getUserAllUser() throws Exception {
        ArrayList<User> newUserList = new ArrayList();
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pre = null;
        String sql = "SELECT * FROM [User]";
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                newUserList.add(new User(rs.getInt("userId"),
                        rs.getString("userName"),
                        rs.getString("password"),
                        rs.getInt("roleId"),
                        rs.getString("profilePic"),
                        rs.getString("userMail"),
                        rs.getBoolean("gender"),
                        rs.getString("userMobile"),
                        rs.getBoolean("status")));
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
        return newUserList;
    }
    
    /**
     * get user from User table using userMail
     *
     * @param userMail is an String
     * @return <code>User</code> object.
     */
    @Override
    public User getUserByMail(String userMail) throws Exception {
        Connection conn = null;
        /* Result set returned by the sqlserver */
        ResultSet rs = null;
        /* Prepared statement for executing sql queries */
        PreparedStatement pre = null;

        String sql = "SELECT * FROM [User] WHERE userMail = ?";
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, userMail);
            rs = pre.executeQuery();
            while (rs.next()) {
                return new User(rs.getInt("userId"),
                        rs.getString("userName"),
                        rs.getString("password"),
                        rs.getInt("roleId"),
                        rs.getString("profilePic"),
                        rs.getString("userMail"),
                        rs.getBoolean("gender"),
                        rs.getString("userMobile"),
                        rs.getBoolean("status"));
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
        return null;
    }
    
    /**
     * get user from User table using userMobile
     *
     * @param userMobile is an String
     * @return <code>User</code> object.
     */
    @Override
    public User getUserByMobile(String userMobile) throws Exception {
        Connection conn = null;
        /* Result set returned by the sqlserver */
        ResultSet rs = null;
        /* Prepared statement for executing sql queries */
        PreparedStatement pre = null;
        String sql = "SELECT * FROM [User] WHERE userMobile = ?";
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, userMobile);
            rs = pre.executeQuery();
            while (rs.next()) {
                return new User(rs.getInt("userId"),
                        rs.getString("userName"),
                        rs.getString("password"),
                        rs.getInt("roleId"),
                        rs.getString("profilePic"),
                        rs.getString("userMail"),
                        rs.getBoolean("gender"),
                        rs.getString("userMobile"),
                        rs.getBoolean("status"));
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
        return null;
    }
    
    /**
     * change a user status from User table
     *
     * @param userId is an int
     * @param newStatus is a boolean object
     * @return a int.
     */
    @Override
    public int changeStatus(int userId, boolean newStatus) throws Exception {
        Connection conn = null;
        ResultSet rs = null;
        /* Result set returned by the sqlserver */
        PreparedStatement pre = null;
        /* Prepared statement for executing sql queries */

        String sql = "UPDATE [User] set [status] = ? where userId = ?";
        int check = 0;
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            pre.setBoolean(1, newStatus);
            pre.setInt(2, userId);
            check = pre.executeUpdate();

        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
        return check;
    }
    
    /**
     * add a user to User table
     *
     * @param newUser is an <code>User</code> object
     * @return a int.
     */
    @Override
    public int addUser(User newUser) throws Exception {
        Connection conn = null;
        ResultSet rs = null;
        /* Result set returned by the sqlserver */
        PreparedStatement pre = null;
        /* Prepared statement for executing sql queries */

        String sql = "INSERT INTO [User](userName,[password],roleId,userMail,gender,userMobile,[status])"
                + "values(?,?,?,?,?,?,?)";
        int check = 0;
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, newUser.getUserName());
            pre.setString(2, newUser.getPassword());
            pre.setInt(3, newUser.getRoleId() == 0 ? 1 : newUser.getRoleId());
            pre.setString(4, newUser.getUserMail());
            pre.setBoolean(5, newUser.isGender());
            pre.setString(6, newUser.getUserMobile());
            pre.setBoolean(7, false);
            check = pre.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }

        return check;
    }
    
    /**
     * update a user from User table
     *
     * @param updatedUser is a <code>User</code> object
     * @return a int.
     */
    @Override
    public int updateUser(User updatedUser) throws Exception {
        Connection conn = null;
        /* Result set returned by the sqlserver */
        ResultSet rs = null;
        /* Prepared statement for executing sql queries */
        PreparedStatement pre = null;

        String sql = " UPDATE [User] set userName = ?, "
                + "[password] = ?,  "
                + "roleId = ?, "
                + "profilePic = ?, "
                + "userMail = ?, "
                + "gender = ?, "
                + "userMobile = ?, "
                + "status = ? "
                + "where userId = ?";
        int check = 0;
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, updatedUser.getUserName());
            pre.setString(2, updatedUser.getPassword());
            pre.setInt(3, updatedUser.getRoleId());
            pre.setString(4, updatedUser.getProfilePic());
            pre.setString(5, updatedUser.getUserMail());
            pre.setBoolean(6, updatedUser.isGender());
            pre.setString(7, updatedUser.getUserMobile());
            pre.setBoolean(8, updatedUser.isStatus());
            pre.setInt(9, updatedUser.getUserId());
            check = pre.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }

        return check;
    }
}
