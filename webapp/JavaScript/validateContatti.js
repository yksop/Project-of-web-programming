function validate(){
const email=document.getElementById("Email").value;
const boxemail=document.getElementById("Email");

    if(email===""){
        displayErrorMessage("inserire l'email");
        boxemail.focus();
        boxemail.style.border= "2px solid red";
        return false;
    }


    if (!/^\w+([\.-]?\w+)@\w+([\.-]?\w+)(\.\w{2,3})+$/.test(email)) {
        displayErrorMessage("inserire un formato di email valido");
        boxemail.focus();
        boxemail.style.border= "2px solid red";
        return false;
    }
}


function displayErrorMessage(message) {
    const errorMessageElement = document.getElementById("errorMessage");
    errorMessageElement.textContent = message;
    errorMessageElement.style.display = "block";
}
