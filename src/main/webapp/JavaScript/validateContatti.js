function validateContatti() {
    var emailInput = document.getElementById('email');

    var emailRegex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

    if (emailInput.value.trim() === '') {
        alert("inserisci un indirizzo email")
        return false;
    } else if (!emailInput.value.match(emailRegex)) {
        alert("Inserisci un indirizzo email valido");
        return false;
    } else {
        return true;
    }
}
