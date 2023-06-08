<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="Immagini progetto/logo.jpg" type="image/icontype">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,400;0,500;0,600;1,300&display=swap" rel="stylesheet">    <title>Tum4World</title>
    <style>
        * {
            font-family: 'Poppins', sans-serif;
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        /* regole per il design dell'intestazione */

        .intestazione {
            background-color: #5B9BE1;
            padding: 2%;
            box-shadow: 5px 5px 15px -10px #000000;
        }

        .intestazione h1 {
            font-size: 40px;
            font-weight: 600;
            text-align: center;
        }

        /* regole per il design della barra di navigazione */

        .navigazione {
            display: flex;
            align-items: center;
            justify-content: center;
            padding-top: 15px;
        }

        nav ul li {
            list-style: none;
            display: inline-block;
            padding-left: 10px;
            padding-right: 10px;
        }

        nav ul li a {
            color: #5B9BE1;
            text-decoration: none;
        }

        /* regole per il design del footer */

        .footer {
            padding: 3%;
            bottom: 0;
            width: 100%;
            background-color: #5B9BE1;
            box-shadow: 5px 5px 42px -10px #000000;
        }

        .contenitore {
            font-weight: 300;
            text-align: center;
            margin: auto;
        }

        .contenitore ul {
            list-style: none;
        }

        .riga-footer {
            display: flex;
            justify-content: space-between;
            flex-wrap: wrap;
        }

        .colonna-footer {
            width: 20%;
            flex: auto;
        }

        .colonna-footer h4 {
            font-size: 18px;
            margin-bottom: 15px;
        }

        .colonna-footer li {
            font-size: 13px;
        }

        .invioConfermato {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            align-items: center;
            justify-content: center;
            text-align: center;
        }

        .footer {
            position: fixed;
        }
    </style>
</head>
<body>
<div class="intestazione">
    <h1>TUM4WORLD</h1>
</div>

<div class="invioConfermato">

    <h1>Invio confermato</h1>
    <p>
        Il tuo messaggio è stato inviato con successo.
        Ti ricontatteremo il prima possibile.
    </p>
</div>

<footer class="footer">
    <div class="contenitore">
        <div class="riga-footer">
            <div class="colonna-footer">
                <h4>Associazione</h4>
                <ul>
                    <li>Tum4World</li>
                </ul>
            </div>

            <div class="colonna-footer">
                <h4>Sede legale</h4>
                <ul>
                    <li>Via della Riva 02000 Trento, Italia</li>
                </ul>
            </div>
        </div>
    </div>
</footer>
</body>
</html>