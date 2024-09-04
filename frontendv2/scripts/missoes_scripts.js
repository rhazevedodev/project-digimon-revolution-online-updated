document.addEventListener('DOMContentLoaded', function () {
    const apiURL = 'http://localhost:8080/api/telaMissoes/carregar';
    const apiIniciarMissaoURL = 'http://localhost:8080/api/missao/iniciarMissao';
    const apiResgatarRecompensaURL = 'http://localhost:8080/api/missao/pegarRecompensa';


    let dataMissoes = {};
    function carregarTelaMissoes() {
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
                dataMissoes = {
                    url_imagem_digimon: data.url_imagem_digimon,
                    nivel: data.nivel,
                    status_premium: data.status_premium,
                    data_inicio_premium: data.data_inicio_premium,
                    data_fim_premium: data.data_fim_premium,
                    emAndamento: data.emAndamento,
                    horaResgate: data.horaResgate,
                    resgateDisponivel: data.resgateDisponivel,
                    nivel: data.nivel,
                    vida: data.vida,
                    energia: data.energia
                    // Adicionar mais atributos conforme necessário
                };
                // Atualizar a interface com os dados recebidos
                displayPremiumContent(dataMissoes);
                atualizarImagemDigimon(dataMissoes);
                carregarCaixaMissao(dataMissoes);
                displayLifeBar();
                displayEnergyBar();
            })
            .catch(error => {
                console.error('Erro ao fazer requisição:', error);
            });
    }

    function displayEnergyBar() {
        var energiaTotal = 100;
        const energiaAtual = dataMissoes.energia; // Pontos de energia atuais
        const energiaMaxima = energiaTotal; // Pontos de energia máximos


        // Calcula a largura da barra de vida com base na porcentagem
        const energyBarWidth = (energiaAtual / energiaMaxima) * 100;
        const energyBar = document.getElementById('energy-bar');
        energyBar.style.width = energyBarWidth + '%';

        const energyBarText = document.getElementById('energy-bar-text');
        energyBarText.textContent = `${energiaAtual}/${energiaMaxima}`;

        /*
                document.getElementById('life-bar').style.width = porcentagemVida + '%';
                document.getElementById('life-bar-text').textContent = `${vidaAtual}/${vidaMaxima}`;
                */
    }


    function displayLifeBar() {
        var vidaTotal = 50 * parseInt(dataMissoes.nivel);
        const vidaAtual = dataMissoes.vida; // Pontos de vida atuais
        const vidaMaxima = vidaTotal; // Pontos de vida máximos


        // Calcula a largura da barra de vida com base na porcentagem
        const lifeBarWidth = (vidaAtual / vidaMaxima) * 100;
        const lifeBar = document.getElementById('life-bar');
        lifeBar.style.width = lifeBarWidth + '%';

        const lifeBarText = document.getElementById('life-bar-text');
        lifeBarText.textContent = `${vidaAtual}/${vidaMaxima}`;

        /*
                document.getElementById('life-bar').style.width = porcentagemVida + '%';
                document.getElementById('life-bar-text').textContent = `${vidaAtual}/${vidaMaxima}`;
                */
    }


    let dataIniciarMissao = {};
    function iniciarMissao(idMissao, horas) {
        // Dados que serão enviados no corpo da requisição
        const requestBody = {
            idDigimon: '3',
            idMissao: parseInt(idMissao),
            qtHoras: parseInt(horas)
        };
        // Configurações da requisição
        const requestOptions = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(requestBody)
        };

        fetch(apiIniciarMissaoURL, requestOptions)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Erro na rede, status: ' + response.status);
                }
                return response.json();
            })
            .then(data => {
                // Armazenar os dados recebidos da API
                dataIniciarMissao = {
                    id: data.id,
                    idDigimon: data.idDigimon,
                    idMissao: data.idMissao,
                    qtHoras: data.qtHoras,
                    data: data.data,
                    horaResgateDisponivel: data.horaResgateDisponivel,
                    recompensaResgatada: data.recompensaResgatada,
                    ultimaAlteracao: data.ultimaAlteracao
                    // Adicionar mais atributos conforme necessário
                };

                location.reload();
            })
            .catch(error => {
                console.error('Erro ao fazer requisição:', error);
            });
    }

    let dataResgatarRecompensa = {};
    function resgatarRecompensaMissao(idDigimon) {
        // Dados que serão enviados no corpo da requisição
        const requestBody = idDigimon;
        // Configurações da requisição
        const requestOptions = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(requestBody)
        };

        fetch(apiResgatarRecompensaURL, requestOptions)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Erro na rede, status: ' + response.status);
                }
                return response.json();
            })
            .then(data => {
                dataResgatarRecompensa = {
                    digimon: data.digimon,
                    recompensaBits: data.recompensaBits,
                    recompensaFragmento: data.recompensaFragmento,
                    quantidadeFragmentos: data.quantidadeFragmentos
                    // Adicionar mais atributos conforme necessário
                };

                // Exibir os dados no modal
                const resultadoContent = document.getElementById('resultadoMissaoContent');
                resultadoContent.innerHTML = `
                                            <hr class="separador">    
                                            <p>Bits: ${dataResgatarRecompensa.recompensaBits}</p>
                                            <p>Fragmento: ${dataResgatarRecompensa.recompensaFragmento}</p>
                                            <p>Quantidade de Fragmentos: ${dataResgatarRecompensa.quantidadeFragmentos}</p>
                                            <hr class="separador">
                                            `;

                // Abrir o modal
                const modal = document.getElementById('resultadoModal');
                modal.style.display = 'block';
            })
            .catch(error => {
                console.error('Erro ao fazer requisição:', error);
            });
    }

    async function atualizarImagemDigimon(dataMissoes) {

        // Obtém o contêiner onde a imagem será adicionada
        const imageContainer = document.getElementById('image-container');

        // Remove qualquer imagem existente no contêiner
        while (imageContainer.firstChild) {
            imageContainer.removeChild(imageContainer.firstChild);
        }

        // Verifica se a URL da imagem está disponível
        if (dataMissoes.url_imagem_digimon) {
            const imgElement = document.createElement('img');
            imgElement.src = dataMissoes.url_imagem_digimon;
            imgElement.alt = "Imagem do Digimon";
            imgElement.width = 163;
            imgElement.height = 174;
            imgElement.className = "img-bordered";

            imageContainer.appendChild(imgElement);
        } else {
            console.error('URL da imagem não encontrada.');
        }
    }

    function displayPremiumContent(dataMissoes) {

        const container = document.getElementById('caixa-informacoes-esquerda');
        container.innerHTML = ''; // Limpa o conteúdo existente

        if (dataMissoes.status_premium === 'Ativo') {
            container.innerHTML = `
                    <h3 class="titulo-caixa">Premium</h3>
                    <hr class="separador">
                    <div id="caixa-premium">
                        <p>De:</p>
                        <p id="premium_inicio">${dataMissoes.data_inicio_premium}</p>
                        <p>Até:</p>
                        <p id="premium_fim">${dataMissoes.data_fim_premium}</p>
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

    function carregarCaixaMissao(dataMissoes) {
        const container = document.getElementById('caixa-missao');
        container.innerHTML = ''; // Limpa o conteúdo existente

        if (dataMissoes.emAndamento) {
            container.innerHTML = `
                        <div id="caixa_informacoes_bloq">
                            <p style="text-align: center;"><strong>Já existe uma missão em andamento. 
                                <br>
                                Ela será encerrada em ${dataMissoes.horaResgate}</strong></p>
                        </div>
                        `;
            if (dataMissoes.resgateDisponivel) {
                container.innerHTML += `
                <hr class="separador">
                            <div align="center" style="margin-right: 10px; margin-top: 10px;">
                                <button type="button" class="button-resgatar" id="button-resgatar-recompensa-missao">
                                    Resgatar
                                </button>
                            </div>
                            `;
            }
        } else {
            container.innerHTML = `
                                

                        <table border="0" cellspacing="1" cellpadding="0">
                            <tbody>
                                <tr>
                                    <td width="450">
                                        <div class="center-content">
                                            Escolha um tier:
                                            <select name="tier" id="tier" class="styled-select" disabled>
                                                <option value="1">Tier 1</option>
                                                <option value="2">Tier 2</option>
                                                <option value="3">Tier 3</option>
                                                <option value="4">Tier 4</option>
                                                <option value="5">Tier 5</option>
                                            </select>
                                        </div>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <hr class="separador">
                        <table border="0" cellspacing="1" cellpadding="0" class="box_largura_100">
                            <tbody>
                                <tr>
                                    <td width="200">Missão:</td>
                                    <td>
                                        <div style="margin-left: 15px;">Nv Minimo:</div>
                                    </td>
                                    <td>
                                        <div>Bits/Hora:</div>
                                    </td>
                                    <td>
                                        <div>Duração:</div>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <hr class="separador">
                        <table border="0" cellspacing="" cellpadding="0">
                            <tbody>
                                <tr>
                                    <td><img src="/imagens/areas/regiao_casteloDoDevimon.jpg" alt="MissaoCasteloDevimon"
                                            width="190" height="80" data-id="6" class="missao-img">
                                    </td>
                                    <td>
                                        <div style="margin-left: 45px;">
                                            <strong>50</strong>
                                        </div>
                                    </td>
                                    <td>
                                        <div style="margin-left: 75px;">
                                            <strong>6.000</strong>
                                        </div>
                                    </td>
                                    <td>
                                        <div style="margin-left: 40px;">
                                            <select name="horasMissao" id="horasMissao" class="select-horas">
                                                <option value="1">1</option>
                                                <option value="2">2</option>
                                                <option value="3">3</option>
                                                <option value="4">4</option>
                                                <option value="5">5</option>
                                                <option value="6">6</option>
                                                <option value="7">7</option>
                                                <option value="8">8</option>
                                                <option value="9">9</option>
                                                <option value="10">10</option>
                                                <option value="11">11</option>
                                                <option value="12">12</option>
                                            </select>
                                            <br> <!-- Adicionando quebra de linha -->
                                            <button type="button" class="button-confirmar">
                                                Confirmar
                                            </button>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td><img src="/imagens/areas/regiao_cidadeDosBrinquedos.jpg" alt="MissaoCidadeBrinquedos"
                                            width="190" height="80" data-id="5" class="missao-img">
                                    </td>
                                    <td>
                                        <div style="margin-left: 45px;">
                                            <strong>40</strong>
                                        </div>
                                    </td>
                                    <td>
                                        <div style="margin-left: 75px;">
                                            <strong>4.000</strong>
                                        </div>
                                    </td>
                                    <td>
                                        <div style="margin-left: 40px;">
                                            <select name="horasMissao" id="horasMissao" class="select-horas">
                                                <option value="1">1</option>
                                                <option value="2">2</option>
                                                <option value="3">3</option>
                                                <option value="4">4</option>
                                                <option value="5">5</option>
                                                <option value="6">6</option>
                                                <option value="7">7</option>
                                                <option value="8">8</option>
                                                <option value="9">9</option>
                                                <option value="10">10</option>
                                                <option value="11">11</option>
                                                <option value="12">12</option>
                                            </select>
                                            <br> <!-- Adicionando quebra de linha -->
                                            <button type="button" class="button-confirmar">
                                                Confirmar
                                            </button>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td><img src="/imagens/areas/regiao_monteMiharashi.jpg" alt="MissaoMonteMiharashi"
                                            width="190" height="80" data-id="4" class="missao-img">
                                    </td>
                                    <td>
                                        <div style="margin-left: 45px;">
                                            <strong>30</strong>
                                        </div>
                                    </td>
                                    <td>
                                        <div style="margin-left: 75px;">
                                            <strong>2.000</strong>
                                        </div>
                                    </td>
                                    <td>
                                        <div style="margin-left: 40px;">
                                            <select name="horasMissao" id="horasMissao" class="select-horas">
                                                <option value="1">1</option>
                                                <option value="2">2</option>
                                                <option value="3">3</option>
                                                <option value="4">4</option>
                                                <option value="5">5</option>
                                                <option value="6">6</option>
                                                <option value="7">7</option>
                                                <option value="8">8</option>
                                                <option value="9">9</option>
                                                <option value="10">10</option>
                                                <option value="11">11</option>
                                                <option value="12">12</option>
                                            </select>
                                            <br> <!-- Adicionando quebra de linha -->
                                            <button type="button" class="button-confirmar">
                                                Confirmar
                                            </button>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td><img src="/imagens/areas/regiao_freezeLand.jpg" alt="MissaoFreezeLand" width="190"
                                            height="80" data-id="3" class="missao-img">
                                    </td>
                                    <td>
                                        <div style="margin-left: 45px;">
                                            <strong>20</strong>
                                        </div>
                                    </td>
                                    <td>
                                        <div style="margin-left: 75px;">
                                            <strong>1.000</strong>
                                        </div>
                                    </td>
                                    <td>
                                        <div style="margin-left: 40px;">
                                            <select name="horasMissao" id="horasMissao" class="select-horas">
                                                <option value="1">1</option>
                                                <option value="2">2</option>
                                                <option value="3">3</option>
                                                <option value="4">4</option>
                                                <option value="5">5</option>
                                                <option value="6">6</option>
                                                <option value="7">7</option>
                                                <option value="8">8</option>
                                                <option value="9">9</option>
                                                <option value="10">10</option>
                                                <option value="11">11</option>
                                                <option value="12">12</option>
                                            </select>
                                            <br> <!-- Adicionando quebra de linha -->
                                            <button type="button" class="button-confirmar">
                                                Confirmar
                                            </button>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td><img src="/imagens/areas/regiao_costa.jpg" alt="MissaoRegiaoCosta" width="190"
                                            height="80" data-id="2" class="missao-img">
                                    </td>
                                    <td>
                                        <div style="margin-left: 45px;">
                                            <strong>10</strong>
                                        </div>
                                    </td>
                                    <td>
                                        <div style="margin-left: 80px;">
                                            <strong>500</strong>
                                        </div>
                                    </td>
                                    <td>
                                        <div style="margin-left: 40px;">
                                            <select name="horasMissao" id="horasMissao" class="select-horas">
                                                <option value="1">1</option>
                                                <option value="2">2</option>
                                                <option value="3">3</option>
                                                <option value="4">4</option>
                                                <option value="5">5</option>
                                                <option value="6">6</option>
                                                <option value="7">7</option>
                                                <option value="8">8</option>
                                                <option value="9">9</option>
                                                <option value="10">10</option>
                                                <option value="11">11</option>
                                                <option value="12">12</option>
                                            </select>
                                            <br> <!-- Adicionando quebra de linha -->
                                            <button type="button" class="button-confirmar">
                                                Confirmar
                                            </button>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td><img src="/imagens/areas/regiao_tropicalJungle.jpg" alt="MissaoTropicalJungle"
                                            width="190" height="80" data-id="1" class="missao-img">
                                    </td>
                                    <td>
                                        <div style="margin-left: 46px;">
                                            <strong>1</strong>
                                        </div>
                                    </td>
                                    <td>
                                        <div style="margin-left: 80px;">
                                            <strong>250</strong>
                                        </div>
                                    </td>
                                    <td>
                                        <div style="margin-left: 40px;">
                                            <select name="horasMissao" class="select-horas">
                                                <option value="1">1</option>
                                                <option value="2">2</option>
                                                <option value="3">3</option>
                                                <option value="4">4</option>
                                                <option value="5">5</option>
                                                <option value="6">6</option>
                                                <option value="7">7</option>
                                                <option value="8">8</option>
                                                <option value="9">9</option>
                                                <option value="10">10</option>
                                                <option value="11">11</option>
                                                <option value="12">12</option>
                                            </select>
                                            <br> <!-- Adicionando quebra de linha -->
                                            <button type="button" class="button-confirmar">
                                                Confirmar
                                            </button>
                                        </div>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    `;

        }

        const buttons = document.querySelectorAll(".button-confirmar");

        buttons.forEach(button => {
            button.addEventListener("click", function () {
                const tr = button.closest("tr");
                const img = tr.querySelector(".missao-img");
                const select = tr.querySelector(".select-horas");

                const id = img.getAttribute("data-id");
                const horas = select.value;

                if (id && horas) {
                    iniciarMissao(id, horas);
                    // Aqui você pode fazer o que quiser com os valores, como enviar uma solicitação AJAX
                } else {
                    console.error("Erro ao obter ID da missão ou horas escolhidas");
                }
            });
        });

        const resgatarButton = document.getElementById('button-resgatar-recompensa-missao');
        if (resgatarButton) {
            resgatarButton.addEventListener('click', function () {
                const idDigimon = '3'; // ID do Digimon
                resgatarRecompensaMissao(idDigimon);
            });
        }

        document.addEventListener('click', function (event) {
            if (event.target && event.target.className === 'close' || event.target.id === 'fecharModal') {
                const modal = document.getElementById('resultadoModal');
                modal.style.display = 'none';
                location.reload();  // Recarregar a página depois que o modal for fechado
            }
        });

    }

    carregarTelaMissoes();

});

