package dao.impl;

import bean.PricePackage;
import dao.DBConnection;
import dao.PricePackageDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Lớp này chứa các method của PricePackageDAOImpl
 *
 */
public class PricePackageDAOImpl extends DBConnection implements PricePackageDAO {

    /**
     * get all price package where status = 1
     *
     * @return
     * @throws Exception
     */
    @Override
    public ArrayList<PricePackage> getAllPricePackage() throws Exception {
        ArrayList<PricePackage> packageList = new ArrayList();
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pre = null;
        String sql = "SELECT * FROM [PricePackage]";
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                packageList.add(new PricePackage(rs.getInt("packId"),
                        rs.getString("packName"),
                        rs.getInt("subjectId"),
                        rs.getInt("duration"),
                        rs.getFloat("listPrice"),
                        rs.getFloat("salePrice"),
                        rs.getBoolean("status")));
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
        return packageList;
    }

    /**
     * get price package by packageId
     *
     * @param packId
     * @return
     * @throws Exception
     */
    @Override
    public PricePackage getPricePackageById(int packId) throws Exception {
        Connection conn = null;
        ResultSet rs = null;
        /* Result set returned by the sqlserver */
        PreparedStatement pre = null;
        /* Prepared statement for executing sql queries */
        String sql = "SELECT * FROM PricePackage WHERE packId=" + packId;
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            if (rs.next()) {
                return new PricePackage(rs.getInt("packId"),
                        rs.getString("packName"),
                        rs.getInt("subjectId"),
                        rs.getInt("duration"),
                        rs.getFloat("listPrice"),
                        rs.getFloat("salePrice"),
                        rs.getBoolean("status"));
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
        return null;
    }

  
    /**
     * get all price package by subjectId
     * @param subjectId
     * @return
     * @throws Exception 
     */
    @Override
    public ArrayList<PricePackage> getAllPricePackagesBySubject(int subjectId) throws Exception {
        ArrayList<PricePackage> pricePackages = new ArrayList<>();
        Connection conn = null;
        ResultSet rs = null;
        /* Result set returned by the sqlserver */
        PreparedStatement pre = null;
        /* Prepared statement for executing sql queries */
        PricePackage pricePackage =null;
        String sql = "SELECT * from PricePackage where subjectId = ?";
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            pre.setInt(1, subjectId);

            rs = pre.executeQuery();
            while (rs.next()) {
                pricePackage = new PricePackage(rs.getInt("packId"),
                        rs.getString("packname"),
                        rs.getInt("subjectId"),
                        rs.getInt("duration"),
                        rs.getInt("listPrice"),
                        rs.getInt("salePrice"),
                        rs.getBoolean("status"));
                pricePackages.add(pricePackage);
                return pricePackages;
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
        return null;
    }

    
}
