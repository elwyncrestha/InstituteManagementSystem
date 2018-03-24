package controller.backend;

import dao.RegistrationDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet({"/404","/login","/logout","/logoutRoot","/index"})
public class IndexController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getRequestURI();
        String cp = request.getContextPath();

        if (url.equals(cp+"/login")){
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            if (username.equals("admin") && password.equals("admin")){
                processRequest(request,response,"You fool !!!");
            }
            boolean validationStatus = RegistrationDao.validateUser(username,password);

            if (validationStatus == true){
                HttpSession httpSession = request.getSession(); //blank parameter in this method indicates creation of new session
                httpSession.setAttribute("user","admin");
                System.out.println("Login successful");
                response.sendRedirect(request.getContextPath()+"/admin/dashboard");
            }
            else{
                response.sendRedirect(cp+"/admin/login");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getRequestURI();
        String cp = request.getContextPath();

        if (url.equals(cp+"/404")){
            request.getRequestDispatcher("/404.jsp").forward(request,response);
        }
        else if (url.equals(cp+"/login")){
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
        else if (url.equals(cp+"/logout")){
            HttpSession httpSession = request.getSession(false);
            httpSession.invalidate();
            System.out.println("Logout successful");
            response.sendRedirect(cp+"/login?logout");
        }
        else if (url.equals(cp+"/index")){
            request.getRequestDispatcher(cp+"/index.jsp").forward(request,response);
        }
        else if (url.equals(cp+"/logoutRoot")){
            HttpSession httpSession = request.getSession(false);
            httpSession.invalidate();
            System.out.println("Root logout successful");
            response.sendRedirect(cp+"/index");
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
            out.println("<h1>"+message+"</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
