package dao;

import bean.Subject;
import java.util.ArrayList;

/**
 * The class has methods needed for initialize connection with database and
 * execute queries with Subject and associate tables
 */
public interface SubjectDAO {

    /**
     *
     * @param subjectId
     * @return
     * @throws Exception Get subject with a certain Id
     */
    public Subject getSubjectbyId(int subjectId) throws Exception;

    /**
     *
     * @return @throws Exception Get featured subjects
     */
    public ArrayList<Subject> getFeaturedSubjects() throws Exception;

    /**
     * Get all available subject in the Subject table (status = 1)
     *
     * @return @throws Exception
     */
    public ArrayList<Subject> getAllSubjects() throws Exception;

    /**
     *
     * @param userId
     * @return
     * @throws Exception Get subjects assigned by certain expert
     */
    public ArrayList<Subject> getSubjectsAssigned(int userId) throws Exception;

    /**
     * Method to perform the single-value parameters of subject
     *
     * @param subjectId
     * @param subject
     * @return
     * @throws Exception
     */
    public int updateSubjectBasic(int subjectId, Subject subject) throws Exception;

    public ArrayList<Subject> getSubjectsPaging(int page) throws Exception;
     
    /**
     * Get All subject assigned to an expert paginated
     * @param userId
     * @param page
     * @return
     * @throws Exception 
     */
    public ArrayList<Subject> getSubjectsAssignedPaging(int userId, int page) throws Exception;
    
     /**
     * Get all subject paginated
     * @param page
     * @return
     * @throws Exception 
     */
    public ArrayList<Subject> getTrueSubjectsPaging(int page) throws Exception;
    

    public ArrayList<Subject> getTrueAllSubjects() throws Exception;

    /**
     *
     * @return @throws Exception Get 5 last added subject in the Subject table
     */
    public ArrayList<Subject> get5LastAddedSubject() throws Exception;
}
