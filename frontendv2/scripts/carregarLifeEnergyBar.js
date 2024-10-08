document.addEventListener('DOMContentLoaded', function() {

    const carregarBarrasHPEnergiaURL = 'http://localhost:8080/api/digimon/carregarVidaEnergia/'+localStorage.getItem('idDigimon');

    let dataBarras = {};

    function carregarBarrasHPEnergia() {
    
        const requestOptions = {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        };
    
        fetch(carregarBarrasHPEnergiaURL, requestOptions)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Erro na rede, status: ' + response.status);
                }
                return response.json();
            })
            .then(data => {
                dataBarras = data;
                console.log(dataBarras);
                atualizarBarras();
            })
            .catch(error => {
                console.error('Erro ao fazer requisição:', error);
            });
        
    }

    function atualizarBarras() {
        displayLifeBar();
        displayEnergyBar();
    }

    function displayLifeBar() {
        var vidaTotal = 50 * parseInt(dataBarras.nivel);
        const vidaAtual = dataBarras.vida; // Pontos de vida atuais
        const vidaMaxima = vidaTotal; // Pontos de vida máximos

        // Calcula a largura da barra de vida com base na porcentagem
        const lifeBarWidth = (vidaAtual / vidaMaxima) * 100;
        const lifeBar = document.getElementById('life-bar');
        lifeBar.style.width = lifeBarWidth + '%';

        const lifeBarText = document.getElementById('life-bar-text');
        lifeBarText.textContent = `${vidaAtual}/${vidaMaxima}`;

    }

    function displayEnergyBar() {
        var energiaTotal = 100;
        const energiaAtual = dataBarras.energia; // Pontos de energia atuais
        const energiaMaxima = energiaTotal; // Pontos de energia máximos

        // Calcula a largura da barra de vida com base na porcentagem
        const energyBarWidth = (energiaAtual / energiaMaxima) * 100;
        const energyBar = document.getElementById('energy-bar');
        energyBar.style.width = energyBarWidth + '%';

        const energyBarText = document.getElementById('energy-bar-text');
        energyBarText.textContent = `${energiaAtual}/${energiaMaxima}`;

    }

    carregarBarrasHPEnergia();
    
});