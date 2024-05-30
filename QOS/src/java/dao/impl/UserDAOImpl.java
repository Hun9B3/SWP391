
package dao.impl;

import bean.User;
import dao.DBConnection;
import dao.UserDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
}
