<%@ page import="com.uncodigo.vwallet.models.users.User" %>
<%@ page import="com.uncodigo.vwallet.models.bankaccounts.BankAccount" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>VWallet Beta - Dashboard</title>
    <!-- bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/index.style.css"/>
    <!-- bootstrap icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>
<body>
<%
    User user = (User) request.getSession().getAttribute("user");
    BankAccount bankAccount = (BankAccount) request.getSession().getAttribute("bankAccount");
%>
<!-- dashboard que muestra saldo y movimientos -->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container">
        <a class="navbar-brand d-flex align-items-center align-content-center gap-3 fs-4"
           href="<%=request.getContextPath()%>">
            <img style="max-width: 50px" src="<%=request.getContextPath()%>/resources/img/logo.png" alt="vWallet">
            vWallet Beta
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>"><i class="bi bi-house-door-fill"></i> Inicio</a>
                </li>
                <li class="nav-item">
                    <% if (user != null) { %>
                    <a class="nav-link" href="<%=request.getContextPath()%>/dashboard"><i class="bi bi-speedometer"></i> Dashboard</a>
                    <% } else { %>
                    <a class="nav-link" href="<%=request.getContextPath()%>/login"><i class="bi bi-box-arrow-in-right"></i> Iniciar sesión</a>
                    <% } %>
                </li>
                <li class="nav-item">
                    <% if (user != null) { %>
                    <form action="<%=request.getContextPath()%>/logout" method="post">
                        <button type="submit" class="btn btn-link nav-link"><i class="bi bi-box-arrow-in-left"></i> Cerrar sesión</button>
                    </form>
                    <% }
                        assert user != null;%>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="container">
    <div class="row">
        <div class="col-sm-12 col-md-12 col-lg-4 col-xl-4 mb-4">
            <div class="card h-100">
                <div class="card-header text-center fw-bold fs-3">
                    <div class="">VWallet Beta</div>
                </div>
                <div class="card-body">
                    <div class="card-title">
                        Hola <%=user.getName()%>! Te damos la bienvenida a vWallet Beta.
                    </div>
                    <div class="card-text my-3 border border-1 border-secondary rounded">
                        <div class="col-12 text-center">
                            <div class="row">
                                <div class="col-12 fs-3">
                                    <%=String.format("%.2f", bankAccount.getBalance())%> <%=bankAccount.getCurrency().getSymbol()%>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-12 fs-4">
                                    Saldo
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- mensajes de éxito o de error dimissable -->
                    <% if (request.getSession().getAttribute("error") != null) { %>
                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                        <%=request.getSession().getAttribute("error")%>
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                    <% request.getSession().removeAttribute("error"); %>
                    <% } %>
                    <% if (request.getSession().getAttribute("success") != null) { %>
                    <div class="alert alert-success alert-dismissible fade show" role="alert">
                        <%=request.getSession().getAttribute("success")%>
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                    <% request.getSession().removeAttribute("success"); %>
                    <% } %>
                    <!-- Opciones de depósito y retiro con botones toggle para desplegar el formulario -->
                    <div class="card-text my-4">
                        <button class="btn btn-light w-100" style="text-align: left;" data-bs-toggle="collapse" data-bs-target="#depositForm"
                                aria-expanded="false" aria-controls="depositForm">
                            <i class="bi bi-plus-circle-fill"></i> Depositar
                        </button>
                        <div class="collapse mt-3" id="depositForm">
                            <form action="<%=request.getContextPath()%>/transaction" method="post">
                                <div class="input-group mb-3">
                                    <span class="input-group-text"
                                          id="basic-addon1"><%=bankAccount.getCurrency().getSymbol()%></span>
                                    <input type="number" class="form-control" name="amount"
                                           placeholder="Monto a depositar" aria-label="Monto a depositar"
                                           aria-describedby="basic-addon1">
                                </div>
                                <input type="hidden" name="type" value="deposit">
                                <button type="submit" class="btn btn-primary w-100"><i class="bi bi-check-circle-fill"></i> Enviar</button>
                            </form>
                        </div>
                        <!-- otro para el retiro -->
                        <button class="btn btn-light w-100 mt-3" style="text-align: left;" data-bs-toggle="collapse"
                                data-bs-target="#withdrawForm" aria-expanded="false" aria-controls="withdrawForm">
                            <i class="bi bi-dash-circle-fill"></i> Retirar
                        </button>
                        <div class="collapse mt-3" id="withdrawForm">
                            <form action="<%=request.getContextPath()%>/transaction" method="post">
                                <div class="input-group mb-3">
                                    <span class="input-group-text"
                                          id="basic-addon2"><%=bankAccount.getCurrency().getSymbol()%></span>
                                    <input type="number" class="form-control" name="amount"
                                           placeholder="Monto a retirar" aria-label="Monto a retirar"
                                           aria-describedby="basic-addon1">
                                </div>
                                <input type="hidden" name="type" value="withdraw">
                                <button type="submit" class="btn btn-danger w-100"><i class="bi bi-check-circle-fill"></i> Enviar</button>
                            </form>
                        </div>
                    </div>
                    <!-- list group con los datos del user -->
                    <ul class="list-group mt-4">
                        <li class="list-group-item">
                            <strong>Nombre:</strong> <%=user.getName()%>
                        </li>
                        <li class="list-group-item">
                            <strong>Email:</strong> <%=user.getEmail()%>
                        </li>
                        <li class="list-group-item">
                            <strong>Nro Cuenta:</strong> <%=bankAccount.getAccountNumber()%>
                        </li>
                        <li class="list-group-item">
                            <strong>Moneda Cta.:</strong> <%=bankAccount.getCurrency().getName()%>
                            (<%=bankAccount.getCurrency().getSymbol()%>)
                        </li>
                        <li class="list-group-item">
                            <strong>Cantidad Transacciones:</strong> <%=bankAccount.getTransactions().size()%>
                        </li>
                    </ul>

                    <div class="card-text my-4">
                        <form action="<%=request.getContextPath()%>/logout" method="post">
                            <button class="btn btn-secondary w-100"><i class="bi bi-box-arrow-in-left"></i> Cerrar sesión</button>
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
