document.addEventListener('DOMContentLoaded', function () {
    const apiURL = 'http://localhost:8080/api/telaAtributos/carregar';
    const salvarAPIURL = 'http://localhost:8080/api/telaAtributos/salvarAtributos';

    let dataAtributos = {}; // Objeto para armazenar os atributos recebidos da API

    let custoTotalInicial = 0;
    document.getElementById('custoTotal').textContent = `Custo Total: ${custoTotalInicial.toFixed(0)} Bits`;

    // Função para fazer a requisição à API e atualizar os dados na página
    function carregarAtributos() {
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
                dataAtributos = {
                    bits_jogador: data.digimonBits, // Exemplo de bits_jogador, ajustar conforme necessidade real
                    atributo1_forca: data.forca,
                    atributo2_inteligencia: data.inteligencia,
                    atributo3_conhecimento: data.conhecimento,
                    atributo4_agilidade: data.agilidade
                    // Adicionar mais atributos conforme necessário
                };
                // Atualizar a interface com os dados recebidos
                atualizarAtributos();
                atualizarCustosAtributos();
            })
            .catch(error => {
                console.error('Erro ao fazer requisição:', error);
            });
    }


    // Função para atualizar os atributos na página
    function atualizarAtributos() {

        document.getElementById('bits_jogador').textContent = '| \u00A0' + dataAtributos.bits_jogador + ' bits';
        document.getElementById('atributo1_forca').textContent = '| \u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0[ ' + dataAtributos.atributo1_forca + ' ]';
        document.getElementById('atributo2_inteligencia').textContent = '| \u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0[ ' + dataAtributos.atributo2_inteligencia + ' ]';
        document.getElementById('atributo3_conhecimento').textContent = '| \u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0[ ' + dataAtributos.atributo3_conhecimento + ' ]';
        document.getElementById('atributo4_agilidade').textContent = '| \u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0[ ' + dataAtributos.atributo4_agilidade + ' ]';
    }

    // Função para atualizar os custos dos atributos na página
    function atualizarCustosAtributos() {
        // Calcula os novos custos baseados nos níveis atuais dos atributos
        /*const custoAtributo1 = 100 * Math.pow(1.15, dataAtributos.atributo1_forca - 1);
        const custoAtributo2 = 100 * Math.pow(1.15, dataAtributos.atributo2_inteligencia - 1);
        const custoAtributo3 = 100 * Math.pow(1.15, dataAtributos.atributo3_conhecimento - 1);
        const custoAtributo4 = 100 * Math.pow(1.15, dataAtributos.atributo4_agilidade - 1);*/
        const custoAtributo1 = 100+(20*(dataAtributos.atributo1_forca-1)+5*(Math.pow(dataAtributos.atributo1_forca-1,2)));
        const custoAtributo2 = 100+(20*(dataAtributos.atributo2_inteligencia-1)+5*(Math.pow(dataAtributos.atributo2_inteligencia-1,2)));
        const custoAtributo3 = 100+(20*(dataAtributos.atributo3_conhecimento-1)+5*(Math.pow(dataAtributos.atributo3_conhecimento-1,2)));
        const custoAtributo4 = 100+(20*(dataAtributos.atributo4_agilidade-1)+5*(Math.pow(dataAtributos.atributo4_agilidade-1,2)));

        document.getElementById('custoAtributo1').textContent = '\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0Valor: ' + custoAtributo1.toFixed(0) + ' Bits';
        document.getElementById('custoAtributo2').textContent = '\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0Valor: ' + custoAtributo2.toFixed(0) + ' Bits';
        document.getElementById('custoAtributo3').textContent = '\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0Valor: ' + custoAtributo3.toFixed(0) + ' Bits';
        document.getElementById('custoAtributo4').textContent = '\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0Valor: ' + custoAtributo4.toFixed(0) + ' Bits';

    }

    function atualizarCustoTotal(custo) {
        custoTotalInicial += custo;
        document.getElementById('custoTotal').textContent = `Custo Total: ${custoTotalInicial.toFixed(0)} Bits`;
    }


    // Função para upar atributo
    function uparAtributo(atributo, custo) {
        if (dataAtributos.bits_jogador >= custo) {
            dataAtributos.bits_jogador -= custo;
            dataAtributos[atributo]++;
            atualizarAtributos();
            atualizarCustosAtributos();
            atualizarCustoTotal(custo)
        } else {
            Swal.fire({
                title: 'Erro!',
                text: 'Bits insuficientes!',
                icon: 'warning',
                confirmButtonText: 'Tentar novamente'
            });
        }
    }

    // Adicionando eventos de clique aos botões de upar atributos
    document.getElementById('uparAtributo1').addEventListener('click', function () {
        const custoAtributo1Text = document.getElementById('custoAtributo1').textContent;
        const valorNumericoAtributo1 = parseInt(custoAtributo1Text.match(/\d+/)[0]);
        uparAtributo('atributo1_forca', valorNumericoAtributo1);
    });

    document.getElementById('uparAtributo2').addEventListener('click', function () {
        const custoAtributo2Text = document.getElementById('custoAtributo2').textContent;
        const valorNumericoAtributo2 = parseInt(custoAtributo2Text.match(/\d+/)[0]);
        uparAtributo('atributo2_inteligencia', valorNumericoAtributo2);
    });

    document.getElementById('uparAtributo3').addEventListener('click', function () {
        const custoAtributo3Text = document.getElementById('custoAtributo3').textContent;
        const valorNumericoAtributo3 = parseInt(custoAtributo3Text.match(/\d+/)[0]);
        uparAtributo('atributo3_conhecimento', valorNumericoAtributo3);
    });

    document.getElementById('uparAtributo4').addEventListener('click', function () {
        const custoAtributo4Text = document.getElementById('custoAtributo4').textContent;
        const valorNumericoAtributo4 = parseInt(custoAtributo4Text.match(/\d+/)[0]);
        uparAtributo('atributo4_agilidade', valorNumericoAtributo4);
    });

    // Função para enviar os dados para a API
    function enviarDados() {
        const requestBody = {
            idDigimon: localStorage.getItem('idDigimon'),
            custoTotal: parseInt(document.getElementById('custoTotal').textContent.match(/\d+/)[0]),
            forca: parseInt(document.getElementById('atributo1_forca').textContent.match(/\d+/)[0]),
            inteligencia: parseInt(document.getElementById('atributo2_inteligencia').textContent.match(/\d+/)[0]),
            conhecimento: parseInt(document.getElementById('atributo3_conhecimento').textContent.match(/\d+/)[0]),
            agilidade: parseInt(document.getElementById('atributo4_agilidade').textContent.match(/\d+/)[0])
        };

        console.log(requestBody);

        const requestOptions = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(requestBody)
        };

        fetch(salvarAPIURL, requestOptions)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Erro na rede, status: ' + response.status);
                }
                return response.json();
            })
            .then(data => {
                console.log('Dados enviados com sucesso:', data);
                // Adicione aqui qualquer lógica adicional após o sucesso da requisição, se necessário...
                location.reload();
            })
            .catch(error => {
                console.error('Erro ao enviar dados:', error);
            });
    }

    // Inicializa os atributos na página ao carregar
    carregarAtributos();

    // Evento de clique no botão "Resetar Atributos"
    document.querySelector('.button-resetar-salvar-atributos:first-of-type').addEventListener('click', function () {
        // Adicione aqui qualquer lógica adicional antes de recarregar a página, se necessário...
        // Recarregar a página
        location.reload();
    });

    // Evento de clique no botão "Salvar Atributos"
    document.querySelector('.button-resetar-salvar-atributos:last-of-type').addEventListener('click', function () {
        enviarDados();
    });

});