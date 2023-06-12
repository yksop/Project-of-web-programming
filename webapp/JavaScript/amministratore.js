
var images = ["../Immagini progetto/Private/12.jpg", "../Immagini progetto/Private/15.jpg", "../Immagini progetto/Private/22.jpg"
                , "../Immagini progetto/Private/27.jpg", "../Immagini progetto/Private/28.jpg", "../Immagini progetto/Private/32.jpg"
                , "../Immagini progetto/Private/33.jpg"];
var currentImage = 0;

function changeImage() {
    var header = document.querySelector('.header');
    currentImage = (currentImage + 1) % images.length;
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
            header.style.backgroundImage = 'url("' + images[currentImage] + '")';
            } else {
                console.log('Errore durante il caricamento dell\'immagine.');
            }
        }
    };
    xhr.open('GET', images[currentImage], true);
    xhr.send();
 }

setInterval(changeImage, 5000);






function logOut(){
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
    return last === 'a' || last === 'e' || last === 'y' || last === 'h'|| last === 'i';
}
