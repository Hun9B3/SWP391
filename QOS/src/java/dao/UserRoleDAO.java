package dao;

import bean.UserRole;
import java.util.ArrayList;

/**
 * Lớp này chứa các interface của UserRoleDAOImpl
 *
 */
public interface UserRoleDAO {
<<<<<<< HEAD
  /**
=======
    /**
>>>>>>> 5bcf8e50d19562d997abb319c60eca73d15e41c5
     * Get all user role where status = 1 
     * @return
     * @throws Exception 
     */
    public ArrayList<UserRole> getAllUserRole() throws Exception;
<<<<<<< HEAD
    
    /**
     * Get user role with a specified id
     * @param roleId
     * @return
     * @throws Exception 
     */
    public UserRole getUserRoleById(int roleId) throws Exception;
    
    ArrayList<UserRole> getAllStatusUserRole() throws Exception;

    int addRole(UserRole userRole) throws Exception;

    int editRole(UserRole userRole) throws Exception;
=======
>>>>>>> 5bcf8e50d19562d997abb319c60eca73d15e41c5
}
