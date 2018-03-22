package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet({"/404","/login","/logout","/logoutRoot","/index"})
public class IndexController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getRequestURI();
        String cp = request.getContextPath();

        if (url.equals(cp+"/login")){
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            if (username.equals("admin") && password.equals("admin")){
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
}
