document.addEventListener('DOMContentLoaded', function () {
    const apiURL = 'http://localhost:8080/api/telaInvasao/carregaTelaInvasoes';

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
                    url_imagem_digimon: data.url_imagem_digimon,
                    pontos_vida_digimon: data.pontos_vida_digimon
                    // Adicionar mais atributos conforme necessário
                };
                console.log(dataInvasao);
                // Atualizar a interface com os dados recebidos
                displayPremiumContent(dataInvasao);
                atualizarImagemDigimon(dataInvasao);
                displayInvasorInfo(dataInvasao);
            })
            .catch(error => {
                console.error('Erro ao fazer requisição:', error);
            });
    }

    function displayInvasorInfo(dataInvasao) {
        const container = document.getElementById('invasor-container');
        container.innerHTML = ''; // Limpa o conteúdo existente

        let derrotadoPor;
        let derrotadoEm;
        if (derrotadoPor == null && derrotadoEm == null) {
            derrotadoPor = ' ';
            derrotadoEm = ' ';
        } else {
            derrotadoPor = dataInvasao.derrotadoPor;
            derrotadoEm = dataInvasao.derrotadoEm;
        }

        /*dataInvasao.derrotado = true;*/
        if (dataInvasao.derrotado) {
            let urlImg = dataInvasao.urlImg;
            if (urlImg.endsWith(".jpg")) {
                dataInvasao.urlImg = urlImg.replace(".jpg", "_derrotado.jpg");
            }
        }

        container.innerHTML = `
                            <div align="center">
                        <img src="${dataInvasao.urlImg}" class="img-bordered" width="180"
                            height="180">
                    </div>
                    <hr class="separador">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="box_largura_100">
                        <tbody>
                            <tr>
                                <!-- Campo hidden adicionado -->
                                <input type="hidden" id="hidden_inimigo" name="hidden_inimigo" value="${dataInvasao.idInvasor}">
                                <td>Nome do inimigo:</td>
                                <td colspan="2"><strong>${dataInvasao.nomeInvasor}</strong></td>
                            </tr>
                            <tr>
                                <td>Recompensa em Bits:</td>
                                <td colspan="2"><strong>${dataInvasao.recompensaBits}</strong></td>
                            </tr>
                            <tr>
                                <td>Recompensa em Diamantes:</td>
                                <td colspan="2"><strong>${dataInvasao.recompensaDiamantes}</strong></td>
                            </tr>
                            <tr>
                                <td>Energia Vital:</td>
                                <td colspan="2"><strong>[ ${dataInvasao.energiaVital} | 10000 ]</strong></td>
                            </tr>
                            <tr>
                                <td>Data da invasão:</td>
                                <td colspan="2"><strong>${dataInvasao.dataInvasao}</strong></td>
                            </tr>
                            <tr>
                                <td>Ataques sofridos:</td>
                                <td colspan="2"><strong>[ ${dataInvasao.ataquesSofridos} ]</strong></td>
                            </tr>
                            <tr>
                                <td>Derrotado por:</td>
                                <td colspan="2"><strong>${derrotadoPor}</strong></td>
                            </tr>
                            <tr>
                                <td>Derrotado em:</td>
                                <td colspan="2"><strong>${derrotadoEm}</strong></td>
                            </tr>
                    </table>    	`;

        if (!dataInvasao.derrotado) {
            container.innerHTML += `
                        <hr class="separador">
                        <div align="center">
                            <button id='buttonAtacar' type="button" class="button-confirmar">
                                Atacar
                            </button>
                        </div>
                    `;

            // Adicione o event listener para o botão após ele ser inserido no DOM
            document.getElementById('buttonAtacar').addEventListener('click', function () {
                if (dataInvasao.pontos_vida_digimon <= 0) {
                    Swal.fire({
                        title: 'Erro!',
                        text: 'Seu digimon está sem vida, não é possível atacar!',
                        icon: 'warning',
                        confirmButtonText: 'Tentar novamente'
                    });
                    return;
                }
                window.location.href = 'invasoes_combate.html';
            });

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

    carregarTelaInvasoes();

});