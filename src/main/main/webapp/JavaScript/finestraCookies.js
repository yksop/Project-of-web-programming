function setCookie(name, value, days) {
    var expires = "";
    if (days) {
        var date = new Date();
        date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
        expires = "; expires=" + date.toUTCString();
    }
    document.cookie = name + "=" + value + expires + "; path=/";
}

// Funzione per ottenere il valore di un cookie
function getCookie(name) {
    var nameCookie = name + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) === ' ') c = c.substring(1, c.length);
        if (c.indexOf(nameCookie) === 0) return c.substring(nameCookie.length, c.length);
    }
    return null;
}

// Funzione per controllare se la finestra delle informazioni sui cookie deve essere mostrata
function showCookieInfo() {
    var cookieInfoShown = getCookie("cookieInfoShown");
    if (!cookieInfoShown) {
        setCookie("cookieInfoShown", true, 1); // scade dopo un giorno
    }
}

function acceptCookies() {
    setCookie("cookieInfoShown", true, 1);
    document.getElementById("cookie-info").style.animation = "fadeOut 1s ease-in-out forwards, slideDown 1s ease-in-out forwards";
    setTimeout(function() {
      document.getElementById("cookie-info").style.bottom= "-300px";//questo setimeout non funziona granchè
    }, 2000); // attesa di 2 secondi prima di spostare la finestra*/
}

function rejectCookies() {
    setCookie("cookieInfoShown", false, 1);
    document.getElementById("cookie-info").style.animation = "fadeOut 1s ease-in-out forwards, slideDown 1s ease-in-out forwards";
    setTimeout(function() {
      document.getElementById("cookie-info").style.bottom= "-300px";//questo setimeout non funziona granchè
    }, 2000); // attesa di 2 secondi prima di spostare la finestra*/
    //da usare e implementare l'url encoding come alternativa
}

// Mostra la finestra delle informazioni sui cookie quando la pagina è completamente caricata

window.addEventListener("load", showCookieInfo);