document.addEventListener('DOMContentLoaded', function () {
    const apiURL = 'http://localhost:8080/api/telaStatus/carregarInformacoes';

    let dataInformacoesStatus = {};

    function carregarTelaStatus() {
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

        // Fazer a requisição à API
        fetch(apiURL, requestOptions)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Erro na rede, status: ' + response.status);
                }
                return response.json();
            })
            .then(data => {
                // Armazenar os dados recebidos da API
                dataInformacoesStatus = {
                    url_imagem_digimon: data.url_imagem_digimon,
                    jogo_desde: data.jogo_desde,
                    indicacao: data.indicacao,
                    reserva_bits: data.reserva_bits,
                    reserva_diamantes: data.reserva_diamantes,
                    apelido_digimon: data.apelido_digimon,
                    tier: data.tier,
                    digimon_estagio_1: data.digimon_estagio_1,
                    digimon_estagio_2: data.digimon_estagio_2,
                    digimon_estagio_3: data.digimon_estagio_3,
                    digimon_estagio_4: data.digimon_estagio_4,
                    nivel: data.nivel,
                    bonus_experiencia: data.bonus_experiencia,
                    bonus_bits: data.bonus_bits,
                    atributo1_forca: data.atributo1_forca,
                    atributo2_inteligencia: data.atributo2_inteligencia,
                    atributo3_conhecimento: data.atributo3_conhecimento,
                    atributo4_agilidade: data.atributo4_agilidade,
                    atributo0_vida: data.atributo0_vida,
                    pontosEnergia: data.pontosEnergia,
                    experiencia: data.experiencia,
                    experienciaNecessaria: data.experienciaNecessaria,
                    bits_obtidos: data.bits_obtidos,
                    exp_obtida: data.exp_obtida,
                    cacadas_concluidas: data.cacadas_concluidas,
                    missoes_concluidas: data.missoes_concluidas,
                    status_premium: data.status_premium,
                    data_inicio_premium: data.data_inicio_premium,
                    data_fim_premium: data.data_fim_premium
                    // Adicionar mais atributos conforme necessário
                };
                console.log(dataInformacoesStatus);
                // Atualizar a interface com os dados recebidos
                atualizarImagemDigimon(dataInformacoesStatus);
                atualizarInformacoes();
                atualizarAtributos();
                atualizarEstatisticas();
                displayPremiumContent(dataInformacoesStatus);
                displayLifeBar();
                displayEnergyBar();
            })
            .catch(error => {
                console.error('Erro ao fazer requisição:', error);
            });
    }

    async function atualizarImagemDigimon(dataInformacoesStatus) {

        // Obtém o contêiner onde a imagem será adicionada
        const imageContainer = document.getElementById('image-container');

        // Remove qualquer imagem existente no contêiner
        while (imageContainer.firstChild) {
            imageContainer.removeChild(imageContainer.firstChild);
        }

        // Verifica se a URL da imagem está disponível
        if (dataInformacoesStatus.url_imagem_digimon) {
            const imgElement = document.createElement('img');
            imgElement.src = dataInformacoesStatus.url_imagem_digimon;
            imgElement.alt = "Imagem do Digimon";
            imgElement.width = 163;
            imgElement.height = 174;
            imgElement.className = "img-bordered";

            imageContainer.appendChild(imgElement);
        } else {
            console.error('URL da imagem não encontrada.');
        }
    }

    function atualizarInformacoes() {
        document.getElementById('jogo_desde').textContent = '| ' + dataInformacoesStatus.jogo_desde;
        document.getElementById('indicacao').textContent = '| ' + dataInformacoesStatus.indicacao;
        document.getElementById('reserva_bits').textContent = '| ' + dataInformacoesStatus.reserva_bits;
        document.getElementById('reserva_diamantes').textContent = '| ' + dataInformacoesStatus.reserva_diamantes;
        document.getElementById('apelido_digimon').textContent = '| ' + dataInformacoesStatus.apelido_digimon;
        document.getElementById('digimon_estagio_1').textContent = '| ' + dataInformacoesStatus.digimon_estagio_1;
        document.getElementById('digimon_estagio_2').textContent = '| ' + dataInformacoesStatus.digimon_estagio_2;
        document.getElementById('digimon_estagio_3').textContent = '| ' + dataInformacoesStatus.digimon_estagio_3;
        document.getElementById('digimon_estagio_4').textContent = '| ' + dataInformacoesStatus.digimon_estagio_4;
        document.getElementById('nivel').textContent = '| ' + dataInformacoesStatus.tier + ' - [ ' + dataInformacoesStatus.nivel + ' ] ';
        document.getElementById('bonus_experiencia').textContent = '| ' + dataInformacoesStatus.bonus_experiencia;
        document.getElementById('bonus_bits').textContent = '| ' + dataInformacoesStatus.bonus_bits;

    }

    function atualizarAtributos() {
        var vidaTotal = 50 * parseInt(dataInformacoesStatus.nivel);
        document.getElementById('atributo1_forca').textContent = '| [ ' + dataInformacoesStatus.atributo1_forca + ' ] ';
        document.getElementById('atributo2_inteligencia').textContent = '| [ ' + dataInformacoesStatus.atributo2_inteligencia + ' ] ';
        document.getElementById('atributo3_conhecimento').textContent = '| [ ' + dataInformacoesStatus.atributo3_conhecimento + ' ] ';
        document.getElementById('atributo4_agilidade').textContent = '| [ ' + dataInformacoesStatus.atributo4_agilidade + ' ] ';
        document.getElementById('atributo0_vida').textContent = '| [ ' + dataInformacoesStatus.atributo0_vida + ' | ' + vidaTotal + ' ] ';
        document.getElementById('experiencia').textContent = '| [ ' + dataInformacoesStatus.experiencia + ' | ' + dataInformacoesStatus.experienciaNecessaria + ' ] ';
    }

    function atualizarEstatisticas() {
        document.getElementById('bits_obtidos').textContent = '| ' + dataInformacoesStatus.bits_obtidos;
        document.getElementById('exp_obtida').textContent = '| ' + dataInformacoesStatus.exp_obtida;
        document.getElementById('cacadas_concluidas').textContent = '| ' + dataInformacoesStatus.cacadas_concluidas;
        document.getElementById('missoes_concluidas').textContent = '| ' + dataInformacoesStatus.missoes_concluidas;
    }

    function displayPremiumContent(dataInformacoesStatus) {

        const container = document.getElementById('caixa-informacoes-esquerda');
        container.innerHTML = ''; // Limpa o conteúdo existente

        if (dataInformacoesStatus.status_premium === 'Ativo') {
            container.innerHTML = `
                    <h3 class="titulo-caixa">Premium</h3>
                    <hr class="separador">
                    <div id="caixa-premium">
                        <p>De:</p>
                        <p id="premium_inicio">${dataInformacoesStatus.data_inicio_premium}</p>
                        <p>Até:</p>
                        <p id="premium_fim">${dataInformacoesStatus.data_fim_premium}</p>
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


    function displayEnergyBar() {
        var energiaTotal = 100;
        const energiaAtual = dataInformacoesStatus.pontosEnergia; // Pontos de energia atuais
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
        var vidaTotal = 50 * parseInt(dataInformacoesStatus.nivel);
        const vidaAtual = dataInformacoesStatus.atributo0_vida; // Pontos de vida atuais
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

    carregarTelaStatus();

    document.getElementById('botaoTreinar').addEventListener('click', function () {
        window.location.href = 'atributos.html';
    });

});


