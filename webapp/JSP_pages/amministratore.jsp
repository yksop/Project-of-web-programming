<!DOCTYPE html>
<html>
<head>
    <title>Tum4world</title>
    <link rel="stylesheet" href="../css/amministratore.css">
    <link rel="icon" href="../Immagini%20progetto/Logo_Tum4World.png" type="image/icontype">
    <script src="https://code.highcharts.com/highcharts.js"></script>

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

<script src="../JavaScript/amministratore.js"></script>
<script>

</script>

</body>
</html>
