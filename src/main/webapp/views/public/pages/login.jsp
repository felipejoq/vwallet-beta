<%--
  Created by IntelliJ IDEA.
  User: felipe
  Date: 25-05-24
  Time: 12:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>VWallet Beta | Log in</title>
    <!-- bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<!-- card bootstrap 5 para login -->
<div class="container">
    <!-- Align vertically and horizontally -->
    <div class="row justify-content-center align-content-center vh-100">
        <div class="col-md-4">
            <div class="card mt-5">
                <div class="card-header text-center fw-bold fs-4">
                    <div class="">VWallet Beta</div>
                </div>
                <div class="card-body">
                    <form action="<%=request.getContextPath()%>/login" method="post">
                        <div class="mb-3">
                            <label for="email" class="form-label">Email</label>
                            <input value="felipe@example.com" type="email" class="form-control" id="email" name="email" required placeholder="name@example.com">
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label">Password</label>
                            <input value="123123" type="password" class="form-control" id="password" name="password" required placeholder="*******">
                        </div>
                        <div class="form-control">
                            <button type="submit" class="btn btn-primary w-100">Entrar</button>
                        </div>
                    </form>
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

