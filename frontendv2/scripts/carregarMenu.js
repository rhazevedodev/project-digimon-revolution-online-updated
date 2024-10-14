document.addEventListener('DOMContentLoaded', () => {

    carregarMenu();

    function carregarMenu() {
        fetch('./subpagina/menu.html')
            .then(response => response.text())
            .then(data => {
                document.getElementById('menu').innerHTML = data;
                /*addEventListeners();*/
            })
            .catch(error => console.error('Error loading menu:', error));
    }

});