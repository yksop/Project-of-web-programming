package Controllers;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;
import javax.mail.Authenticator;


@WebServlet(name = "InvioEmail", value = "/InvioEmail")
public class InvioEmail extends HttpServlet {
    /*String nome, cognome, email, messaggio, oggetto;
    public void invio(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        nome = request.getParameter("nome");
        cognome = request.getParameter("cognome");
        email = request.getParameter("email");
        oggetto = request.getParameter("motivo");
        messaggio = request.getParameter("dettagli");

        String email_ricevente = "tum4world@nessunonoluogonoesiste.com";
        String host = "smtp.nessunonoluogonoesiste.com";
        String username = email;
        String password = "admin";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", 587);
        Authenticator auth= new Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };

        Session session = Session.getInstance(properties,auth);

        /*
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email_ricevente));
            message.setSubject(oggetto);
            message.setText(nome + cognome + messaggio);

            response.sendRedirect("./JSP_pages/invioConfermatoPaginaContatti.jsp");
            Transport.send(message);
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }


    }
*/

}

