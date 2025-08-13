const mensagens = [
    "Você é mais forte do que imagina!",
    "Cada pequeno esforço conta!",
    "A disciplina supera a motivação!",
    "Continue, os resultados estão chegando!",
    "Sua jornada é única, valorize cada passo!",
    "Confie no processo, você está indo bem!",
    "O importante é não desistir!"
  ];
  
  function mudarMensagem() {
    const frase = mensagens[Math.floor(Math.random() * mensagens.length)];
    document.getElementById('mensagemMotivacional').innerText = frase;
  }
  
  // Troca de 10 em 10 segundos
  setInterval(mudarMensagem, 10000);
  
  // Inicializar assim que carregar
  window.onload = mudarMensagem;
  
  