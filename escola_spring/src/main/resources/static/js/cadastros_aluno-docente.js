const container = document.getElementById('container');
const registerBtn = document.getElementById('register');
const loginBtn = document.getElementById('login');

registerBtn.addEventListener('click', () => {
    container.classList.add("active");
});

loginBtn.addEventListener('click', () => {
    container.classList.remove("active");
});

setTimeout(function() {
    var erroSenha = document.getElementById('erro2');
    if (erroSenha) {
        erroSenha.style.display = 'none';
    }
}, 5000); // 5000 milissegundos = 5 segundos