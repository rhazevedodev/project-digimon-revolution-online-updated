document.addEventListener('DOMContentLoaded', function () {

    const apiListarEvolucoes = 'http://localhost:8080/api/digievolucao/1';

    let dataEvolucoes = {};
    function carregarTelaCacadas() {

        // Configurações da requisição
        const requestOptions = {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        };

        fetch(apiURL, requestOptions)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Erro na rede, status: ' + response.status);
                }
                return response.json();
            })
            .then(data => {
                // Armazenar os dados recebidos da API

                // Atualizar a interface com os dados recebidos
                /*displayPremiumContent(dataCacadas);*/
                /*atualizarImagemDigimon(dataCacadas)*/
                /*carregarCaixaCacadaPorTempo(dataCacadas);*/
                /*displayLifeBar();*/
                /*displayEnergyBar();*/
            })
            .catch(error => {
                console.error('Erro ao fazer requisição:', error);
            });
    }


    // Dados simulados da API para um Digimon
    const evolutionData = {
        currentDigimon: {
            name: 'Greymon',
            image: '/imagens/digimons/champions/greymon2.jpg',
            fragments: 40,
            specialItem: false
        },
        evolutionRequirements: {
            name: 'Greymon',
            fragmentsRequired: 50,
            specialItemRequired: 'Nenhum'
        }
    };

    // Função para carregar as informações na tela
    function loadEvolutionData() {
        const digimonImage = document.getElementById('digimon-image');
        const digimonName = document.getElementById('digimon-name');
        const requiredFragments = document.getElementById('required-fragments');
        const requiredItem = document.getElementById('required-item');
        const currentFragments = document.getElementById('current-fragments');
        const specialItem = document.getElementById('special-item');
        const evolveButton = document.getElementById('evolve-button');

        // Dados do Digimon atual
        digimonImage.src = evolutionData.currentDigimon.image;
        digimonName.textContent = evolutionData.currentDigimon.name;
        currentFragments.textContent = evolutionData.currentDigimon.fragments;
        specialItem.textContent = evolutionData.currentDigimon.specialItem ? 'Sim' : 'Não';

        // Requisitos da evolução
        requiredFragments.textContent = evolutionData.evolutionRequirements.fragmentsRequired;
        requiredItem.textContent = evolutionData.evolutionRequirements.specialItemRequired;

        // Verifica se o jogador tem fragmentos e item especial para a evolução
        const hasEnoughFragments = evolutionData.currentDigimon.fragments >= evolutionData.evolutionRequirements.fragmentsRequired;
        const hasSpecialItem = evolutionData.currentDigimon.specialItem || evolutionData.evolutionRequirements.specialItemRequired === 'Nenhum';

        // Habilita ou desabilita o botão de evolução
        if (hasEnoughFragments) {
            evolveButton.disabled = false;
        } else {
            evolveButton.disabled = true;
        }
    }

    // Função chamada ao clicar no botão de evolução
    function digivolve() {
        alert(`Parabéns! ${evolutionData.currentDigimon.name} evoluiu para ${evolutionData.evolutionRequirements.name}!`);
        // Aqui você pode adicionar a lógica de enviar uma requisição para a API
    }

    // Evento para carregar os dados ao iniciar a página
    window.onload = loadEvolutionData;

    // Adiciona um evento de clique ao botão de evolução
    document.getElementById('evolve-button').addEventListener('click', digivolve);

});