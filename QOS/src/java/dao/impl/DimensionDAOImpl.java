package dao.impl;

import bean.Dimension;
import dao.DBConnection;
import dao.DimensionDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Lớp này chứa các interface của DimensionDAOImpl
 */
public class DimensionDAOImpl extends DBConnection implements DimensionDAO {
    
    @Override
    public ArrayList<Dimension> getAllDimension() throws Exception {
        Connection conn = null;
        ResultSet rs = null;
        /* Result set returned by the sqlserver */
        PreparedStatement pre = null;
        /* Prepared statement for executing sql queries */

        ArrayList<Dimension> listDimension = new ArrayList();
        String sql = "SELECT  [dimensionId]\n"
                + "      ,[subjectId]\n"
                + "      ,[dimensionTypeId]\n"
                + "      ,[dimensionName]\n"
                + "      ,[description]\n"
                + "      ,[status]\n"
                + "  FROM [QuizSystem].[dbo].[Dimension]";
        /* Get the dimension */
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            /* Get information from resultset and add it to arrayList */
            while (rs.next()) {
                int dimensionId = rs.getInt("dimensionId");
                int subjectId = rs.getInt("subjectId");
                int dimensionTypeId = rs.getInt("dimensionTypeId");
                String dimensionName = rs.getString("dimensionName");
                String description = rs.getString("description");
                Boolean status = rs.getBoolean("status");

                listDimension.add(new Dimension(dimensionId, subjectId, dimensionTypeId, dimensionName, dimensionName, description, status));
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
        return listDimension;
    }

    
    @Override
    public ArrayList<Dimension> getDimensionBySubject(int subjectId) throws Exception {
        Connection conn = null;
        ResultSet rs = null;
  
        PreparedStatement pre = null;
    
        ArrayList<Dimension> dimensions = new ArrayList<>();
        String sql = "SELECT S.[subjectId]\n"
                + "      ,D.[dimensionId]\n"
                + "      ,D.[subjectId]\n"
                + "      ,D.[dimensionTypeId]\n"
                + "      ,D.[dimensionName]\n"
                + "      ,D.[description]\n"
                + "      ,D.[status]\n"
                + "	  ,DT.[dimensionTypeName]\n"
                + "  FROM [QuizSystem].[dbo].[Subject] S \n"
                + "  INNER JOIN [QuizSystem].[dbo].[Dimension] D ON S.subjectId = D.subjectId \n"
                + "  INNER JOIN [QuizSystem].[dbo].DimensionType DT ON DT.dimensionTypeId = D.dimensionTypeId\n"
                + "  WHERE S.subjectId =" + subjectId;
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                dimensions.add(new Dimension(rs.getInt("dimensionId"),
                        subjectId,
                        rs.getInt("dimensionTypeId"),
                        rs.getString("dimensionTypeName"),
                        rs.getString("dimensionName"),
                        rs.getString("description"),
                        rs.getBoolean("status")));
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
        return dimensions;
    }

    @Override
    public Dimension getDimensionById(int dimensionId) throws Exception {
        Connection conn = null;
        ResultSet rs = null;
        /* Result set returned by the sqlserver */
        PreparedStatement pre = null;
        /* Prepared statement for executing sql queries */

        Dimension dimensionById = null;
        String sql = "SELECT * FROM [Dimension] WHERE [dimensionId] = ?";

        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            pre.setInt(1, dimensionId);
            rs = pre.executeQuery();
            if (rs.next()) {
                int subjectId = rs.getInt("subjectId");
                int dimensionTypeId = rs.getInt("dimensionTypeId");
                String dimensionName = rs.getString("dimensionName");
                String description = rs.getString("description");
                Boolean status = rs.getBoolean("status");
                dimensionById = new Dimension(dimensionId, subjectId, dimensionTypeId, dimensionName, dimensionName, description, status);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
        return dimensionById;
    }
}
