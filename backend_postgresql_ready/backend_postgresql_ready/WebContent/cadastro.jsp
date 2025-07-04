<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Cadastro</title>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="shortcut icon" href="/projeto AimUp/front/img/Logo AimUp.png" type="image/x-icon">
    <link rel="shortcut icon" href="/img/Logo AimUp.png" type="image/x-icon">
    <style>
        ::placeholder {
            color: white !important;
            opacity: 1;
        }
        .login-box {
  background: rgba(255, 255, 255, 0.1);
  border-radius: 1rem;
  padding: 2rem;
  backdrop-filter: blur(5px);
  -webkit-backdrop-filter: blur(5px);
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
  padding-left: 5%;
  padding-right: 5%;
}
    </style>
</head>
<body class="d-flex align-items-center justify-content-center vh-100 bg-light " style="background: rgb(76, 132, 105);
background: linear-gradient(0deg, rgb(111, 187, 150) 0%, rgba(35,168,107,1) 100%);">




 
    <div class="login-box">
        <img src="/img/Logo AimUp.png" alt="Logo AimUp" style="width: 250px;">
        <h1 class="text-center" style="color: #fff;">Cadastro</h1>
        <form action="cadastro" method="post">
            <div class="mb-3" style="color: #fff;">
                <label for="nome-usuario" class="form-label" >Usuário</label>
                <input type="text" name="nome" class="form-control" id="nome-usuario" placeholder="Digite seu usuário" required style="border-radius: 20px; background: none; border-width: 3px; color: #fff;">
            </div>
            <div class="mb-3" style="color: #fff;">
                <label for="email" class="form-label" >E-mail</label>
                <input type="email" name="email" class="form-control" id="email" placeholder="Digite seu e-mail" required style="border-radius: 20px; background: none; border-width: 3px; color: #fff;">
            </div>
            <div class="mb-3" style="color: #fff;">
                <label for="password" class="form-label">Senha</label>
                <input type="password" name="senha" class="form-control" id="password" placeholder="Digite sua senha" required style="border-radius: 20px; background: none; border-width: 3px;">
            </div>
           
            <button type="submit" class="btn btn-light w-100" style="background-color: #fff; border-radius: 20px; border-color: #fff; color: rgb(111, 187, 150);">Entrar</button>
        </form>
        <div class="text-center mt-3">
            <a href="login.jsp">Já tenho uma conta</a>
        </div>
    </div>
</body>
</html>