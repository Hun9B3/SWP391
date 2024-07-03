package dao;

import bean.TestType;
import java.util.ArrayList;

/**
 * Lớp này chứa các interface của TestTypeDAOImpl
 *
 *
 */
public interface TestTypeDAO {
       /**
     * Get all testType in the database where status = 1
     *
     * @return <code>ArrayList<TestType></code>
     * @throws Exception
     */
    public ArrayList<TestType> getAllTestTypes() throws Exception;

    public TestType getTestTypeById(int testTypeId) throws Exception;
}
