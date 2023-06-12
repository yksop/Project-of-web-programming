<!--QUESTO CODICE è PER INSERIRE LA FINESTRA COOKIES
DA INSERIRE LA DIV PARENT CONTAINER E I DUE LINK AL CSS E JS IN OGNI PAGINA-->

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width">
  <link rel="stylesheet" href="../css/finestraCookies.css">
  <script src="../JavaScript/finestraCookies.js"></script>
  <title>Tum4World</title>

</head>
<body>

<!--COOKIES-->
<div id="parent-container">
<div id="cookie-info" class="show" style="margin-right: 75%">
  <div style="margin-top: 10px; margin-bottom: 10px;">
    Questo sito utilizza i cookie per migliorare la tua esperienza utente.<br>
    Accettando i cookie, acconsenti all'utilizzo dei cookie in conformit&agrave; alla nostra politica sulla privacy.
  </div>
  <a id="policy" href="${pageContext.request.contextPath}/JSP_pages/cookiesPolicy.jsp" target="_blank" >Politica sui cookie</a>
  <br>
  <button class="button accept" id="accept" onclick="acceptCookies()">Accetta</button>
  <button class="button reject" id="reject" onclick="rejectCookies()">Rifiuta</button>

</div>
</div>

</body>
</html>