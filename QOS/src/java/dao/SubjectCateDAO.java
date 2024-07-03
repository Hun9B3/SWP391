package dao;

import bean.SubjectCate;
import java.util.ArrayList;

/**
 * Lớp này chứa các interface của SubjectCateDAOImpl
 *
 *
 */
public interface SubjectCateDAO {
  /**
     * Get subject categories of a specified subject
     *
     * @param subjectId
     * @return
     * @throws Exception
     */
    public ArrayList<SubjectCate> getSubjectCateBySubject(int subjectId) throws Exception;

       /**
     * Get subject categories that is not referenced by a specified subject
     *
     * @param subjectId
     * @return
     * @throws Exception
     */
    public ArrayList<SubjectCate> getRemainSubjectCateBySubject(int subjectId) throws Exception;
 
}
