package dao.impl;

import bean.PostCate;
import dao.DBConnection;
import dao.PostCateDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * The class has methods needed for initialize connection with database and
 * execute queries with PostCate and associate tables
 *
 */
public class PostCateDAOImpl extends DBConnection implements PostCateDAO {

    /**
     * get all psot categories where status = 1
     *
     * @return
     * @throws Exception
     */
    @Override
    public ArrayList<PostCate> getAllPostCates() throws Exception {
        Connection conn = null;
        ResultSet rs = null;
        /* Result set returned by the sqlserver */
        PreparedStatement pre = null;
        /* Prepared statement for executing sql queries */

        ArrayList<PostCate> allPostCate = new ArrayList();
        String sql = "SELECT * FROM [PostCate] where status = 1 ";
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                allPostCate.add(new PostCate(rs.getInt("postCateId"),
                        rs.getString("postCateName"),
                        rs.getBoolean("status")));
            }
            return allPostCate;
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
    }

    /**
     * get blog category id by blog id
     *
     * @param blogId
     * @return
     * @throws Exception
     */
    @Override
    public int getBlogCateByBlogId(int blogId) throws Exception {
        Connection conn = null;
        ResultSet rs = null;
        /* Result set returned by the sqlserver */
        PreparedStatement pre = null;
        /* Prepared statement for executing sql queries */

        String sql = "SELECT * FROM [BlogCate] WHERE blogId=" + blogId;
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            if (rs.next()) {
                return rs.getInt("postCateId");
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
        return 0;
    }

    /**
     * get post categoory by id
     *
     * @param postCateId
     * @return
     * @throws Exception
     */
    @Override
    public PostCate getPostCateById(int pcId) throws Exception {
        Connection conn = null;
        ResultSet rs = null;
        /* Result set returned by the sqlserver */
        PreparedStatement pre = null;
        /* Prepared statement for executing sql queries */

        String sql = "SELECT * FROM [PostCate] WHERE postCateId = ?";
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            pre.setInt(1, pcId);
            rs = pre.executeQuery();
            while (rs.next()) {
                return new PostCate(rs.getInt("postCateId"),
                        rs.getString("postCateName"),
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

}
