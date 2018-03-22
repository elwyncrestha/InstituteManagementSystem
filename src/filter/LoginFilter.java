package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/admin/*")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        HttpServletResponse httpServletResponse = (HttpServletResponse) resp;

        HttpSession httpSession = httpServletRequest.getSession(false); //false takes the pre-created session

        if ((httpSession != null) && (httpSession.getAttribute("user") != null)){
            chain.doFilter(req, resp);
        }
        else{
            httpServletRequest.getRequestDispatcher("/login.jsp").forward(req,resp);
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
