document.addEventListener('DOMContentLoaded', function() {
    const loginForm = document.getElementById('loginForm');
    const errorMessage = document.getElementById('error-message');

    loginForm.addEventListener('submit', function(event) {
        event.preventDefault();
        
        const username = document.getElementById('username').value;
        const password = document.getElementById('password').value;
        
        // Valores de login pré-definidos para autenticação
        const validUsername = 'usuario';
        const validPassword = 'senha123';
        
        if (username === validUsername && password === validPassword) {
            Swal.fire({
                title: 'Login bem-sucedido!',
                text: 'Você será redirecionado para a página principal.',
                icon: 'success',
                confirmButtonText: 'OK'
            }).then(() => {
                // Redirecionar para outra página ou realizar outra ação
                window.location.href = 'selecaoInicialv2.html';
            });
        } else {
            Swal.fire({
                title: 'Erro!',
                text: 'Usuário ou senha incorretos!',
                icon: 'error',
                confirmButtonText: 'Tentar novamente'
            });
        }
    });
});