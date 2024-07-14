package dao;

import bean.PostCate;
import java.util.ArrayList;

/**
 * Lớp này chứa các interface của PostCateDAOImpl
 *
 */
public interface PostCateDAO {
    ArrayList<PostCate> getAllStatusPostCates() throws Exception;

    PostCate getPostCateById(int postCateId) throws Exception;

    int addPostCate(PostCate newPostCate) throws Exception;

    int updatePostCate(PostCate updatedPostCate) throws Exception;
}
