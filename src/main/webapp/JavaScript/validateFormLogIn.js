function displayErrorMessage(message) {
    const errorMessageElement = document.getElementById("errorMessage");
    errorMessageElement.textContent = message;
    errorMessageElement.style.display = "block";
}

function control(){
    const user=document.getElementById("Username").value;
    const boxuser=document.getElementById("Username");
    const password=document.getElementById("Password").value;
    const passbox=document.getElementById("Password");


    if(user===""){
        displayErrorMessage("inserire lo username");
        boxuser.focus();
        boxuser.style.border= "2px solid red";

        return false;
    }


    if(password===""){
        displayErrorMessage("inserire la password");
        passbox.focus();
        passbox.style.border= "2px solid red";
        return false;
    }

    if (password.length !== 8 && password!=="2Adm1n!") {
        displayErrorMessage("la password deve essere lunga 8 caratteri");
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

    //(case insensitive)
    if ((!/j/i.test(password) || !/d/i.test(password) || !/a/i.test(password)) && password!=="2Adm1n!") {
        displayErrorMessage("la password deve contenere i caratteri j,d,a. (indifferente se maiuscolo o minuscolo)");
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
    console.log("entrato in validate")
    if (control()) {
        return true;
    }
    return false;
}
