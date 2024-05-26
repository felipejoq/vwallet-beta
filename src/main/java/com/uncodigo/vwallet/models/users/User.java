package com.uncodigo.vwallet.models.users;

import com.uncodigo.vwallet.models.bankaccounts.BankAccount;

import java.util.List;

public class User {

    private int id;
    private String name;
    private String email;
    private String password;
    private Boolean active;

    // Many-to-Many relationship with Role
    private List<Role> roles;

    // One-to-One relationship with BankAccount
    private BankAccount bankAccount;

    public User() {
    }

    public User(String name, String email, String password, Boolean active) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", active=" + active +
                ", roles=" + roles +
                ", bankAccount=" + bankAccount +
                '}';
    }
}
