<!DOCTYPE html>
<html>
<head>
    <title>Tum4world</title>
    <link rel="stylesheet" href="../css/amministratore.css">
    <link rel="icon" href="../Immagini%20progetto/Logo_Tum4World.png" type="image/icontype">
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
<div class="header" id="img"></div>
<div class="title" >
    <h1 id="welcome-message"></h1>
</div>
<button class="logout-button" onclick="logOut()">Logout</button>

<h2 class="sottotitolo">Operazioni Disponibili</h2>

<div class="box">

    <div class="menu-button-cont">
        <button id="UtentiRegistrati" onclick="getUtenti()">Utenti registrati</button>
        <button id="Simpatizzanti" onclick="getSimpatizzanti()">Simpatizzanti</button>
        <button id="Aderenti" onclick="getAderenti()" >Aderenti</button>
        <button onclick="visualizzareVisiteTot()">Visite</button>
        <button onclick="visualizzareVisite()">Visite per pagina</button>
        <button id="Donazioni" onclick="getDonazioni()">Donazioni</button>
        <button onclick="window.location.href='../index.jsp'">Torna al sito</button>
    </div>

    <div class="finestre">
        <div id="utenti-data" class="content-container ">
            <div id="elencoUtentiContainer"></div>
            <button class="buttonB" onclick="goBack('utenti-data')">Indietro</button>
        </div>

        <div id="donation-data" class="content-container">
            <div id="donationContainer"></div>
            <button class="buttonB" onclick="goBack('donation-data')">Indietro</button>
        </div>

        <div id="totvisit-data" class="content-container ">
            <div id="totalVisitsContainer" style="margin: 30px; color: saddlebrown; font-size: 40px; font-weight: bold"></div>
            <div id="reset-message" style="margin: 20px; color: saddlebrown; font-size: 30px; font-weight: bold"></div>
            <button id="reset-button" class="buttonB" onclick="resetVisite()">Reset</button><br>
            <button class="buttonB" onclick="goBack('totvisit-data')" style="margin-top: 15px;">Indietro</button>
        </div>

        <div id="visit-data" class="content-container ">
            <div id="visitsByPageContainer"></div>
            <button class="buttonB" onclick="goBack('visit-data')">Indietro</button>
        </div>

    </div>

</div>


<script>
    function logOut(){
        console.log("entrato in logout");
        var xhr = new XMLHttpRequest();
        xhr.open('GET', '../LoginServlet?action=logout', true);
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                console.log("chiamata con successo");
                xhr.response;
                window.location.href="logIn.jsp?message=Logout%20eseguito%20con%20successo";
            }
        };
        xhr.send();
    }
    function getUtenti() {
        hideContentContainers();
        document.getElementById("utenti-data").style.display = 'block';
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
            if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
                document.getElementById("elencoUtentiContainer").innerHTML = xmlhttp.responseText;
            }
        };
        xmlhttp.open("GET", "../ElencoRegServlet", true);
        xmlhttp.send();
    }

    function getSimpatizzanti() {
        hideContentContainers();
        document.getElementById("utenti-data").style.display = 'block';
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
            if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
                document.getElementById("elencoUtentiContainer").innerHTML = xmlhttp.responseText;
            }
        };
        xmlhttp.open("GET", "../ElencoRegServlet?type=simpatizzanti", true);
        xmlhttp.send();
    }

    function getAderenti() {
        hideContentContainers();
        document.getElementById("utenti-data").style.display = 'block';
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
            if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
                document.getElementById("elencoUtentiContainer").innerHTML = xmlhttp.responseText;
            }
        };
        xmlhttp.open("GET", "../ElencoRegServlet?type=aderenti", true);
        xmlhttp.send();
    }



    function visualizzareVisiteTot() {
        hideContentContainers();
        document.getElementById("totvisit-data").style.display = 'block';
        var xhr = new XMLHttpRequest();
        xhr.open('GET', '../Visite?action=totale', true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                var response = JSON.parse(xhr.responseText);
                document.getElementById('totalVisitsContainer').textContent = 'Visite totali: ' + response;
            }
        };

        xhr.send();
    }

    function resetVisite() {
        var xhr = new XMLHttpRequest();
        xhr.open('GET', '../Visite?action=reset', true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                var resetMessage = document.getElementById('reset-message');
                var totvisits = document.getElementById('totalVisitsContainer');
                var response = JSON.parse(xhr.responseText);
                totvisits.innerHTML="Visite totali: "+response;
                resetMessage.innerText = 'Visite resettate';
                resetMessage.style.display = 'block';
                setTimeout(function() {
                    resetMessage.style.display = 'none';
                }, 3000);
            }
        };
        xhr.send();
    }

    function visualizzareVisite() {
        hideContentContainers();
        document.getElementById("visit-data").style.display = 'block';
        var xhr = new XMLHttpRequest();
        xhr.open('GET', '../Visite?action=visite', true);
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

    function goBack(button) {
        const cancelData = document.getElementById(button);
        cancelData.style.display = 'none';
    }

    function hideContentContainers() {
        const contentContainers = document.querySelectorAll('.content-container');
        contentContainers.forEach(function(container) {
            container.style.display = 'none';
        });
    }


    function getDonazioni() {
        hideContentContainers();
        var cont= document.getElementById("donation-data");
        cont.style.display = 'block';
        var xhr = new XMLHttpRequest();
        xhr.open("GET", "../DonazioniServlet", true);
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                // La richiesta è stata completata con successo
                let dati = JSON.parse(xhr.responseText);
                creaIsto(dati);
            }
        };
        xhr.send();

    }

    function creaIsto(dati){
        var datiIstogramma = [];
        for (var i = 0; i < dati.length; i++) {
            var mese = dati[i].mese;
            var totaleDonazioni = dati[i].totale_donazioni;
            datiIstogramma.push([mese, totaleDonazioni]);
        }

        Highcharts.chart('donationContainer', {
            chart: {
                type: 'column'
            },
            title: {
                text: 'Totale donazioni per mese'
            },
            xAxis: {
                type: 'category',
                title: {
                    text: 'Mese'
                }
            },
            yAxis: {
                title: {
                    text: 'Totale donazioni'
                }
            },
            series: [{
                name: 'Donazioni',
                data: datiIstogramma
            }]
        });
    }


    document.addEventListener('DOMContentLoaded', function() {
        var xhr = new XMLHttpRequest();
        xhr.open('GET', '../RegServlet?action=welcome', true);
        xhr.setRequestHeader('Accept', 'text/plain');
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                var user_name = JSON.parse(xhr.responseText);
                var parts = user_name.split(":");
                var name = parts[1];
                var welcomeMessage = document.getElementById("welcome-message");
                if (getGender(name)) {
                    welcomeMessage.innerText = 'Benvenuta ' + name + '!';
                } else {
                    welcomeMessage.innerText = 'Benvenuto ' + name + '!';
                }

            }
        };
        xhr.send();
    });

    function getGender(name) {
        var lastCharacter = name.charAt(name.length - 1);
        var last=lastCharacter.toLowerCase();
        return last === 'a' || last === 'e';
    }

</script>

</body>
</html>
