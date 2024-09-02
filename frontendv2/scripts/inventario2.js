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
            const items = data.itens;
            console.log(items) // Ajuste conforme a estrutura da resposta da sua API

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
