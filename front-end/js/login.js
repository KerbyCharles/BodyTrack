const formularioLogin =document.getElementById("login");
const campoEmail = document.getElementById("emailLogin");
const campoSenha = document.getElementById("senhaLogin");

formularioLogin.addEventListener("submit", function(event){
    event.preventDefault();
    const email = campoEmail.value;
    const senha = campoSenha.value;

    if (email === "" || senha.trim() === ""){
        alert("Preencha todos os campos!");
    } else if (!campoEmail.validity.valid){
        alert("Digite um e-mail válido!");
    } else{
        alert("Dados preenchidos com sucesso!");
        formularioLogin.reset();
    }

    console.log("E-mail",email);   
});