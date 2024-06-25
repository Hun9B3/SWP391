
package controller;

import bean.Answer;
import bean.Dimension;
import bean.Lesson;
import bean.Question;
import bean.QuestionManage;
import bean.Subject;
import bean.User;
import bean.UserRole;
import dao.AnswerDAO;
import dao.DimensionDAO;
import dao.LessonDAO;
import dao.QuestionDAO;
import dao.SubjectDAO;
import dao.impl.AnswerDAOImpl;
import dao.impl.DimensionDAOImpl;
import dao.impl.LessonDAOImpl;
import dao.impl.QuestionDAOImpl;
import dao.impl.SubjectDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * This class has the process request in Manage Question
 *
 * @author tuan
 */
public class QuestionController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
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
            QuestionDAO questionInterface = new QuestionDAOImpl();

            if (service.equalsIgnoreCase("searchQuestionByContent")) {
                /* Get user and role on session scope */
                User currUser = (User) request.getSession().getAttribute("currUser");
                UserRole currRole = (UserRole) request.getSession().getAttribute("role");
                /* If user is not logged in, or not admin/expert, redirect to index */
                if ((currUser == null) || (currRole == null)
                        || ((!currRole.getUserRoleName().equalsIgnoreCase("admin"))
                        && (!currRole.getUserRoleName().equalsIgnoreCase("expert")))) {
                    sendDispatcher(request, response, "error.jsp");
                } /* Else: get questions by content and redirect to page*/ else {
                    String content = request.getParameter("content").trim();
                    ArrayList<QuestionManage> listQuestionManage = new ArrayList<>();
                    if (content.length() == 0) {
                        listQuestionManage = questionInterface.getQuestionByContent(null);
                    } else {
                        listQuestionManage = questionInterface.getQuestionByContent(content);
                    }
                    request.setAttribute("listQuestionManage", listQuestionManage);
                    request.getRequestDispatcher("jsp/questionList.jsp").forward(request, response);
                }
            }

            /**
             * Service: filter Question by subjectId, dimensionId, lessonId
             */
            if (service.equalsIgnoreCase("filterQuestion")) {
                /* Get user and role on session scope */
                User currUser = (User) request.getSession().getAttribute("currUser");
                UserRole currRole = (UserRole) request.getSession().getAttribute("role");
                /* If user is not logged in, or not admin/expert, redirect to index */
                if ((currUser == null) || (currRole == null)
                        || ((!currRole.getUserRoleName().equalsIgnoreCase("admin"))
                        && (!currRole.getUserRoleName().equalsIgnoreCase("expert")))) {
                    sendDispatcher(request, response, "error.jsp");
                } /* Else: get questions filter by subject,lesson,dimension and redirect to page*/ else {
                    int subjectId = Integer.parseInt(request.getParameter("subjectId"));
                    int lessonId = Integer.parseInt(request.getParameter("lessonId"));
                    int dimensionId = Integer.parseInt(request.getParameter("dimensionId"));
                    ArrayList<QuestionManage> listQuestionManage = questionInterface.getQuestionManage(subjectId, dimensionId, lessonId);
                    request.setAttribute("listQuestionManage", listQuestionManage);
                    request.getRequestDispatcher("jsp/questionList.jsp").forward(request, response);
                }
            }

            /**
             * Service: get all Subject, Dimension, Lesson Information
             */
            if (service.equalsIgnoreCase("getFilterInformation")) {
                String message = (String) request.getAttribute("message");
                SubjectDAO subjectDAO = new SubjectDAOImpl();
                DimensionDAO dimensionDAO = new DimensionDAOImpl();
                LessonDAO lessonDAO = new LessonDAOImpl();
                ArrayList<Subject> listSubject = subjectDAO.getAllSubjects();
                ArrayList<Dimension> listDimension = dimensionDAO.getAllDimension();
                ArrayList<Lesson> listLesson = lessonDAO.getAllLessons();
                request.getSession().setAttribute("listFilterSubject", listSubject);
                request.getSession().setAttribute("listFilterDimension", listDimension);
                request.getSession().setAttribute("listFilterLesson", listLesson);
                int subjectId = 0;
                int lessonId = 0;
                int dimensionId = 0;
                ArrayList<QuestionManage> listQuestionManage = questionInterface.getQuestionManage(subjectId, dimensionId, lessonId);
                request.setAttribute("listQuestionManage", listQuestionManage);
                request.getRequestDispatcher("jsp/questionList.jsp").forward(request, response);
                if (message != null) {
                    request.setAttribute("message", message);
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(QuestionController.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("errorMess", ex.toString());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    /* Forward the request to the destination, catch any unexpected exceptions and log it */
    public void sendDispatcher(HttpServletRequest request, HttpServletResponse response, String path) {
        try {
            RequestDispatcher rd = request.getRequestDispatcher(path);
            rd.forward(request, response);

        } catch (ServletException | IOException ex) {
            Logger.getLogger(SubjectController.class
                    .getName()).log(Level.SEVERE, null, ex);
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
