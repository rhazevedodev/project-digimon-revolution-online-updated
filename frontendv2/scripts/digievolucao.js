document.addEventListener('DOMContentLoaded', function () {
    const apiListarEvolucoes = 'http://localhost:8080/api/digievolucao/'+localStorage.getItem("idDigimon");
    const apiDigievoluir = 'http://localhost:8080/api/digievolucao/digievoluir'

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
            const fragmentosNecessariosText = document.createElement('p');
            fragmentosNecessariosText.textContent = `Fragmentos Necessários: ${evolucao.fragmentosNecessarios}`;

            // Cria o parágrafo com o texto "Fragmentos Obtidos:"
            const fragmentosObtidosText = document.createElement('p');
            fragmentosObtidosText.textContent = `Fragmentos Disponíveis: ${evolucao.fragmentosDisponiveis}`;
            
            let itemEspecialNecessario = 'Não';
            if(evolucao.itemEspecialNecessario){
                itemEspecialNecessario = 'Sim';
            }
            const itemEspecialText = document.createElement('p');
            itemEspecialText.textContent = `Item Especial Necessário: ${itemEspecialNecessario}`;

            const evolutionButton = document.createElement('button');
            evolutionButton.textContent = `Evoluir para ${evolucao.digimonDestino}`;
            evolutionButton.className = `button-digievoluir`;
            evolutionButton.disabled = evolucao.fragmentosDisponiveis >= evolucao.fragmentosNecessarios ? false : true;

            evolutionButton.addEventListener('click', () => digivolve(index));

            // Adiciona a imagem e o botão à interface
            evolutionDiv.appendChild(digimonName); // Adiciona o nome do Digimon ao card
            evolutionDiv.appendChild(digimonImage); // Adiciona a imagem do Digimon ao card// Adiciona o texto logo após a imagem
            evolutionDiv.appendChild(document.createElement('br'));
            evolutionDiv.appendChild(document.createElement('br'));
            evolutionDiv.appendChild(fragmentosNecessariosText);
            evolutionDiv.appendChild(fragmentosObtidosText);
            evolutionDiv.appendChild(document.createElement('br'));
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
        digievoluir(evolucaoEscolhida);
        alert(`Parabéns! ${evolucaoEscolhida.digimonOrigem} evoluiu para ${evolucaoEscolhida.digimonDestino}!`);
        // Aqui você pode adicionar a lógica de enviar uma requisição para a API
        // para registrar a evolução, por exemplo.
    }

    function digievoluir(evolucaoEscolhida) {
        // Dados que serão enviados no corpo da requisição
        const requestBody = {
            idDigimon: localStorage.getItem('idDigimon'),
            evolucaoEscolhida: evolucaoEscolhida.digimonDestino,
            fragmentosNecessarios: evolucaoEscolhida.fragmentosNecessarios
        };
        // Configurações da requisição
        const requestOptions = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(requestBody)
        };

        fetch(apiDigievoluir, requestOptions)
            .then(response => {
                if (response.status === 200) {
                    // Se o status da resposta for 200, redireciona para a página de status
                    const redirectPage = 'status.html';
                    window.location.href = redirectPage;
                } else {
                    // Se o status não for 200, lança um erro
                    return response.text().then(errorMessage => {
                        throw new Error('Erro ao fazer requisição: ' + errorMessage);
                    });
                }
            })
            .catch(error => {
                console.error(error.message); // Exibe o erro no console
                alert('Erro ao tentar realizar a evolução: ' + error.message); // Exibe uma mensagem de erro na interface
            });
    }

    // Evento para carregar os dados ao iniciar a página
    window.onload = carregarEvolucoes;

});
