document.addEventListener("DOMContentLoaded", function () {
    const container = document.getElementById('cardsContainer');

    const urlApi = 'http://localhost:8080/api/digimon/obterDigimons/' + localStorage.getItem('usuario');

    // Faz a requisição à API para obter os Digimons
    fetch(urlApi)
        .then(response => response.json())
        .then(data => {
            data.forEach(digimon => {
                console.log(digimon);
                const card = document.createElement('div');
                card.className = 'card';

                const idRookie = digimon.idRookie;
                let imgSrc;

                switch (idRookie) {
                    case 1:
                        imgSrc = './images/rookies/agumon.jpg';
                        break;
                    case 2:
                        imgSrc = './images/rookies/gabumon.jpg';
                        break;
                    case 3:
                        imgSrc = './images/rookies/piyomon.jpg';
                        break;
                    case 4:
                        imgSrc = './images/rookies/tentomon.jpg';
                        break;
                    case 5:
                        imgSrc = './images/rookies/palmon.jpg';
                        break;
                    case 6:
                        imgSrc = './images/rookies/gomamon.jpg';
                        break;
                    case 7:
                        imgSrc = './images/rookies/patamon.jpg';
                        break;
                    // Adicione mais casos conforme necessário
                    default:
                        imgSrc = './images/rookies/default.jpg'; // Caminho para uma imagem padrão caso o idRookie não seja reconhecido
                }

                card.innerHTML = `
                    <img src="${imgSrc}" alt="${digimon.nome}">
                    <div class="card-title">${digimon.nome}</div>
                    <p>Nível: ${digimon.nivel}</p>
                    <p>Bits: ${digimon.bits}</p>
                    <button class="card-btn">Continuar</button>
                `;
                container.appendChild(card);
            });
        })
        .catch(error => {
            console.error('Erro ao carregar os Digimons:', error);
        });
});

function logout() {
    // Lógica para realizar logout (exemplo)
    localStorage.removeItem('usuario');
    localStorage.removeItem('token');
    window.location.href = 'login.html'; // Redireciona para a página de login
}