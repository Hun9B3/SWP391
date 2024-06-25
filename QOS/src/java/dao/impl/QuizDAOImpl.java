
package dao.impl;

import dao.DBConnection;
import dao.QuizDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Lớp này có các phương thức truy xuất và thêm dữ liệu vào database liên quan
 * tới bảng Quiz.
 *
 */
public class QuizDAOImpl extends DBConnection implements QuizDAO {

       @Override
    public ArrayList<Quiz> getQuizBySubject(int subjectId) throws Exception {
        ArrayList<Quiz> subjectQuiz = new ArrayList<>();
        Connection conn = null;
        ResultSet rs = null;
        /* Result set returned by the sqlserver */
        PreparedStatement pre = null;
        /* Prepared statement for executing sql queries */
        String quizLevelName = null;
        String testTypeName = null;
        String dimensionTypeName = null;
        String sql = "select * from Quiz where testTypeId = 1 and subjectId = ?";
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            pre.setInt(1, subjectId);
            rs = pre.executeQuery();
            if (rs.next()) {
                if (rs.getObject("quizLevelId") != null) {
                    QuizLevelDAOImpl quizLevelDAO = new QuizLevelDAOImpl();
                    QuizLevel quizLevel = quizLevelDAO.getQuizLevelById(rs.getInt("quizLevelId"));
                    quizLevelName = quizLevel.getQuizLevelName();
                }
                if (rs.getObject("testTypeId") != null) {
                    TestTypeDAOImpl testTypeDAO = new TestTypeDAOImpl();
                    TestType testType = testTypeDAO.getTestTypeById(rs.getInt("testTypeId"));
                    testTypeName = testType.getTestTypeName();
                }
                if (rs.getObject("dimensionTypeId") != null) {
                    DimensionTypeDAOImpl dimensionTypeDAO = new DimensionTypeDAOImpl();
                    DimensionType dimensionType = dimensionTypeDAO.getDimensionTypeById(rs.getInt("dimensionTypeId"));
                    dimensionTypeName = dimensionType.getDimensionTypeName();
                }
                LessonDAO lessonDAO = new LessonDAOImpl();
                Lesson lesson = lessonDAO.getLessonById(rs.getInt("lessonId"));
                SubjectDAO subjectDAO = new SubjectDAOImpl();
                Subject subject = subjectDAO.getSubjectbyId(rs.getInt("subjectId"));
                subjectQuiz.add(new Quiz(rs.getInt("quizId"),
                        lesson,
                        subject,
                        rs.getString("quizName"),
                        rs.getInt("quizLevelId"),
                        quizLevelName,
                        rs.getInt("quizDuration"),
                        rs.getInt("passRate"),
                        rs.getInt("testTypeId"),
                        testTypeName,
                        rs.getString("description"),
                        rs.getInt("numberQuestion"),
                        rs.getInt("dimensionTypeId"),
                        dimensionTypeName,
                        rs.getBoolean("status")));
            }
            return subjectQuiz;
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }       
    }
    
}
