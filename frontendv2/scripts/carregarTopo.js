document.addEventListener('DOMContentLoaded', () => {

    carregarTopo();

    function carregarTopo() {
        fetch('./subpagina/topo.html')
            .then(response => response.text())
            .then(data => {
                document.getElementById('topo').innerHTML = data;
                /*addEventListeners();*/
            })
            .catch(error => console.error('Error loading topo:', error));
    }

});