
package dao;

import java.util.ArrayList;

/**
 * Lớp này chứa các interface của QuizDAOImpl
 *
 */
public interface QuizDAO {
    
    public ArrayList<Quiz> getQuizBySubject(int subjectId) throws Exception;

}
