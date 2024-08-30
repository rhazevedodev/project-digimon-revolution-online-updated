document.addEventListener('DOMContentLoaded', () => {

    const apiURL = 'http://localhost:8080/api/login/autenticar';
    const firstAccessURL = 'http://localhost:8080/api/login/verificaPrimeiroAcesso';
    const encryptUrl = 'http://localhost:8080/api/login/encryptUsuario/';

    async function autenticarUsuario(event) {
        event.preventDefault(); // Evita o comportamento padrão de submissão do formulário

        try {
            const usuario = document.getElementById('username').value;
            const senha = document.getElementById('password').value;

            const data = await fetchAPI(apiURL, 'POST', { usuario, senha });

            Swal.fire({
                title: 'Login bem-sucedido!',
                text: 'Você será redirecionado para a página principal.',
                icon: 'success',
                confirmButtonText: 'OK'
            }).then(async () => {
                localStorage.setItem('token', data.token);
                await encryptUsuario(usuario);
                await verificarPrimeiroAcesso(localStorage.getItem('usuario'));
            });
        } catch (error) {
            displayError('Erro ao fazer login', error.message);
        }
    }

    async function encryptUsuario(usuario) {
        try {
            const data = await fetchAPI(`${encryptUrl}${usuario}`, 'GET');
            localStorage.setItem('usuario', data.usuario);
        } catch (error) {
            displayError('Erro ao criptografar usuário', error.message);
        }
    }

    async function verificarPrimeiroAcesso(usuario) {
        try {
            const data = await fetchAPI(firstAccessURL, 'POST', { usuario });
            const mensagem = data.primeiroAcesso 
                ? 'Este é o seu primeiro acesso. Aproveite!' 
                : 'Bom vê-lo novamente!';

            Swal.fire({
                title: data.primeiroAcesso ? 'Bem-vindo!' : 'Bem-vindo de volta!',
                text: mensagem,
                icon: 'info',
                confirmButtonText: 'OK'
            }).then(() => {
                const redirectPage = data.primeiroAcesso 
                    ? 'selecaoDigimon.html' 
                    : 'continuarJornada.html';
                window.location.href = redirectPage;
            });

        } catch (error) {
            displayError('Não foi possível verificar o primeiro acesso', error.message);
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

    document.getElementById('loginForm').addEventListener('submit', autenticarUsuario);
});
