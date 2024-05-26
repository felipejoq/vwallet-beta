<%@ page import="com.uncodigo.vwallet.models.users.User" %>
<%@ page import="com.uncodigo.vwallet.models.bankaccounts.BankAccount" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>VWallet Beta - Dashboard</title>
    <!-- bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/index.style.css" />
</head>
<body>
<%
    User user = (User) request.getSession().getAttribute("user");
    BankAccount bankAccount = (BankAccount) request.getSession().getAttribute("bankAccount");
%>
<!-- dashboard que muestra saldo y movimientos -->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container">
        <a class="navbar-brand d-flex align-items-center align-content-center gap-3 fs-4" href="<%=request.getContextPath()%>">
            <img style="max-width: 50px" src="<%=request.getContextPath()%>/resources/img/logo.png" alt="vWallet">
            vWallet Beta
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>">Inicio</a>
                </li>
                <li class="nav-item">
                    <% if (user != null) { %>
                    <a class="nav-link" href="<%=request.getContextPath()%>/dashboard">Dashboard</a>
                    <% } else { %>
                    <a class="nav-link" href="<%=request.getContextPath()%>/login">Iniciar sesión</a>
                    <% } %>
                </li>
                <li class="nav-item">
                    <% if (user != null) { %>
                    <form action="<%=request.getContextPath()%>/logout" method="post">
                        <button type="submit" class="btn btn-link nav-link">Cerrar sesión</button>
                    </form>
                    <% } %>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-4 mb-4">
            <div class="card h-100 text-center">
                <div class="card-body d-flex flex-column align-items-center">
                    <img style="max-width: 100px" src="<%=request.getContextPath()%>/resources/img/depositar.webp" alt="Depositar fondos">
                    <h5 class="card-title">Añadir Fondos</h5>
                    <p class="card-text">Deposite dinero en su cuenta de forma rápida y sencilla.</p>
                    <a href="#" class="btn btn-custom w-100">Añadir Fondos</a>
                </div>
            </div>
        </div>
        <!-- card para datos del usuario nombre, email, saldo -->
        <div class="col-md-4">
            <div class="card mt-5">
                <div class="card-header text-center fw-bold fs-3">
                    <div class="">VWallet Beta</div>
                </div>
                <div class="card-body">
                    <div class="card-text my-4">
                        Hola <%=user.getName()%>! Te damos la bienvenida a vWallet Beta.
                    </div>
                    <div class="card-text my-3 border border-1 border-primary text-center rounded">
                        <div class="col-12">
                            <div class="row">
                                <div class="col-12 fs-3">
                                    <%=bankAccount.getBalance()%> <%=bankAccount.getCurrency().getSymbol()%>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-12 fs-4">
                                    Saldo
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- list group con los datos del user -->
                    <ul class="list-group list-group-flush mt-4">
                        <li class="list-group list-group-item">
                            <strong>Nombre:</strong> <%=user.getName()%>
                        </li>
                        <li class="list-group list-group-item">
                            <strong>Email:</strong> <%=user.getEmail()%>
                        </li>
                        <li class="list-group list-group-item">
                            <strong>Nro Cuenta:</strong> <%=bankAccount.getAccountNumber()%>
                        </li>
                        <li class="list-group list-group-item">
                            <strong>Moneda Cta.:</strong> <%=bankAccount.getCurrency().getName()%> (<%=bankAccount.getCurrency().getSymbol()%>)
                        </li>
                        <li class="list-group list-group-item">
                            <strong>Cantidad Transacciones:</strong> <%=bankAccount.getTransactions().size()%>
                        </li>
                    </ul>

                    <div class="card-text my-4">
                        <form action="<%=request.getContextPath()%>/logout" method="post">
                            <button class="btn btn-secondary w-100">Cerrar sesión</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
        integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<!-- Bootstrap 5 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>
