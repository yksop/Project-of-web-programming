<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="/Immagini progetto/Logo_Tum4World.png" type="image/icontype">
    <link rel="stylesheet" href="css/home-page.css">
    <link rel="stylesheet" href="css/template.css">
    <link rel="stylesheet" href="css/finestraCookies.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,400;0,500;0,600;1,300&display=swap" rel="stylesheet">
    <script src="JavaScript/finestraCookies.js"></script>
    <title>Tum4World</title>
</head>
<body style="overflow-y: auto">
<jsp:include page="templates/intestazione.jsp"/>
<jsp:include page="templates/barra_navigazione.jsp"/>
<div style="margin: 20px">
    <div style="display: flex; justify-content: space-between">
    <img id="img1" alt="img1" src="Immagini progetto/Logo_Tum4World.png" style="height: 100px; align-content: center;">
    <div style="margin: 10px; text-align: left; align-content: center">
        Ciao! Noi siamo Tum4World!<br>Un'associazione non-profit che ha l'obiettivo di rendere il mondo un posto migliore. Siamo nati nel
        2016 da un'idea del nostro fondatore Chris Formag, e da allora non abbiamo smesso di aiutare persone ed animali in giro per la Terra!<br>
        Ad oggi siamo attivi nelle zone del mondo meno fortunate, in particolare siamo presenti in Africa, Asia del Sud-est e nel centro America.
        Siamo contenti che tu sia capitato sulla nostra pagine web oggi. Come puoi vedere, un men&ugrave; per navigare all'interno delle pagine &egrave;
        al tuo servizio, come anche la possibilit&agrave; di iscriverti se non lo hai ancora fatto. <br>Speriamo vivamente che tu possa
        trovare cio&apos; che ti serva.
    </div>
    </div>
    <div style="display: flex; justify-content: space-between">
        <div id="p1" style="text-align: left; align-content: center">
            Se hai bisogno di parlare direttamente con noi, non esitare a contattarci, tutte le informazioni
            di cui hai bisogno sono in fondo ad ogni pagina.<br>Buona navigazione da parte di Tum4World!!!<br>
            Qui puoi scaricare il nostro volantino informativo:<br>
            <a id="bottoneVolantino" href = "Immagini progetto/volantino.pdf" Download = "Volantino">
                <button type = "button" class="buttonB"> Scarica il volantino </button>
            </a>
        </div>
        <img id="img2" alt="img2" src="Immagini progetto/immagine_homepage.jpg" style=" height: 180px; align-content: center">
    </div>
</div>
<jsp:include page="templates/mostraCitazioni.jsp"/>
<jsp:include page="templates/footer.jsp"/>
<jsp:include page="JSP_pages/provafinestra.jsp"/>
</body>

<script>

    // Funzione per ottenere una citazione casuale dal file JSON
    function getCitazioneCasuale() {
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                console.log("entrato");
                var data = JSON.parse(xhr.responseText);
                var citazioni = data.citazioni;
                var indice = Math.floor(Math.random() * citazioni.length);
                var citazione = citazioni[indice];
                var citazioneDiv = document.getElementById("citazione");
                citazioneDiv.innerHTML = citazione;
            }
        };
        xhr.open("GET", "DataJSON/citazioni.json", true);
        xhr.send();
    }

    // Aggiorna la citazione iniziale
    getCitazioneCasuale();

    // Aggiorna la citazione ogni 20 secondi
    setInterval(getCitazioneCasuale, 2000);
</script>

</html>