document.addEventListener('DOMContentLoaded', () => {

    const apiUrlCadastroUsuario = 'http://localhost:8080/api/cadastro/usuario';

    async function cadastrarUsuario() {
        try {
            const usuario = document.getElementById('username').value;
            const senha = document.getElementById('password').value;
            const email = document.getElementById('email').value;
            const dataNascimento = document.getElementById('birthdate').value;

            const data = await fetchAPI(apiUrlCadastroUsuario, 'POST', {
                usuario, 
                senha, 
                email, 
                dataNascimento
            });

            Swal.fire({
                title: 'Cadastro feito com sucesso!',
                text: 'Você será redirecionado para a página de login.',
                icon: 'success',
                confirmButtonText: 'OK'
            }).then(() => {
                window.location.href = 'login.html';
            });
        } catch (error) {
            displayError('Erro ao fazer cadastro', error.message);
        }
    }

    async function fetchAPI(url, method, body = null) {
        const requestOptions = {
            method,
            headers: { 'Content-Type': 'application/json' },
            body: body ? JSON.stringify(body) : null
        };

        const response = await fetch(url, requestOptions);
        if (!response.ok) {
            const errorData = await response.json();
            throw new Error(errorData.erro || 'Erro desconhecido');
        }
        return response.json();
    }

    function displayError(title, message) {
        Swal.fire({
            title: 'Erro!',
            text: `${title}: ${message}`,
            icon: 'error',
            confirmButtonText: 'Tentar novamente'
        });
    }

    document.getElementById('loginForm').addEventListener('submit', (event) => {
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
            cadastrarUsuario();
        }
    });

});
