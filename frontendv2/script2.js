// script.js

// Exemplo de dados para simular a resposta do banco de dados
const items = [
    { id: 1, name: 'Poção de Vida', category: 'consumiveis', image: 'images/potion.png', description: 'Restaura 50 pontos de vida.' },
    { id: 2, name: 'Espada de Fogo', category: 'equipamentos', image: 'images/sword.png', description: 'Aumenta o ataque em 20 pontos.' },
    { id: 3, name: 'Fragmento de Cristal', category: 'fragmentos', image: 'images/crystal.png', description: 'Parte de um cristal mágico.' },
    { id: 4, name: 'Medalha de Honra', category: 'itens', image: 'images/medal.png', description: 'Concede 100 pontos de experiência.' },
    { id: 5, name: 'Poção de Mana', category: 'consumiveis', image: 'images/mana_potion.png', description: 'Restaura 30 pontos de mana.' },
    { id: 6, name: 'Escudo de Defesa', category: 'equipamentos', image: 'images/shield.png', description: 'Aumenta a defesa em 15 pontos.' },
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

        itemDetails.appendChild(itemName);
        itemDetails.appendChild(itemDescription);

        itemRow.appendChild(itemIcon);
        itemRow.appendChild(itemDetails);

        itemsList.appendChild(itemRow);
    });
}

function filterItems(category) {
    loadItems(category);
}

// Carrega todos os itens ao iniciar
window.onload = () => {
    loadItems('todos');
};
