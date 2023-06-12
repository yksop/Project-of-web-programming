<!DOCTYPE html>
<!-- signIn.jsp -->
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width">
  <link rel="icon" href="../Immagini%20progetto/Logo_Tum4World.png" type="image/icontype">
  <link rel="stylesheet" href="../css/template.css">
  <link rel="stylesheet" href="../css/signIn.css">
  <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,400;0,500;0,600;1,300&display=swap" rel="stylesheet">
  <title>Tum4World</title>
  <script rel="script" src="../JavaScript/validateFormRegistrazione.js"></script>
</head>

<jsp:include page="/templates/intestazione.jsp"></jsp:include>
<jsp:include page="../templates/barra_navigazione.jsp"></jsp:include>

<!--FORM PER ISCRIZIONE-->
<div style="height:1200px; overflow-y: auto; z-index: 1;" ;>

    <h1 style="color: #0e2ab7">Crea il tuo account</h1>

    <p><b style="color: #f60505"><i>&Egrave; obbligatorio compilare tutti i campi richiesti</i></b></p>
    <form  action="../RegServlet" onsubmit="return validate();" id="form" name="personalInfo" method="POST">
        <div class="form-group">
            <label for="Name">Nome:</label>
            <input type="text" id="Name" name="Name" placeholder="Nome" >
        </div>

        <div class="form-group">
            <label for="LastName">Cognome:</label>
            <input type="text" id ="LastName" name="LastName" placeholder="Cognome" >
        </div>

        <div class="form-group">
            <label for="Date">Data di nascita:</label>
            <input type="date" id="Date" name="Date" placeholder="Data di nascita" >
        </div>

        <div class="form-group">
            <label for="Email">Email:</label>
            <input type="email" id="Email" name="Email" placeholder="Email" >
        </div>

        <div class="form-group">
            <label for="Tel">Numero di telefono:</label>
            <input type="tel" id ="Tel" name="Tel" placeholder="Telefono" >
        </div>

        <div class="form-group">
            <p class="istruzioni">Scegli se vuoi essere simpatizzante o aderente</p>
            <div class="radio-group">
                <input type="radio" id="simp" name="Choice" value="Simpatizzante" checked>
                <label for="simp">Simpatizzante</label>
                <br>
                <input type="radio" id="aderente" name="Choice" value="Aderente">
                <label for="aderente">Aderente</label>
            </div>
        </div>

        <div class="form-group">
            <p class="istruzioni">Scegli uno username</p>
            <label for="Username">Username:</label>
            <input type="text" id="Username" name="Username" placeholder="Username" >
        </div>

        <div class="form-group">
            <p class="istruzioni">Scegli una password</p>
            <ul>Parametri richiesti:
                <li>deve essere di 8 caratteri</li>
                <li>deve contenere i caratteri j,d,a (maiuscoli o minuscoli)</li>
                <li>deve contenere almeno un carattere numerico, uno maiuscolo, e uno tra i caratteri speciali '$','!','?'</li>
            </ul>
            <label for="Password">Password:</label>
            <input type="password" id ="Password" name="Password" placeholder="Password" >
        </div>

        <div class="form-group">
            <p class="istruzioni">Conferma la password</p>
            <label for="ConfPass">Password:</label>
            <input type="password" id ="ConfPass" name="ConfPass" placeholder="Password" >
        </div>

        <div id="errorMessage" style="display: none; color: red; padding: 10px;"></div>

        <div>
            <br>
            <button type="submit" name="Send" value="Conferma Registrazione">Conferma Registrazione</button>
            <button type="reset" name="Reset" value="Cancella dati">Cancella dati</button>
        </div>

    </form>


    <script>
        window.onload = function() {
            var urlParams = new URLSearchParams(window.location.search);
            var errorMessage = urlParams.get('errorMessage');
            if (errorMessage) {
                var errorMessageElement = document.getElementById('errorMessage');
                errorMessageElement.innerText = errorMessage;
                errorMessageElement.style.display = 'block';
            }
        };
    </script>

</div>
<script rel="script" src="../JavaScript/changeCitations.js"></script>
<script rel="script" src="../JavaScript/visite.js"></script>
<jsp:include page="../templates/mostraCitazioni.jsp"/>
<jsp:include page="../templates/footer.jsp"></jsp:include>

</html>