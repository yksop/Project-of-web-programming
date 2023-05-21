<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="templates/intestazione.jsp"></jsp:include>
<jsp:include page="templates/barra_navigazione.jsp"></jsp:include>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="Immagini progetto/Logo_Tum4World.png" type="image/icontype">
    <link rel="stylesheet" type="text/css" href="css/template_style.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,400;0,500;0,600;1,300&display=swap" rel="stylesheet">
    <script rel="script" src="JavaScript/changeCitations.js"></script>
    <title>Tum4World</title>
</head>
<body>
    <div>
        <p id="p1">Il nostro numero di telefono e': +39 1234 56 78 910 <br>
        Mentre il nostro indirizzo e': Via della Riva 02000 Trento, Italia</p>
    </div>
    <div>
        <p><b><i>&Egrave; obbligatorio compilare tutti i campi contrassegnati con *</i></b></p>
        <form action="contattiValidation.jsp" onsubmit="return validate();" id="form" name="personalInfo" method="POST"><br>
            <div>
                <p class="istruzioni">Inserisci il tuo nome <b>*</b></p>
                <label>Nome:</label>
                <input type="text" id="Nome" name="Nome" placeholder="Il tuo nome" required>
            </div>

            <div>
                <p class="istruzioni">Inserisci il tuo cognnome <b>*</b></p>
                <label>Cognome:</label>
                <input type="text" id="Cognome" name="Cognome" placeholder="Il tuo cognome" required>
            </div>

            <div>
                <p class="istruzioni">Inserisci la tua E-mail <b>*</b></p>
                <label>E-mail:</label>
                <input type="text" id="Email" name="Email" placeholder="La tua E-mail" required>
            </div>

            <div>
                <p class="istruzioni">Qual'è il motivo per cui vuoi metterti in contatto con noi? <b>*</b></p>
                <div>
                    <input type="radio" id="curiosità" name="Choice" value="Curiosità" required>
                    <label>Curiosità</label>
                    <br>
                    <input type="radio" id="richiestainfo" name="Choice" value="Richiesta informazioni">
                    <label>Richiesta informazioni</label>
                    <br>
                    <input type="radio" id="altro" name="Choice" value="Altro">
                    <label>Altro</label>
                </div>
            </div>

            <div>
                <p class="istruzioni">Inserisci altri motivi per cui ci contatti</p>
                <input type="text area" id="Altre motivazioni" name="Altre motivazioni" placeholder="scrivi qua...">
            </div>

            <div>
                <br>
                <button type="submit" name="Send" value="Conferma invio">Conferma invio</button>
                <button type="reset" name="Reset" value="Cancella dati">Cancella dati</button>
            </div>

        </form>
    </div>
</body>
</html>

<jsp:include page="templates/footer.jsp"></jsp:include>
