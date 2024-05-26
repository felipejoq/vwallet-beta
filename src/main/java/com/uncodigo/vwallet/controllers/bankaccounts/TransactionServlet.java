package com.uncodigo.vwallet.controllers.bankaccounts;

import com.uncodigo.vwallet.dao.IBankAccountDao;
import com.uncodigo.vwallet.dao.ITransactionDao;
import com.uncodigo.vwallet.dao.IUserDao;
import com.uncodigo.vwallet.dao.impl.BankAccountDaoImpl;
import com.uncodigo.vwallet.dao.impl.TransactionDaoImpl;
import com.uncodigo.vwallet.dao.impl.UserDaoImpl;
import com.uncodigo.vwallet.models.bankaccounts.BankAccount;
import com.uncodigo.vwallet.models.bankaccounts.Transaction;
import com.uncodigo.vwallet.models.bankaccounts.TransactionType;
import com.uncodigo.vwallet.services.IBankAccountService;
import com.uncodigo.vwallet.services.ITransactionService;
import com.uncodigo.vwallet.services.impl.BankAccountServiceImpl;
import com.uncodigo.vwallet.services.impl.TransactionServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;

@WebServlet(name = "TransactionServlet", value = "/transaction")
public class TransactionServlet extends HttpServlet {

    ITransactionService transactionService;
    IBankAccountService bankAccountService;

    @Override
    public void init() throws ServletException {
        ITransactionDao transactionDao = new TransactionDaoImpl();
        IUserDao userDao = new UserDaoImpl();
        IBankAccountDao bankAccountDao = new BankAccountDaoImpl(userDao);
        transactionService = new TransactionServiceImpl(transactionDao);
        bankAccountService = new BankAccountServiceImpl(bankAccountDao);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("TransactionServlet doPost");

        String type = req.getParameter("type");
        String amount = req.getParameter("amount");
        BankAccount bankAccount = (BankAccount) req.getSession().getAttribute("bankAccount");

        if (type.equals("deposit")) {
            // Deposit
            Transaction depositTransaction = new Transaction();
            depositTransaction.setTotal(Double.parseDouble(amount));
            depositTransaction.setSenderBankAccount(bankAccount);
            depositTransaction.setReceiverBankAccount(null);
            depositTransaction.setTransactionType(new TransactionType(2));
            depositTransaction.setDate(new Date());
            this.transactionService.deposit(depositTransaction);
            this.bankAccountService.addBalance(bankAccount.getId(), Double.parseDouble(amount));
        } else if (type.equals("withdraw")) {
            // Withdraw
            Transaction withdrawTransaction = new Transaction();
            withdrawTransaction.setTotal(Double.parseDouble(amount));
            withdrawTransaction.setSenderBankAccount(bankAccount);
            withdrawTransaction.setReceiverBankAccount(null);
            withdrawTransaction.setTransactionType(new TransactionType(1));
            withdrawTransaction.setDate(new Date());
            Transaction transaction = this.transactionService.withdraw(withdrawTransaction);
            if (transaction == null) {
                req.getSession().setAttribute("error", "Fondos insuficientes");
                resp.sendRedirect(req.getContextPath()+"/dashboard");
                return;
            }
            this.bankAccountService.deductBalance(bankAccount.getId(), Double.parseDouble(amount));
        } else {
            // Transfer
            throw new UnsupportedOperationException("Transfer not implemented");
        }
        req.getSession().setAttribute("success", "Transacción realizada con éxito");
        resp.sendRedirect(req.getContextPath()+"/dashboard");
    }
}
