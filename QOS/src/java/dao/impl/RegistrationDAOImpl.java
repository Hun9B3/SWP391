package dao.impl;

import bean.Registration;
import bean.Subject;
import bean.RegistrationManage;
import dao.DBConnection;
import dao.PricePackageDAO;
import java.util.ArrayList;
import dao.RegistrationDAO;
import dao.SubjectDAO;
import dao.UserDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Lớp này có các phương thức thực hiện truy xuất và ghi dữ liệu vào database
 * liên quan tới bảng Registration phục vụ cho các chức năng liên quan tới Registration
 * của dự án
 *
 */
public class RegistrationDAOImpl extends DBConnection implements RegistrationDAO {

    /**
     * getRegistrationById
     *
     * @param registrationId
     * @return
     * @throws Exception
     */
    @Override
    public Registration getRegistrationById(int registrationId) throws Exception {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pre = null;
        String sql = "SELECT * FROM [Registration] WHERE regId=?";
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            pre.setInt(1, registrationId);
            rs = pre.executeQuery();
            if (rs.next()) {
                return new Registration(rs.getInt("regId"),
                        rs.getInt("userId"),
                        rs.getDate("regTime"),
                        rs.getInt("packId"),
                        rs.getDouble("cost"),
                        rs.getDate("validFrom"),
                        rs.getDate("validTo"),
                        rs.getInt("lastUpdatedBy"),
                        rs.getString("note"),
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
     * addRegistration
     *
     * @param newRegistration
     * @return
     * @throws Exception
     */
    @Override
    public int addRegistration(Registration newRegistration) throws Exception {
        Connection conn = null;
        ResultSet rs = null;/* Result set returned by the sqlserver */
        PreparedStatement pre = null;/* Prepared statement for executing sql queries */

        String sql = "INSERT INTO [QuizSystem].[dbo].[Registration]"
                + "(userId,regTime,packId,cost,validFrom,validTo,lastUpdatedBy,note,status)"
                + " values (?,GETDATE(),?,?,?,?,?,?,?)";
        int count = 0;
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            pre.setInt(1, newRegistration.getUserId());
            pre.setInt(2, newRegistration.getPackId());
            pre.setDouble(3, newRegistration.getCost());
            pre.setDate(4, (Date) newRegistration.getValidFrom());
            pre.setDate(5, (Date) newRegistration.getValidTo());
            pre.setInt(6, newRegistration.getLastUpdatedBy());
            pre.setString(7, newRegistration.getNote());
            pre.setBoolean(8, newRegistration.isStatus());
            count = pre.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
        return count;
    }

    /**
     * editRegistration
     *
     * @param registrationId
     * @param editedRegistration
     * @return
     * @throws Exception
     */
    @Override
    public int editRegistration(int registrationId, Registration editedRegistration) throws Exception {
        int i = 0;
        Connection conn = null;
        PreparedStatement pre = null;
        /* Prepared statement for executing sql queries */

        String sql = "UPDATE [QuizSystem].[dbo].[Registration] \n"
                + "SET lastUpdatedBy=?,\n"
                + "	note=?,\n"
                + "	[status]=?\n"
                + "	WHERE regId=?";
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            pre.setInt(1, editedRegistration.getLastUpdatedBy());
            pre.setString(2, editedRegistration.getNote());
            pre.setBoolean(3, editedRegistration.isStatus());
            pre.setInt(4, registrationId);
            i = pre.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        } finally {
            closePreparedStatement(pre);
            closeConnection(conn);
        }
        return i;
    }

    /**
     *  get all subject registed
     * @param userId
     * @return @throws Exception get registed subject by user's Id
     */
    @Override
    public ArrayList<Subject> getRegistedSubject(int userId) throws Exception {
        Connection conn = null;
        ResultSet rs = null;
        /* Result set returned by the sqlserver */
        PreparedStatement pre = null;
        /* Prepared statement for executing sql queries */
        SubjectDAO subjectDAO = new SubjectDAOImpl();
        ArrayList<Subject> registedSubject = new ArrayList<>();
        String sql = "SELECT b.subjectId\n"
                + "  FROM [QuizSystem].[dbo].[Registration] as a "
                + "inner join [QuizSystem].[dbo].[PricePackage] as b "
                + "on a.packId = b.packId where a.userId = ?";
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            pre.setInt(1, userId);
            rs = pre.executeQuery();
            while (rs.next()) {
                registedSubject.add(subjectDAO.getSubjectbyId(rs.getInt("subjectId")));
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
        return registedSubject;
    }
   

    @Override
    public ArrayList<RegistrationManage> getFilterRegistration(int subjectId, int userId) throws Exception {
        Connection conn = null;
        ResultSet rs = null;/* Result set returned by the sqlserver */
        PreparedStatement pre = null;/* Prepared statement for executing sql queries */
        ArrayList<RegistrationManage> registrationList = new ArrayList<>();
        String sql = "SELECT [regId]\n"
                + "      ,R.[userId]\n"
                + "      ,[regTime]\n"
                + "	  ,S.[subjectId]\n"
                + "      ,R.[packId]\n"
                + "      ,[cost]\n"
                + "      ,[validFrom]\n"
                + "      ,[validTo]\n"
                + "      ,[lastUpdatedBy]\n"
                + "      ,[note]\n"
                + "      ,R.[status]\n"
                + "  FROM [QuizSystem].[dbo].[Registration] R INNER JOIN [QuizSystem].[dbo].[User] U ON R.userId=U.userId\n"
                + "  INNER JOIN [QuizSystem].[dbo].PricePackage PP ON R.packId = PP.packId\n"
                + "  INNER JOIN [QuizSystem].[dbo].[Subject] S ON S.subjectId=PP.subjectId\n"
                + "  WHERE 1=1";
        if (subjectId > 0) {
            sql = sql.concat(" and S.subjectId = " + subjectId);
        }
        if (userId > 0) {
            sql = sql.concat(" and U.userId = " + userId);
        }
        RegistrationManage registrationManage = null;
        SubjectDAO subjectDAO = new SubjectDAOImpl();
        PricePackageDAO pricePackageDAO = new PricePackageDAOImpl();
        UserDAO userDAO = new UserDAOImpl();
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                boolean status = rs.getBoolean("status");
                if (rs.wasNull()) {
                    registrationManage = new RegistrationManage(rs.getInt("regId"),
                            userDAO.getUserById(rs.getInt("userId")).getUserMail(),
                            rs.getDate("regTime"), subjectDAO.getSubjectbyId(rs.getInt("subjectId")).getSubjectName(),
                            pricePackageDAO.getPricePackageById(rs.getInt("packId")).getPackName(),
                            rs.getDouble("cost"), rs.getDate("validFrom"), rs.getDate("validTo"),
                            userDAO.getUserById(rs.getInt("userId")).getUserName(),
                            rs.getString("note"), "Submitted");
                } else if (status) {
                    registrationManage = new RegistrationManage(rs.getInt("regId"),
                            userDAO.getUserById(rs.getInt("userId")).getUserMail(),
                            rs.getDate("regTime"), subjectDAO.getSubjectbyId(rs.getInt("subjectId")).getSubjectName(),
                            pricePackageDAO.getPricePackageById(rs.getInt("packId")).getPackName(),
                            rs.getDouble("cost"), rs.getDate("validFrom"), rs.getDate("validTo"),
                            userDAO.getUserById(rs.getInt("userId")).getUserName(),
                            rs.getString("note"), "Paid");
                } else if (!status) {
                    registrationManage = new RegistrationManage(rs.getInt("regId"),
                            userDAO.getUserById(rs.getInt("userId")).getUserMail(),
                            rs.getDate("regTime"), subjectDAO.getSubjectbyId(rs.getInt("subjectId")).getSubjectName(),
                            pricePackageDAO.getPricePackageById(rs.getInt("packId")).getPackName(),
                            rs.getDouble("cost"), rs.getDate("validFrom"), rs.getDate("validTo"),
                            userDAO.getUserById(rs.getInt("userId")).getUserName(),
                            rs.getString("note"), "Unpaid");
                }
                registrationList.add(registrationManage);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
        return registrationList;
    }

}