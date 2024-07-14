package dao;

import bean.SubjectCate;
import java.util.ArrayList;
import java.util.HashMap;

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

    ArrayList<SubjectCate> getAllStatusSubjectCates() throws Exception;

    SubjectCate getSubjectCateById(int scId) throws Exception;

    int updateSubjectCate(SubjectCate updatedSubjectCate) throws Exception;

    int addSubjectCate(SubjectCate updatedSubjectCate) throws Exception;

    /**
     * Get subject count by subject categories
     *
     * @return <code>HashMap</code>
     * @throws Exception
     */
    public HashMap<String, Integer> getSubjectCountByCate() throws Exception;
}
