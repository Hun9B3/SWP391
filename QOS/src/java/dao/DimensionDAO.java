
package dao;

import bean.Dimension;
import java.util.ArrayList;

/**
 * Lớp này chứa các interface của DimensionDAOImpl
 *
 */
public interface DimensionDAO {
    /**
     * Get dimensions of a subject
     * @param subjectId
     * @return
     * @throws Exception 
     */
    public ArrayList<Dimension> getAllDimension() throws Exception;
    
    public ArrayList<Dimension> getDimensionBySubject(int subjectId) throws Exception;
    
}
