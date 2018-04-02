package controller.backend;

import dao.RegistrationDao;
import model.RegistrationModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet({"/register","/register/display","/register/delete"})
public class RegistrationController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getRequestURI().equals(request.getContextPath()+"/register")){
            String firstName = request.getParameter("firstname");
            String lastName = request.getParameter("lastname");
            String email = request.getParameter("email");
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            RegistrationModel registrationModel = new RegistrationModel(firstName,lastName,email,username,password);

            //send to dao
            if (RegistrationDao.insert(registrationModel) == true){
                System.out.println("One row inserted");
                response.sendRedirect(request.getContextPath()+"/login");
            }
            else{
                processRequest(request,response,"Insertion process failed !!!");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getRequestURI().equals(request.getContextPath()+"/register")){
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/registerAccount.jsp");
            requestDispatcher.forward(request,response);
        }
        else if (request.getRequestURI().equals(request.getContextPath()+"/register/display")){
            ArrayList<RegistrationModel> arrayList = RegistrationDao.displayRegUser();
            request.setAttribute("userInfo",arrayList);
            request.getRequestDispatcher("/displayUser.jsp").forward(request,response);
        }
        else if (request.getRequestURI().equals(request.getContextPath()+"/register/delete")){
            String email = request.getParameter("email");
            if (RegistrationDao.deleteUser(email) == true){
                response.sendRedirect(request.getContextPath()+"/register/display");
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
