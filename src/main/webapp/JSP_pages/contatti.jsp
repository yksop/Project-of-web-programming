<!DOCTYPE html>

<html>
    <head>
        <meta charset="UTF-8">
        <title>Pagina Contatti</title>

        <link rel="stylesheet" href="../css/template.css">
        <link rel="stylesheet" href="../css/contatti.css">
        <link rel="icon" href="../Immagini%20progetto/Logo_Tum4World.png" type="image/icontype">
        <jsp:include page="../templates/intestazione.jsp"></jsp:include>
        <jsp:include page="../templates/barra_navigazione.jsp"></jsp:include>
    </head>
    <body>
        <h1>Contatti</h1>
        <h2>Indirizzo mail: tum4world@nessunonoluogonoesiste.com<br>Numero di telefono: 1234543210</h2>
        <form action="invioConfermatoPaginaContatti.jsp" method="post" style="margin-bottom: 60px;">
            <br>
            <label for="nomeCognome">Nome e Cognome:</label>
            <input type="text" id="nomeCognome" name="nomeCognome"><br><br>

            <label for="email">Indirizzo Email:</label>
            <input type="email" id="email" name="email"><br><br>

            <label for="motivo">Motivo di Contatto:</label>
            <select id="motivo" name="motivo">
                <option value="" disabled selected></option>
                <option value="opzione1">collaborazione</option>
                <option value="opzione2">domanda su attivit&agrave;</option>
                <option value="opzione3">informazioni</option>
                <option value="altro">Altro</option>
            </select><br><br>

            <label for="dettagli">Dettagli della Richiesta:</label>
            <textarea id="dettagli" name="dettagli" rows="4" cols="50"></textarea><br><br>
            <div class="button-cont">
            <input type="submit" value="Invia">
            <input type="reset" value="Reset">
            </div>
        </form>

        <script>
          //SMTP FITTIZIA E INVIO A MAIL tum4world@nessunonoluogonoesiste.com
        </script>

    <script rel="script" src="../JavaScript/changeCitations.js"></script>
    <jsp:include page="../templates/mostraCitazioni.jsp"/>
    <jsp:include page="../templates/footer.jsp"></jsp:include>
    </body>
</html>
