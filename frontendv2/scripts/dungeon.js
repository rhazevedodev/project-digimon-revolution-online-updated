function openModal(dungeonName) {
    const modal = document.getElementById('rewardsModal');
    const modalTitle = document.getElementById('modalTitle');
    const modalRewards = document.getElementById('modalRewards');

    modalTitle.innerText = 'Recompensas para ' + dungeonName;
    modalRewards.innerHTML = generateRewards(dungeonName);

    modal.style.display = 'block';
}

function closeModal() {
    const modal = document.getElementById('rewardsModal');
    modal.style.display = 'none';
}

function generateRewards(dungeonName) {
    let rewards = '';

    switch (dungeonName) {
        case 'Masmorra da Tempestade':
            rewards = `
                <table>
                    <tr>
                        <th>Item</th>
                        <th>Tipo</th>
                    </tr>
                    <tr>
                        <td>Emblema da Coragem</td>
                        <td>Comum</td>
                    </tr>
                    <tr>
                        <td>Emblema da Amizade</td>
                        <td>Comum</td>
                    </tr>
                    <tr>
                        <td>Emblema da Confiança</td>
                        <td>Comum</td>
                    </tr>
                    <tr>
                        <td>Emblema do Amor</td>
                        <td>Comum</td>
                    </tr>
                    <tr>
                        <td>Emblema da Esperança</td>
                        <td>Comum</td>
                    </tr>
                    <tr>
                        <td>Emblema do Conhecimento</td>
                        <td>Comum</td>
                    </tr>
                    <tr>
                        <td>Cristal do Mar</td>
                        <td>Comum</td>
                    </tr>
                    <tr>
                        <td>Garra de Urso</td>
                        <td>Comum</td>
                    </tr>
                    <tr>
                        <td>Punho do Dragão</td>
                        <td>Comum</td>
                    </tr>
                </table>
            `;
            break;
        case 'Masmorra da Serenidade':
            rewards = `
                <table>
                    <tr>
                        <th>Item</th>
                        <th>Tipo</th>
                    </tr>
                    <tr>
                        <td>Emblema da Serenidade</td>
                        <td>Comum</td>
                    </tr>
                    <tr>
                        <td>Emblema da Luz</td>
                        <td>Comum</td>
                    </tr>
                    <tr>
                        <td>Emblema do Milagre</td>
                        <td>Comum</td>
                    </tr>
                    <tr>
                        <td>Pedra da Escuridão</td>
                        <td>Comum</td>
                    </tr>
                    <tr>
                        <td>Artefato Místico</td>
                        <td>Comum</td>
                    </tr>
                    <tr>
                        <td>Modificador de Dados</td>
                        <td>Comum</td>
                    </tr>
                    <tr>
                        <td>Pena da Harmonia</td>
                        <td>Comum</td>
                    </tr>
                    <tr>
                        <td>Poder da Terra</td>
                        <td>Comum</td>
                    </tr>
                    <tr>
                        <td>Poder da Serenidade</td>
                        <td>Comum</td>
                    </tr>
                </table>
            `;
            break;
        case 'Masmorra do Labirinto':
            rewards = `
                <table>
                    <tr>
                        <th>Item</th>
                        <th>Tipo</th>
                    </tr>
                    <tr>
                        <td>Emblema da Sorte</td>
                        <td>Comum</td>
                    </tr>
                    <tr>
                        <td>Emblema da Sabedoria</td>
                        <td>Comum</td>
                    </tr>
                    <tr>
                        <td>Pena da Escuridão</td>
                        <td>Comum</td>
                    </tr>
                    <tr>
                        <td>Tridente do Oceano</td>
                        <td>Comum</td>
                    </tr>
                    <tr>
                        <td>Osso da Destruição</td>
                        <td>Comum</td>
                    </tr>
                    <tr>
                        <td>Gerador de Energia</td>
                        <td>Comum</td>
                    </tr>
                    <tr>
                        <td>Cristal das Sombras</td>
                        <td>Comum</td>
                    </tr>
                </table>
            `;
            break;
        case 'Masmorra do Crepusculo':
            rewards = `
                <table>
                    <tr>
                        <th>Item</th>
                        <th>Tipo</th>
                    </tr>
                    <tr>
                        <td>Emblema do Amor</td>
                        <td>Comum</td>
                    </tr>
                    <tr>
                        <td>Fóssil Ancestral</td>
                        <td>Comum</td>
                    </tr>
                    <tr>
                        <td>Espada da União</td>
                        <td>Comum</td>
                    </tr>
                    <tr>
                        <td>Poder da Fúria</td>
                        <td>Comum</td>
                    </tr>
                    <tr>
                        <td>Espada da Luz</td>
                        <td>Comum</td>
                    </tr>
                    <tr>
                        <td>Pena da Harmonia</td>
                        <td>Comum</td>
                    </tr>
                    <tr>
                        <td>Armadura de Titânio</td>
                        <td>Comum</td>
                    </tr>
                </table>
            `;
            break;
        case 'Masmorra das Profundezas':
            rewards = `
                <table>
                    <tr>
                        <th>Item</th>
                        <th>Tipo</th>
                    </tr>
                    <tr>
                        <td>Esfera do Destino</td>
                        <td>Comum</td>
                    </tr>
                    <tr>
                        <td>Núcleo de Energia</td>
                        <td>Comum</td>
                    </tr>
                    <tr>
                        <td>Essência do Gelo</td>
                        <td>Comum</td>
                    </tr>
                    <tr>
                        <td>Flor da Pureza</td>
                        <td>Comum</td>
                    </tr>
                    <tr>
                        <td>Pérola do Mar</td>
                        <td>Comum</td>
                    </tr>
                    <tr>
                        <td>Coração da Escuridão</td>
                        <td>Comum</td>
                    </tr>
                    <tr>
                        <td>Escudo da Coragem</td>
                        <td>Comum</td>
                    </tr>
                    <tr>
                        <td>Máscara do Engano</td>
                        <td>Comum</td>
                    </tr>
                </table>
            `;
            break;
        case 'Masmorra das Correntes':
            rewards = `
                <table>
                    <tr>
                        <th>Item</th>
                        <th>Tipo</th>
                    </tr>
                    <tr>
                        <td>Asas da Divindade</td>
                        <td>Comum</td>
                    </tr>
                    <tr>
                        <td>Pena de Fênix</td>
                        <td>Comum</td>
                    </tr>
                    <tr>
                        <td>Armadura de Titânio</td>
                        <td>Comum</td>
                    </tr>
                    <tr>
                        <td>Chave do Paraíso</td>
                        <td>Comum</td>
                    </tr>
                    <tr>
                        <td>Gema do Milagre</td>
                        <td>Comum</td>
                    </tr>
                    <tr>
                        <td>Essência das Trevas</td>
                        <td>Comum</td>
                    </tr>
                    <tr>
                        <td>Artefato Sagrado</td>
                        <td>Comum</td>
                    </tr>
                </table>
            `;
            break;
        case 'Masmorra do Vórtice':
            rewards = `
                <table>
                    <tr>
                        <th>Item</th>
                        <th>Tipo</th>
                    </tr>
                    <tr>
                        <td>Circuito Supremo</td>
                        <td>Comum</td>
                    </tr>
                    <tr>
                        <td>Joia da Evolução</td>
                        <td>Comum</td>
                    </tr>
                    <tr>
                        <td>Lança da Justiça</td>
                        <td>Comum</td>
                    </tr>
                    <tr>
                        <td>Essência do Gelo</td>
                        <td>Comum</td>
                    </tr>
                    <tr>
                        <td>Halo da Luz</td>
                        <td>Comum</td>
                    </tr>
                    <tr>
                        <td>Mapa do Tesouro</td>
                        <td>Comum</td>
                    </tr>
                    <tr>
                        <td>Bastão de Bambú</td>
                        <td>Comum</td>
                    </tr>
                </table>
            `;
            break;
        case 'Masmorra do Tempo':
            rewards = `
                <table>
                    <tr>
                        <th>Item</th>
                        <th>Tipo</th>
                    </tr>
                    <tr>
                        <td>Relógio do Tempo</td>
                        <td>Comum</td>
                    </tr>
                    <tr>
                        <td>Pedra da Sabedoria</td>
                        <td>Comum</td>
                    </tr>
                    <tr>
                        <td>Pena do Falcão</td>
                        <td>Comum</td>
                    </tr>
                    <tr>
                        <td>Asas da Divindade</td>
                        <td>Comum</td>
                    </tr>
                    <tr>
                        <td>Corrente Sagrada</td>
                        <td>Comum</td>
                    </tr>
                    <tr>
                        <td>Olho da Tempestade</td>
                        <td>Comum</td>
                    </tr>
                </table>
            `;
            break;
        default:
            rewards = 'Nenhuma recompensa disponível.';
    }

    return rewards;
}


function startDungeon(dungeonName) {
    alert('Iniciando ' + dungeonName + '...');
}

function mostrarDungeons(tier) {
    const dungeonsContainer = document.getElementById('dungeons-container');
    dungeonsContainer.innerHTML = ''; // Limpa o container antes de carregar as novas dungeons

    let dungeons = [];

    if (tier === 'champion') {
        dungeons = [
            { nome: 'Masmorra da Tempestade', nivelMin: 10, tier: 'Champion' },
            { nome: 'Masmorra da Serenidade', nivelMin: 20, tier: 'Champion' },
            { nome: 'Masmorra do Labirinto', nivelMin: 30, tier: 'Champion' },
            { nome: 'Masmorra do Crepusculo', nivelMin: 40, tier: 'Champion' }
        ];
    } else if (tier === 'ultimate') {
        dungeons = [
            { nome: 'Masmorra das Profundezas', nivelMin: 50, tier: 'Ultimate' },
            { nome: 'Masmorra das Correntes', nivelMin: 60, tier: 'Ultimate' },
            { nome: 'Masmorra do Vórtice', nivelMin: 70, tier: 'Ultimate' },
            { nome: 'Masmorra do Tempo', nivelMin: 80, tier: 'Ultimate' }
        ];
    }

        // Gerar o HTML para cada dungeon
        dungeons.forEach(dungeon => {
            const dungeonCard = `
                <div class="dungeon-card">
                    <img src="./imagens/bannerMissao.jpg" alt="${dungeon.nome}" class="dungeon-image">
                    <div class="dungeon-info">
                        <h3>${dungeon.nome}</h3>
                        <p>Nível Mínimo: ${dungeon.nivelMin}</p>
                        <p>Tier: ${dungeon.tier}</p>
                        <button class="rewards-btn" onclick="openModal('${dungeon.nome}')">Ver Recompensas</button>
                        <button class="start-btn" onclick="startDungeon('${dungeon.nome}')">Iniciar Dungeon</button>
                    </div>
                </div>
            `;
            dungeonsContainer.innerHTML += dungeonCard;
        });
    }

// Carregar as dungeons de Champion por padrão ao carregar a página
document.addEventListener('DOMContentLoaded', function() {
    mostrarDungeons('champion');
});

window.onclick = function(event) {
    const modal = document.getElementById('rewardsModal');
    if (event.target == modal) {
        closeModal();
    }
}