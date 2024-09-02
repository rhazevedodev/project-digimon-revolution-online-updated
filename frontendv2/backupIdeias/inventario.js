// script.js

// Exemplo de dados para simular a resposta do banco de dados
const items = [
    { id: 1, name: 'Poção de Vida', category: 'consumiveis', image: '/imagens/icones/pocaoVida.png', description: 'Restaura 50 pontos de vida.' },
    { id: 2, name: 'Espada de Fogo', category: 'equipamentos', image: 'images/sword.png', description: 'Aumenta o ataque em 20 pontos.' },
    { id: 3, name: 'Fragmento de Cristal', category: 'fragmentos', image: 'images/crystal.png', description: 'Parte de um cristal mágico.' },
    { id: 4, name: 'Medalha de Honra', category: 'itens', image: 'images/medal.png', description: 'Concede 100 pontos de experiência.' }
];

function loadItems(category) {
    const itemsList = document.getElementById('items-list');
    itemsList.innerHTML = '';

    const filteredItems = category ? items.filter(item => item.category === category) : items;

    filteredItems.forEach(item => {
        const itemCard = document.createElement('div');
        itemCard.className = 'item-card';

        itemCard.innerHTML = `
            <img src="${item.image}" alt="${item.name}">
            <h3>${item.name}</h3>
            <p>${item.description}</p>
        `;

        itemsList.appendChild(itemCard);
    });
}

function filterItems(category) {
    loadItems(category);
}

// Carrega todos os itens ao iniciar
loadItems();
