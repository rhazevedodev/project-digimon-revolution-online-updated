document.addEventListener('DOMContentLoaded', function () {

    const apiUrlCadastroUsuario = 'http://localhost:8080/api/cadastro/usuario';

    function cadastrarUsuario() {

        // Dados que serão enviados no corpo da requisição
        const requestBody = {
            usuario: document.getElementById('username').value,
            senha: document.getElementById('password').value,
            email: document.getElementById('email').value,
            dataNascimento: document.getElementById('birthdate').value
        };

        // Configurações da requisição
        const requestOptions = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(requestBody)
        };

        // Fazer a requisição à API
        fetch(apiUrlCadastroUsuario, requestOptions)
            .then(response => {
                if (!response.ok) {
                    return response.json().then(errorData => {
                        throw new Error(errorData.erro || 'Erro desconhecido');
                    });
                }
                return response.json();
            })
            .then(data => {
                console.log(data);
                Swal.fire({
                    title: 'Cadastro feito com sucesso!',
                    text: 'Você será redirecionado para a página de login.',
                    icon: 'success',
                    confirmButtonText: 'OK'
                }).then(() => {
                    // Redirecionar para outra página ou realizar outra ação
                    window.location.href = 'login.html';
                });
            })
            .catch(error => {
                Swal.fire({
                    title: 'Erro!',
                    text: `Erro ao fazer cadastro: ${error.message}`,
                    icon: 'error',
                    confirmButtonText: 'Tentar novamente'
                });
            });


    }


    document.getElementById('loginForm').addEventListener('submit', function (event) {
        event.preventDefault(); // Impede o envio do formulário

        const password = document.getElementById('password').value;
        const confirmPassword = document.getElementById('confirm-password').value;

        if (password !== confirmPassword) {
            Swal.fire({
                icon: 'error',
                title: 'Campos de senha e confirmação de senha diferentes',
                text: 'Por favor, verifique as senhas informadas.',
            });
        } else {
            // Se as senhas forem iguais, envie o formulário
            //this.submit();
            cadastrarUsuario();
        }
    });

});