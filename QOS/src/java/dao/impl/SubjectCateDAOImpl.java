package dao.impl;

import bean.SubjectCate;
import dao.DBConnection;
import dao.SubjectCateDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The class has methods needed for initialize connection with database and
 * execute queries with SubjectCate and associate tables
 *
 */
public class SubjectCateDAOImpl extends DBConnection implements SubjectCateDAO {

    /**
     * Get subject categories of a specified subject
     *
     * @param subjectId
     * @return
     * @throws Exception
     */
    @Override
    public ArrayList<SubjectCate> getSubjectCateBySubject(int subjectId) throws Exception {
        Connection conn = null;
        ResultSet rs = null;
        /* Result set returned by the sqlserver */
        PreparedStatement pre = null;
        /* Prepared statement for executing sql queries */
 /* Getcategory list of the subject */
        ArrayList<SubjectCate> categories = new ArrayList<>();
        String sql = "SELECT C.[subjectId]\n"
                + "      ,C.[cateId]\n"
                + "	   ,S.[status]\n"
                + "	   ,S.subjectCateName\n"
                + "  FROM [QuizSystem].[dbo].[CategorySubject] C \n"
                + "  INNER JOIN [QuizSystem].[dbo].SubjectCate S\n"
                + "  ON C.cateId = S.subjectCateId WHERE C.subjectId = ?";
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            pre.setInt(1, subjectId);
            rs = pre.executeQuery();
            while (rs.next()) {
                categories.add(new SubjectCate(rs.getInt("cateId"), rs.getString("subjectCateName"), rs.getBoolean("status")));
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
        return categories;
    }

    /**
<<<<<<< HEAD
     * Get subject categories that is not referenced by a specified subject
     *
     * @param subjectId
     * @return
     * @throws Exception
     */
    @Override
    public ArrayList<SubjectCate> getRemainSubjectCateBySubject(int subjectId) throws Exception {
        Connection conn = null;
        ResultSet rs = null;
        /* Result set returned by the sqlserver */
        PreparedStatement pre = null;
        /* Prepared statement for executing sql queries */
 /* Getcategory list of the subject */
        ArrayList<SubjectCate> remainCategories = new ArrayList<>();
        String sql = "SELECT [subjectCateId]\n"
                + "      ,[subjectCateName]\n"
                + "      ,[status]\n"
                + "  FROM [QuizSystem].[dbo].[SubjectCate]\n"
                + "  WHERE [subjectCateId] NOT IN (SELECT C.[cateId]\n"
                + "				FROM [QuizSystem].[dbo].[CategorySubject] C\n"
                + "				INNER JOIN [QuizSystem].[dbo].SubjectCate S\n"
                + "				ON C.cateId = S.subjectCateId WHERE C.subjectId = ?)";
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            pre.setInt(1, subjectId);
            rs = pre.executeQuery();
            while (rs.next()) {
                remainCategories.add(new SubjectCate(rs.getInt("subjectCateId"), rs.getString("subjectCateName"), rs.getBoolean("status")));
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
        return remainCategories;
    }

    /**
=======
>>>>>>> 5bcf8e50d19562d997abb319c60eca73d15e41c5
     * Get all subject categories
     *
     * @return
     * @throws Exception
     */
    @Override
    public ArrayList<SubjectCate> getAllStatusSubjectCates() throws Exception {
        Connection conn = null;
        ResultSet rs = null;
        /* Result set returned by the sqlserver */
        PreparedStatement pre = null;
        /* Prepared statement for executing sql queries */
 /* Get category list */
        ArrayList<SubjectCate> allCategory = new ArrayList<>();
        String sql = "SELECT [subjectCateId]\n"
                + "      ,[subjectCateName]\n"
                + "      ,[status]\n"
                + "  FROM [QuizSystem].[dbo].[SubjectCate]";
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                allCategory.add(new SubjectCate(rs.getInt("subjectCateId"),
                        rs.getString("subjectCateName"),
                        rs.getBoolean("status")));
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
        return allCategory;
    }

    /**
<<<<<<< HEAD
     * Get subject category by Id
     *
     * @param scId Subject Category ID
     * @return
     * @throws Exception
     */
    @Override
    public SubjectCate getSubjectCateById(int scId) throws Exception {
        Connection conn = null;
        ResultSet rs = null;
        /* Result set returned by the sqlserver */
        PreparedStatement pre = null;
        /* Prepared statement for executing sql queries */
 /* Get category list */
        String sql = "SELECT [subjectCateId]\n"
                + "      ,[subjectCateName]\n"
                + "      ,[status]\n"
                + "  FROM [QuizSystem].[dbo].[SubjectCate]\n where subjectCateId = ?";
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            pre.setInt(1, scId);
            rs = pre.executeQuery();
            if (rs.next()) {
                return new SubjectCate(rs.getInt("subjectCateId"), rs.getString("subjectCateName"), rs.getBoolean("status"));
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
     * Update subject Category
     *
     * @param subjectCategoryId
     * @param updatedSubjectCate Update Subject Category
     * @return
     * @throws Exception
     */
    @Override
    public int updateSubjectCate(SubjectCate updatedSubjectCate) throws Exception {
        Connection conn = null;
        ResultSet rs = null;
        /* Result set returned by the sqlserver */
        PreparedStatement pre = null;
        /* Prepared statement for executing sql queries */
        String sql = "  UPDATE [QuizSystem].[dbo].[SubjectCate]\n"
                + "  SET [subjectCateName] = ?\n"
                + "      ,[status] = ?\n"
                + "WHERE [subjectCateId] = ?";
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, updatedSubjectCate.getSubjectCateName());
            pre.setBoolean(2, updatedSubjectCate.isStatus());
            pre.setInt(3, updatedSubjectCate.getSubjectCateId());
            return pre.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
    }

    /**
     * Add new subject Category
     *
     * @param updatedSubjectCate Subject Category
     * @return
     * @throws Exception
     */
    @Override
    public int addSubjectCate(SubjectCate newSubjectCate) throws Exception {
        Connection conn = null;
        ResultSet rs = null;
        /* Result set returned by the sqlserver */
        PreparedStatement pre = null;
        /* Prepared statement for executing sql queries */
        String sql = "INSERT INTO dbo.SubjectCate(subjectCateName,status) VALUES(?,1)";
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, newSubjectCate.getSubjectCateName());
            return pre.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
    }

    /**
=======
>>>>>>> 5bcf8e50d19562d997abb319c60eca73d15e41c5
     * Get subject count by subject categories
     *
     * @return <code>HashMap</code>
     * @throws Exception
     */
    @Override
    public HashMap<String, Integer> getSubjectCountByCate() throws Exception {
        HashMap<String, Integer> map = new HashMap();
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pre = null;
        String sql = "SELECT subjectCateName, COUNT(cateId) AS number "
                + "FROM SubjectCate AS a JOIN CategorySubject AS b "
                + "ON a.subjectCateId = b.cateId "
                + "GROUP BY cateId,subjectCateName";
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                map.put(rs.getString("subjectCateName"), rs.getInt("number"));
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
        return map;
    }

}
