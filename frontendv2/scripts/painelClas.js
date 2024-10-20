// Exemplo de dados do clã (substitua por dados reais da API ou banco de dados)
const clanData = {
    name: "Clã dos Dragões",
    description: "Este clã é dedicado a explorar e conquistar o mundo dos Digimons.",
    members: [
        { name: "Membro 1", level: 97, tier: "Mega", role: "Líder" },
        { name: "Membro 2", level: 42, tier: "Ultimate", role: "Vice-Líder" },
        { name: "Membro 3", level: 6, tier: "Rookie", role: "Membro" },
    ],
    pendingInvites: [
        { nickname: "Agumon Terrorista", level: 23, tier: "Champion" },
        { nickname: "TerrorDelas", level: 71, tier: "Mega" },
    ],
    maxMembers: 10,
    level: 5,
    entryRequirements: "Nível 5 e acima",
};

// Função para carregar dados do clã
function loadClanData() {
    document.getElementById("clan-name").innerText = clanData.name;
    document.getElementById("current-members").innerText = clanData.members.length;
    document.getElementById("max-members").innerText = clanData.maxMembers;
    document.getElementById("clan-level").innerText = clanData.level;
    document.getElementById("entry-requirements").innerText = clanData.entryRequirements;
    document.getElementById("clan-description").innerText = clanData.description;

    const memberList = document.getElementById("member-list");
    clanData.members.forEach(member => {
        const row = document.createElement("tr");
        row.innerHTML = `<td>${member.name}</td><td>${member.level}</td><td>${member.tier}</td><td>${member.role}</td>`;
        memberList.appendChild(row);
    });

    const pendingInvites = document.getElementById("pending-invites");
    clanData.pendingInvites.forEach(invite => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${invite.nickname}</td>
            <td>${invite.level}</td>
            <td>${invite.tier}</td>
            <td>
                <button onclick="acceptInvite('${invite.nickname}')">Aceitar</button>
                <button onclick="declineInvite('${invite.nickname}')">Recusar</button>
            </td>`;
        pendingInvites.appendChild(row);
    });
}

// Função para aceitar convite
function acceptInvite(nickname) {
    alert(`Convite de ${nickname} aceito!`);
    // Lógica para aceitar o convite
}

// Função para recusar convite
function declineInvite(nickname) {
    alert(`Convite de ${nickname} recusado!`);
    // Lógica para recusar o convite
}

// Função para abrir a loja do clã
function openClanStore() {
    alert("A loja do clã ainda não está implementada.");
}

// Função para visualizar eventos do clã
function viewClanEvents() {
    alert("Os eventos do clã ainda não estão implementados.");
}

// Carrega os dados do clã ao iniciar a página
document.addEventListener("DOMContentLoaded", loadClanData);
