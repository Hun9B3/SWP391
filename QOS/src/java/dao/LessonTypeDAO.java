
package dao;

import bean.LessonType;
import java.util.ArrayList;

/**
 * Lớp này chứa các interface của LessonTypeDAOImpl
 *
 */
public interface LessonTypeDAO {
     ArrayList<LessonType> getAllStatusLessonType() throws Exception;

    LessonType getLessonTypeById(int ltId) throws Exception;

    int addLessonType(LessonType newLessonType) throws Exception;

    int updateLessonType(LessonType updatedLessonType) throws Exception;
}
