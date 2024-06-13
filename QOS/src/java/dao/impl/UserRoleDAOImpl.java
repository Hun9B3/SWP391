package dao.impl;

import bean.UserRole;
import dao.DBConnection;
import dao.UserRoleDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * The class has methods needed for initialize connection with database and
 * execute queries with UserRole and associate tables
 *
 */
public class UserRoleDAOImpl extends DBConnection implements UserRoleDAO {

    /**
     * Get all user role
     * @return
     * @throws Exception 
     */
    @Override
    public ArrayList<UserRole> getAllUserRole() throws Exception {
        Connection conn = null;
        ResultSet rs = null;
        /* Result set returned by the sqlserver */
        PreparedStatement pre = null;
        /* Prepared statement for executing sql queries */

        String sql = "SELECT [userRoleId],[userRoleName],[status] FROM [QuizSystem].[dbo].[UserRole] where status = 1";
        ArrayList<UserRole> allUserRole = new ArrayList<>();
        UserRole add = null;
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                add = new UserRole(rs.getInt("userRoleId"), rs.getString("userRoleName"), rs.getBoolean("status"));
                allUserRole.add(add);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
        return allUserRole;
    }

}
