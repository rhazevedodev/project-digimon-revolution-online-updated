// Exemplo de dados para simular a resposta do banco de dados
const items = [
    { id: 1, name: 'Poção de Vida', category: 'consumiveis', image: 'images/potion.png', description: 'Restaura 50 pontos de vida.', quantity: 10 },
    { id: 2, name: 'Espada de Fogo', category: 'equipamentos', image: 'images/sword.png', description: 'Aumenta o ataque em 20 pontos.', quantity: 1 },
    { id: 3, name: 'Fragmento de Cristal', category: 'fragmentos', image: 'images/crystal.png', description: 'Parte de um cristal mágico.', quantity: 5 },
    { id: 4, name: 'Medalha de Honra', category: 'itens', image: 'images/medal.png', description: 'Concede 100 pontos de experiência.', quantity: 2 },
    { id: 5, name: 'Poção de Mana', category: 'consumiveis', image: 'images/mana_potion.png', description: 'Restaura 30 pontos de mana.', quantity: 8 },
    { id: 6, name: 'Escudo de Defesa', category: 'equipamentos', image: 'images/shield.png', description: 'Aumenta a defesa em 15 pontos.', quantity: 1 },
    // Adicione mais itens conforme necessário
];

function loadItems(category = 'todos') {
    const itemsList = document.getElementById('items-list');
    itemsList.innerHTML = '';

    let filteredItems;
    if (category === 'todos') {
        filteredItems = items;
    } else {
        filteredItems = items.filter(item => item.category === category);
    }

    if (filteredItems.length === 0) {
        itemsList.innerHTML = '<p class="no-items">Nenhum item encontrado nesta categoria.</p>';
        return;
    }

    filteredItems.forEach(item => {
        const itemRow = document.createElement('div');
        itemRow.className = 'item-row';

        const itemIcon = document.createElement('img');
        itemIcon.src = item.image;
        itemIcon.alt = item.name;
        itemIcon.className = 'item-icon';

        const itemDetails = document.createElement('div');
        itemDetails.className = 'item-details';

        const itemName = document.createElement('h3');
        itemName.className = 'item-name';
        itemName.textContent = item.name;

        const itemDescription = document.createElement('p');
        itemDescription.className = 'item-description';
        itemDescription.textContent = item.description;

        const itemQuantity = document.createElement('p');
        itemQuantity.className = 'item-quantity';
        itemQuantity.textContent = `Quantidade: ${item.quantity}`;

        const itemActions = document.createElement('div');
        itemActions.className = 'item-actions';

        // Adiciona os botões com base na categoria
        if (item.category === 'consumiveis') {
            const useButton = createButton('Usar', 'use-button');
            const sellButton = createButton('Vender', 'sell-button');
            itemActions.appendChild(useButton);
            itemActions.appendChild(sellButton);
        } else if (item.category === 'equipamentos') {
            const equipButton = createButton('Equipar', 'equip-button');
            const sellButton = createButton('Vender', 'sell-button');
            itemActions.appendChild(equipButton);
            itemActions.appendChild(sellButton);
        } else if (item.category === 'itens') {
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
}

function createButton(text, className) {
    const button = document.createElement('button');
    button.className = className;
    button.textContent = text;
    // Adicionar lógica para o botão aqui
    return button;
}

function filterItems(category) {
    loadItems(category);
}

// Carrega todos os itens ao iniciar
window.onload = () => {
    loadItems('todos');
};
