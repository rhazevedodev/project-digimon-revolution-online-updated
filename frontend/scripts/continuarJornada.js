document.addEventListener("DOMContentLoaded", function () {
    const container = document.getElementById('cardsContainer');
    const usuario = localStorage.getItem('usuario');
    const urlApi = `http://localhost:8080/api/continuarJornada/obterDigimons/${usuario}`;

    const digimonImages = {
        1: './images/rookies/agumon.jpg',
        2: './images/rookies/gabumon.jpg',
        3: './images/rookies/piyomon.jpg',
        4: './images/rookies/tentomon.jpg',
        5: './images/rookies/palmon.jpg',
        6: './images/rookies/gomamon.jpg',
        7: './images/rookies/patamon.jpg',
        default: './images/rookies/default.jpg',
    };

    function getImageSrc(idRookie) {
        return digimonImages[idRookie] || digimonImages.default;
    }

    function createDigimonCard(digimon) {
        const card = document.createElement('div');
        card.className = 'card';

        const imgSrc = getImageSrc(digimon.idRookie);

        card.innerHTML = `
            <img src="${imgSrc}" alt="${digimon.nome}">
            <div class="card-title">${digimon.nome}</div>
            <p>Nível: ${digimon.nivel}</p>
            <p>Bits: ${digimon.bits}</p>
            <div hidden>${digimon.id}</div>
            <button class="card-btn" data-id="${digimon.id}">Continuar</button>
        `;

        card.querySelector('.card-btn').addEventListener('click', function () {
            handleContinueButtonClick(digimon.id);
        });

        return card;
    }

    function handleContinueButtonClick(id) {
        console.log('ID do Digimon:', id);
        localStorage.setItem('idDigimon', id);
        window.location.href = 'status.html';
    }

    function fetchDigimons() {
        fetch(urlApi)
            .then(response => {
                if (!response.ok) {
                    throw new Error(`Erro na requisição: ${response.statusText}`);
                }
                return response.json();
            })
            .then(data => {
                data.forEach(digimon => {
                    const card = createDigimonCard(digimon);
                    container.appendChild(card);
                });
            })
            .catch(error => {
                console.error('Erro ao carregar os Digimons:', error);
            });
    }

    fetchDigimons();
});

function logout() {
    localStorage.removeItem('usuario');
    localStorage.removeItem('token');
    window.location.href = 'login.html';
}
