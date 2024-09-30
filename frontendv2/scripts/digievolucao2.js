document.addEventListener('DOMContentLoaded', function () {
    const apiListarEvolucoes = 'http://localhost:8080/api/digievolucao/1';

    let evolucoes = []; // Armazena as evoluções recebidas da API
    /*let currentDigimon = null; // Armazena o digimon atual*/
    // Função para carregar os dados da API
    function carregarEvolucoes() {
        // Configurações da requisição
        const requestOptions = {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        };

        // Faz a requisição à API
        fetch(apiListarEvolucoes, requestOptions)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Erro na rede, status: ' + response.status);
                }
                return response.json();
            })
            .then(data => {
                // Armazenar as evoluções recebidas
                evolucoes = data;
                /*currentDigimon = evolucoes[0].digimonOrigem; // Exemplo de pegar o nome do Digimon atual*/
                atualizarInterface();
            })
            .catch(error => {
                console.error('Erro ao fazer requisição:', error);
            });
    }

    // Função para atualizar a interface com as opções de evolução
    function atualizarInterface() {
        const evolucoesContainer = document.getElementById('evolucoes-container');

        // Limpa as opções de evolução
        evolucoesContainer.innerHTML = '';

        // Itera sobre as evoluções e cria botões para cada uma
        evolucoes.forEach((evolucao, index) => {
            const evolutionDiv = document.createElement('div');
            evolutionDiv.classList.add('evolution-card'); // Adiciona uma classe para estilização

            // Cria o elemento h1 para o nome do Digimon
            const digimonName = document.createElement('h1');
            digimonName.textContent = evolucao.digimonDestino;

            // Atualiza a imagem e os requisitos de evolução
            const digimonImage = document.createElement('img');
            digimonImage.src = evolucao.pathImagemDigimonDestino;
            digimonImage.alt = evolucao.digimonDestino;
            digimonImage.id = `digimon-image`;

            // Cria o parágrafo com o texto "Fragmentos Necessários:"
            const fragmentosText = document.createElement('p');
            fragmentosText.textContent = `Fragmentos Necessários: ${evolucao.fragmentosNecessarios}`;

            let itemEspecialNecessario = 'Não';
            if(evolucao.itemEspecialNecessario){
                itemEspecialNecessario = 'Sim';
            }
            const itemEspecialText = document.createElement('p');
            itemEspecialText.textContent = `Item Especial Necessário: ${itemEspecialNecessario}`;

            const evolutionButton = document.createElement('button');
            evolutionButton.textContent = `Evoluir para ${evolucao.digimonDestino}`;
            evolutionButton.className = `button-digievoluir`;
            evolutionButton.disabled = evolucao.fragmentosNecessarios > 50 ? true : false;

            evolutionButton.addEventListener('click', () => digivolve(index));

            // Adiciona a imagem e o botão à interface
            evolutionDiv.appendChild(digimonName); // Adiciona o nome do Digimon ao card
            evolutionDiv.appendChild(digimonImage); // Adiciona a imagem do Digimon ao card// Adiciona o texto logo após a imagem
            evolutionDiv.appendChild(document.createElement('br'));
            evolutionDiv.appendChild(document.createElement('br'));
            evolutionDiv.appendChild(fragmentosText);
            evolutionDiv.appendChild(itemEspecialText);
            evolutionDiv.appendChild(document.createElement('br'));
            evolutionDiv.appendChild(document.createElement('br'));
            evolutionDiv.appendChild(evolutionButton); // Adiciona o botão de evolução ao card


            // Adiciona a div completa ao container principal
            evolucoesContainer.appendChild(evolutionDiv);
        });
    }

    // Função chamada ao clicar no botão de evolução
    function digivolve(index) {
        const evolucaoEscolhida = evolucoes[index];
        alert(`Parabéns! ${evolucaoEscolhida.digimonOrigem} evoluiu para ${evolucaoEscolhida.digimonDestino}!`);
        // Aqui você pode adicionar a lógica de enviar uma requisição para a API
        // para registrar a evolução, por exemplo.
    }

    // Evento para carregar os dados ao iniciar a página
    window.onload = carregarEvolucoes;

});
