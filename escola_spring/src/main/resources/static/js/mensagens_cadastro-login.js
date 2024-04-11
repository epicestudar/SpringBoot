 // Função para ocultar a mensagem de erro após 5 segundos
 setTimeout(function() {
    var errorMessage = document.getElementById('erroMessage');
    if (errorMessage) {
        errorMessage.style.display = 'none';
    }
}, 5000); // 5000 milissegundos = 5 segundos