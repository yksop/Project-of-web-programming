<html>
<head>
    <meta charset="UTF-8">
    <title>Pagina di Conferma</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            background-color: #f7f7f7;

        }

        h1 {
            font-size: 38px;
            color: #333;
            margin: 10px;
            color: orange;

        }

        p {
            font-size: 20px;
            color: #666;
            margin: 10px;

        }
        .confMessage{
            margin: 10%;
            display: block;
            justify-content: center;
            align-items: center;
            height: auto;
            align-self: center;
            padding: 20px;
            border-radius: 30px;
            animation: slideUp 1s ease-in;
        }

        @keyframes fadeIn {
            0% { opacity: 0; }
            100% { opacity: 1; }
        }

        @keyframes slideUp {
            0% { transform: translateY(30px); opacity: 0; }
            100% { transform: translateY(0); opacity: 1; }
        }


        .container {
            height: 100px;
        }

        #paper-plane {
            background-image: url('../Immagini progetto/plane.png');
            position: fixed;
            z-index: 1;
            width: 50px;
            height: 50px;
            background-size: cover;
            animation: fly 2s linear,fadeOut 0.5s 1.7s forwards;
            transform-origin: center left;

        }
        @keyframes fadeOut {
            0% {
                opacity: 1;
            }
            100% {
                opacity: 0;
            }
        }


        @keyframes fly {
            0% {
                transform: translate(0vw, 100vh) rotate(0deg) scale(2);
            }
            100%{
                transform: translate(100vw, 0vh) rotate(0deg) scale(2);
                display: none;
            }

        }
    </style>
    <link rel="stylesheet" href="../css/template.css">
    <jsp:include page="../templates/intestazione.jsp"></jsp:include>
    <jsp:include page="../templates/barra_navigazione.jsp"></jsp:include>
</head>
<body>

<div class="container">
    <div id="paper-plane"></div>
</div>
    <div class="confMessage">
        <h1>Invio Confermato</h1>
        <p>Grazie per averci contattato!<br>Abbiamo ricevuto il tuo messaggio e ti contatteremo al più presto.</p>
    </div>


<script>

</script>
    <jsp:include page="../templates/footer.jsp"></jsp:include>
</body>
</html>
