

// Funzione per ottenere una citazione casuale dal file JSON
function getCitazioneCasuale() {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "../DataJSON/citazioni.json", true);
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
    xhr.send();
}

// Aggiorna la citazione iniziale
getCitazioneCasuale();

// Aggiorna la citazione ogni 20 secondi
setInterval(getCitazioneCasuale, 2000);

