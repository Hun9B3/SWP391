package dao;

import bean.Question;
import bean.QuestionManage;
import java.util.ArrayList;

/**
 * Lớp này chứa các interface của QuestionDAOImpl
 *
 */
public interface QuestionDAO {

    /**
     * get list of question of the target quiz
     *
     * @param quizId the target quiz's id. It is a <code>int</code> primitive
     * type
     * @return a list of question. It is a <code>java.util.ArrayList</code>
     * object.
     * @throws java.lang.Exception
     */
    public ArrayList<Question> getQuestionByQuizId(int quizId) throws Exception;

    /**
     * turn a list of question into list of question quiz handle
     *
     * @param questionId the target question's id. It is a <code>int</code>
     * primitive type
     * @return a question. It is a <code>Question</code> object.
     * @throws java.lang.Exception
     */
    public Question getQuestionById(int questionId) throws Exception;

    /**
     *
     * @param numberOfQuestion
     * @param subjectId
     * @param dimensionId
     * @return <code>ArrayList<DimensionType></code> object
     * @throws Exception
     */
    public ArrayList<Question> getQuestionForCreateQuiz(int numberOfQuestion, int subjectId, int dimensionId) throws Exception;

    public ArrayList<QuestionManage> getQuestionByContent(String content) throws Exception;
    
    public ArrayList<QuestionManage> getQuestionManage(int subjectId, int dimensionId, int lessonId) throws Exception;
    
    public int addQuestion(Question newQuestion) throws Exception;
    
    public int getQuestionIdCreated(Question question) throws Exception;
    
    public int editQuestion(int questionId, Question question) throws Exception;

}
