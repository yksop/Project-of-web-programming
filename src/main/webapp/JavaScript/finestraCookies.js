let sceltaPresa = false;

function setCookie(name, value) {
    var expires;
    var date = new Date();
    date.setTime(date.getTime() + (2 * 24 * 60 * 60));
    expires = "; expires=" + date.toUTCString();
    document.cookie = name + "=" + value + expires + "; path=/";
}

// Funzione per controllare se la finestra delle informazioni sui cookie deve essere mostrata
function showCookieInfo() {
    if (document.cookie.includes('useCookie')) {
        document.getElementById("parent-container").remove();
    }
}

function acceptCookies() {
    sceltaPresa = true;
    document.getElementById("cookie-info").style.animation = "fadeOut 1s ease-in-out forwards, slideDown 1s ease-in-out forwards";
    document.cookie = "useCookie=true; path=/";
}

function rejectCookies() {
    sceltaPresa = true;
    document.getElementById("cookie-info").style.animation = "fadeOut 1s ease-in-out forwards, slideDown 1s ease-in-out forwards";
    sessionStorage.setItem("useCookie", "false");
}

window.addEventListener('beforeunload', function(event) {
    if (!sceltaPresa) {
        event.preventDefault();
        event.returnValue = '';
    }
});

// Mostra la finestra delle informazioni sui cookie quando la pagina è completamente caricata

window.addEventListener("load", showCookieInfo);
