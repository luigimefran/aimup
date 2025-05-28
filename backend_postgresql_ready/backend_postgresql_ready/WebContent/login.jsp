<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Login</title>
    
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

    <!-- Ícone -->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/Logo AimUp.png" type="image/x-icon">

    <!-- Estilos -->
    <style>
        ::placeholder {
            color: white !important;
            opacity: 1;
        }
        .login-box {
            background: rgba(255, 255, 255, 0.1);
            border-radius: 1rem;
            padding: 2rem 5%;
            backdrop-filter: blur(5px);
            -webkit-backdrop-filter: blur(5px);
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
        }
    </style>
</head>
<body class="d-flex align-items-center justify-content-center vh-100 bg-light"
      style="background: linear-gradient(0deg, rgb(111, 187, 150) 0%, rgba(35,168,107,1) 100%);">

    <div class="login-box text-white">
        <div class="text-center mb-3">
            <img src="${pageContext.request.contextPath}/imagens/logo.png" alt="Logo AimUp" style="width: 250px;">
        </div>
        <h1 class="text-center">Login</h1>
        
        <form method="post" action="login">
            <div class="mb-3">
                <label for="email" class="form-label">E-mail</label>
                <input type="email" name="email" id="email" class="form-control"
                       placeholder="Digite seu e-mail" required
                       style="border-radius: 20px; background: none; border-width: 3px; color: #fff;">
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Senha</label>
                <input type="password" name="senha" id="password" class="form-control"
                       placeholder="Digite sua senha" required
                       style="border-radius: 20px; background: none; border-width: 3px; color: #fff;">
            </div>
            <button type="submit" class="btn btn-light w-100"
                    style="border-radius: 20px; color: rgb(111, 187, 150);">
                Entrar
            </button>
        </form>

        <div class="text-center mt-3">
            <a href="/esqueceuSenha/esqueceuSenha.html" class="text-white text-decoration-underline">
                Esqueceu a senha?
            </a>
        </div>
    </div>

</body>
</html>
