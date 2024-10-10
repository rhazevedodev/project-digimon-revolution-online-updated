document.addEventListener('DOMContentLoaded', function () {
    const apiURL = 'http://localhost:8080/api/telaStatus/carregar';

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
                    jogo_desde: data.dataJogoDesde,
                    indicacao: data.indicacao,
                    reserva_bits: data.reservaBits,
                    reserva_diamantes: data.reservaDiamantes,
                    apelido_digimon: data.apelidoDigimon,
                    digimon_estagio_1: data.digimonRookie,
                    digimon_estagio_2: data.digimonChampion,
                    digimon_estagio_3: data.digimonUltimate,
                    digimon_estagio_4: data.digimonMega,
                    tier: data.tierDigimon,
                    nivel: data.nivelDigimon,
                    atributo1_forca: data.forca,
                    atributo2_inteligencia: data.inteligencia,
                    atributo3_conhecimento: data.conhecimento,
                    atributo4_agilidade: data.agilidade,
                    atributo0_vida: data.vida,
                    modificadorForca: data.modificadorForca,
                    modificadorInteligencia: data.modificadorInteligencia,
                    modificadorConhecimento: data.modificadorConhecimento,
                    modificadorAgilidade: data.modificadorAgilidade,
                    experiencia: data.experiencia,
                    experienciaNecessaria: data.experienciaNecessaria,
                    bonus_experiencia: data.bonus_experiencia,
                    bonus_bits: data.bonus_bits,
                    bits_obtidos: data.bitsObtidos,
                    exp_obtida: data.experienciaObtida,
                    cacadas_concluidas: data.cacadasConcluidas,
                    missoes_concluidas: data.missoesConcluidas,
                    // Adicionar mais atributos conforme necessário
                };
                atualizarInformacoes();
                atualizarAtributos();
                atualizarEstatisticas();
            })
            .catch(error => {
                console.error('Erro ao fazer requisição:', error);
            });
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
        document.getElementById('atributo1_forca').textContent = '| [ ' + dataInformacoesStatus.atributo1_forca + ' ] + [ ' + dataInformacoesStatus.modificadorForca + ' ]';
        document.getElementById('atributo2_inteligencia').textContent = '| [ ' + dataInformacoesStatus.atributo2_inteligencia + ' ] + [ ' + dataInformacoesStatus.modificadorInteligencia + ' ]';
        document.getElementById('atributo3_conhecimento').textContent = '| [ ' + dataInformacoesStatus.atributo3_conhecimento + ' ] + [ ' + dataInformacoesStatus.modificadorConhecimento + ' ]';
        document.getElementById('atributo4_agilidade').textContent = '| [ ' + dataInformacoesStatus.atributo4_agilidade + ' ] + [ ' + dataInformacoesStatus.modificadorAgilidade + ' ]';
        document.getElementById('atributo0_vida').textContent = '| [ ' + dataInformacoesStatus.atributo0_vida + ' | ' + vidaTotal + ' ] ';
        document.getElementById('experiencia').textContent = '| [ ' + dataInformacoesStatus.experiencia + ' | ' + dataInformacoesStatus.experienciaNecessaria + ' ] ';
    }

    function atualizarEstatisticas() {
        document.getElementById('bits_obtidos').textContent = '| ' + dataInformacoesStatus.bits_obtidos;
        document.getElementById('exp_obtida').textContent = '| ' + dataInformacoesStatus.exp_obtida;
        document.getElementById('cacadas_concluidas').textContent = '| ' + dataInformacoesStatus.cacadas_concluidas;
        document.getElementById('missoes_concluidas').textContent = '| ' + dataInformacoesStatus.missoes_concluidas;
    }

    carregarTelaStatus();

    document.getElementById('botaoTreinar').addEventListener('click', function () {
        window.location.href = 'atributos.html';
    });

});


