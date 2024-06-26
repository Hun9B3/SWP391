package dao;

import bean.PricePackage;
import java.util.ArrayList;

/**
 * Lớp này chứa các interface của PricePackageDAOImpl
 *
 */
public interface PricePackageDAO {

    /**
     * get all price package where status = 1
     *
     * @return
     * @throws Exception
     */
    public ArrayList<PricePackage> getAllPricePackage() throws Exception;

    /**
     * get price package by packageId
     *
     * @param packId
     * @return
     * @throws Exception
     */
    public PricePackage getPricePackageById(int ppId) throws Exception;

}
