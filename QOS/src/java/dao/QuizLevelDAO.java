package dao;

import bean.QuizLevel;
import java.util.ArrayList;

/**
 * Lớp này chứa các interface của QuizLevelDAOImpl
 *
 */
public interface QuizLevelDAO {

    /**
     * get all quizlevel in the database where status = 1 
     *
     * @return <code>ArrayList<QuizLevel></code>
     * @throws Exception
     */
    public ArrayList<QuizLevel> getAllQuizLevel() throws Exception;
    
    public QuizLevel getQuizLevelById(int quizLevelId) throws Exception;
    
     ArrayList<QuizLevel> getAllStatusQuizLevel() throws Exception;

    int addQuizLevel(QuizLevel quizLevel) throws Exception;

    int editQuizLevel(QuizLevel quizLevel) throws Exception;
}
