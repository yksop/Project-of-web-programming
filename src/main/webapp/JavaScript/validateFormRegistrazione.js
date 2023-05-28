function displayErrorMessage(message) {
    const errorMessageElement = document.getElementById("errorMessage");
    errorMessageElement.textContent = message;
    errorMessageElement.style.display = "block";
    //alert(message);
}

function controllaPassword() {
    const password=document.getElementById("Password").value;
    const conferma=document.getElementById("ConfPass").value;
    const passbox=document.getElementById("Password");
    const confpassbox=document.getElementById("ConfPass");
    confpassbox.style.border= "2px solid white";
    passbox.style.border= "2px solid white";

    if(password === "") {
        displayErrorMessage("Immetti la password");
        passbox.focus();
        passbox.style.border= "2px solid red";
        return false;
    }

    if (password.length !== 8) {
        displayErrorMessage("la password deve essere lunga 8 caratteri");
        passbox.focus();
        passbox.style.border= "2px solid red";
        return false;
    }

    //(case insensitive)
    if (!/j/i.test(password) || !/d/i.test(password) || !/a/i.test(password)) {
        displayErrorMessage("la password deve contenere i caratteri j,d,a. (indifferente se maiuscolo o minuscolo)");
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

        if(password !== conferma){
            displayErrorMessage("La password confermata è diversa da quella scelta, controllare.");
            confpassbox.focus();
            confpassbox.style.border= "2px solid red";
            return false;
        }
    // La password ha superato tutti i controlli
    else {return true}
}

function controllaData(){
    const data=document.getElementById("Date").value;
    const dataInserita=new Date(data);
    const dataAttuale=new Date();
    const diff = dataAttuale.getFullYear() - dataInserita.getFullYear();

    if (diff < 18) {
        displayErrorMessage("La registrazione è consentita solo a utenti maggiorenni.\nCi scusiamo per il disagio.");
        return false;
    }
}

function validateFormRegistrazione(){
    return(controllaData()&&controllaPassword());
}