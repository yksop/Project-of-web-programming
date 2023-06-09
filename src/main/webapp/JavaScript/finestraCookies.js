// Funzione per controllare se la finestra delle informazioni sui cookie deve essere mostrata
function showCookieInfo() {
    if (document.cookie.includes('useCookie') || sessionStorage.getItem("useSession")) {
        document.getElementById("parent-container").remove();

    }
}

function acceptCookies() {
    document.getElementById("cookie-info").style.animation = "fadeOut 1s ease-in-out forwards, slideDown 1s ease-in-out forwards";
    document.cookie = "useCookie=true; path=/";
}

function rejectCookies() {
    document.getElementById("cookie-info").style.animation = "fadeOut 1s ease-in-out forwards, slideDown 1s ease-in-out forwards";
    sessionStorage.setItem("useSession","true");
}

// Mostra la finestra delle informazioni sui cookie quando la pagina è completamente caricata

window.addEventListener("load", showCookieInfo);

