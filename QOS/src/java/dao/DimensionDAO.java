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
     *
     * @param subjectId
     * @return
     * @throws Exception
     */
    public ArrayList<Dimension> getAllDimension() throws Exception;

    /**
     * Get dimension by a specified id
     *
     * @param dimensionId
     * @return
     * @throws Exception
     */
    public Dimension getDimensionById(int dimensionId) throws Exception;

    /**
     * Add new subject dimension
     *
     * @param dimension
     * @return
     * @throws Exception
     */
    public int addDimension(Dimension dimension) throws Exception;

    /**
     * Delete subject's dimension
     *
     * @param dimensionId
     * @return
     * @throws Exception
     */
    public int deleteDimension(int dimensionId) throws Exception;

    /**
     * Edit subject's dimension
     *
     * @param dimensionId
     * @param dimension
     * @return
     * @throws Exception
     */
    public int editDimension(int dimensionId, Dimension dimension) throws Exception;

    public ArrayList<Dimension> getDimensionBySubject(int subjectId) throws Exception;

}
