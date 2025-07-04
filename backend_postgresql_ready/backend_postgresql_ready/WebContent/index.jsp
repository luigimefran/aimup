<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>AimUp - Início</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" />
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" rel="stylesheet" />
  <link rel="shortcut icon" href="/img/Logo AimUp.png" type="image/x-icon" />
  <style>
    body {
      background: linear-gradient(0deg, #6fbb96 0%, #23a86b 100%);
      font-family: 'Segoe UI', sans-serif;
      color: #fff;
      min-height: 100vh;
    }
    .card {
      background-color: rgba(255, 255, 255, 0.1);
      border: none;
      border-radius: 20px;
      backdrop-filter: blur(5px);
      color: #fff;
    }
    .sidebar {
      position: fixed;
      top: 0;
      left: 0;
      height: 100%;
      width: 250px;
      background-color: rgba(0, 0, 0, 0.7);
      padding-top: 60px;
      display: none;
      flex-direction: column;
      z-index: 1000;
    }
    .sidebar a {
      color: white;
      padding: 15px;
      text-decoration: none;
      transition: background 0.3s;
    }
    .sidebar a:hover {
      background-color: rgba(255, 255, 255, 0.2);
    }
    .menu-icon {
      font-size: 24px;
      cursor: pointer;
    }
    .overlay {
      position: fixed;
      top: 0;
      left: 0;
      height: 100%;
      width: 100%;
      background: rgba(0, 0, 0, 0.5);
      z-index: 500;
      display: none;
    }
    /* Ãcone lixeira sÃ³ aparece no modo gerenciamento */
    .reminder-delete {
      display: none;
      cursor: pointer;
      color: #dc3545;
      margin-left: 10px;
      font-size: 1.2rem;
      transition: color 0.2s ease;
    }
    .reminder-delete:hover {
      color: #a71d2a;
    }
    .manage-mode .reminder-delete {
      display: inline-block;
    }
    #manageRemindersBtn {
      color: #23a86b;
      border-color: #23a86b;
    }
  </style>
</head>
<body>

  <div class="container-fluid p-4">
    <div class="d-flex justify-content-between align-items-center mb-4">
      <i class="fas fa-bars menu-icon" onclick="toggleMenu()"></i>
      <div class="text-center py-2">
        <h5 id="mensagemMotivacional" class="fw-bold text-white">Acredite em você! Um passo de cada vez.</h5>
      </div>
      <a href="/perfil/perfil.html"><i class="fas fa-user-circle fa-2x text-white"></i></a>
    </div>

    <div class="row g-4">
      <!-- TAREFAS -->
      <div class="col-md-4">
        <div class="card p-3 text-dark" style="background-color: #ffffffd7">
          <h5 class="fw-bold text-success">TAREFAS</h5>
          <ul id="taskList" class="list-unstyled"></ul>
          <!-- BotÃ£o de Gerenciar Tarefas removido conforme pedido -->
        </div>
      </div>

      <!-- CALENDÃRIO -->
      <div class="col-md-4">
        <div class="card p-3 text-dark bg-white text-center" style="background-color: #ffffffd7 !important">
          <div class="d-flex justify-content-between align-items-center">
            <h5 class="fw-bold text-success mb-0">CALENDÁRIO</h5>
            <div>
              <button class="btn btn-outline-success me-2 fw-bold rounded-circle" style="width: 40px; height: 40px;" onclick="toggleForm()">
                <i class="fas fa-plus"></i>
              </button>
              <button id="manageRemindersBtn" class="btn btn-outline-danger fw-bold" onclick="toggleManageMode()">
                Gerenciar
              </button>
            </div>
          </div>

          <div id="formContainer" class="mt-3 d-none">
            <input type="text" id="eventTitle" class="form-control mb-2" placeholder="TÃ­tulo" />
            <input type="datetime-local" id="eventDateTime" class="form-control mb-2" />
            <button class="btn btn-success w-100" onclick="addReminder()">Salvar</button>
          </div>

          <div id="calendar-list" class="mt-3 d-flex flex-column gap-2"></div>
        </div>
      </div>

      <!-- PROGRESSO -->
      <div class="col-md-4">
        <div class="card p-3 text-dark bg-white" style="background-color: #ffffffd7 !important">
          <h5 class="fw-bold text-success">PROGRESSO</h5>
          <p class="mb-2">Início do grupo: 01/01/01</p>
          <div class="progress">
            <div class="progress-bar bg-success" style="width: 100%">99/99/99</div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <!-- SIDEBAR -->
  <div id="sidebar" class="sidebar">
    <a href="/index/index.html"><i class="fas fa-home me-2"></i> Início</a>
    <a href="/chat/chat.html"><i class="fas fa-comments me-2"></i> Chat do grupo</a>
    <a href="/ranking/ranking.html"><i class="fas fa-users me-2"></i> Grupo</a>
  </div>
  <div id="overlay" class="overlay" onclick="toggleMenu()"></div>

  <!-- SCRIPTS -->
  <script>
    function toggleMenu() {
      const sidebar = document.getElementById('sidebar');
      const overlay = document.getElementById('overlay');
      const isOpen = sidebar.style.display === 'flex';
      sidebar.style.display = isOpen ? 'none' : 'flex';
      overlay.style.display = isOpen ? 'none' : 'block';
    }

    function toggleForm() {
      const form = document.getElementById("formContainer");
      form.classList.toggle("d-none");
    }

    function addReminder() {
      const title = document.getElementById("eventTitle").value.trim();
      const dateTime = document.getElementById("eventDateTime").value;

      if (!title || !dateTime) {
        alert("Preencha todos os campos!");
        return;
      }

      const dateObj = new Date(dateTime);
      const now = new Date();

      if (dateObj < now) {
        alert("A data e hora precisam estar no futuro!");
        return;
      }

      const day = String(dateObj.getDate()).padStart(2, '0');
      const month = dateObj.toLocaleString('pt-BR', { month: 'short' }).toUpperCase();
      const hour = dateObj.toLocaleTimeString('pt-BR', { hour: '2-digit', minute: '2-digit' });

      const container = document.getElementById("calendar-list");

      const div = document.createElement("div");
      div.className = "bg-light rounded-4 p-2 text-dark d-flex justify-content-between align-items-center reminder-item";
      div.dataset.timestamp = dateObj.getTime();

      div.innerHTML = `
        <div class="d-flex align-items-center flex-grow-1">
          <div>
            <h4 class="mb-0 fw-bold">${day}</h4>
            <small class="text-uppercase fw-bold">${month}</small>
          </div>
          <div class="ms-3">
            <p class="mb-1 fw-semibold">${title}</p>
            <small class="text-muted">${hour}</small>
          </div>
        </div>
        <i class="fas fa-trash reminder-delete" title="Excluir lembrete" onclick="removeReminder(this)"></i>
      `;

      container.appendChild(div);

      document.getElementById("eventTitle").value = "";
      document.getElementById("eventDateTime").value = "";
      toggleForm();
    }

    function removeReminder(el) {
      const reminderDiv = el.closest('.reminder-item');
      if (reminderDiv) {
        reminderDiv.remove();
      }
    }

    // Remove lembretes expirados a cada minuto
    setInterval(() => {
      const now = Date.now();
      document.querySelectorAll("#calendar-list > div").forEach(reminder => {
        if (parseInt(reminder.dataset.timestamp) < now) {
          reminder.remove();
        }
      });
    }, 60000);

    // SimulaÃ§Ã£o: Tarefas definidas pelo ADM
    const tarefasDoAdm = [
      { nome: "Beber 2L de Ã¡gua", pontos: 5 },
      { nome: "Fazer alongamento", pontos: 4 },
      { nome: "Subir escadas", pontos: 6 }
    ];

    function carregarTarefas() {
      const lista = document.getElementById("taskList");
      tarefasDoAdm.forEach(tarefa => {
        const li = document.createElement("li");
        li.className = "d-flex justify-content-between align-items-center";
        li.innerHTML = `
          <div><input type="checkbox" /> ${tarefa.nome}</div>
          <span class="badge bg-success">+${tarefa.pontos} pts</span>
        `;
        lista.appendChild(li);
      });
    }

    // Ativa/desativa o modo gerenciamento lembretes
    function toggleManageMode() {
      const calendarList = document.getElementById('calendar-list');
      calendarList.classList.toggle('manage-mode');

      const btn = document.getElementById('manageRemindersBtn');
      if (calendarList.classList.contains('manage-mode')) {
        btn.textContent = 'Concluir';
        btn.classList.remove('btn-outline-danger');
        btn.classList.add('btn-success');
      } else {
        btn.textContent = 'Gerenciar';
        btn.classList.remove('btn-success');
        btn.classList.add('btn-outline-danger');
      }
    }

    window.onload = () => {
      carregarTarefas();
    };
  </script>
</body>
</html>
