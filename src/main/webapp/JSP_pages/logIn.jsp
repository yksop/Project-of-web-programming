<!DOCTYPE html>
<!-- signIn.jsp -->
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width">
  <link rel="icon" href="../Immagini%20progetto/Logo_Tum4World.png" type="image/icontype">
  <link rel="stylesheet" href="../css/signIn.css">
  <link rel="stylesheet" href="../css/template.css">
  <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,400;0,500;0,600;1,300&display=swap" rel="stylesheet">
  <title>Tum4World</title>
  <script rel="script" src="../JavaScript/changeCitations.js"></script>
  <script rel="script" src="../JavaScript/validateFormLogIn.js"></script>
</head>

<jsp:include page="../templates/intestazione.jsp"></jsp:include>
<jsp:include page="../templates/barra_navigazione.jsp"></jsp:include>

<!--FORM PER LOGIN-->
<div style="height:1200px; overflow-y: auto; z-index: 1;">

  <h1 style="color: #0e2ab7">LOGIN</h1>

<div id="formContainer" style="display: none">
  <p><b style="color: #f60505; font-size: 11px"><i>&Egrave; obbligatorio compilare tutti i campi richiesti</i></b></p>
  <form action="../LoginServlet" onsubmit="return validate();" id="form" name="personalInfo" method="POST">
      <br>
    <div class="form-group">
      <p class="istruzioni">Inserisci il tuo username</p>
      <label for="Username">Username:</label>
      <input type="text" id="Username" name="Username" placeholder="Username">
    </div>
    <br>
    <div class="form-group">
      <p class="istruzioni">Inserisci la tua password</p>
      <label for="Password">Password:</label>
      <input type="password" id ="Password" name="Password" placeholder="Password">
    </div>

    <div id="errorMessage" style="display: none; color: red; padding: 10px;"></div>

    <div>
      <br>
      <button type="submit" name="Send" value="Login">Conferma Login</button>
      <button type="reset" name="Reset" value="Cancella dati">Cancella dati</button>
    </div>

  </form>

</div>

  <div id="fast-way" style="display: none" >
  <form action="../LoginServlet">
    <div>
      <button type=submit name="Send" value="Login" style="margin: 50px">Login</button>
    </div>
  </form>
  </div>

</div>

<jsp:include page="../templates/mostraCitazioni.jsp"/>
<jsp:include page="../templates/footer.jsp"></jsp:include>

</html>