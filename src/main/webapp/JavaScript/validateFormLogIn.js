function displayErrorMessage(message) {
    const errorMessageElement = document.getElementById("errorMessage");
    errorMessageElement.textContent = message;
    errorMessageElement.style.display = "block";
}

function control(){
    const user=document.getElementById("Username").value;
    const boxuser=document.getElementById("Username");



    if(user==""){
        displayErrorMessage("inserire il nome");
        boxuser.focus();
        boxuser.style.border= "2px solid red";
        return false;
    }


    const password=document.getElementById("Password").value;
    const passbox=document.getElementById("Password");
    passbox.style.border= "2px solid white";

    if(password==""){
        displayErrorMessage("inserire la password");
        passbox.focus();
        passbox.style.border= "2px solid red";
        return false;
    }


    if (!/\d/.test(password)) {
        displayErrorMessage("la password deve contenere almeno un carattere numerico");
        passbox.focus();
        passbox.style.border= "2px solid red";
        return false;
    }

    if (!/[A-Z]/.test(password)) {
        displayErrorMessage("la password deve contenere almeno un carattere maiuscolo");
        passbox.focus();
        passbox.style.border= "2px solid red";
        return false;
    }

    if (!/[$!?]/.test(password)) {
        displayErrorMessage("la password deve contenere uno tra i caratteri speciali '$', '!', '?'");
        passbox.focus();
        passbox.style.border= "2px solid red";
        return false;
    }


    else return true;

}


function validate() {
    if(document.cookie)
    if (control()) {
        return true;
    }
    return false;
}

window.addEventListener('load', function(){
    var loginCookie = document.cookie.includes('loginCookie');
    var encodedUsername = getQueryParam('encodedUsername');
    var encodedPassword = getQueryParam('encodedPassword');

// Mostra il contenitore corretto in base ai dati disponibili
    if (loginCookie || (encodedUsername && encodedPassword)) {
        document.getElementById('fast-way').style.display = 'block';
    } else{
        document.getElementById('formContainer').style.display = 'block';
    }

});

function getQueryParam(paramName) {
    var urlParams = new URLSearchParams(window.location.search);
    return urlParams.get(paramName);
}