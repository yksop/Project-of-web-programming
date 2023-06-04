<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="Immagini progetto/logo.jpg" type="image/icontype">
    <link rel="stylesheet" type="text/css" href="../css/template.css">
    <link rel="stylesheet" type="text/css" href="../css/pagina_delle_singole_attivita.css">
    <script rel="script" src="../JavaScript/changeCitations.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,400;0,500;0,600;1,300&display=swap" rel="stylesheet">    <title>Tum4World</title>
  </head>
  <body>
    <jsp:include page="../templates/intestazione.jsp"/>
    <jsp:include page="../templates/barra_navigazione.jsp"/>

    <div class="corpo-attivita">
      <h1>Aiuto sfollati</h1>
      <div class="riga-corpo">
        <div class="colonna-corpo">
          <p>
            Siamo anche impegnati nell'aiutare gli sfollati nelle zone sopra citate.
            In queste regioni, conflitti armati, disastri naturali e altre crisi causano migliaia di persone costrette a lasciare le proprie case e ad affrontare situazioni di emergenza.
            Tum4World si impegna a fornire sostegno immediato agli sfollati, inclusa l'assistenza essenziale, la protezione e il supporto psicosociale.
         </p>
        </div>
      </div>
      <div class="riga-corpo">
        <div class="immagine-corpo">
          <img src="../Immagini%20progetto/volontariato1.jpg" alt="distribuzione cibo 1">
         </div>
      </div>
      <div class="riga-corpo">
        <div class="colonna-corpo">
          <p>
            Siamo sempre pronti ad intervenire in caso di emergenze umanitarie che causano lo sfollamento di persone,
            fornendo assistenza immediata agli sfollati, garantendo loro cibo, acqua potabile, riparo, assistenza medica di base e servizi igienico-sanitari.
            Questa assistenza di emergenza è vitale per salvaguardare la vita e la dignità delle persone coinvolte. 
            Svolgiamo anche l'identificazione e la registrazione gli sfollati per facilitare il riconoscimento tra le varie famiglie divise.
         </p>
        </div>
      </div>
      <div class="riga-corpo">
        <div class="immagine-corpo">
          <img src="../Immagini%20progetto/volontariato2.jpg" alt="distribuzione cibo 1">
         </div>
      </div>
      <div class="riga-corpo">
        <div class="colonna-corpo">
          <p>
            Infine, Tum4World si impegna anche nella fase di riabilitazione e ristabilimento degli sfollati.
            Ciò implica il supporto per il ritorno sicuro nelle loro comunità d'origine o il reinsediamento in nuove aree, fornendo assistenza per la ricostruzione delle abitazioni, la ripresa delle attività economiche e l'accesso a servizi essenziali come l'istruzione e l'assistenza sanitaria.         
          </p>
        </div>
      </div>
      <div class="riga-corpo">
        <div class="immagine-corpo">
          <img src="../Immagini%20progetto/volontariato3.jpg" alt="distribuzione cibo 1">
        </div>
      </div>
    </div>
    <jsp:include page="../templates/mostraCitazioni.jsp"/>
    <jsp:include page="../templates/footer.jsp"/>
  </body>
</html>