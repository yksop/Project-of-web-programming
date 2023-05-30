function validateContatti()
{
    var email=document.forms['personalInfoContatti'].email.value;
    if(email===''){alert("Devi indicare un indirizzo email"); return false;}
    if (/^\w+([\.-]?\w+)@\w+([\.-]?\w+)(\.\w{2,3})+$/.test(email))
    {
        alert("L'indirizzo email che hai inserito e' valido")
    }
    else {
        alert("L'indirizzo email che hai inserito non e' valido");
    }
    return false;
}