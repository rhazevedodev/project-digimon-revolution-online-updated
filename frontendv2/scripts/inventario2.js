// Exemplo de dados para simular a resposta do banco de dados
/*
const items = [
    { id: 1, name: 'Poção de Vida', category: 'consumiveis', image: 'images/potion.png', description: 'Restaura 50 pontos de vida.', quantity: 10 },
    { id: 2, name: 'Espada de Fogo', category: 'equipamentos', image: 'images/sword.png', description: 'Aumenta o ataque em 20 pontos.', quantity: 1 },
    { id: 3, name: 'Fragmento de Cristal', category: 'fragmentos', image: 'images/crystal.png', description: 'Parte de um cristal mágico.', quantity: 5 },
    { id: 4, name: 'Medalha de Honra', category: 'itens', image: 'images/medal.png', description: 'Concede 100 pontos de experiência.', quantity: 2 },
    { id: 5, name: 'Poção de Mana', category: 'consumiveis', image: 'images/mana_potion.png', description: 'Restaura 30 pontos de mana.', quantity: 8 },
    { id: 6, name: 'Escudo de Defesa', category: 'equipamentos', image: 'images/shield.png', description: 'Aumenta a defesa em 15 pontos.', quantity: 1 },
    // Adicione mais itens conforme necessário
];
*/
document.addEventListener('DOMContentLoaded', function () {

    const apiURL = 'http://localhost:8080/api/telaInventario/carregar';

    let dataInformacoesTelaInventario = {};

    function carregarTelaInventario() {
        // Dados que serão enviados no corpo da requisição
        const requestBody = {
            idDigimon: localStorage.getItem('idDigimon')
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
                    throw new Error('Erro na rede, status: ' + response.status);
                }
                return response.json();
            })
            .then(data => {
                // Armazenar os dados recebidos da API
                dataInformacoesTelaInventario = {
                    url_imagem_digimon: data.url_imagem_digimon,
                    idDigimon: data.idDigimon,
                    status_premium: data.status_premium,
                    data_inicio_premium: data.dataInicio,
                    data_fim_premium: data.dataFim,
                    digimonNivel: data.nivel,
                    digimonVida: data.vida,
                    digimonEnergia: data.energia

                    // Adicionar mais atributos conforme necessário
                };
                // Atualizar a interface com os dados recebidos
                console.log(dataInformacoesTelaInventario)
                atualizarImagemDigimon(dataInformacoesTelaInventario);
                displayPremiumContent(dataInformacoesTelaInventario);
                displayLifeBar();
                displayEnergyBar();
            })
            .catch(error => {
                console.error('Erro ao fazer requisição:', error);
            });
    }

    function displayEnergyBar() {
        var energiaTotal = 100;
        const energiaAtual = dataInformacoesTelaInventario.digimonEnergia; // Pontos de energia atuais
        const energiaMaxima = energiaTotal; // Pontos de energia máximos


        // Calcula a largura da barra de vida com base na porcentagem
        const energyBarWidth = (energiaAtual / energiaMaxima) * 100;
        const energyBar = document.getElementById('energy-bar');
        energyBar.style.width = energyBarWidth + '%';

        const energyBarText = document.getElementById('energy-bar-text');
        energyBarText.textContent = `${energiaAtual}/${energiaMaxima}`;

        /*
                document.getElementById('life-bar').style.width = porcentagemVida + '%';
                document.getElementById('life-bar-text').textContent = `${vidaAtual}/${vidaMaxima}`;
                */
    }


    function displayLifeBar() {
        var vidaTotal = 50 * parseInt(dataInformacoesTelaInventario.digimonNivel);
        const vidaAtual = dataInformacoesTelaInventario.digimonVida; // Pontos de vida atuais
        const vidaMaxima = vidaTotal; // Pontos de vida máximos


        // Calcula a largura da barra de vida com base na porcentagem
        const lifeBarWidth = (vidaAtual / vidaMaxima) * 100;
        const lifeBar = document.getElementById('life-bar');
        lifeBar.style.width = lifeBarWidth + '%';

        const lifeBarText = document.getElementById('life-bar-text');
        lifeBarText.textContent = `${vidaAtual}/${vidaMaxima}`;

        /*
                document.getElementById('life-bar').style.width = porcentagemVida + '%';
                document.getElementById('life-bar-text').textContent = `${vidaAtual}/${vidaMaxima}`;
                */
    }

    async function atualizarImagemDigimon(dataInformacoesTelaInventario) {

        // Obtém o contêiner onde a imagem será adicionada
        const imageContainer = document.getElementById('image-container');

        // Remove qualquer imagem existente no contêiner
        while (imageContainer.firstChild) {
            imageContainer.removeChild(imageContainer.firstChild);
        }

        // Verifica se a URL da imagem está disponível
        if (dataInformacoesTelaInventario.url_imagem_digimon) {
            const imgElement = document.createElement('img');
            imgElement.src = dataInformacoesTelaInventario.url_imagem_digimon;
            imgElement.alt = "Imagem do Digimon";
            imgElement.width = 163;
            imgElement.height = 174;
            imgElement.className = "img-bordered";

            imageContainer.appendChild(imgElement);
        } else {
            console.error('URL da imagem não encontrada.');
        }
    }

    function displayPremiumContent(dataInformacoesTelaInventario) {

        const container = document.getElementById('caixa-informacoes-esquerda');
        container.innerHTML = ''; // Limpa o conteúdo existente

        if (dataInformacoesTelaInventario.status_premium === 'Ativo') {
            container.innerHTML = `
                <h3 class="titulo-caixa">Premium</h3>
                <hr class="separador">
                <div id="caixa-premium">
                    <p>De:</p>
                    <p id="premium_inicio">${dataInformacoesTelaInventario.data_inicio_premium}</p>
                    <p>Até:</p>
                    <p id="premium_fim">${dataInformacoesTelaInventario.data_fim_premium}</p>
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

    // Função para carregar itens de uma API
    function loadItems(categoriaItem = 'TODOS') {
        const itemsList = document.getElementById('items-list');
        itemsList.innerHTML = '';

        // URL da API
        const apiUrl = `http://localhost:8080/api/telaInventario/carregarInventario`;

        // Dados a serem enviados no corpo da requisição
        const requestBody = {
            idDigimon: "2"
        };

        fetch(apiUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(requestBody)
        })
            .then(response => response.json())
            .then(data => {
                const items = data.itens;// Ajuste conforme a estrutura da resposta da sua API

                if (categoriaItem.toUpperCase() === 'TODOS') {
                    filteredItems = items;
                } else {
                    filteredItems = items.filter(item => item.categoriaItem.toUpperCase() === categoriaItem.toUpperCase());
                }

                if (filteredItems.length === 0) {
                    itemsList.innerHTML = '<p class="no-items">Nenhum item encontrado nesta categoria.</p>';
                    return;
                }


                filteredItems.forEach(item => {
                    const itemRow = document.createElement('div');
                    itemRow.className = 'item-row';

                    const itemIcon = document.createElement('img');
                    itemIcon.src = item.urlImagem;
                    itemIcon.alt = item.name;
                    itemIcon.className = 'item-icon';

                    const itemDetails = document.createElement('div');
                    itemDetails.className = 'item-details';

                    const itemName = document.createElement('h3');
                    itemName.className = 'item-name';
                    itemName.textContent = item.nomeItem;

                    const itemDescription = document.createElement('p');
                    itemDescription.className = 'item-description';
                    itemDescription.textContent = item.descricaoItem;

                    const itemQuantity = document.createElement('p');
                    itemQuantity.className = 'item-quantity';
                    itemQuantity.textContent = `Quantidade: ${item.quantidade}`;

                    const itemActions = document.createElement('div');
                    itemActions.className = 'item-actions';

                    // Adiciona os botões com base na categoria
                    if (item.categoriaItem === 'CONSUMIVEIS') {
                        const useButton = createButton('Usar', 'use-button');
                        const sellButton = createButton('Vender', 'sell-button');
                        itemActions.appendChild(useButton);
                        itemActions.appendChild(sellButton);
                    } else if (item.categoriaItem === 'EQUIPAMENTOS') {
                        const equipButton = createButton('Equipar', 'equip-button');
                        const sellButton = createButton('Vender', 'sell-button');
                        itemActions.appendChild(equipButton);
                        itemActions.appendChild(sellButton);
                    } else if (item.categoriaItem === 'ITENS') {
                        const useButton = createButton('Usar', 'use-button');
                        const sellButton = createButton('Vender', 'sell-button');
                        itemActions.appendChild(useButton);
                        itemActions.appendChild(sellButton);
                    }

                    itemDetails.appendChild(itemName);
                    itemDetails.appendChild(itemDescription);
                    itemDetails.appendChild(itemQuantity);
                    itemRow.appendChild(itemIcon);
                    itemRow.appendChild(itemDetails);
                    itemRow.appendChild(itemActions);

                    itemsList.appendChild(itemRow);
                });
            })
            .catch(error => {
                console.error('Erro ao carregar itens:', error);
                itemsList.innerHTML = '<p class="error">Erro ao carregar itens. Tente novamente mais tarde.</p>';
            });
    }

    // Função para criar botões
    function createButton(text, className) {
        const button = document.createElement('button');
        button.className = className;
        button.textContent = text;
        // Adicionar lógica para o botão aqui
        return button;
    }

    // Função para filtrar itens
    function filterItems(categoriaItem) {
        loadItems(categoriaItem);
    }

    // Carrega todos os itens ao iniciar
    window.onload = () => {
        loadItems('todos');
    };

    carregarTelaInventario();


});