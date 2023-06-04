<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Aderente</title>

    <link rel="stylesheet" href="../css/aderente.css">
    <script rel="script" src="../JavaScript/aderente.js"></script>
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

    <h1 style="position: center">Bentornato!</h1>
    <button class="logout-button" onclick="window.location.href='signIn.jsp'">Logout</button>

    <div class="box">
        <div class="menu-button-cont">
            <button class="menu-button profile-button">Visualizza Dati Profilo</button>
            <button class="menu-button registration-button">Iscrizione alle attivit&agrave;</button>
            <button class="menu-button donation-button">Effettua una donazione</button>
            <button class="menu-button cancel-button">Cancella Iscrizione</button>
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
                <p style=" font-family: 'Arial'; font-size: 20px; color: orangered">Sei sicuro di voler cancellare l'iscrizione?</p>
                <div class="confirm-buttons">
                    <button class="buttonB confButton" onclick="cancellaIscrizione()">Conferma</button>
                    <button class="buttonB" onclick="goBack('cancel-data')">Indietro</button>
                </div>
            </div>

            <div id="confirmation-message" class="confirm-container" style="display: none; text-align: center; margin-top: 20px; font-size: 40px; color: lightseagreen"></div>

        </div>



    </div>



</div>



<script>

    const profileButton = document.querySelector('.profile-button');
    const registrationButton = document.querySelector('.registration-button');
    const donationButton = document.querySelector('.donation-button');
    const cancelButton = document.querySelector('.cancel-button');
    const profileData = document.getElementById('profile-data');
    const activityData = document.getElementById('activity-data');
    const cancelData = document.getElementById('cancel-data');
    const donationData = document.getElementById('donation-data');


    profileButton.addEventListener('click', function() {
        hideContentContainers();
        profileData.style.display = 'block';
    });

    registrationButton.addEventListener('click', function() {
        hideContentContainers();
        activityData.style.display = 'block';
    });

    donationButton.addEventListener('click', function() {
        hideContentContainers();
        donationData.style.display="block";
    });

    cancelButton.addEventListener('click', function() {
        hideContentContainers();
        cancelData.style.display = 'block';
    });


    function hideContentContainers() {
        const contentContainers = document.querySelectorAll('.content-container');
        contentContainers.forEach(function(container) {
            container.style.display = 'none';
        });
    }

    function cancellaIscrizione() {
        var confirmationMessage = document.getElementById('confirmation-message');
        confirmationMessage.innerText = 'Iscrizione cancellata';
        confirmationMessage.style.display = 'block';

        var xhr = new XMLHttpRequest();
        xhr.open('GET', '../RegServlet?action=delete', true); // Aggiungi l'azione "delete" come parametro nella richiesta
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                // Chiamata AJAX completata con successo
                setTimeout(function() {
                    window.location.href = 'signIn.jsp';
                }, 3000);
            }
        };

        xhr.send();
    }


    function sendDonation() {
        var donationAmount = document.getElementById('donation-input').value;
        var url = '../DonazioniServlet?action=aderente&donation=' + donationAmount;
        if (donationAmount !== '') {
            document.getElementById('donation-input').value = '';
            var xhr = new XMLHttpRequest();
            xhr.open('GET', url, true);
            xhr.onreadystatechange = function() {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    // Chiamata AJAX completata con successo
                    var messageDiv = document.getElementById('donation-message');
                    messageDiv.innerText = 'Grazie per aver donato!';
                    messageDiv.style.display = 'block';
                    setTimeout(function() {
                        messageDiv.style.display = 'none';
                    }, 3000);
                }
            };
            xhr.send();
        }
    }

    function goBack(button) {
        const cancelData = document.getElementById(button);
        cancelData.style.display = 'none';
    }



</script>


</body>
</html>
