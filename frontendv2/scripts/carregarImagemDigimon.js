document.addEventListener('DOMContentLoaded', function() {

    const premiumAPIURL = 'http://localhost:8080/api/digimon/carregarImagemDigimon/'+localStorage.getItem('idDigimon');
    
    let dataImagemDigimon = {};
    
    function carregarImagemDigimon() {
    
        const requestOptions = {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        };
    
        fetch(premiumAPIURL, requestOptions)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Erro na rede, status: ' + response.status);
                }
                return response.json();
            })
            .then(data => {
                dataImagemDigimon = data;
                atualizarImagemDigimon();
            })
            .catch(error => {
                console.error('Erro ao fazer requisição:', error);
            });
        
    }

    async function atualizarImagemDigimon() {

        // Obtém o contêiner onde a imagem será adicionada
        const imageContainer = document.getElementById('image-container');

        // Remove qualquer imagem existente no contêiner
        while (imageContainer.firstChild) {
            imageContainer.removeChild(imageContainer.firstChild);
        }

        // Verifica se a URL da imagem está disponível
        if (dataImagemDigimon.url_imagem_digimon) {
            const imgElement = document.createElement('img');
            imgElement.src = dataImagemDigimon.url_imagem_digimon;
            imgElement.alt = "Imagem do Digimon";
            imgElement.width = 163;
            imgElement.height = 174;
            imgElement.className = "img-bordered";

            imageContainer.appendChild(imgElement);
        } else {
            console.error('URL da imagem não encontrada.');
        }
    }

    carregarImagemDigimon();

});