<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Tum4world</title>
    <link rel="icon" href="../Immagini%20progetto/Logo_Tum4World.png" type="image/icontype">
    <link rel="stylesheet" href="../css/aderente.css">
    <style>
        .sidebar {
            width: 25%;
            height: 100vh;
            background-image: url("../Immagini progetto/Private/14v.jpg");
            background-size: cover;
            background-position: center;
            z-index: 0;
        }
    </style>

</head>

<body>

<div class="sidebar"></div>

<div class="content">

    <h1 id="welcome-message" style="position: center"></h1>
    <button class="logout-button" onclick="logOut()">Logout</button>

    <div class="box">
        <div class="menu-button-cont">
            <button class="menu-button profile-button">Visualizza Dati Profilo</button>
            <button class="menu-button registration-button">Iscrizione alle attivit&agrave;</button>
            <button class="menu-button donation-button">Effettua una donazione</button>
            <button class="menu-button cancel-button">Cancella Iscrizione</button>
            <button class="menu-button" onclick="window.location.href='../index.jsp'">Torna al sito</button>
        </div>

        <div class="finestre">

            <div id="profile-data" class="content-container ">
                <ul>
                    <li>Nome:</li>
                    <li>Cognome:</li>
                    <li>Data di nascita:</li>
                    <li>Email:</li>
                    <li>Telefono:</li>
                    <li>Username:</li>
                    <li>Password:</li>
                </ul>
                <button class="buttonB" onclick="goBack('profile-data')">Indietro</button>
            </div>

            <div id="activity-data" class="content-container ">
                <input type="checkbox" id="volunteer"> <label class="checkbox-label" for="volunteer">Volontariato</label><br>
                <input type="checkbox" id="soup-kitchen"> <label class="checkbox-label" for="soup-kitchen">Mensa Poveri</label><br>
                <input type="checkbox" id="civil-help"> <label class="checkbox-label" for="civil-help">Aiuto Civile</label><br>
                <input type="checkbox" id="meetings"> <label class="checkbox-label" for="meetings">Riunioni</label><br>
                <button class="buttonB" onclick="goBack('activity-data')">Indietro</button>
            </div>

            <div id="donation-data" class="content-container" style="height: 350px">
                <input type="number" min="0" id="donation-input" placeholder="Inserisci la cifra in euro">
                <button id="donation-button" class="buttonB" onclick="sendDonation()">Invia donazione</button><br>
                <button class="buttonB" onclick="goBack('donation-data')">Indietro</button>
                <br>
                <br>
                <div id="donation-message" style="display: none; text-align: center; height: 30px; font-size: 30px; color: lightseagreen; "></div>
            </div>


            <div id="cancel-data" class="content-container ">
                <div id="iscrizone" class="confirm-buttons">
                    <p style=" font-family: 'Arial'; font-size: 20px; color: orangered">Sei sicuro di voler cancellare l'iscrizione?</p>
                    <button class="buttonB confButton" onclick="cancellaIscrizione()">Conferma</button>
                    <button class="buttonB" onclick="goBack('cancel-data')">Indietro</button>
                </div>
            </div>
            <p id="confirmation-message" class="content-container"></p>
        </div>
    </div>
</div>

<script rel="script" src="../JavaScript/aderente.js"></script>

</body>
</html>
