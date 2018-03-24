package controller.backend;

import com.mysql.fabric.xmlrpc.base.Array;
import dao.CourseDao;
import dao.RelationshipDao;
import dao.StudentDao;
import model.CourseModel;
import model.StudentCourseModel;
import model.StudentModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet({"/admin/relationship/addSCRelation","/admin/relationship/displayEnrolled","/admin/relationship/delete"})
public class RelationshipController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getRequestURI();
        String cp = request.getContextPath();

        if (url.equals(cp+"/admin/relationship/addSCRelation")){
            int sId = Integer.parseInt(request.getParameter("sId"));
            int cId = Integer.parseInt(request.getParameter("cId"));

            StudentCourseModel studentCourseModel = new StudentCourseModel(sId,cId);

            if(RelationshipDao.insert(studentCourseModel) == true){
                response.sendRedirect(cp+"/admin/relationship/addSCRelation");
            }
            else{
                processRequest(request,response,"Insertion process failed !!!");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getRequestURI();
        String cp = request.getContextPath();

        if (url.equals(cp+"/admin/relationship/addSCRelation")){
            ArrayList<StudentModel> arrayListStudent = StudentDao.display();
            ArrayList<CourseModel> arrayListCourse = CourseDao.selectAll();

            request.setAttribute("StudentDetails",arrayListStudent);
            request.setAttribute("CourseDetails",arrayListCourse);

            request.getRequestDispatcher("/studentCourse.jsp").forward(request,response);
        }
        else if (url.equals(cp+"/admin/relationship/displayEnrolled")){
            ArrayList<StudentCourseModel> arrayList = RelationshipDao.display();
            request.setAttribute("SCDetails",arrayList);

            request.getRequestDispatcher("/enrolledStudent.jsp").forward(request,response);
        }
        else if (url.equals(cp+"/admin/relationship/delete")){
            int tableId = Integer.parseInt(request.getParameter("id"));

            if (RelationshipDao.delete(tableId) == true){
                System.out.println("One row deleted !!!");
                response.sendRedirect(cp+"/admin/relationship/displayEnrolled");
            }
            else{
                processRequest(request,response,"Deletion process failed !!!");
            }
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        try(PrintWriter out = response.getWriter()){
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>IMS</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Status report at "+request.getContextPath() + "</h1>");
            out.println("<h1>"+message+"</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
