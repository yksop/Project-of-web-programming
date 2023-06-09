<!DOCTYPE html>

<html>
    <head>
        <meta charset="UTF-8">
        <title>Pagina Contatti</title>
        <link rel="icon" href="../Immagini progetto/Logo_Tum4World.png" type="image/icontype">
        <link rel="stylesheet" href="../css/template.css">
        <link rel="stylesheet" href="../css/contatti_style.css">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,400;0,500;0,600;1,300&display=swap" rel="stylesheet">
        <script rel="script" src="../JavaScript/changeCitations.js"></script>
        <script rel="script" src="../JavaScript/validateContatti.js"></script>
        <jsp:include page="../templates/intestazione.jsp"></jsp:include>
        <jsp:include page="../templates/barra_navigazione.jsp"></jsp:include>
    </head>
    <body>
        <h1 id="h1contatti">Contatti</h1>
        <h2 id="h2contatti">Indirizzo mail: tum4world@nessunonoluogonoesiste.com<br>Numero di telefono: 1234543210</h2>
        <form action="../InvioEmail" onsubmit="return validateContatti()" method="post">
            <br>
            <label for="nome">Nome:</label>
            <input type="text" id="nome" name="nome"><br>
            <label for="cognome">Cognome:</label>
            <input type="text" id="cognome" name="cognome"><br><br>

            <label for="email">Indirizzo Email:</label>
            <input type="email" id="email" name="email"><br><br>

            <label for="motivo">Motivo di Contatto:</label>
            <select id="motivo" name="motivo">
                <option value="" disabled selected></option>
                <option value="collaborazione">collaborazione</option>
                <option value="domanda su attività">domanda su attivit&agrave;</option>
                <option value="informazioni">informazioni</option>
                <option value="altro">Altro</option>
            </select><br><br>

            <label for="dettagli">Dettagli della Richiesta:</label>
            <textarea id="dettagli" name="dettagli" rows="4" cols="50"></textarea><br><br>
            <div class="button-cont">
            <input type="submit" value="Invia">
            <input type="reset" value="Reset">
            </div>
        </form>


    <jsp:include page="../templates/mostraCitazioni.jsp"/>
    <jsp:include page="../templates/footer.jsp"></jsp:include>
    </body>
</html>
