package dao;

import bean.PostCate;
import java.util.ArrayList;

/**
 * Lớp này chứa các interface của PostCateDAOImpl
 *
 */
public interface PostCateDAO {

    /**
     * get all psot categories where status = 1
     *
     * @return
     * @throws Exception
     */
    public ArrayList<PostCate> getAllPostCates() throws Exception;

    /**
     * get blog category id by blog id
     *
     * @param blogId
     * @return
     * @throws Exception
     */
    public int getBlogCateByBlogId(int blogId) throws Exception;

    /**
     * get post categoory by id
     *
     * @param postCateId
     * @return
     * @throws Exception
     */
    public PostCate getPostCateById(int postCateId) throws Exception;
}
