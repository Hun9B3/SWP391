package dao;

import bean.ItemDashboard;
import java.util.ArrayList;

/**
 * ViewDAO Interface
 *
 */
public interface ViewDAO {

    /**
     * get total view count
     *
     * @return <code>int</code>
     * @throws java.lang.Exception
     */
    public int getTotalView() throws Exception;

    /**
     * get statistic from database
     *
     * @param from Lower range limit. <code>String</code>
     * @param to Upper range limit. <code>String</code>
     * @return list of statistics data. It is a <code>java.util.ArrayList</code>
     * object.
     * @throws java.lang.Exception
     */
    public ArrayList<ItemDashboard> getViewStatistics(String from, String to) throws Exception;

    /**
     * Convert statistics data into JSon string
     *
     * @param views statistics data
     * @return JSon strings of data. It is a <code>java.util.ArrayList</code>
     * object.
     * @throws java.lang.Exception
     */
    public ArrayList<String> convertJson(ArrayList<ItemDashboard> views) throws Exception;
}
