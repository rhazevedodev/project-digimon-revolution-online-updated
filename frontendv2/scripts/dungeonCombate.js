// Dados estáticos (mockados) para o Digimon Desafiante
const desafiante = {
    nome: "Togemon",
    imagem: "./imagens/digimons/champions/togemon.jpg",
    vida: 200,
    energia: 100,
    forca: 50,
    inteligencia: 30,
    conhecimento: 21,
    agilidade: 10,
    sorte: 50
};

function carregarDesafiante() {
    // Seleciona os elementos HTML onde os dados serão inseridos
    const imgDesafiante = document.querySelector(".card img");
    const nomeDesafiante = document.querySelector(".card-title-name-desafiante");
    const vidaDesafiante = document.querySelector(".card p:nth-of-type(1)");
    const energiaDesafiante = document.querySelector(".card p:nth-of-type(2)");
    const forcaDesafiante = document.querySelector(".card p:nth-of-type(3)");
    const inteligenciaDesafiante = document.querySelector(".card p:nth-of-type(4)");
    const conhecimentoDesafiante = document.querySelector(".card p:nth-of-type(5)");
    const agilidadeDesafiante = document.querySelector(".card p:nth-of-type(6)");
    const sorteDesafiante = document.querySelector(".card p:nth-of-type(7)");

    // Insere os dados mockados nos elementos HTML
    imgDesafiante.src = desafiante.imagem;
    nomeDesafiante.textContent = desafiante.nome;
    vidaDesafiante.textContent = `Vida: ${desafiante.vida}`;
    energiaDesafiante.textContent = `Energia: ${desafiante.energia}`;
    forcaDesafiante.textContent = `Força: ${desafiante.forca}`;
    inteligenciaDesafiante.textContent = `Inteligência: ${desafiante.inteligencia}`;
    conhecimentoDesafiante.textContent = `Conhecimento: ${desafiante.conhecimento}`;
    agilidadeDesafiante.textContent = `Agilidade: ${desafiante.agilidade}`;
    sorteDesafiante.textContent = `Sorte: ${desafiante.sorte}`;
}

const defensores = [
    {
        nome: "Greymon",
        imagem: "./imagens/digimons/champions/greymon2.jpg",
        vida: 100,
        energia: 20,
        forca: 20,
        inteligencia: 20,
        conhecimento: 20,
        agilidade: 20,
        sorte: 20
    },
    {
        nome: "Ikkakumon",
        imagem: "./imagens/digimons/champions/ikkakumon.jpg",
        vida: 100,
        energia: 20,
        forca: 20,
        inteligencia: 20,
        conhecimento: 20,
        agilidade: 20,
        sorte: 20
    },
    {
        nome: "Kabuterimon",
        imagem: "./imagens/digimons/champions/kabuterimon.jpg",
        vida: 100,
        energia: 20,
        forca: 20,
        inteligencia: 20,
        conhecimento: 20,
        agilidade: 20,
        sorte: 20
    },
    {
        nome: "Angemon",
        imagem: "./imagens/digimons/champions/angemon.jpg",
        vida: 100,
        energia: 20,
        forca: 20,
        inteligencia: 20,
        conhecimento: 20,
        agilidade: 20,
        sorte: 20
    }
];

// Função para carregar um defensor aleatório da lista de mockados
function carregarDefensor() {
    // Seleciona aleatoriamente um Digimon da lista de defensores
    const defensor = defensores[Math.floor(Math.random() * defensores.length)];

    // Seleciona os elementos HTML onde os dados serão inseridos
    const imgDefensor = document.querySelector(".card:nth-of-type(2) img");
    const nomeDefensor = document.querySelector(".card:nth-of-type(2) .card-title-name-desafiante");
    const vidaDefensor = document.querySelector(".card:nth-of-type(2) p:nth-of-type(1)");
    const energiaDefensor = document.querySelector(".card:nth-of-type(2) p:nth-of-type(2)");
    const forcaDefensor = document.querySelector(".card:nth-of-type(2) p:nth-of-type(3)");
    const inteligenciaDefensor = document.querySelector(".card:nth-of-type(2) p:nth-of-type(4)");
    const conhecimentoDefensor = document.querySelector(".card:nth-of-type(2) p:nth-of-type(5)");
    const agilidadeDefensor = document.querySelector(".card:nth-of-type(2) p:nth-of-type(6)");
    const sorteDefensor = document.querySelector(".card:nth-of-type(2) p:nth-of-type(7)");

    // Insere os dados mockados nos elementos HTML
    imgDefensor.src = defensor.imagem;
    nomeDefensor.textContent = defensor.nome;
    vidaDefensor.textContent = `Vida: ${defensor.vida}`;
    energiaDefensor.textContent = `Energia: ${defensor.energia}`;
    forcaDefensor.textContent = `Força: ${defensor.forca}`;
    inteligenciaDefensor.textContent = `Inteligência: ${defensor.inteligencia}`;
    conhecimentoDefensor.textContent = `Conhecimento: ${defensor.conhecimento}`;
    agilidadeDefensor.textContent = `Agilidade: ${defensor.agilidade}`;
    sorteDefensor.textContent = `Sorte: ${defensor.sorte}`;
}

// Função para registrar a ação no log
function registrarAcao(acao, digimon) {
    const logBox = document.getElementById('logBox');
    const timestamp = new Date().toLocaleTimeString();
    const mensagem = `${timestamp} - ${acao}.`;
            
    // Cria um novo parágrafo para a mensagem
    const novaMensagem = document.createElement('p');
    novaMensagem.textContent = mensagem;
            
    // Adiciona a nova mensagem ao log
    logBox.appendChild(novaMensagem);
            
    // Faz o scroll automático para o final do log
    logBox.scrollTop = logBox.scrollHeight;
}

let turno = 1; // Variável para controlar o número do turno


// Função para simular um ataque entre o Desafiante e o Defensor
function atacar() {
    // Obtém os dados do Digimon Desafiante
    const desafianteVidaElement = document.querySelector(".card p:nth-of-type(1)");
    const desafianteVida = parseInt(desafianteVidaElement.textContent.split(": ")[1]); // Extrai a vida do desafiante
    const desafianteForcaElement = document.querySelector(".card p:nth-of-type(3)");
    const desafianteForca = parseInt(desafianteForcaElement.textContent.split(": ")[1]); // Extrai a força do desafiante

    // Obtém os dados do Digimon Defensor
    const defensorVidaElement = document.querySelector(".card:nth-of-type(2) p:nth-of-type(1)");
    let defensorVida = parseInt(defensorVidaElement.textContent.split(": ")[1]); // Extrai a vida do defensor
    const defensorForcaElement = document.querySelector(".card:nth-of-type(2) p:nth-of-type(3)");
    const defensorForca = parseInt(defensorForcaElement.textContent.split(": ")[1]); // Extrai a força do defensor

    // Cálculo de dano
    const danoDesafiante = Math.round(desafianteForca * (Math.random() * 0.5 + 0.75)); // Dano do desafiante
    const danoDefensor = Math.round(defensorForca * (Math.random() * 0.5 + 0.75)); // Dano do defensor

    // Atualiza a vida dos Digimons
    const novaVidaDefensor = Math.max(defensorVida - danoDesafiante, 0); // Vida do defensor não pode ser negativa
    const novaVidaDesafiante = Math.max(desafianteVida - danoDefensor, 0); // Vida do desafiante não pode ser negativa

    // Registra a ação no log do desafiante
    registrarAcao(`Turno ${turno}: O Desafiante atacou e causou ${danoDesafiante} de dano ao Defensor. Vida restante do Defensor: ${novaVidaDefensor}`);
    
    // Registra a ação no log do defensor
    registrarAcao(`Turno ${turno}: O Defensor atacou e causou ${danoDefensor} de dano ao Desafiante. Vida restante do Desafiante: ${novaVidaDesafiante}`);

    // Atualiza a vida no HTML
    defensorVidaElement.textContent = `Vida: ${novaVidaDefensor}`;
    desafianteVidaElement.textContent = `Vida: ${novaVidaDesafiante}`; // Atualiza a vida do desafiante

    // Lógica para lidar com a vida de ambos os Digimons
    if (novaVidaDefensor <= 0) {
        // O defensor foi derrotado
        registrarAcao(`Turno ${turno}: O Defensor foi derrotado!`);
        // Desabilitar botões ou ações adicionais se necessário
        desabilitarBotoes(); // Desabilitar botões quando o desafiante for derrotado
    }
    
    if (novaVidaDesafiante <= 0) {
        // O desafiante foi derrotado
        registrarAcao(`Turno ${turno}: O Desafiante foi derrotado!`);
        // Desabilitar botões ou ações adicionais se necessário
        desabilitarBotoes(); // Desabilitar botões quando o desafiante for derrotado
    }
        // Incrementa o turno após as ações
        turno++;
}

function fecharModal() {
    const modal = document.getElementById('recompensaModal');
    modal.style.display = 'none';
    window.location.href = "status.html"; // Redireciona para a tela de status
}

function mostrarModal(recompensa) {
    const modal = document.getElementById('recompensaModal');
    const mensagemRecompensa = document.getElementById('mensagemRecompensa');

    mensagemRecompensa.textContent = `Você ganhou: ${recompensa}!`;
    modal.style.display = 'flex'; // Exibe o modal
}

// Função para desabilitar todos os botões
function desabilitarBotoes() {
    const botoes = document.querySelectorAll('.card-btn, .card-btn-sacrificar'); // Seleciona todos os botões
    botoes.forEach(botao => {
        botao.disabled = true; // Desabilita o botão
    });

    // Exibir o modal com a recompensa
    mostrarModal("100 pontos de experiência"); // Personalize a recompensa aqui
}

// Chama a função para carregar os dados mockados assim que a página for carregada
document.addEventListener("DOMContentLoaded", () => {
    carregarDesafiante();
    carregarDefensor();
});