package controller.frontend;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/frontend/homepage")
public class FrontendController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        String cp = request.getContextPath();

        if (uri.equals(cp+"/frontend/homepage")){
            request.getRequestDispatcher("/studentHome.jsp").forward(request,response);
        }
    }
}
