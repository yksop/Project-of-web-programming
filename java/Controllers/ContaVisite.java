package Controllers;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name ="ContaVisite", value = "/ContaVisite")
public class ContaVisite extends HttpServlet {
    private static final String DB_URL = "jdbc:derby://localhost:1527/WEB;create=true";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page = request.getParameter("path");
        //String page = request.getPathInfo(); // Ottieni il path della richiesta
        String visitedPage = getVisitedPage(page); // Ottieni la pagina visitata dalla URL
        if (visitedPage != null) {
            try {
                incrementVisits(visitedPage);
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("errore conta pagine\n");
            }
        }
        System.out.println("Conteggio visite aggiornato per la pagina '" + visitedPage + "'");
    }

    private String getVisitedPage(String path) {
        if (path != null) {
            if (path.contains("index")) {
                return "Home";
            } else if (path.contains("chi-siamo")) {
                return "Chi-siamo";
            } else if (path.contains("attivita")) {
                return "Attività";
            } else if (path.contains("contatti")) {
                return "Contatti";
            } else if (path.contains("logIn")) {
                return "LogIn";
            } else if (path.contains("signIn")) {
                return "SignIn";
            } else if (path.contains("PaginaAttivitaCibo")) {
                return "Cibo";
            } else if (path.contains("PaginaAttivitaAlberi")) {
                return "Alberi";
            } else if (path.contains("PaginaAttivitaVolontariato")) {
                return "Volontariato";
            }
        }
        return null;
    }

    private void incrementVisits(String page) throws SQLException {
        try { Class.forName("org.apache.derby.jdbc.ClientDriver");
              Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement select = conn.prepareStatement("SELECT visits FROM visits WHERE page = ?");
             PreparedStatement update = conn.prepareStatement("UPDATE visits SET visits = ? WHERE page = ?");

            select.setString(1, page);
            ResultSet rs = select.executeQuery();

            int visiteAttuali = 0;
            if (rs.next()) {
                visiteAttuali = rs.getInt("visits");
            }

            int visite = visiteAttuali + 1;


            update.setInt(1, visite);
            update.setString(2, page);
            update.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

