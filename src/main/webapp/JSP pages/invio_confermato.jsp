<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="Immagini progetto/logo.jpg" type="image/icontype">
    <link rel="stylesheet" type="text/css" href="../css/template_style.css">
    <link rel="stylesheet" type="text/css" href="../css/invioConfermato_style.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,400;0,500;0,600;1,300&display=swap" rel="stylesheet">    <title>Tum4World</title>
  </head>
  <body>
    <jsp:include page="../templates/intestazione.jsp"/>

    <div class="invioConfermato">
      <h1>Invio confermato</h1>
      <p>
        Il tuo messaggio è stato inviato con successo.
        Ti ricontatteremo il prima possibile.
      </p>
    </div>

    <jsp:include page="../templates/footer.jsp"/>
  </body>
</html>