<!DOCTYPE html>
<html>
<head>
    <title>Amministratore</title>
    <link rel="stylesheet" href="../css/amministratore.css">
    <script src="https://code.highcharts.com/highcharts.js"></script>
    <script src="../JavaScript/amministratore.js"></script>
    <style>
        .header {
            width: auto;
            height: 35vh;
            background: url("../Immagini progetto/Private/15.jpg") no-repeat center center;
            background-size: cover;
            margin: 0;
            padding: 0;
            position: relative;

        }
    </style>


</head>

        <body>


        <div class="header" id="img">
        </div>
        <div class="title">
            <h1>Bentornato Boss!</h1>
        </div>


        <h2>Operazioni Disponibili</h2>
        <button onclick="elencareUtenti()">Utenti registrati</button>
        <button onclick="elencareSimpatizzanti()">Simpatizzanti</button>
        <button onclick="elencareAderenti()">Aderenti</button>
        <button onclick="visualizzareVisiteTot()">Numero di visite</button>
        <button onclick="visualizzareVisite()">Visualizza visite per pagina</button>
        <button onclick="visualizzareDonazioni()">Donazioni</button>

        <div id="risultati"></div>

        <div id="totalVisitsContainer"></div>
        <div id="visitsByPageContainer"></div>
        <div id="resetButtonContainer"></div>



        <script>
            function elencareUtenti() {

            }

            function elencareSimpatizzanti() {

            }

            function elencareAderenti() {

            }

            function visualizzareDonazioni() {

            }

            function visualizzareVisiteTot() {
                var xhr = new XMLHttpRequest();
                xhr.open('GET', '/amministratore/visualizzareVisiteTot', true);
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        var response = JSON.parse(xhr.responseText);
                        document.getElementById('totalVisitsContainer').textContent = 'Visite totali: ' + response;
                    }
                };

                // Aggiungi il bottone "Reset Visite"
                var resetButton = document.createElement('button');
                resetButton.textContent = 'Reset Visite';
                resetButton.addEventListener('click', resetVisite);
                document.getElementById('resetButtonContainer').appendChild(resetButton);
                xhr.send();
            }


            function resetVisite() {
                var xhr = new XMLHttpRequest();
                xhr.open('GET', '/amministratore/resetVisite', true);
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        alert('Conto delle visite resettato con successo.');
                    }
                };
                xhr.send();
            }

            function visualizzareVisite() {
                var xhr = new XMLHttpRequest();
                xhr.open('GET', '/amministratore/visualizzareVisite', true);
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        var response = JSON.parse(xhr.responseText);
                        var visitsData = [];

                        for (var page in response) {
                            if (response.hasOwnProperty(page)) {
                                visitsData.push([page, response[page]]);
                            }
                        }

                        renderChart(visitsData);
                    }
                };
                xhr.send();
            }

            function renderChart(data) {
                Highcharts.chart('visitsByPageContainer', {
                    chart: {
                        type: 'column'
                    },
                    title: {
                        text: 'Visite per pagina'
                    },
                    xAxis: {
                        type: 'category',
                        title: {
                            text: 'Pagina'
                        }
                    },
                    yAxis: {
                        title: {
                            text: 'Visite'
                        }
                    },
                    series: [{
                        name: 'Visite',
                        data: data
                    }]
                });
            }

        </script>




    </body>
</html>
