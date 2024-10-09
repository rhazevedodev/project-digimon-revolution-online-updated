document.addEventListener("DOMContentLoaded", function () {
    const container = document.getElementById('cardsContainer');
    const usuario = localStorage.getItem('usuario');
    const urlApi = `http://localhost:8080/api/continuarJornada/obterDigimons/${usuario}`;

    function createDigimonCard(digimon) {
        const card = document.createElement('div');
        card.className = 'card';

        const imgSrc = digimon.urlImagemDigimon;

        card.innerHTML = `
            <img src="${imgSrc}" alt="${dataContinuarJornada.digimon.nome}">
            <div class="card-title">${dataContinuarJornada.digimon.nome}</div>
            <p>Energia: ${dataContinuarJornada.digimon.atributos.pontosEnergia}</p>
            <p>Vida: ${dataContinuarJornada.digimon.atributos.pontosVida}</p>
            <p>Nível: ${dataContinuarJornada.digimon.nivel}</p>
            <p>Bits: ${dataContinuarJornada.digimon.bits}</p>
            <div hidden>${dataContinuarJornada.digimon.id}</div>
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

    let dataContinuarJornada = {};
    function fetchDigimons() {
        fetch(urlApi)
            .then(response => {
                if (!response.ok) {
                    throw new Error(`Erro na requisição: ${response.statusText}`);
                }
                return response.json();
            })
            .then(data => {
                data.forEach(data => {
                    dataContinuarJornada = data;
                    const card = createDigimonCard(dataContinuarJornada);
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
