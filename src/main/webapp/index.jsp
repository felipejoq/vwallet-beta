<%@ page import="java.util.Date" %>
<%@ page import="com.uncodigo.vwallet.models.users.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>vWallet Beta - Home</title>
    <!-- bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <!-- index style css -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/index.style.css" />
    <!-- bootstrap icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

</head>
<body>
<%
    User user = (User) request.getSession().getAttribute("user");
%>
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
                    <% } %>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <div class="row">
        <div class="col-md-4 mb-4">
            <div class="card h-100 text-center">
                <div class="card-body d-flex flex-column align-items-center">
                    <img style="max-width: 100px" src="<%=request.getContextPath()%>/resources/img/depositar.png" alt="Depositar fondos">
                    <h5 class="card-title">Añadir Fondos</h5>
                    <p class="card-text">Deposite dinero en su cuenta de forma rápida y sencilla.</p>
                    <a href="#" class="btn btn-custom w-100">Añadir Fondos</a>
                </div>
            </div>
        </div>
        <div class="col-md-4 mb-4">
            <div class="card h-100 text-center">
                <div class="card-body d-flex flex-column align-items-center">
                    <img style="max-width: 100px" src="<%=request.getContextPath()%>/resources/img/retirar.png" alt="Retirar fondos">
                    <h5 class="card-title">Retirar Fondos</h5>
                    <p class="card-text">Retire dinero de su cuenta cuando lo necesite.</p>
                    <a href="#" class="btn btn-custom w-100">Retirar Fondos</a>
                </div>
            </div>
        </div>
        <div class="col-md-4 mb-4">
            <div class="card h-100 text-center">
                <div class="card-body d-flex flex-column align-items-center">
                    <img style="max-width: 100px" src="<%=request.getContextPath()%>/resources/img/transferir.png" alt="Transferir fondos">
                    <h5 class="card-title">Transferir Fondos <span class="badge badge-info">(Pronto)</span></h5>
                    <p class="card-text">Transfiera dinero a otras cuentas de manera fácil y segura.</p>
                    <a href="#" class="btn btn-custom w-100">Transferir Fondos</a>
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