document.addEventListener('DOMContentLoaded', function() {

const premiumAPIURL = 'http://localhost:8080/api/premium/carregarInformacoesDeTelaPremium/'+localStorage.getItem('idDigimon');

let dataInformacoesPremium = {};

function carregarInformacoesPremium() {

    const requestOptions = {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    };

    fetch(premiumAPIURL, requestOptions)
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro na rede, status: ' + response.status);
            }
            return response.json();
        })
        .then(data => {
            dataInformacoesPremium = data;
            atualizarInterfacePremium();
        })
        .catch(error => {
            console.error('Erro ao fazer requisição:', error);
        });
    
}

function atualizarInterfacePremium(){

    const container = document.getElementById('caixa-informacoes-esquerda');
    container.innerHTML = ''; // Limpa o conteúdo existente

    if (dataInformacoesPremium.status_premium === 'Ativo') {
        container.innerHTML = `
                <h3 class="titulo-caixa">Premium</h3>
                <hr class="separador">
                <div id="caixa-premium">
                    <p>De:</p>
                    <p id="premium_inicio">${dataInformacoesPremium.data_inicio_premium}</p>
                    <p>Até:</p>
                    <p id="premium_fim">${dataInformacoesPremium.data_fim_premium}</p>
                </div>
                <hr class="separador">
            `;
    } else {
        container.innerHTML = `
                <h3 class="titulo-caixa">Premium</h3>
                <hr class="separador">
                <div id="caixa-premium">
                    <p>Sem Premium Ativo</p>
                </div>
                <hr class="separador">
            `;
    }

}

carregarInformacoesPremium();

});