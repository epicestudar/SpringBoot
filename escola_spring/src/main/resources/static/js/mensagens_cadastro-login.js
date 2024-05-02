function validarFormularioAluno() {
    var senha = document.getElementById("senha_aluno").value;
    var confirmarSenha = document.getElementById("confirmar_senha_aluno").value;

    if (senha !== confirmarSenha) {
        alert("As senhas não correspondem.");
        return false;
    }

    var inputs = document.querySelectorAll("#container .sign-up input[required]");
    for (var i = 0; i < inputs.length; i++) {
        if (!inputs[i].value) {
            alert("Por favor, preencha todos os campos.");
            return false;
        }
    }
    return true;
}

function validarFormularioDocente() {
    var senha = document.getElementById("senha_docente").value;
    var confirmarSenha = document.getElementById("confirmar_senha_docente").value;

    if (senha !== confirmarSenha) {
        alert("As senhas não correspondem.");
        return false;
    }

    var inputs = document.querySelectorAll("#container .sign-in input[required]");
    for (var i = 0; i < inputs.length; i++) {
        if (!inputs[i].value) {
            alert("Por favor, preencha todos os campos.");
            return false;
        }
    }
    return true;
}