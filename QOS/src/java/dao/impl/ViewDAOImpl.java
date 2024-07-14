
package dao.impl;

import bean.ItemDashboard;
import com.google.gson.Gson;
import dao.DBConnection;
import dao.ViewDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
<<<<<<< HEAD
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

=======
import java.util.ArrayList;
import java.util.HashMap;
>>>>>>> 5bcf8e50d19562d997abb319c60eca73d15e41c5

public class ViewDAOImpl extends DBConnection implements ViewDAO {

    /**
<<<<<<< HEAD
     * update View when a session is created
     *
     * @return number of lines changed. It is a <code>int</code>
     * @throws java.lang.Exception
     */
    @Override
    public int updateView() throws Exception {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pre = null;

        Date currentDateRaw = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(currentDateRaw);
        String sql;
        //if current day view is not exist then create new row, else update current day view
        if (checkCurrentDateViewExist() == true) {
            sql = "UPDATE [ViewCount] SET [view] = [view] + 1 WHERE [date] = '" + currentDate + "'";
        } else {
            sql = "INSERT INTO [ViewCount] values('" + currentDate + "'," + 1 + ")";
        }
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            return pre.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
    }

    /**
     * check if current date view exist
     *
     * @return <code>boolean</code>
     * @throws java.lang.Exception
     */
    @Override
    public boolean checkCurrentDateViewExist() throws Exception {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pre = null;

        Date currentDateRaw = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(currentDateRaw);

        String sql = "SELECT * FROM [ViewCount] WHERE date = '" + currentDate + "'";
=======
     * get total view count
     *
     * @return <code>int</code>
     * @throws java.lang.Exception
     */
    @Override
    public int getTotalView() throws Exception {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pre = null;
        String sql = "select SUM([view]) AS totalView FROM [ViewCount]";
>>>>>>> 5bcf8e50d19562d997abb319c60eca73d15e41c5
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            if (rs.next()) {
<<<<<<< HEAD
                return true;
=======
                return rs.getInt("totalView");
>>>>>>> 5bcf8e50d19562d997abb319c60eca73d15e41c5
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
<<<<<<< HEAD
        return false;
=======
        return 0;
>>>>>>> 5bcf8e50d19562d997abb319c60eca73d15e41c5
    }

    /**
     * get statistic from database
     *
     * @param from Lower range limit. <code>String</code>
     * @param to Upper range limit. <code>String</code>
     * @return list of statistics data. It is a <code>java.util.ArrayList</code>
     * object.
     * @throws java.lang.Exception
     */
    @Override
    public ArrayList<ItemDashboard> getViewStatistics(String from, String to) throws Exception {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pre = null;
        ArrayList<ItemDashboard> itemList = new ArrayList();
        String sql = "SELECT * FROM [ViewCount] WHERE [date]  >= ? AND [date] <= ?";
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, from);
            pre.setString(2, to);
            rs = pre.executeQuery();
            while (rs.next()) {
                itemList.add(new ItemDashboard("view",
                        rs.getDouble("view"),
                        rs.getDate("date").getTime()));
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
        return itemList;
    }

    /**
<<<<<<< HEAD
     * get total view count
     *
     * @return <code>int</code>
     * @throws java.lang.Exception
     */
    @Override
    public int getTotalView() throws Exception {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pre = null;
        String sql = "select SUM([view]) AS totalView FROM [ViewCount]";
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            if (rs.next()) {
                return rs.getInt("totalView");
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
        return 0;
    }

    /**
=======
>>>>>>> 5bcf8e50d19562d997abb319c60eca73d15e41c5
     * Convert statistics data into JSon string
     *
     * @param viewList statistics data
     * @return JSon strings of data. It is a <code>java.util.ArrayList</code>
     * object.
     * @throws java.lang.Exception
     */
    @Override
    public ArrayList<String> convertJson(ArrayList<ItemDashboard> viewList) throws Exception {
        ArrayList<String> ret = new ArrayList();
        Gson gson = new Gson();
        HashMap<String, Integer> map = new HashMap<>();
        int j = 0;
        ArrayList<ArrayList<ItemDashboard>> list = new ArrayList();

        for (ItemDashboard item : viewList) {
            if (!map.containsKey(item.getName())) {
                map.put(item.getName(), j);
                j++;
                list.add(new ArrayList<>());
            }
            list.get(map.get(item.getName())).add(item);
        }

        // convert your list to json
        for (ArrayList<ItemDashboard> item : list) {
            ret.add(gson.toJson(item));
        }
        // print your generated json
        return ret;
    }

<<<<<<< HEAD
    /**
     * get name of each JSon string
     *
     * @param viewList statistics data
     * @return names of data. It is a <code>java.util.ArrayList</code> object
     * @throws java.lang.Exception
     */
    @Override
    public ArrayList<String> getNameList(ArrayList<ItemDashboard> viewList) throws Exception {
        ArrayList<String> nameList = new ArrayList();
        HashMap<String, Integer> map = new HashMap<>();
        int j = 0;
        for (ItemDashboard subject : viewList) {
            if (!map.containsKey(subject.getName())) {
                map.put(subject.getName(), j);
                nameList.add(subject.getName());
                j++;
            }
        }
        return nameList;
    }
=======
>>>>>>> 5bcf8e50d19562d997abb319c60eca73d15e41c5
}
