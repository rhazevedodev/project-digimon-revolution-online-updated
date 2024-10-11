// Simulação de conquistas (poderia vir de uma API no seu projeto real)
const conquistas = [
    { id: 1, nome: "Primeira Exploração", descricao: "Complete sua primeira exploração.", desbloqueada: true, /*imagem: "exploracao.png",*/ progresso: 1, meta: 1 },
    { id: 2, nome: "Evolução de Campeão", descricao: "Evolua um Digimon para o nível Campeão.", desbloqueada: false, /*imagem: "campeao.png",*/ progresso: 0, meta: 1 },
    { id: 3, nome: "100 Bits", descricao: "Acumule 100 bits.", desbloqueada: false, /*imagem: "bits.png",*/ progresso: 50, meta: 100 },
    { id: 4, nome: "10 Explorações", descricao: "Complete 10 explorações.", desbloqueada: false, /*imagem: "exploracoes.png",*/ progresso: 7, meta: 10 }
];

// Função para renderizar conquistas na tela
function renderizarConquistas() {
    const container = document.querySelector('.achievements');
    container.innerHTML = ''; // Limpa a área de conquistas

    conquistas.forEach(conquista => {
        const div = document.createElement('div');
        div.classList.add('achievement');
        if (conquista.desbloqueada) {
            div.classList.add('unlocked');
        }

        // Cria a barra de progresso se a conquista tiver uma meta
        const progressoTexto = conquista.meta > 1 ? 
            `<div class="progress-text">${conquista.progresso} / ${conquista.meta}</div>` : '';

        const barraProgresso = conquista.meta > 1 ? `
            <div class="progress-bar-container">
                <div class="progress-bar" style="width: ${(conquista.progresso / conquista.meta) * 100}%;"></div>
            </div>
        ` : '';

        div.innerHTML = `
            <h3>${conquista.nome}</h3>
            <p>${conquista.descricao}</p>
            ${progressoTexto}
            ${barraProgresso}
        `;
        
        container.appendChild(div);
    });
}

// Executa a renderização ao carregar a página
document.addEventListener('DOMContentLoaded', () => {
    renderizarConquistas();
});
