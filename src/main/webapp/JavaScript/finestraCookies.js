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
    //setCookie("cookieInfoShown", true, 1);
    document.getElementById("cookie-info").style.animation = "fadeOut 1s ease-in-out forwards, slideDown 1s ease-in-out forwards";
    document.cookie = "useCookie=true; path=/";
}

function rejectCookies() {
   //setCookie("cookieInfoShown", false, 1);
    document.getElementById("cookie-info").style.animation = "fadeOut 1s ease-in-out forwards, slideDown 1s ease-in-out forwards";
    sessionStorage.setItem("useCookie", "false");
}

// Mostra la finestra delle informazioni sui cookie quando la pagina è completamente caricata

window.addEventListener("load", showCookieInfo);

