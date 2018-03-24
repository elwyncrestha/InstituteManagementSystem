package controller.backend;

import dao.CourseDao;
import dao.ExamDao;
import model.CourseModel;
import model.ExamModel;
import model.SubjectModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet({"/admin/exam/addQuestion","/admin/exam/choose","/admin/exam/display","/admin/exam/answer","/admin/exam/addSubject","/admin/exam/displaySubject","/admin/exam/deleteSubject"})
//reminder: display result after complete design of Take Exam page
public class ExamController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getRequestURI().equals(request.getContextPath()+"/admin/exam/addQuestion")){
            String subject = request.getParameter("qSubject");
            String qName = request.getParameter("qName");
            String ans1 = request.getParameter("qAnswer1");
            String ans2 = request.getParameter("qAnswer2");
            String ans3 = request.getParameter("qAnswer3");
            String ans4 = request.getParameter("qAnswer4");
            String correctAns = request.getParameter("qCorrectAnswer");

            //send to model
            ExamModel examModel = new ExamModel(subject,qName,ans1,ans2,ans3,ans4,correctAns);

            //save through dao
            if (ExamDao.insert(examModel) == true){
                response.sendRedirect(request.getContextPath()+"/admin/exam/addQuestion");  //redirecting to the same page
            }
            else{
                processRequest(request,response,"Insertion process failed !!!");
            }
        }
        else if (request.getRequestURI().equals(request.getContextPath()+"/admin/exam/display")){
            String subjectCode = request.getParameter("qSubject");
            ArrayList<ExamModel> arrayList = ExamDao.displayQuestion(subjectCode);
            request.setAttribute("ExamQuestion",arrayList);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/displayQuestion.jsp");
            requestDispatcher.forward(request,response);
        }
        else if (request.getRequestURI().equals(request.getContextPath()+"/admin/exam/addSubject")){
            String subjectCode = request.getParameter("sCode");
            String subjectName = request.getParameter("sName");

            if (ExamDao.insertSubject(subjectCode,subjectName) == true){
                response.sendRedirect("/addSubject.jsp");
            }
            else{
                processRequest(request,response,"Insertion process failed !!!");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getRequestURI().equals(request.getContextPath()+"/admin/exam/addQuestion")){
            //first, get all subject information
            ArrayList<SubjectModel> arrayList = ExamDao.getAllSubjects();
            request.setAttribute("SubjectDetails",arrayList);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/addQuestion.jsp");
            requestDispatcher.forward(request,response);
        }
        else if (request.getRequestURI().equals(request.getContextPath()+"/admin/exam/choose")){
            ArrayList<SubjectModel> arrayList = ExamDao.getAllSubjects();
            request.setAttribute("SubjectDetails",arrayList);
            request.getRequestDispatcher("/chooseSubject.jsp").forward(request,response);
        }
        else if (request.getRequestURI().equals(request.getContextPath()+"/admin/exam/addSubject")){
            request.getRequestDispatcher("/addSubject.jsp").forward(request,response);
        }
        else if (request.getRequestURI().equals(request.getContextPath()+"/admin/exam/displaySubject")){
            ArrayList<SubjectModel> arrayList = ExamDao.getAllSubjects();
            request.setAttribute("SubjectDetails",arrayList);
            request.getRequestDispatcher("/displaySubject.jsp").forward(request,response);
        }
        else if (request.getRequestURI().equals(request.getContextPath()+"/admin/exam/deleteSubject")){
            String subjectCode = request.getParameter("code");
            if (ExamDao.deleteSubject(subjectCode) == true){
                System.out.println("One row deleted !!!");
                response.sendRedirect(request.getContextPath()+"/admin/exam/displaySubject");
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
