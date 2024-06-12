
package dao;

import bean.CustomerQuiz;
import java.util.ArrayList;

/**
 * Lớp này chứa các interface của CustomerQuizDAOImpl
 *
 */
public interface CustomerQuizDAO {
    public ArrayList<CustomerQuiz> getAllCustomerQuiz() throws Exception;

    public ArrayList<CustomerQuiz> getQuizByUser(int userId) throws Exception;
}
