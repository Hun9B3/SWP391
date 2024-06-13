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
     * Get all subject categories
     *
     * @return
     * @throws Exception
     */
    public ArrayList<SubjectCate> getAllStatusSubjectCates() throws Exception;

    /**
     * Get subject count by subject categories
     *
     * @return <code>HashMap</code>
     * @throws Exception
     */
    public HashMap<String, Integer> getSubjectCountByCate() throws Exception;
}
