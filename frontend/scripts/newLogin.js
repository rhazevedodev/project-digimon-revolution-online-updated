document.addEventListener('DOMContentLoaded', function () {

    const apiURL = 'http://localhost:8080/api/login/autenticar';
    const firstAccessURL = 'http://localhost:8080/api/digimon/verificaPrimeiroAcesso';

    let dataLogin = {};

    function autenticarUsuario(event) {
        event.preventDefault(); // Evita o comportamento padrão de submissão do formulário

        // Dados que serão enviados no corpo da requisição
        const requestBody = {
            usuario: document.getElementById('username').value,
            senha: document.getElementById('password').value
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
        fetch(apiURL, requestOptions)
            .then(response => {
                if (!response.ok) {
                    return response.json().then(errorData => {
                        throw new Error(errorData.erro || 'Erro desconhecido');
                    });
                }
                return response.json();
            })
            .then(data => {
                Swal.fire({
                    title: 'Login bem-sucedido!',
                    text: 'Você será redirecionado para a página principal.',
                    icon: 'success',
                    confirmButtonText: 'OK'
                }).then(() => {
                    // Redirecionar para outra página ou realizar outra ação
                    localStorage.setItem('token', data.token);
                    localStorage.setItem('usuario', document.getElementById('username').value);
                    var usuario = document.getElementById('username').value;

                    // Verificar se é o primeiro acesso
                    verificarPrimeiroAcesso(usuario);
                });
            })
            .catch(error => {
                Swal.fire({
                    title: 'Erro!',
                    text: `Erro ao fazer login: ${error.message}`,
                    icon: 'error',
                    confirmButtonText: 'Tentar novamente'
                });
            });
    }

    function verificarPrimeiroAcesso(usuario) {
        // Dados que serão enviados no corpo da requisição
        const requestBody = {
            usuario
        };

        const requestOptions = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: requestBody.usuario
        };

        fetch(firstAccessURL, requestOptions)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Erro na rede, status: ' + response.status);
                }
                return response.json();
            })
            .then(data => {
                if (data.primeiroAcesso == true) {
                    Swal.fire({
                        title: 'Bem-vindo!',
                        text: 'Este é o seu primeiro acesso. Aproveite!',
                        icon: 'info',
                        confirmButtonText: 'OK'
                    }).then(() => {
                        // Redirecionar para outra página ou realizar outra ação
                        window.location.href = 'selecaoInicialv2.html';
                    });
                } else {
                    Swal.fire({
                        title: 'Bem-vindo de volta!',
                        text: 'Bom vê-lo novamente!',
                        icon: 'info',
                        confirmButtonText: 'OK'
                    }).then(() => {
                        // Redirecionar para outra página ou realizar outra ação
                        window.location.href = 'continuarJornada.html';
                    });
                }
            })
            .catch(error => {
                Swal.fire({
                    title: 'Erro!',
                    text: 'Não foi possível verificar o primeiro acesso.',
                    icon: 'error',
                    confirmButtonText: 'Tentar novamente'
                });
            });
    }

    // Adiciona um ouvinte de evento para o formulário
    document.getElementById('loginForm').addEventListener('submit', autenticarUsuario);
});
