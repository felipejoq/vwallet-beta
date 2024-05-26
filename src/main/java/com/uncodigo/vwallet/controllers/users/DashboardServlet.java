package com.uncodigo.vwallet.controllers.users;

import com.uncodigo.vwallet.dao.IBankAccountDao;
import com.uncodigo.vwallet.dao.IUserDao;
import com.uncodigo.vwallet.dao.impl.BankAccountDaoImpl;
import com.uncodigo.vwallet.dao.impl.UserDaoImpl;
import com.uncodigo.vwallet.models.bankaccounts.BankAccount;
import com.uncodigo.vwallet.models.users.User;
import com.uncodigo.vwallet.services.impl.BankAccountServiceImpl;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "DashboardServlet", value = "/dashboard")
public class DashboardServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private BankAccountServiceImpl bankAccountService;

    @Override
    public void init() throws ServletException {
        IUserDao userDao = new UserDaoImpl();
        IBankAccountDao bankAccountDao = new BankAccountDaoImpl(userDao);
        this.bankAccountService = new BankAccountServiceImpl(bankAccountDao);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("DashboardServlet doGet");

        User user = (User) req.getSession().getAttribute("user");

        BankAccount bankAccount = bankAccountService.getBankAccountByUserId(user.getId());

        if (bankAccount == null) {
            req.getSession().setAttribute("error", "Bank account not found");
            resp.sendRedirect(req.getContextPath()+"/login");
            req.getSession().removeAttribute("user");
            return;
        }

        req.getSession().setAttribute("bankAccount", bankAccount);

        req.getRequestDispatcher("/views/privates/dashboard.jsp").forward(req, resp);
    }
}
