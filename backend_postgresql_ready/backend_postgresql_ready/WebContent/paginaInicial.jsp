<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>AimUp | Sua plataforma de produtividade</title>

  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" rel="stylesheet">

  <link rel="shortcut icon" href="/login/login.html" type="image/x-icon">

  <style>
    * {
      margin: 0;
      padding: 0;
      box-sizing: border-box;
    }

    html, body {
      height: 100%;
      font-family: 'Poppins', sans-serif;
      background: linear-gradient(to bottom, #6fbb96, #23a86b);
      color: #fff;
      overflow-x: hidden;
    }

    body {
      display: flex;
      flex-direction: column;
      align-items: center;
      padding: 20px;
    }

    header {
      width: 100%;
      max-width: 1200px;
      display: flex;
      align-items: center;
      justify-content: space-between;
      padding: 20px;
    }

    header img {
      height: 60px;
    }

    nav a {
      color: #fff;
      text-decoration: none;
      font-size: 1.1rem;
      margin-left: 30px;
      transition: 0.3s;
    }

    nav a:hover {
      color: #d4ffd8;
    }

    .hero {
      text-align: center;
      margin-top: 50px;
      animation: fadeIn 1.2s ease;
    }

    .hero h1 {
      font-size: 3rem;
      margin-bottom: 15px;
    }

    .hero p {
      font-size: 1.4rem;
      opacity: 0.9;
      margin-bottom: 30px;
    }

    .button-container {
      display: flex;
      gap: 20px;
      justify-content: center;
      flex-wrap: wrap;
    }

    .button {
      background-color: #0e4f36;
      color: white;
      padding: 12px 30px;
      font-size: 1.1rem;
      border-radius: 30px;
      text-decoration: none;
      transition: all 0.3s;
      box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
    }

    .button:hover {
      background-color: #0e4f36;
      transform: translateY(-3px);
    }

    .sections {
      display: flex;
      flex-wrap: wrap;
      gap: 30px;
      margin-top: 80px;
      justify-content: center;
      width: 100%;
      max-width: 1200px;
    }

    .card {
      background: rgba(0, 0, 0, 0.7);
      border-radius: 15px;
      padding: 30px;
      flex: 1 1 300px;
      min-height: 250px;
      text-align: center;
      transition: all 0.3s;
    }

    .card:hover {
      background: rgba(0, 0, 0, 0.8);
      transform: translateY(-5px);
    }

    .card i {
      font-size: 3rem;
      color: #16ac73;
      margin-bottom: 15px;
    }

    .card h2 {
      font-size: 2rem;
      margin-bottom: 15px;
    }

    .card p {
      font-size: 1.1rem;
      opacity: 0.85;
    }

    footer {
      margin-top: 80px;
      font-size: 0.9rem;
      opacity: 0.7;
      text-align: center;
      padding-bottom: 20px;
    }

    /* AnimaÃ§Ãµes */
    @keyframes fadeIn {
      from {
        opacity: 0;
        transform: translateY(-20px);
      }
      to {
        opacity: 1;
        transform: translateY(0);
      }
    }

    /* Responsivo */
    @media (max-width: 768px) {
      .hero h1 {
        font-size: 2.4rem;
      }

      .hero p {
        font-size: 1.2rem;
      }

      nav {
        display: none;
      }
    }
  </style>
</head>

<body>

<div class="background"></div>

<header>
  <img src="${pageContext.request.contextPath}/imagens/logo.png" alt="Logo AimUp">
  <nav>
    <a href="#sobre">Sobre</a>
    <a href="#servicos">Serviços</a>
    <a href="#contato">Contato</a>
  </nav>
</header>

<section class="hero">
  <h1>Seja bem-vindo ao AimUp!</h1>
  <p>Inovação, produtividade e conexão para o seu sucesso.</p>

  <div class="button-container">
    <a href="cadastro.jsp" class="button">Criar Conta</a>
    <a href="login.jsp" class="button">Entrar</a>
  </div>
</section>

<section class="sections">
  <div class="card" id="sobre">
    <i class="fas fa-bullseye"></i>
    <h2>Sobre o AimUp</h2>
    <p>O AimUp © uma plataforma moderna focada em aumentar a produtividade e promover a comunicação ágil e inteligente entre equipes.</p>
  </div>

  <div class="card" id="servicos">
    <i class="fas fa-cogs"></i>
    <h2>Serviços</h2>
    <p>Oferecemos ferramentas práticas para gestão de projetos, organização de tarefas e integração eficiente entre times de trabalho.</p>
  </div>

  <div class="card" id="contato">
    <i class="fas fa-envelope"></i>
    <h2>Entre em Contato</h2>
    <p>Entre em contato com nossa equipe para saber como podemos impulsionar seus resultados de maneira simples, rápida e segura!</p>
  </div>
</section>

<footer>
  © 2025 AimUp. Todos os direitos reservados.
</footer>

</body>
</html>
