package Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name ="DonazioniServlet", value = "/DonazioniServlet")
public class DonazioniServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Ottieni i dati delle donazioni raggruppati per mese dal database
        List<String> donazioni = getDatiDonazioni();

        // Imposta il tipo di contenuto della risposta come testo
        response.setContentType("text/plain");

        // Scrivi i dati delle donazioni come risposta
        PrintWriter out = response.getWriter();
        for (String donazione : donazioni) {
            out.println(donazione);
        }
        out.close();
    }

    private List<String> getDatiDonazioni() {
        // Lista per memorizzare i dati delle donazioni raggruppati per mese
        List<String> donazioni = new ArrayList<>();

        // Collegati al database Derby
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/WEB");


            // Esegui la query per ottenere i dati delle donazioni raggruppati per mese
            String query = "SELECT MONTH(data) AS mese, SUM(donazione) AS totale_donazioni FROM donazioni GROUP BY MONTH(data)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            // Leggi i risultati della query e memorizza i dati nella lista
            while (rs.next()) {
                int mese = rs.getInt("mese");
                double totaleDonazioni = rs.getInt("totale_donazioni"); //da aggiungere \n????????
                String donazione = "Mese: " + mese + ", Totale Donazioni: " + totaleDonazioni;
                donazioni.add(donazione);
            }

            // Chiudi le risorse del database
            rs.close();
            pstmt.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return donazioni;
    }
}







