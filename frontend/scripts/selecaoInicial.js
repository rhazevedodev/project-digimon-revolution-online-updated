const cardsData = [
    { src: "./images/rookies/agumon.jpg", alt: "Agumon", name: "Agumon" },
    { src: "./images/rookies/gabumon.jpg", alt: "Gabumon", name: "Gabumon" },
    { src: "./images/rookies/gomamon.jpg", alt: "Gomamon", name: "Gomamon" },
    { src: "./images/rookies/palmon.jpg", alt: "Palmon", name: "Palmon" },
    { src: "./images/rookies/patamon.jpg", alt: "Patamon", name: "Patamon" },
    { src: "./images/rookies/piyomon.jpg", alt: "Piyomon", name: "Piyomon" },
    { src: "./images/rookies/tentomon.jpg", alt: "Tentomon", name: "Tentomon" }
];

document.addEventListener("DOMContentLoaded", function () {

    const container = document.getElementById('cardsContainer');
    const template = document.querySelector('.card-template');

    cardsData.forEach(card => {
        const cardClone = template.cloneNode(true);
        cardClone.style.display = ''; // Torna visível
        cardClone.querySelector('.card-img-top').src = card.src;
        cardClone.querySelector('.card-img-top').alt = card.alt;
        cardClone.querySelector('.text-center').textContent = card.name;
        const button = cardClone.querySelector('.btn-confirm');
        button.id = `openModalBtn${card.name}`;
        button.addEventListener('click', function () {
            const modal = document.getElementById(`myModal${card.name}`);
            modal.style.display = "block";
        });
        container.appendChild(cardClone);
    });

    // Fecha modais ao clicar no botão de fechar ou fora do modal
    const modals = document.querySelectorAll('.modal');
    modals.forEach(modal => {
        const span = modal.querySelector('.close');
        span.addEventListener('click', function () {
            modal.style.display = "none";
        });

        window.addEventListener('click', function (event) {
            if (event.target === modal) {
                modal.style.display = "none";
            }
        });
    });

    const nicknameModal = document.getElementById("nicknameModal");
    const nicknameInput = document.getElementById("nicknameInput");
    const confirmNicknameBtn = document.getElementById("confirmNicknameBtn");
    let chosenDigimon = '';

    // Adiciona listener aos botões de escolha dentro dos modais
    const chooseButtons = document.querySelectorAll('.choose-button');
    chooseButtons.forEach(button => {
        button.addEventListener('click', function (event) {
            event.preventDefault();
            const modal = button.closest('.modal');
            modal.style.display = "none";

            // Armazena o Digimon escolhido
            chosenDigimon = button.textContent.split(' ')[1];
            localStorage.setItem('chosenDigimon', chosenDigimon);
            
            // Exibe o modal de apelido
            nicknameModal.style.display = "block";
        });
    });

    // Fecha o modal de apelido
    document.querySelector('.close-nickname-modal').addEventListener('click', function () {
        nicknameModal.style.display = "none";
    });

    // Confirmação do apelido
    confirmNicknameBtn.addEventListener('click', function () {
        const nickname = nicknameInput.value.trim();
        if (nickname) {
            nicknameModal.style.display = "none";
            showCustomAlert(`Você escolheu ${chosenDigimon} e o apelidou de ${nickname}!`);
            localStorage.setItem('nickname', nickname);
            selecionarDigimon();
        } else {
            showCustomAlert('Por favor, insira um apelido para seu Digimon.');
        }
    });

    function showCustomAlert(message) {
        const customAlertModal = document.createElement('div');
        customAlertModal.classList.add('custom-alert-modal');
        customAlertModal.innerHTML = `
            <div class="custom-alert-content">
                <span class="custom-alert-message">${message}</span><br><br>
                <button class="custom-alert-button">OK</button>
            </div>
        `;

        document.body.appendChild(customAlertModal);

        const alertButton = customAlertModal.querySelector('.custom-alert-button');
        alertButton.addEventListener('click', function () {
            customAlertModal.remove();
        });
    }

    const apiURL = 'http://localhost:8080/api/digimon/selecaoInicialv2';

    function selecionarDigimon() {
        // Dados que serão enviados no corpo da requisição
        const requestBody = {
            "nomeUsuario": localStorage.getItem('usuario'),
            "nomeDigimon": localStorage.getItem('chosenDigimon'),
            "apelidoDigimon": localStorage.getItem('nickname')
        };

        const requestOptions = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(requestBody)
        };

        fetch(apiURL, requestOptions)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Erro na rede, status: ' + response.status);
                }
                return response.json();
            })
            .then(data => {
                // Redirecionar para outra página ou realizar outra ação
                //localStorage.removeItem('usuario');
                localStorage.removeItem('chosenDigimon');
                localStorage.removeItem('nickname');

                window.location.href = 'continuarJornada.html';
                console.log("DIGIMON CADASTRADO COM SUCESSO");
            })
            .catch(error => {
                console.log("ERRO SELECAO DIGIMON: "+error);
            });
    }

});
