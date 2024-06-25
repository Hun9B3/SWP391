package dao;

import bean.Registration;
import bean.RegistrationManage;
import java.util.ArrayList;

/**
 * Lớp này chứa các interface của RegistrationDAOImpl
 *
 */
public interface RegistrationDAO {

     /**
     * addRegistration
     *
     * @param newRegistration
     * @return
     * @throws Exception
     */
    public int addRegistration(Registration newRegistration) throws Exception;
    
     /**
     * editRegistration
     *
     * @param registrationId
     * @param editedRegistration
     * @return
     * @throws Exception
     */
    public int editRegistration(int registrationId, Registration editedRegistration) throws Exception;

    /**
     * getRegistrationById
     *
     * @param registrationId
     * @return
     * @throws Exception
     */
    public Registration getRegistrationById(int registrationId) throws Exception;

    /**
     * getFilterRegistration
     *
     * @param subjectId
     * @param userId
     * @return
     * @throws Exception
     */
    public ArrayList<RegistrationManage> getFilterRegistration(int subjectId, int userId) throws Exception;

    public ArrayList<Subject> getRegistedSubjectbyUserId(int userId) throws Exception;
}
