// Função para carregar conquistas de uma API
async function carregarConquistas() {
    try {
        // Substitua pela URL da sua API
        const response = await fetch('http://localhost:8080/api/telaConquistas/carregar/' + localStorage.getItem("idDigimon"));
        const conquistasAPI = await response.json();

        // Chama a função de renderização com os dados da API
        renderizarConquistas(conquistasAPI);
    } catch (error) {
        console.error("Erro ao carregar conquistas:", error);
    }
}

// Função para renderizar conquistas na tela
function renderizarConquistas(conquistas) {
    const container = document.querySelector('.achievements');
    container.innerHTML = ''; // Limpa a área de conquistas

    conquistas.forEach(conquista => {
        const div = document.createElement('div');
        div.classList.add('achievement');
        
        // Se a conquista foi desbloqueada, adiciona a classe 'unlocked'
        if (conquista.conquista.desbloqueada) {
            div.classList.add('unlocked');
        }

        // Se a conquista foi resgatada, adiciona a classe 'resgatada'
        if (conquista.conquista.desbloqueada && conquista.conquista.conquistaResgatada) {
            div.classList.add('resgatada');
        }

        // Cria a barra de progresso se a conquista tiver uma meta
        const progressoTexto = conquista.conquista.meta > 1 ? 
            `<div class="progress-text">${conquista.conquista.progresso} / ${conquista.conquista.meta}</div>` : '';

        const barraProgresso = conquista.conquista.meta > 1 ? `
            <div class="progress-bar-container">
                <div class="progress-bar" style="width: ${(conquista.conquista.progresso / conquista.conquista.meta) * 100}%;"></div>
            </div>
        ` : '';

        // Definir o texto da recompensa com base no tipo de recompensa
        let recompensaTexto = '';
        if (conquista.tipoRecompensa === 'DIAMANTE') {
            recompensaTexto = `${conquista.quantidadeRecompensa} Diamantes`;
        } else if (conquista.tipoRecompensa === 'BIT') {
            recompensaTexto = `${conquista.quantidadeRecompensa} Bits`;
        }

        // Verifica se o botão de resgate deve ser exibido
        let botaoCommands = '';
        if (conquista.conquista.desbloqueada && !conquista.conquista.conquistaResgatada) { 
            botaoCommands = `<button class="button-confirmar" onclick="receberConquista(${conquista.conquista.id})">Receber</button>`;
        }

        // Monta o HTML da conquista
        div.innerHTML = `
            <h3>${conquista.conquista.nomeConquista}</h3>
            <p>${conquista.descricaoConquista}</p>
            ${progressoTexto}
            ${barraProgresso}
            <br>
            <p><strong>Recompensa: 
            <br>
            </strong>${recompensaTexto}</p>
            <br>
            ${botaoCommands}
        `;
        
        container.appendChild(div);
    });
}

// Função para resgatar a recompensa
function receberConquista(idConquista) {
    fetch(`http://localhost:8080/api/telaConquistas/resgatarConquista/${idConquista}/` + localStorage.getItem("idDigimon"), {
        method: 'GET', // Altere conforme o método correto da sua API ('POST', 'PUT', etc.)
        headers: {
            'Content-Type': 'application/json',
        }
    })
    .then(response => {
        if (response.status === 200) {
            console.log("Recompensa resgatada com sucesso!");
        } else {
            throw new Error('Erro ao resgatar a recompensa');
        }
    })
    .then(data => {
        // Exibir o SweetAlert se o resgate for bem-sucedido
        Swal.fire({
            icon: 'success',
            title: 'Recompensa Resgatada!',
            text: 'Você resgatou sua recompensa com sucesso.',
            confirmButtonText: 'Ok'
        });

        // Aqui você pode também atualizar a UI para refletir que a recompensa foi recebida
        carregarConquistas(); // Recarrega as conquistas para atualizar a UI
    })
    .catch(error => {
        // Tratamento de erros, exibir SweetAlert com erro
        Swal.fire({
            icon: 'error',
            title: 'Erro',
            text: 'Houve um problema ao resgatar a recompensa. Tente novamente mais tarde.',
            confirmButtonText: 'Ok'
        });
        console.error('Erro:', error);
    });
}

// Executa a função para carregar e renderizar conquistas ao carregar a página
document.addEventListener('DOMContentLoaded', () => {
    carregarConquistas(); // Carregar as conquistas da API
});
