document.addEventListener('DOMContentLoaded', function () {
    const apiURL = 'http://localhost:8080/api/telaInvasao/carregaTelaInvasoes';
    const apiURLAtaque = 'http://localhost:8080/api/combate/combateInvasao';

    let dataInvasao = {};
    function carregarTelaInvasoes() {
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

        fetch(apiURL, requestOptions)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Erro na rede, status: ' + response.status);
                }
                return response.json();
            })
            .then(data => {
                // Armazenar os dados recebidos da API
                dataInvasao = {
                    idInvasor: data.idInvasor,
                    nomeInvasor: data.nomeInvasor,
                    urlImg: data.urlImg,
                    recompensaBits: data.recompensaBits,
                    recompensaDiamantes: data.recompensaDiamantes,
                    energiaVital: data.energiaVital,
                    dataInvasao: data.dataInvasao,
                    ataquesSofridos: data.ataquesSofridos,
                    derrotadoPor: data.derrotadoPor,
                    derrotadoEm: data.derrotadoEm,
                    derrotado: data.derrotado,
                    status_premium: data.status_premium,
                    data_inicio_premium: data.data_inicio_premium,
                    data_fim_premium: data.data_fim_premium,
                    url_imagem_digimon: data.url_imagem_digimon
                    // Adicionar mais atributos conforme necessário
                };
                console.log(dataInvasao);
                // Atualizar a interface com os dados recebidos
                displayPremiumContent(dataInvasao);
                atualizarImagemDigimon(dataInvasao);
            })
            .catch(error => {
                console.error('Erro ao fazer requisição:', error);
            });
    }

    let dataAtaque = {};
    function carregarLogAtaqueInvasao() {
        // Dados que serão enviados no corpo da requisição
        const requestBody = {
            idDigimonAtacante: localStorage.getItem('idDigimon')
        };
        // Configurações da requisição
        const requestOptions = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(requestBody)
        };

        fetch(apiURLAtaque, requestOptions)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Erro na rede, status: ' + response.status);
                }
                return response.json();
            })
            .then(data => {
                // Armazenar os dados recebidos da API
                dataAtaque = {
                    log: data.log,
                    totalDanoAtacante: data.totalDanoAtacante,
                    totalDanoDefensor: data.totalDanoDefensor,
                    vidaRestanteAtacante: data.vidaRestanteAtacante,
                    vidaRestanteDefensor: data.vidaRestanteDefensor
                    // Adicionar mais atributos conforme necessário
                };
                const log = dataAtaque.log;
                console.log(dataAtaque);
                displayCombatLog(log);
                displayResultado(dataAtaque);
            })
            .catch(error => {
                console.error('Erro ao fazer requisição:', error.message);
            });
    }

    // Função para processar e exibir o log de combate
    function displayCombatLog(log) {
        const tableBody = document.querySelector("#combatLogTable tbody");
        const logLines = log.split("\n");

        logLines.forEach((line, index) => {
            const row = document.createElement("tr");
            const cell = document.createElement("td");
            cell.textContent = line;

            // Aplicar classes alternadas para as linhas
            if (index % 2 === 0) {
                row.classList.add("table-row-even");
            } else {
                row.classList.add("table-row-odd");
            }

            row.appendChild(cell);
            tableBody.appendChild(row);
        });
    }

    function displayResultado(dataAtaque) {

        const container = document.getElementById('resultado-invasao');
        container.innerHTML = ''; // Limpa o conteúdo existente

        /*
        dataAtaque.vidaRestanteAtacante = 100;
        dataAtaque.vidaRestanteDefensor = 0;
        */

        if (dataAtaque.vidaRestanteAtacante <= 0) {
            container.innerHTML = `
                <div id="caixa_informacoes_bloq">
                        <p style="text-align: center;"><strong>O digimon atacante foi derrotado.<br>
                        O digimon atacante causou ${dataAtaque.totalDanoAtacante} de dano.<br>
                        Restou ${dataAtaque.vidaRestanteDefensor} de vida ao defensor.</p><br>
                        <hr><br>
                        <p style="text-align: justify;">O digimon atacante ganhou 10 pontos de experiência.</strong></p>
                    </div>
                    `;

        }

        if (dataAtaque.vidaRestanteDefensor <= 0) {
            container.innerHTML = `
                <div id="caixa_informacoes_bloq">
                        <p style="text-align: center;"><strong>O digimon defensor foi derrotado.<br>
                        Restou ${dataAtaque.vidaRestanteDefensor} de vida ao defensor.</p><br>
                        O digimon atacante causou ${dataAtaque.totalDanoAtacante} de dano.<br>
                        <hr><br>
                        <p style="text-align: justify;">O digimon atacante ganhou 100 pontos de experiência.<br>
                        O digimon atacante ganhou 100 diamantes.<br>
                        O digimon atacante ganhou 100.000 Bits.</strong></p>
                    </div>
                    `;
        }
    }

    function displayPremiumContent(dataInvasao) {

        const container = document.getElementById('caixa-informacoes-esquerda');
        container.innerHTML = ''; // Limpa o conteúdo existente

        if (dataInvasao.status_premium === 'Ativo') {
            container.innerHTML = `
                    <h3 class="titulo-caixa">Premium</h3>
                    <hr class="separador">
                    <div id="caixa-premium">
                        <p>De:</p>
                        <p id="premium_inicio">${dataInvasao.data_inicio_premium}</p>
                        <p>Até:</p>
                        <p id="premium_fim">${dataInvasao.data_fim_premium}</p>
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

    async function atualizarImagemDigimon(dataInvasao) {

        // Obtém o contêiner onde a imagem será adicionada
        const imageContainer = document.getElementById('image-container');

        // Remove qualquer imagem existente no contêiner
        while (imageContainer.firstChild) {
            imageContainer.removeChild(imageContainer.firstChild);
        }

        // Verifica se a URL da imagem está disponível
        if (dataInvasao.url_imagem_digimon) {
            const imgElement = document.createElement('img');
            imgElement.src = dataInvasao.url_imagem_digimon;
            imgElement.alt = "Imagem do Digimon";
            imgElement.width = 163;
            imgElement.height = 174;
            imgElement.className = "img-bordered";

            imageContainer.appendChild(imgElement);
        } else {
            console.error('URL da imagem não encontrada.');
        }
    }

    document.getElementById('toggleLogButton').addEventListener('click', function () {
        const logContainer = document.getElementById('log-invasao-container');
        const isVisible = logContainer.style.display === 'block';

        logContainer.style.display = isVisible ? 'none' : 'block';
        this.textContent = isVisible ? 'Mostrar Log de Combate' : 'Esconder Log de Combate';
    });

    
    document.getElementById('botaoRetornar').addEventListener('click', function () {
        window.location.href = 'invasoes.html';
    });

    carregarTelaInvasoes();
    carregarLogAtaqueInvasao();


});