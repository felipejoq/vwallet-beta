package com.uncodigo.vwallet.dao.impl;

import com.uncodigo.vwallet.dao.ITransactionDao;
import com.uncodigo.vwallet.models.bankaccounts.Transaction;
import com.uncodigo.vwallet.models.database.ConnectionDb;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TransactionDaoImpl implements ITransactionDao {

    private ConnectionDb conn;

    public TransactionDaoImpl() {
        this.conn = ConnectionDb.getInstance();
    }

    @Override
    public Transaction getTransactionByBankAccountId(int bankAccountId) {
        Connection connection = conn.getConnection();
        String sql = "SELECT * FROM transactions WHERE sender_bk_account_id = ?";

        return getTransaction(bankAccountId, connection, sql);

    }

    @Override
    public Transaction getTransactionById(int transactionId) {
        Connection connection = conn.getConnection();
        String sql = "SELECT * FROM transactions WHERE id = ?";

        return getTransaction(transactionId, connection, sql);
    }

    @Override
    public Transaction createTransaction(Transaction transaction) {
        Connection connection = conn.getConnection();
        String sql = "INSERT INTO transactions (sender_bk_account_id, receiver_bk_account_id, total, transaction_type_id, transaction_date) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, transaction.getSenderBankAccount().getId());
            preparedStatement.setInt(2, transaction.getReceiverBankAccount().getId());
            preparedStatement.setDouble(3, transaction.getTotal());
            preparedStatement.setInt(4, transaction.getTransactionType().getId());
            preparedStatement.setDate(5, new Date(transaction.getDate().getTime()));

            preparedStatement.executeUpdate();

            return transaction;

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Transaction deposit(Transaction transaction) {
        return getTransaction(transaction);
    }

    @Override
    public Transaction withdraw(Transaction transaction) {
        // Check if the sender has enough balance
        if (transaction.getSenderBankAccount().getBalance() < transaction.getTotal()) {
            System.out.println("Insufficient balance");
            return null;
        }

        return getTransaction(transaction);
    }

    @Override
    public Transaction transfer(Transaction transaction) {
        return null;
    }

    private Transaction getTransaction(int transactionId, Connection connection, String sql) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, transactionId);
            ResultSet resultSet = preparedStatement.executeQuery();
            Transaction transaction = null;

            if (resultSet.next()) {
                transaction = new Transaction();
                transaction.setId(resultSet.getInt("id"));
                transaction.setTotal(resultSet.getDouble("total"));
                transaction.setDate(resultSet.getDate("transaction_date"));
            }

            return transaction;

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    private Transaction getTransaction(Transaction transaction) {
        String sql = "INSERT INTO transactions (sender_bk_account_id, total, transaction_type_id, transaction_date) VALUES (?, ?, ?, ?)";

        Connection connection = conn.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, transaction.getSenderBankAccount().getId());
            preparedStatement.setDouble(2, transaction.getTotal());
            preparedStatement.setInt(3, transaction.getTransactionType().getId());
            preparedStatement.setDate(4, new Date(transaction.getDate().getTime()));

            preparedStatement.executeUpdate();

            return transaction;

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
}
