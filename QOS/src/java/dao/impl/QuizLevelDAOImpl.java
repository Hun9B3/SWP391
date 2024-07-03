package dao.impl;

import bean.QuizLevel;
import dao.DBConnection;
import dao.QuizLevelDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * The class has methods needed for initialize connection with database and
 * execute queries with QuizLevel and associate tables
 *
 */
public class QuizLevelDAOImpl extends DBConnection implements QuizLevelDAO {

    @Override
    public QuizLevel getQuizLevelById(int quizLevelId) throws Exception {
        Connection conn = null;
        ResultSet rs = null;
        /* Result set returned by the sqlserver */
        PreparedStatement pre = null;
        /* Prepared statement for executing sql queries */

        String sql = "SELECT * FROM [QuizLevel] where quizLevelId=" + quizLevelId;
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            if (rs.next()) {
                return new QuizLevel(rs.getInt("quizLevelId"),
                        rs.getString("quizLevelName"),
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
     * get all quizlevel in the database where status = 1
     *
     * @return <code>ArrayList<QuizLevel></code>
     * @throws Exception
     */
    @Override
    public ArrayList<QuizLevel> getAllQuizLevel() throws Exception {
        ArrayList<QuizLevel> quizLevels = new ArrayList<>();
        Connection conn = null;
        ResultSet rs = null;
        /* Result set returned by the sqlserver */
        PreparedStatement pre = null;
        /* Prepared statement for executing sql queries */
        QuizLevel quizLevel = null;
        String sql = "SELECT [quizLevelId]\n"
                + "      ,[quizLevelName]\n"
                + "      ,[status]\n"
                + "  FROM [QuizSystem].[dbo].[QuizLevel] where status = 1";
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                quizLevel = new QuizLevel(rs.getInt("quizLevelId"),
                        rs.getString("quizLevelName"),
                        rs.getBoolean("status"));
                quizLevels.add(quizLevel);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
        return quizLevels;
    }

}
