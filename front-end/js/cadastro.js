
const formularioCadastro = document.getElementById("formCadastro");

const campoNome = document.getElementById("nome");
const campoEmail = document.getElementById("email");
const campoSenha = document.getElementById("senha");
const campoConfirmarSenha = document.getElementById("confirmarSenha");
const campoDataNascimento = document.getElementById("dataNascimento");
const campoAltura = document.getElementById("altura");

    formularioCadastro.addEventListener("submit", function(event){
        event.preventDefault();
        const  nome = campoNome.value;
        const  email = campoEmail.value
        const  senha = campoSenha.value
        const  confirmarSenha = campoConfirmarSenha.value
        const  nascimento = campoDataNascimento.value
        const  altura = campoAltura.value

        if (nome.trim() === "" ||
            email.trim() ==="" ||
            senha.trim() === "" ||
            confirmarSenha.trim() === "" ||
            nascimento === "" || altura === ""
           
        ){
              alert("Preencha todos os campos!");
        } else if (!campoEmail.validity.valid){
            alert("DIgite um e-mail válido!")
        } else if (senha !== confirmarSenha){
            alert("Senhas não coincidem")
        } else {
            alert("Dados preenchidos corretamente!")
            formularioCadastro.reset();
        }

        console.log("Formulário de cadastro enviado!");
    })
