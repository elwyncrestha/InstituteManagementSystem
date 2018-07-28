package controller.frontend;

import dao.CourseDao;
import dao.StudentDao;
import dao.SubscriptionDao;
//import jdk.jshell.JShell;
import mailController.MailController;
import model.CourseModel;
import model.StudentModel;
import model.SubscriptionModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet({"/frontend/homepage","/frontend/subscription/add","/frontend/subscription/display","/frontend/subscription/delete"})
public class FrontendController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        String cp = request.getContextPath();

        if (request.getRequestURI().equals(request.getContextPath()+"/frontend/subscription/add")){
            String name = request.getParameter("name");
            String email = request.getParameter("email");

            SubscriptionModel subscriptionModel = new SubscriptionModel(name,email);

            //send to dao
            if (SubscriptionDao.insert(subscriptionModel) == true){
                System.out.println("One row inserted");
                boolean mailStatus = MailController.sendEmail(email,"IMS Subscription","You've been subscribed to Institute Management System");
                if (mailStatus){
                    System.out.println("Subscription email was sent to "+email);
                }
                response.sendRedirect(cp+"/frontend/homepage");
            }
            else{
                processRequest(request,response,"Insertion process failed !!!");
            }
        }
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
        else if (uri.equals(cp+"/frontend/subscription/display")){
            ArrayList<SubscriptionModel> arrayList = SubscriptionDao.displaySubcription();
            request.setAttribute("SubscriptionDetail",arrayList);
            request.getRequestDispatcher("/displaySubscription.jsp").forward(request,response);
        }
        else if (uri.equals(cp+"/frontend/subscription/delete")){
            String email = request.getParameter("email");
            if (SubscriptionDao.delete(email) == true){
                response.sendRedirect(cp+"/frontend/subscription/display");
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
