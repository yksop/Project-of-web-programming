<!DOCTYPE html>

<html>
    <head>
        <meta charset="UTF-8">
        <title>Pagina Contatti</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f2f2f2;
            }

            h1 {
                text-align: center;
                font-size: 42px;
                color: darkslategrey;
                margin-top: 5%;
            }

            h2 {
                text-align: center;
                font-size: 15px;
                color: darkslategrey;
                margin-bottom: 2%;
                line-height: 1.5;
            }

            form {
                max-width: 600px;
                margin: 10px auto;
                padding: 10px;
                background-color: rgba(135, 206, 250, 0.08);
                border-radius: 40px;
                box-shadow: 0 0 20px rgba(0, 0, 0, 0.3);
            }

            label {
                margin-left: 5%;
                display: block;
                margin-top: 15px;
                font-size: 17px;
                color: darkslategrey;
            }

            input[type="text"],
            input[type="email"],
            select,
            textarea {
                width: 90%;
                margin-left: 5%;
                background-color: rgb(255, 255, 255);
                border: 1px solid #ccc;
                border-radius: 4px;
                font-size: 16px;
            }

            textarea {
                resize: vertical;
            }

            .button-cont{
                margin-left: 25%;
            }
            input[type="submit"],
            input[type="reset"] {
                display: inline-block;
                padding: 10px 20px;
                margin: 20px;
                background-color: cadetblue;
                color: #fff;
                border: none;
                border-radius: 50px;
                font-size: 18px;
                cursor: pointer;
                transition: background-color 0.3s ease;
                width: 100px;

            }

            input[type="submit"]:hover,
            input[type="reset"]:hover {
                background-color: #4caf50;
                animation-name: buttonAnimation;
                animation-duration: 1s;
                animation-iteration-count: infinite;

            }

            input[type="submit"]:focus,
            input[type="reset"]:focus {
                outline: none;
            }

            @keyframes buttonAnimation {
                0% { transform: translateY(0); }
                50% { transform: translateY(-5px); }
                100% { transform: translateY(0); }
            }
        </style>
        <link rel="stylesheet" href="../css/template.css">
        <jsp:include page="../templates/intestazione.jsp"></jsp:include>
        <jsp:include page="../templates/barra_navigazione.jsp"></jsp:include>
    </head>
    <body>
        <h1>Contatti</h1>
        <h2>Indirizzo mail: tum4world@nessunonoluogonoesiste.com<br>Numero di telefono: 1234543210</h2>
        <form action="invioConfermatoPaginaContatti.jsp" method="post">
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


    </body>
    <jsp:include page="../templates/footer.jsp"></jsp:include>
</html>
