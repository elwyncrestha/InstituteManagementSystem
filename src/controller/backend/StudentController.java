package controller.backend;

import dao.StudentDao;
import model.StudentModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet({"/admin/student/add","/admin/student/display","/admin/student/edit","/admin/student/delete"})
public class StudentController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getRequestURI().equals(request.getContextPath()+"/admin/student/add")){
            String name = request.getParameter("sName");
            String country = request.getParameter("sCountry");
            String gender = request.getParameter("sGender");
            String[] hobbies = request.getParameterValues("sHobby");

            StudentModel studentModel = new StudentModel(name,country,gender,hobbies);

            if (StudentDao.insert(studentModel) == true){
                response.sendRedirect(request.getContextPath()+"/admin/student/add");
            }
            else{
                processRequest(request,response,"Insert process failed !!!");
            }
        }
        else if (request.getRequestURI().equals(request.getContextPath()+"/admin/student/edit")){
            int id = Integer.parseInt(request.getParameter("sId"));
            String name = request.getParameter("sName");
            String country = request.getParameter("sCountry");
            String gender = request.getParameter("sGender");
            String[] hobbies = request.getParameterValues("sHobby");

            StudentModel studentModel = new StudentModel(id,name,country,gender,hobbies);

            if (StudentDao.update(studentModel) == true){
                response.sendRedirect(request.getContextPath()+"/admin/student/display");
            }
            else{
                processRequest(request,response,"Update process failed !!!");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getRequestURI().equals(request.getContextPath()+"/admin/student/add")){
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/addStudent.jsp");
            requestDispatcher.forward(request,response);
        }
        else if (request.getRequestURI().equals(request.getContextPath()+"/admin/student/display")){
            ArrayList<StudentModel> arrayList = StudentDao.display();
            request.setAttribute("StudentDetails",arrayList);
            request.getRequestDispatcher("/displayStudent.jsp").forward(request,response);
        }
        else if (request.getRequestURI().equals(request.getContextPath()+"/admin/student/edit")){
            int id = Integer.parseInt(request.getParameter("id"));

            ArrayList<StudentModel> arrayList = StudentDao.getById(id);
            request.setAttribute("EditStudentDetails",arrayList);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/editStudent.jsp");
            requestDispatcher.forward(request,response);
        }
        else if(request.getRequestURI().equals(request.getContextPath()+"/admin/student/delete")){
            int id = Integer.parseInt(request.getParameter("id"));

            if (StudentDao.delete(id) == true){
                response.sendRedirect(request.getContextPath()+"/admin/student/display");
            }
            else{
                processRequest(request,response,"Delete process failed !!!");
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
