document.addEventListener('DOMContentLoaded', function () {
    const apiURL = 'http://localhost:8080/api/telaCacada/carregar';
    const apiResgateCacadaURL = 'http://localhost:8080/api/telaCacada/resgatarRecompensa';
    const apiIniciarCacadaURL = 'http://localhost:8080/api/telaCacada/iniciarCacada';

    let dataCacadas = {};
    function carregarTelaCacadas() {
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
                dataCacadas = {
                    url_imagem_digimon: data.url_imagem_digimon,
                    tempoDisponivel: data.tempoDisponivel,
                    status_premium: data.status_premium,
                    data_inicio_premium: data.dataInicio,
                    data_fim_premium: data.dataFim,
                    emAndamento: data.emAndamento,
                    horaResgate: data.horaResgate,
                    resgateDisponivel: data.resgateDisponivel,
                    nivel: data.nivel,
                    vida: data.vida,
                    energia: data.energia
                    // Adicionar mais atributos conforme necessário
                };
                // Atualizar a interface com os dados recebidos
                displayPremiumContent(dataCacadas);
                atualizarImagemDigimon(dataCacadas)
                /*carregarCaixaCacadaPorTempo(dataCacadas);*/
                displayLifeBar();
                displayEnergyBar();
            })
            .catch(error => {
                console.error('Erro ao fazer requisição:', error);
            });
    }

    function displayEnergyBar() {
        var energiaTotal = 100;
        const energiaAtual = dataCacadas.energia; // Pontos de energia atuais
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
        var vidaTotal = 50 * parseInt(dataCacadas.nivel);
        const vidaAtual = dataCacadas.vida; // Pontos de vida atuais
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

    let dataRecompensaCacada = {};
    function resgatarRecompensa(idDigimon) {
        // Dados que serão enviados no corpo da requisição
        const requestBody = localStorage.getItem('idDigimon');
        // Configurações da requisição
        const requestOptions = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(requestBody)
        };

        fetch(apiResgateCacadaURL, requestOptions)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Erro na rede, status: ' + response.status);
                }
                return response.json();
            })
            .then(data => {
                // Armazenar os dados recebidos da API
                dataRecompensaCacada = {
                    digimon: data.digimon,
                    recompensaBits: data.recompensaBits,
                    recompensaExp: data.recompensaExp,
                    recompensaFragmento: data.recompensaFragmento,
                    quantidadeFragmentos: data.quantidadeFragmentos
                    // Adicionar mais atributos conforme necessário
                };

                // Exibir os dados no modal
                const resultadoContent = document.getElementById('resultadoCacadaContent');
                resultadoContent.innerHTML = `
                            <hr class="separador">    
                            <p>Bits: ${dataRecompensaCacada.recompensaBits}</p>
                            <p>Experiência: ${dataRecompensaCacada.recompensaExp}</p>
                            <p>Fragmento: ${dataRecompensaCacada.recompensaFragmento}</p>
                            <p>Quantidade de Fragmentos: ${dataRecompensaCacada.quantidadeFragmentos}</p>
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

    let dataIniciarCacada = {};
    function iniciarCacada(tempoMinutos) {
        // Dados que serão enviados no corpo da requisição
        const requestBody = {
            idDigimon: localStorage.getItem('idDigimon'),
            minutos: tempoMinutos
        };
        // Configurações da requisição
        const requestOptions = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(requestBody)
        };

        fetch(apiIniciarCacadaURL, requestOptions)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Erro na rede, status: ' + response.status);
                }
                return response.json();
            })
            .then(data => {
                // Armazenar os dados recebidos da API
                dataIniciarCacada = {
                    id: data.id,
                    idDigimon: data.idDigimon,
                    data: data.data,
                    minutos: data.minutos,
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

    async function atualizarImagemDigimon(dataCacadas) {

        // Obtém o contêiner onde a imagem será adicionada
        const imageContainer = document.getElementById('image-container');

        // Remove qualquer imagem existente no contêiner
        while (imageContainer.firstChild) {
            imageContainer.removeChild(imageContainer.firstChild);
        }

        // Verifica se a URL da imagem está disponível
        if (dataCacadas.url_imagem_digimon) {
            const imgElement = document.createElement('img');
            imgElement.src = dataCacadas.url_imagem_digimon;
            imgElement.alt = "Imagem do Digimon";
            imgElement.width = 163;
            imgElement.height = 174;
            imgElement.className = "img-bordered";

            imageContainer.appendChild(imgElement);
        } else {
            console.error('URL da imagem não encontrada.');
        }
    }

    function carregarCaixaCacadaPorTempo(dataCacadas) {
        const container = document.getElementById('caixa-cacada-tempo');
        container.innerHTML = ''; // Limpa o conteúdo existente

        if (dataCacadas.emAndamento === true) {
            container.innerHTML = `
                        <div id="caixa_informacoes_bloq">
                            <p style="text-align: center;"><strong>Já existe uma caçada em andamento. 
                                <br>
                                Ela será encerrada em ${dataCacadas.horaResgate}</strong></p>
                        </div>
                        `;
            if (dataCacadas.resgateDisponivel === true) {
                container.innerHTML += `
                <hr class="separador">
                            <div align="center" style="margin-right: 10px; margin-top: 10px;">
                                <button type="button" class="button-confirmar" id="button-resgatar-recompensa-cacada">
                                    Resgatar
                                </button>
                            </div>
                            `;
            }

        } else {
            if (dataCacadas.tempoDisponivel === 0) {
                container.innerHTML = `
                            <div id="caixa_informacoes_bloq">
                                <p style="text-align: center;">
                                    Você já não tem mais tempo disponível hoje. Volte outro dia.</p>
                            </div>
                            `;
            } else {
                container.innerHTML = `
                            <p style="text-align: justify;">Aqui você poderá caçar digimons inimigos durante um período
                                máximo de 60 minutos por dia e poderá ganhar bits e experiência (jogadores premium tem
                                120
                                minutos por dia).</p>

                            <br>
                            <p style="text-align: justify;"></p><strong>Dica</strong>: Procure fazer suas caçadas de 10
                            em
                            10 minutos para aumentar sua renda em bits e
                            experiência!</p>

                            <hr class="separador">
                            <table border="0" cellspacing="0" cellpadding="0">
                                <tbody>
                                    <fieldset>
                                        <legend>&nbsp;Caçar por Tempo</legend>
                                        <div class="cacada-box">
                                            Tempo em Minutos
                                            <br>
                                            <br>
                                            <select name="tempoCacada" id="tempoCacada" class="select-cacada">
                                                <option value="10">10</option>
                                                <option value="20">20</option>
                                                <option value="30">30</option>
                                                <option value="40">40</option>
                                                <option value="50">50</option>
                                                <option value="60">60</option>
                                            </select>

                                            <span class="descricao-campos">
                                                <br>
                                                <br>
                                                Selecione a quantidade de minutos que deseja usar para caçar!
                                            </span>
                                            </div>  
                                    </fieldset>
                                </tbody>
                            </table>
                            <hr class="separador">
                            <div align="center" style="margin-right: 10px; margin-top: 10px;">
                                <button type="button" class="button-confirmar" id="button-iniciar-cacada">
                                    Iniciar
                                </button>
                            </div>
                                `;
            }
        }
    }

    function displayPremiumContent(dataCacadas) {

        const container = document.getElementById('caixa-informacoes-esquerda');
        container.innerHTML = ''; // Limpa o conteúdo existente

        if (dataCacadas.status_premium === 'Ativo') {
            container.innerHTML = `
                    <h3 class="titulo-caixa">Premium</h3>
                    <hr class="separador">
                    <div id="caixa-premium">
                        <p>De:</p>
                        <p id="premium_inicio">${dataCacadas.data_inicio_premium}</p>
                        <p>Até:</p>
                        <p id="premium_fim">${dataCacadas.data_fim_premium}</p>
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

    // Event listener para o botão de iniciar caçada
    document.addEventListener('click', function (event) {
        if (event.target && event.target.id === 'button-iniciar-cacada') {
            if (dataCacadas.tempoDisponivel < parseInt(document.querySelector("#tempoCacada").value)) {
                // Exibir os dados no modal
                const erroModalContent = document.getElementById('erroModalContent');
                erroModalContent.innerHTML = `
                            <hr class="separador">    
                            <p>Você não tem tempo disponível suficiente para o tempo de caçada escolhido.</p>
                            <p>Seu tempo disponível é de <strong>${dataCacadas.tempoDisponivel}</strong> minutos.</p>
                            <hr class="separador">
                            `;

                // Abrir o modal
                const modal = document.getElementById('erroModal');
                modal.style.display = 'block';

            } else {
                const tempoMinutos = parseInt(document.querySelector("#tempoCacada").value)
                iniciarCacada(tempoMinutos);
                location.reload();
            }
        }
        if (event.target && event.target.id === 'button-resgatar-recompensa-cacada') {
            const idDigimon = '3';
            resgatarRecompensa(idDigimon);
        }
        if (event.target && event.target.className === 'close' || event.target.id === 'fecharModal') {
            const modal = document.getElementById('resultadoModal');
            const erroModal = document.getElementById('erroModal');
            modal.style.display = 'none';
            erroModal.style.display = 'none';

            location.reload();  // Recarregar a página depois que o modal for fechado

        }
    });

    carregarTelaCacadas();

});

