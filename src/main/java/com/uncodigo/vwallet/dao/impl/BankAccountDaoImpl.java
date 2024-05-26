package com.uncodigo.vwallet.dao.impl;

import com.uncodigo.vwallet.dao.IBankAccountDao;
import com.uncodigo.vwallet.dao.IUserDao;
import com.uncodigo.vwallet.models.bankaccounts.BankAccount;
import com.uncodigo.vwallet.models.bankaccounts.Currency;
import com.uncodigo.vwallet.models.bankaccounts.Transaction;
import com.uncodigo.vwallet.models.bankaccounts.TransactionType;
import com.uncodigo.vwallet.models.database.ConnectionDb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BankAccountDaoImpl implements IBankAccountDao {

    private final ConnectionDb conn;
    private final IUserDao userDao;
    // Initialize the UserDaoImpl class
    public BankAccountDaoImpl(IUserDao userDao){
        this.userDao = userDao;
        this.conn = ConnectionDb.getInstance();
    }

    @Override
    public BankAccount getBankAccountByUserId(int userId) {
        try {
            String query = "SELECT \n" +
                    "    u.id AS user_id,\n" +
                    "    u.name AS user_name,\n" +
                    "    ba.account_number AS account_number,\n" +
                    "    ba.balance AS account_balance,\n" +
                    "    c.name AS currency_name,\n" +
                    "    c.symbol AS currency_symbol,\n" +
                    "    t.id AS transaction_id,\n" +
                    "    t.total AS transaction_total,\n" +
                    "    t.transaction_date AS transaction_date,\n" +
                    "    ttt.name AS transaction_type,\n" +
                    "    sender_u.name AS sender_name,\n" +
                    "    sender_ba.account_number AS sender_account_number,\n" +
                    "    receiver_u.name AS receiver_name,\n" +
                    "    receiver_ba.account_number AS receiver_account_number,\n" +
                    "    CASE\n" +
                    "        WHEN t.sender_bk_account_id = ba.id THEN 'Sent'\n" +
                    "        WHEN t.receiver_bk_account_id = ba.id THEN 'Received'\n" +
                    "    END AS transaction_direction\n" +
                    "FROM \n" +
                    "    users u\n" +
                    "JOIN \n" +
                    "    bank_accounts ba ON u.id = ba.user_id\n" +
                    "JOIN \n" +
                    "    currencies c ON ba.currency_id = c.id\n" +
                    "LEFT JOIN \n" +
                    "    transactions t ON ba.id = t.sender_bk_account_id OR ba.id = t.receiver_bk_account_id\n" +
                    "LEFT JOIN \n" +
                    "    transaction_types ttt ON t.transaction_type_id = ttt.id\n" +
                    "LEFT JOIN \n" +
                    "    bank_accounts sender_ba ON t.sender_bk_account_id = sender_ba.id\n" +
                    "LEFT JOIN \n" +
                    "    users sender_u ON sender_ba.user_id = sender_u.id\n" +
                    "LEFT JOIN \n" +
                    "    bank_accounts receiver_ba ON t.receiver_bk_account_id = receiver_ba.id\n" +
                    "LEFT JOIN \n" +
                    "    users receiver_u ON receiver_ba.user_id = receiver_u.id\n" +
                    "WHERE \n" +
                    "    u.id = ?";

            Connection connection = conn.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);

            return getBankAccount(preparedStatement);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    private BankAccount getBankAccount(PreparedStatement preparedStatement) {
        BankAccount bankAccount = null;
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                bankAccount = new BankAccount();
                bankAccount.setId(resultSet.getLong("user_id"));
                bankAccount.setAccountNumber(resultSet.getString("account_number"));
                bankAccount.setBalance(resultSet.getFloat("account_balance"));
                bankAccount.setUser(userDao.getUserById(resultSet.getInt("user_id")));
                bankAccount.setCurrency(new Currency(resultSet.getString("currency_name"), resultSet.getString("currency_symbol")));
            }

            if (bankAccount != null) {
                List<Transaction> transactions = new ArrayList<>();
                do {
                    Transaction transaction = new Transaction();
                    transaction.setId(resultSet.getLong("transaction_id"));
                    transaction.setTotal(resultSet.getDouble("transaction_total"));
                    transaction.setDate(resultSet.getDate("transaction_date"));
                    transaction.setTransactionType(new TransactionType(resultSet.getString("transaction_type")));
                    transaction.setSenderBankAccount(new BankAccount(resultSet.getString("sender_account_number"), null));
                    transaction.setReceiverBankAccount(new BankAccount(resultSet.getString("receiver_account_number"), null));
                    transactions.add(transaction);
                } while (resultSet.next());
                bankAccount.setTransactions(transactions);
                System.out.println("Bank Account: " + bankAccount.getAccountNumber());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return bankAccount;
    }
}
