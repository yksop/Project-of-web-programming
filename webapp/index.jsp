<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="Immagini%20progetto/Logo_Tum4World.png" type="image/icontype">
    <link rel="stylesheet" href="css/home-page.css">
    <link rel="stylesheet" href="css/template.css">
    <link rel="stylesheet" href="css/finestraCookies.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,400;0,500;0,600;1,300&display=swap" rel="stylesheet">
    <script src="JavaScript/finestraCookies.js"></script>
    <title>Tum4World</title>
</head>
<body style="overflow-y: auto; pointer-events: none">

    <jsp:include page="templates/intestazione.jsp"/>
    <jsp:include page="templates/barra_navigazione.jsp"/>
    <div style="margin: 20px; align-content: center; display: flex; flex-direction: column; align-items: center; text-align: center; font-size: 15px;">
        <img id="img1" alt="img1" src="Immagini progetto/Logo_Tum4World.png" style="height: 100px; align-content: center;">
        <div style="width: 1200px; margin-top:20px;">
            <h2 style="margin-bottom:5px; font-size: 25px; font-weight: bold; color: darkcyan">Ciao! Noi siamo Tum4World!</h2>
            <p style="font-weight: bold">Un'associazione non-profit che ha l'obiettivo di rendere il mondo un posto migliore.</p><br><a href="JSP_pages/chi-siamo.jsp">Siamo</a> nati nel
            2016 da un'idea del nostro fondatore Chris Formag, e da allora non abbiamo smesso di aiutare persone ed animali in giro per la Terra!<br>
            Ad oggi siamo <a href="JSP_pages/attivita.jsp">attivi</a> nelle zone del mondo meno fortunate, in particolare siamo presenti in Africa, Asia del Sud-est e nel centro America.<br>
            Siamo contenti che tu sia capitato sulla nostra pagine web oggi.<br><br>Come puoi vedere, un men&ugrave; per navigare all'interno delle pagine &egrave;
            al tuo servizio, come anche la possibilit&agrave; di <a href="JSP_pages/signIn.jsp">iscriverti</a>, se non lo hai ancora fatto. <br>Speriamo vivamente che tu possa
            trovare cio&apos; che ti serva.
            <br><br>
            Se hai bisogno di parlare direttamente con noi, non esitare a <a href="JSP_pages/contatti.jsp">contattarci</a>, tutte le informazioni
            di cui hai bisogno sono in fondo ad ogni pagina.<br><br>
            <h2 style="margin-bottom:10px; font-size: 20px; font-weight: bold; color: darkcyan">Buona navigazione da parte di Tum4World!!!</h2>
            <p style="font-weight: bold; margin-bottom: 10px">Qui puoi scaricare il nostro volantino informativo:</p>
            <a id="bottoneVolantino" href = "Immagini progetto/volantino.pdf" Download = "Volantino">
                <button type = "button" class="buttonB"> Scarica il volantino </button>
            </a>
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

   getCitazioneCasuale();
    // Aggiorna la citazione ogni 20 secondi
    setInterval(getCitazioneCasuale, 20000);


    document.addEventListener("DOMContentLoaded", function() {
        var path = window.location.pathname;
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
            }
        };
        xhr.open("GET", "ContaVisite?path="+ "index" , true);
        xhr.send();
    });

</script>

</html>