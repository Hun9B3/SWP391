package dao.impl;

import bean.TestType;
import dao.DBConnection;
import dao.TestTypeDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * The class has methods needed for initialize connection with database and
 * execute queries with TestType and associate tables
 *
 */
public class TestTypeDAOImpl extends DBConnection implements TestTypeDAO {

    @Override
    public TestType getTestTypeById(int testTypeId) throws Exception {
        Connection conn = null;
        ResultSet rs = null;
        /* Result set returned by the sqlserver */
        PreparedStatement pre = null;
        /* Prepared statement for executing sql queries */
        String sql = "SELECT * FROM [TestType] WHERE testTypeId =" + testTypeId;
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            if (rs.next()) {
                return new TestType(rs.getInt("testTypeId"),
                        rs.getString("testTypeName"),
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
     * Get all testType in the database where status = 1
     *
     * @return <code>ArrayList<TestType></code>
     * @throws Exception
     */
    @Override
    public ArrayList<TestType> getAllTestTypes() throws Exception {
        Connection conn = null;
        ResultSet rs = null;
        /* Result set returned by the sqlserver */
        PreparedStatement pre = null;
        /* Prepared statement for executing sql queries */
        TestType testType = null;
        ArrayList<TestType> testTypeList = new ArrayList<>();
        String sql = "SELECT [testTypeId]\n"
                + "      ,[testTypeName]\n"
                + "      ,[status]\n"
                + "  FROM [QuizSystem].[dbo].[TestType] where status = 1";
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                testType = new TestType(rs.getInt("testTypeId"),
                        rs.getString("testTypeName"),
                        rs.getBoolean("status"));
                testTypeList.add(testType);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
        return testTypeList;
    }

    /**
     * Get all testType in the database
     *
     * @return <code>ArrayList<TestType></code>
     * @throws Exception
     */
    @Override
    public ArrayList<TestType> getAllStatusTestTypes() throws Exception {
        Connection conn = null;
        ResultSet rs = null;
        /* Result set returned by the sqlserver */
        PreparedStatement pre = null;
        /* Prepared statement for executing sql queries */
        TestType testType = null;
        ArrayList<TestType> testTypeList = new ArrayList<>();
        String sql = "SELECT [testTypeId]\n"
                + "      ,[testTypeName]\n"
                + "      ,[status]\n"
                + "  FROM [QuizSystem].[dbo].[TestType]";
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                testType = new TestType(rs.getInt("testTypeId"),
                        rs.getString("testTypeName"),
                        rs.getBoolean("status"));
                testTypeList.add(testType);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
        return testTypeList;
    }

    /**
     * update existed test type in the database
     *
     * @param updatedTestType
     * @return <code>Integer</code>
     * @throws Exception
     */
    @Override
    public int updateTestType(TestType updatedTestType) throws Exception {
        Connection conn = null;
        ResultSet rs = null;
        /* Result set returned by the sqlserver */
        PreparedStatement pre = null;
        /* Prepared statement for executing sql queries */
        String sql = "  UPDATE [QuizSystem].[dbo].[TestType]\n"
                + "  SET [testTypeName] = ? \n"
                + "      ,[status] = ?\n"
                + "WHERE [testTypeId] = ?";
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, updatedTestType.getTestTypeName());
            pre.setBoolean(2, updatedTestType.isStatus());
            pre.setInt(3, updatedTestType.getTestTypeId());
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
     * add a new test type to the database
     *
     * @param newTestType
     * @return <code>Integer</code>
     * @throws Exception
     */
    @Override
    public int addTestType(TestType newTestType) throws Exception {
        Connection conn = null;
        ResultSet rs = null;
        /* Result set returned by the sqlserver */
        PreparedStatement pre = null;
        /* Prepared statement for executing sql queries */
        String sql = "INSERT INTO dbo.TestType(testTypeName,status) VALUES(?,1);";
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, newTestType.getTestTypeName());
            return pre.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
    }

}
