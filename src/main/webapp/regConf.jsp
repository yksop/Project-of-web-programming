<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Registration Confirmation</title>
  <link rel="stylesheet" href="css/regConf_style.css">
  <link rel="icon" href="Immagini progetto/logo.jpg" type="image/icontype">
  <link rel="stylesheet" href="css/template_style.css">
  <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,400;0,500;0,600;1,300&display=swap" rel="stylesheet">
  <script rel="script" src="JavaScript/changeCitations.js"></script>
  <title>Tum4World</title>
</head>

<jsp:include page="templates/intestazione.jsp"></jsp:include>
<jsp:include page="templates/barra_navigazione.jsp"></jsp:include>

<div class="bannerConferma">
  <div class="container fade-in">
    <h1>REGISTRAZIONE CONFERMATA!</h1>
    <h2 class="slide-in">Benvenuto in Tum4World!</h2>
  </div>
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
        <div class="citazione" id="citazione" ></div>
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

<jsp:include page="templates/footer.jsp"></jsp:include>

</html>
