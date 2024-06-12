
package dao.impl;

import bean.CustomerQuiz;
import bean.Quiz;
import dao.CustomerQuizDAO;
import dao.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Lớp này có các phương thức thực hiện truy xuất và ghi dữ liệu vào database
 * liên quan tới bảng CustomerQuiz, TakeAnswer phục vụ cho các chức năng liên
 * quan tới QuizReview của dự án
 *
 */
public class CustomerQuizDAOImpl extends DBConnection implements CustomerQuizDAO {

   @Override
    public ArrayList<CustomerQuiz> getQuizByUser(int userId) throws Exception {
        Connection conn = null;
        ResultSet rs = null;
        /* Result set returned by the sqlserver */
        PreparedStatement pre = null;
        /* Prepared statement for executing sql queries */
        CustomerQuiz add = null;
        QuizDAOImpl quizDAO = new QuizDAOImpl();
        TestTypeDAOImpl testTypeDAO = new TestTypeDAOImpl();
        ArrayList<CustomerQuiz> custormerQuiz = new ArrayList<>();
        String sql = "SELECT [quizTakeId]\n"
                + "      ,[quizId]\n"
                + "      ,[userId]\n"
                + "      ,[score]\n"
                + "      ,[time]\n"
                + "      ,[sumitedAt]\n"
                + "      ,[status]\n"
                + "  FROM [QuizSystem].[dbo].[CustomerQuiz]\n"
                + "  WHERE userId = ?";
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            pre.setInt(1, userId);
            rs = pre.executeQuery();
            while (rs.next()) {
                Timestamp time = new Timestamp(rs.getTimestamp("sumitedAt").getTime());
                add = new CustomerQuiz(rs.getInt("quizTakeId"),
                        rs.getInt("quizId"),
                        rs.getInt("userId"),
                        rs.getInt("score"),
                        rs.getInt("time"),
                        time,
                        rs.getBoolean("status"));
                Quiz quiz = quizDAO.getQuizById(rs.getInt("quizId"));
                add.setQuizName(quiz.getQuizName());
                add.setSubjectName(quiz.getSubject().getSubjectName());
                add.setTestTypeName(testTypeDAO.getTestTypeById(quiz.getTestTypeId()).getTestTypeName());
                custormerQuiz.add(add);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
        return custormerQuiz;
    }

    @Override
    public ArrayList<CustomerQuiz> getAllCustomerQuiz() throws Exception {
        ArrayList<CustomerQuiz> allCustomerQuiz = null;
        return allCustomerQuiz;
    }
}
