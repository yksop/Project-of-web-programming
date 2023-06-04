<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="../Immagini progetto/Logo_Tum4World.png" type="image/icontype">
    <link rel="stylesheet" type="text/css" href="../css/template.css">
    <link rel="stylesheet" type="text/css" href="../css/attivita.css">
    <script rel="script" src="../JavaScript/changeCitations.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,400;0,500;0,600;1,300&display=swap" rel="stylesheet">    <title>Tum4World</title>
  </head>
  <body>
    <jsp:include page="../templates/intestazione.jsp"/>
    <jsp:include page="../templates/barra_navigazione.jsp"/>

    <div class="attivita">
      <h1>Distribuzione cibo</h1>
      <div class="riga-attivita">
        <div class="colonna-attivita">
          <p>
            Tum4World è un'organizzazione umanitaria impegnata nella distribuzione di cibo in diverse regioni del mondo, tra cui l'America Latina, l'Asia e l'Africa.
            Attraverso un approccio globale e collaborativo, cii impegnamo a fornire sostegno alimentare alle comunità più vulnerabili e affrontare l'insicurezza alimentare diffusa in queste regioni.
            Per riuscire in questa impresa lavoriamo a stretto contatto con organizzazioni locali, governi e altre parti interessate per identificare le comunità più bisognose. Questa fase è cruciale per garantire che i bisogni alimentari delle persone siano adeguatamente valutati e che le risorse siano dirette alle aree più colpite.
            Per saperne di più clicca <a href="PaginaAttivitaCibo.jsp">qui</a> oppure sull'immagine qui accanto.
          </p>
        </div>
        <div class="colonna-attivita">
          <a href="PaginaAttivitaCibo.jsp"><img src="../Immagini%20progetto/cibo.jpg" alt="Cibo in beneficenza"></a>
        </div>
      </div>
      <h1>Alberi piantati</h1>
      <div class="riga-attivita">
        <div class="colonna-attivita">
          <a href="PaginaAttivitaAlberi.jsp"><img src="../Immagini%20progetto/pianta1.jpg" alt="Foto volontariato"></a>
        </div>
        <div class="colonna-attivita">
          <p>
            Come organizzazione umanitaria siamo impegnati anche nella protezione dell'ambiente e nella promozione dello sviluppo sostenibile. Per questo, svolge un ruolo fondamentale, piantare alberi in America Latina, Asia e Africa. 
            Attraverso il nostro impegno nel ripristino forestale e nella conservazione degli ecosistemi, miriamo a mitigare il cambiamento climatico e preservare la biodiversità, fornendo sostegno alle comunità locali. 
            Per saperne di più clicca <a href="PaginaAttivitaAlberi.jsp">qui</a> oppure sull'immagine qui accanto.          </p>
        </div>
      </div>
      <h1>Aiuto sfollati</h1>
      <div class="riga-attivita">
        <div class="colonna-attivita">
          <p>
            Siamo anche impegnati nell'aiutare gli sfollati nelle zone sopra citate.
            In queste regioni, conflitti armati, disastri naturali e altre crisi causano migliaia di persone costrette a lasciare le proprie case e ad affrontare situazioni di emergenza.
            Tum4World si impegna a fornire sostegno immediato agli sfollati, inclusa l'assistenza essenziale, la protezione e il supporto psicosociale.
            Per saperne di più clicca <a href="PaginaAttivitaVolontariato.jsp">qui</a> oppure sull'immagine qui accanto.
          </p>
        </div>
        <div class="colonna-attivita">
          <a href="PaginaAttivitaVolontariato.jsp"><img src="../Immagini%20progetto/volontariato1.jpg" alt="Foto volontariato"></a>
        </div>
      </div>
    </div>
    <jsp:include page="../templates/mostraCitazioni.jsp"/>
    <jsp:include page="../templates/footer.jsp"/>
  </body>
</html>