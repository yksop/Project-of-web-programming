<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <title>Tum4world</title>
    <link rel="stylesheet" href="../css/simpatizzante.css">
    <link rel="icon" href="../Immagini%20progetto/Logo_Tum4World.png" type="image/icontype">
</head>

<body>

    <h1 id="welcome-message" style="position: center"></h1>
    <div style="position: absolute; top: 10px; right: 10px;">
        <button class="button logout-button">Logout</button>
    </div>
    <div class="button-container">
        <button class="button profile-button">Visualizza Dati Profilo</button>
        <button class="button subscription-button">Iscrizione alle attivit&agrave;</button>
        <button id="cancelButton" class="button cancel-button">Cancella iscrizione</button>
        <button class="button" onclick="window.location.href='../index.jsp'">Torna al sito</button>
    </div>
    <div id="foot-distance-data">
        <div id="dataModal" class="confirmation-modal">
            <div id="data">
            </div>
            <button id="closeButton" class="button close-button">Chiudi</button>
        </div>
        <br>
    </div>
    <br>
    <div id="foot-distance-checkbox">
        <div id="activityModal" class="confirmation-modal">
            <ul class="activity-list">
                <li class="activity-list-item">
                    <label>
                        <input type="checkbox" name="activity" value="incontro"> Incontro
                    </label>
                </li>
                <li class="activity-list-item">
                    <label>
                        <input type="checkbox" name="activity" value="volontariato"> Volontariato
                    </label>
                </li>
                <li class="activity-list-item">
                    <label>
                        <input type="checkbox" name="activity" value="ricerca"> Ricerca
                    </label>
                </li>
                <li class="activity-list-item">
                    <label>
                        <input type="checkbox" name="activity" value="supporto"> Supporto
                    </label>
                </li>
            </ul>
            <button id="closeButtonCheckBox" class="button close-button">Chiudi</button>
        </div>
        <br>
    </div>

    <div id="foot-distance-conf">
        <div id="confirmationModal" class="confirmation-modal">
            <div class="message" id="confirmationMessage">Sei sicuro di voler cancellare la tua iscrizione?</div>
            <div class="buttons">
                <button id="confirmButton" class="button">SI, cancella iscrizione</button>
                <button id="cancelButtonModal" class="button">NO, torna indietro</button>
            </div>
        </div>
        <p id="confirmation-message" class="confirmation-modal message" style="color: #ffa500;"></p>
        <br>
        <br>
    </div>

</body>
<script rel="script" src="../JavaScript/simpatizzante.js"></script>

</html>