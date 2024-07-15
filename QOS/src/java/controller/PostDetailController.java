
package controller;

import bean.Blog;
import bean.PostCate;
import bean.User;
import dao.BlogDAO;
import dao.PostCateDAO;
import dao.impl.BlogDAOImpl;
import dao.impl.PostCateDAOImpl;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * This class has the process request to edit blog detail
 *
 * @author QuyLM
 */
public class PostDetailController extends HttpServlet {

    /**
     * Function Post Detail: allow the marketing member to update blog
     * information
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            BlogDAO blogDAO = new BlogDAOImpl();
            PostCateDAO postCateDAO = new PostCateDAOImpl();

            /**
             * get all post categories in the database
             */
            if (service.equalsIgnoreCase("getPostCategory")) {
                //get all post categories in the database
                ArrayList<PostCate> categoryList = postCateDAO.getAllPostCates();
                request.setAttribute("categoryList", categoryList);
                String message = request.getParameter("message");
                if (message != null) {
                    request.setAttribute("message", message);
                }
                request.getRequestDispatcher("jsp/postDetail.jsp").forward(request, response);
            }

            /**
             * get all information to display in the postDetail to edit post
             */
            if (service.equalsIgnoreCase("editPost")) {
                int blogId = Integer.parseInt(request.getParameter("blogId"));
                String message = request.getParameter("message");
                Blog editBlog = blogDAO.getBlogById(blogId);
                //get informations from database
                ArrayList<PostCate> categoryList = postCateDAO.getAllPostCates();
                ArrayList<PostCate> blogList = blogDAO.getBlogCategory(blogId);
                ArrayList<Integer> blogCategoryId = new ArrayList<>();
                for (PostCate blog : blogList) {
                    blogCategoryId.add(blog.getPostCateId());
                }
                request.setAttribute("editBlog", editBlog);
                request.setAttribute("categoryList", categoryList);
                request.setAttribute("blogCategoryId", blogCategoryId);
                if (message != null) {
                    request.setAttribute("message", message);
                }
                request.getRequestDispatcher("jsp/postDetail.jsp").forward(request, response);
            }

            /**
             * get input information in the postDetail to create new post
             */
            if (service.equalsIgnoreCase("addBlog")) {
                User currUser = (User) request.getSession().getAttribute("currUser");
                Blog newBlog = new Blog();
                ArrayList<PostCate> categoryList = postCateDAO.getAllPostCates();
                ArrayList<Integer> blogCategoryId = new ArrayList<>();
                String filename = null;
                try {
                    // Create a factory for disk-based file items
                    DiskFileItemFactory factory = new DiskFileItemFactory();
                    // Configure a repository (to ensure a secure temp location is used)
                    ServletContext servletContext = this.getServletConfig().getServletContext();
                    File repository = (File) servletContext.getAttribute("jakarta.servlet.context.tempdir");
                    factory.setRepository(repository);
                    // Create a new file upload handler
                    ServletFileUpload upload = new ServletFileUpload(factory);
                    // Parse the request
                    List<FileItem> items = null;
                    Iterator<FileItem> iter = items.iterator();
                    HashMap<String, String> fields = new HashMap<>();
                    while (iter.hasNext()) {
                        FileItem item = iter.next();
                        //if item is a regular form field then add to the HashMap
                        if (item.isFormField()) {
                            fields.put(item.getFieldName(), item.getString().trim());
                        } else { // 
                            filename = item.getName();
                            if (filename == null || filename.equals("")) {
                                filename = "images\\blog\\default.png";
                                break;
                            } else { //if item is a uploaded file then write into system
                                Path path = Paths.get(filename);
                                String storePath = servletContext.getRealPath("/images");
                                File uploadFile = new File(storePath + "/blog/" + path.getFileName());
                                //if file name already existed then delete old file
                                if (uploadFile.canRead()) {
                                    uploadFile.delete();
                                }
                                item.write(uploadFile);
                                filename = "images\\blog\\" + filename;
                            }
                        }
                    }
                    String postTitle = fields.get("postTitle");
                    String detail = fields.get("detail");

                    //get all cotegory id that user have input
                    for (PostCate postCate : categoryList) {
                        String cateId = fields.get("categories_" + postCate.getPostCateId());
                        if (cateId != null) {
                            blogCategoryId.add(Integer.parseInt(cateId));
                        }
                    }

                    //if the postTitle or detail have yet entered then return message
                    if (postTitle.length() == 0 || detail.length() == 0) {
                        request.setAttribute("message", "You have to enter all required form");
                        request.getRequestDispatcher("PostDetailController?service=getPostCategory").forward(request, response);
                        return;
                    }
                    //if  the postTitle or detail is too long then return message
                    if (postTitle.length() >= 100 || detail.length() >= 1000) {
                        request.setAttribute("message", "Your input is to long!!!");
                        request.getRequestDispatcher("PostDetailController?service=getPostCategory").forward(request, response);
                        return;
                    }
                    //if user not check any category return message
                    if (blogCategoryId.size() == 0) {
                        request.setAttribute("message", "Your post must have at least 1 category !!!");
                        request.getRequestDispatcher("PostDetailController?service=getPostCategory").forward(request, response);
                        return;
                    }

                    newBlog.setBlogTitle(postTitle);
                    newBlog.setDetail(detail);
                    newBlog.setAuthor(currUser);
                    newBlog.setThumbnail(filename);
                    //add new blog
                    blogDAO.addBlog(newBlog);
                    // get blogId that have just been added
                    int createdBlogId = blogDAO.getCreatedBlogID(newBlog);
                    //input blog's categories
                    for (Integer integer : blogCategoryId) {
                        blogDAO.addBlogCategory(createdBlogId, integer);
                    }
                    request.setAttribute("message", "Your blog have successfull posted !!!");
                    request.getRequestDispatcher("PostDetailController?service=getPostCategory").forward(request, response);
                } catch (FileUploadException ex) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            /**
             * get information from postDetail to edit existed blog in the
             * database
             */
            if (service.equalsIgnoreCase("editBlog")) {
                User currUser = (User) request.getSession().getAttribute("currUser");
                ArrayList<PostCate> categoryList = postCateDAO.getAllPostCates();
                ArrayList<Integer> blogCategoryId = new ArrayList<>();
                String filename = null;
                try {
                    // Create a factory for disk-based file items
                    DiskFileItemFactory factory = new DiskFileItemFactory();
                    // Configure a repository (to ensure a secure temp location is used)
                    ServletContext servletContext = this.getServletConfig().getServletContext();
                    File repository = (File) servletContext.getAttribute("jakarta.servlet.context.tempdir");
                    factory.setRepository(repository);
                    // Create a new file upload handler
                    ServletFileUpload upload = new ServletFileUpload(factory);
                    // Parse the request
                    List<FileItem> items = null;
                    Iterator<FileItem> iter = items.iterator();
                    HashMap<String, String> fields = new HashMap<>();
                    while (iter.hasNext()) {
                        FileItem item = iter.next();
                        //if item is a regular form field then add to the HashMap
                        if (item.isFormField()) {
                            fields.put(item.getFieldName(), item.getString().trim());
                        } else { // 
                            filename = item.getName();
                            if (filename == null || filename.equals("")) {
                                break;
                            } else { //if item is a uploaded file then write into system
                                Path path = Paths.get(filename);
                                String storePath = servletContext.getRealPath("/images");
                                File uploadFile = new File(storePath + "/blog/" + path.getFileName());
                                //if file name already existed then delete old file
                                if (uploadFile.canRead()) {
                                    uploadFile.delete();
                                }
                                item.write(uploadFile);
                                filename = "images\\blog\\" + filename;
                            }
                        }
                    }
                    String postTitle = fields.get("postTitle");
                    String detail = fields.get("detail");
                    int editBlogId = Integer.parseInt(fields.get("editBlogId"));
                    Blog editBlog = blogDAO.getBlogById(editBlogId);
                    //get all cotegory id that user have input
                    for (PostCate postCate : categoryList) {
                        String cateId = fields.get("categories_" + postCate.getPostCateId());
                        if (cateId != null) {
                            blogCategoryId.add(Integer.parseInt(cateId));
                        }
                    }

                    //if the postTitle or detail have yet entered then return message
                    if (postTitle.length() == 0 || detail.length() == 0) {
                        request.setAttribute("message", "You have to enter all required form");
                        request.getRequestDispatcher("PostDetailController?service=editPost&blogId=" + editBlogId).forward(request, response);
                    }
                    //if  the postTitle or detail is too long then return message
                    if (postTitle.length() >= 100 || detail.length() >= 1000) {
                        request.setAttribute("message", "Your input is to long!!!");
                        request.getRequestDispatcher("PostDetailController?service=editPost&blogId=" + editBlogId).forward(request, response);
                    }
                    //if user not check any category return message
                    if (blogCategoryId.size() == 0) {
                        request.setAttribute("message", "Your post must have at least 1 category !!!");
                        request.getRequestDispatcher("PostDetailController?service=editPost&blogId=" + editBlogId).forward(request, response);
                        return;
                    }
                    //set new attribute
                    editBlog.setBlogTitle(postTitle);
                    editBlog.setDetail(detail);
                    editBlog.setAuthor(currUser);
                    //if there is a new thumnail then set thumnail attribute
                    if (filename != null && !filename.equals("")) {
                        editBlog.setThumbnail(filename);
                    }
                    //add new blog
                    blogDAO.editBlog(editBlog.getBlogId(), editBlog);
                    //remove all old categories
                    blogDAO.removeAllBlogCategory(editBlogId);
                    //input new blog's categories
                    for (Integer integer : blogCategoryId) {
                        blogDAO.addBlogCategory(editBlogId, integer);
                    }
                    request.setAttribute("message", "Your blog have successfull edited !!!");
                    request.getRequestDispatcher("PostDetailController?service=editPost&blogId=" + editBlogId).forward(request, response);
                } catch (FileUploadException ex) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(PostDetailController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
