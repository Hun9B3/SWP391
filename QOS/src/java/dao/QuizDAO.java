
package dao;

import bean.Quiz;

/**
 * Lớp này chứa các interface của QuizDAOImpl
 *
 */
public interface QuizDAO {
    public Quiz getQuizById(int quizId) throws Exception;
}
