package com.uncodigo.vwallet.controllers.users;

import com.uncodigo.vwallet.dao.IUserDao;
import com.uncodigo.vwallet.dao.impl.UserDaoImpl;
import com.uncodigo.vwallet.dto.UserLoginDto;
import com.uncodigo.vwallet.models.users.User;
import com.uncodigo.vwallet.services.ILoginService;
import com.uncodigo.vwallet.services.impl.LoginServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    private ILoginService loginService;

    @Override
    public void init() throws ServletException {
        IUserDao userDao = new UserDaoImpl();
        this.loginService = new LoginServiceImpl(userDao);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("LoginServlet doGet");
        if(req.getSession().getAttribute("user") != null) {
            resp.sendRedirect(req.getContextPath()+"/dashboard");
        } else {
            req.getRequestDispatcher("/views/public/pages/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("LoginServlet doPost");

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (email.isEmpty() || password.isEmpty()) {
            req.getSession().setAttribute("error", "Email y password son requeridos");
            resp.sendRedirect(req.getContextPath()+"/login");
        } else {
            UserLoginDto userLoginDto = new UserLoginDto(email, password);
            User user = loginService.login(userLoginDto);

            if (user != null) {
                // Login successful, redirect to dashboard
                HttpSession session = req.getSession();
                session.setAttribute("user", user);
                resp.sendRedirect(req.getContextPath()+"/dashboard");
            } else {
                // Login failed, show error message
                req.getSession().setAttribute("error", "Email o Password inválidos");
                resp.sendRedirect(req.getContextPath()+"/login");
            }
        }
    }
}
