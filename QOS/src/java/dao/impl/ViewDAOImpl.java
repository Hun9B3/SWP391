package dao.impl;

import bean.ItemDashboard;
import com.google.gson.Gson;
import dao.DBConnection;
import dao.ViewDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Count view, update view in database
 *
 */
public class ViewDAOImpl extends DBConnection implements ViewDAO {

    /**
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

}
