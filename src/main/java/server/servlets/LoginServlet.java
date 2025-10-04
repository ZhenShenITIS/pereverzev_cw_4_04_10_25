package server.servlets;

import server.dto.UserDto;
import server.dto.UserLoginDto;
import server.services.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Login", urlPatterns = "/login")
public class LoginServlet  extends HttpServlet {
    LoginService loginService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("login.ftl");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        UserDto user = loginService.login(new UserLoginDto(login, password));
        if (user != null) {

//            // session
//            HttpSession httpSession = req.getSession();
//            httpSession.setAttribute("user", login);
//            httpSession.setMaxInactiveInterval(60*60);
//
//            // cookie
//            Cookie cookie = new Cookie("user", login);
//            cookie.setMaxAge(24 * 60 * 60);
//            resp.addCookie(cookie);
//            resp.sendRedirect("main");
            req.setAttribute("user", user);
            req.getRequestDispatcher("main").forward(req, resp);
        } else {
            resp.sendRedirect("login.ftl");
        }
    }


    @Override
    public void init() throws ServletException {
        loginService = (LoginService) getServletContext().getAttribute("loginService");
    }
}
