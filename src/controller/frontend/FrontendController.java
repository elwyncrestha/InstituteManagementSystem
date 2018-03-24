package controller.frontend;

import dao.CourseDao;
import dao.StudentDao;
import model.CourseModel;
import model.StudentModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/frontend/homepage")
public class FrontendController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        String cp = request.getContextPath();

        if (uri.equals(cp+"/frontend/homepage")){
            //Course
            // fetch all course information from database
            ArrayList<CourseModel> arrayList = CourseDao.selectAll();
            request.setAttribute("CourseData",arrayList);

            //Student
            ArrayList<StudentModel> arrayList1 = StudentDao.display();
            request.setAttribute("StudentDetails",arrayList1);

            //Examination

            //forwarding to the display page
            request.getRequestDispatcher("/studentHome.jsp").forward(request,response);
        }
    }
}
