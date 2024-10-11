// Simulação de conquistas (poderia vir de uma API no seu projeto real)
const conquistas = [
    { id: 1, nome: "Caçador I", descricao: "Complete sua primeira caçada.", desbloqueada: false, /*imagem: "exploracao.png",*/ progresso: 1, meta: 1, recompensa: "10 Diamantes"  },
    { id: 2, nome: "Explorador I", descricao: "Complete sua primeira missão.", desbloqueada: false, /*imagem: "campeao.png",*/ progresso: 0, meta: 1, recompensa: "10 Diamantes"  },
    { id: 3, nome: "Em busca da riqueza I", descricao: "Acumule 100 bits ganhos.", desbloqueada: false, /*imagem: "bits.png",*/ progresso: 2, meta: 100, recompensa: "10 Diamantes"  },
    { id: 4, nome: "Em busca da riqueza II", descricao: "Acumule 1000 bits ganhos.", desbloqueada: false, /*imagem: "bits.png",*/ progresso: 533, meta: 1000, recompensa: "10 Diamantes"  },
    { id: 5, nome: "Caçador II", descricao: "Complete 10 caçadas.", desbloqueada: false, /*imagem: "exploracoes.png",*/ progresso: 7, meta: 10, recompensa: "10 Diamantes"  },
    { id: 6, nome: "Explorador II", descricao: "Complete 10 missões.", desbloqueada: false, /*imagem: "exploracoes.png",*/ progresso: 4, meta: 10, recompensa: "10 Diamantes"  },
    { id: 7, nome: "Digievolução I", descricao: "Consiga seu primeiro Tier Champion.", desbloqueada: false, /*imagem: "exploracoes.png",*/ progresso: 0, meta: 1, recompensa: "10 Diamantes"  },
    { id: 8, nome: "Digievolução II", descricao: "Consiga seu primeiro Tier Ultimate.", desbloqueada: false, /*imagem: "exploracoes.png",*/ progresso: 0, meta: 1, recompensa: "10 Diamantes"  },
    { id: 9, nome: "Digievolução III", descricao: "Consiga seu primeiro Tier Mega.", desbloqueada: false, /*imagem: "exploracoes.png",*/ progresso: 0, meta: 1, recompensa: "10 Diamantes"  },
    { id: 10, nome: "Fragmento Digimon", descricao: "Consiga seus primeiros 10 fragmentos de digievolução.", desbloqueada: false, /*imagem: "exploracoes.png",*/ progresso: 7, meta: 10, recompensa: "10 Diamantes"  },
    { id: 11, nome: "Fragmento Digimon", descricao: "Consiga seus primeiros 10 fragmentos de digievolução.", desbloqueada: false, /*imagem: "exploracoes.png",*/ progresso: 7, meta: 10, recompensa: "10 Diamantes"  },
    { id: 12, nome: "Fragmento Digimon", descricao: "Consiga seus primeiros 10 fragmentos de digievolução.", desbloqueada: false, /*imagem: "exploracoes.png",*/ progresso: 7, meta: 10, recompensa: "10 Diamantes"  },
    { id: 13, nome: "Fragmento Digimon", descricao: "Consiga seus primeiros 10 fragmentos de digievolução.", desbloqueada: false, /*imagem: "exploracoes.png",*/ progresso: 7, meta: 10, recompensa: "10 Diamantes"  },
    { id: 14, nome: "Fragmento Digimon", descricao: "Consiga seus primeiros 10 fragmentos de digievolução.", desbloqueada: false, /*imagem: "exploracoes.png",*/ progresso: 7, meta: 10, recompensa: "10 Diamantes"  },
    { id: 15, nome: "Fragmento Digimon", descricao: "Consiga seus primeiros 10 fragmentos de digievolução.", desbloqueada: false, /*imagem: "exploracoes.png",*/ progresso: 7, meta: 10, recompensa: "10 Diamantes"  },
    { id: 16, nome: "Fragmento Digimon", descricao: "Consiga seus primeiros 10 fragmentos de digievolução.", desbloqueada: false, /*imagem: "exploracoes.png",*/ progresso: 7, meta: 10, recompensa: "10 Diamantes"  },
    { id: 17, nome: "Fragmento Digimon", descricao: "Consiga seus primeiros 10 fragmentos de digievolução.", desbloqueada: false, /*imagem: "exploracoes.png",*/ progresso: 7, meta: 10, recompensa: "10 Diamantes"  },
    { id: 18, nome: "Fragmento Digimon", descricao: "Consiga seus primeiros 10 fragmentos de digievolução.", desbloqueada: false, /*imagem: "exploracoes.png",*/ progresso: 7, meta: 10, recompensa: "10 Diamantes"  },
    { id: 19, nome: "Fragmento Digimon", descricao: "Consiga seus primeiros 10 fragmentos de digievolução.", desbloqueada: false, /*imagem: "exploracoes.png",*/ progresso: 7, meta: 10, recompensa: "10 Diamantes"  },
    { id: 20, nome: "Fragmento Digimon", descricao: "Consiga seus primeiros 10 fragmentos de digievolução.", desbloqueada: false, /*imagem: "exploracoes.png",*/ progresso: 7, meta: 10, recompensa: "10 Diamantes"  }
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
            <br>
            <p><strong>Recompensa: 
            <br>
            </strong>${conquista.recompensa}</p>
            <br>
            <button class="button-confirmar">Receber</button>
        `;
        
        container.appendChild(div);
    });
}

// Executa a renderização ao carregar a página
document.addEventListener('DOMContentLoaded', () => {
    renderizarConquistas();
});
