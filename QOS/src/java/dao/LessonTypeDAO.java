package dao;

import bean.LessonType;
import java.util.ArrayList;

/**
 * Lớp này chứa các interface của LessonTypeDAOImpl
 *
 */
public interface LessonTypeDAO {

    /**
     * get all lesson type from the database have status = 1
     *
     * @return
     * @throws Exception
     */
    public ArrayList<LessonType> getAllLessonType() throws Exception;

    ArrayList<LessonType> getAllStatusLessonType() throws Exception;

    LessonType getLessonTypeById(int ltId) throws Exception;

    int addLessonType(LessonType newLessonType) throws Exception;

    int updateLessonType(LessonType updatedLessonType) throws Exception;
}
