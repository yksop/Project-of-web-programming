package Controllers;

import com.google.gson.Gson;

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
import java.time.LocalDate;
import Models.Donazione;

@WebServlet(name ="DonazioniServlet", value = "/DonazioniServlet")
public class DonazioniServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String source = request.getParameter("action");

        if (source != null && source.equals("aderente")) {
            String donationAmount = request.getParameter("donation");

            if (donationAmount != null && !donationAmount.isEmpty()) {
                try {

                    Class.forName("org.apache.derby.jdbc.ClientDriver");
                    Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/WEB");
                    String query = "INSERT INTO donazioni (data, donazione) VALUES (?, ?)";
                    PreparedStatement stmt = conn.prepareStatement(query);
                    LocalDate currentDate = LocalDate.now();
                    int currentMonth = currentDate.getMonthValue();
                    int donazione=Integer.parseInt(donationAmount);
                    stmt.setInt(1, currentMonth);
                    stmt.setInt(2, donazione);

                    stmt.executeUpdate();

                    stmt.close();
                    conn.close();
                    // Invio della risposta HTTP con stato 200 (OK)
                    response.setStatus(HttpServletResponse.SC_OK);
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                    // Invio della risposta HTTP con stato 500 (Internal Server Error) in caso di errore nel database
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                }
            } else {
                // Invio della risposta HTTP con stato 400 (Bad Request) se donationAmount è vuoto o nullo
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            }

        } else {


            List<Donazione> risultati = new ArrayList<>();

            try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/WEB");

                // Esegui la query per ottenere i dati delle donazioni raggruppati per mese
                String query = "SELECT DATA as mese, SUM(donazione) AS totale_donazioni FROM donazioni GROUP BY DATA";
                PreparedStatement pstmt = conn.prepareStatement(query);
                ResultSet rs = pstmt.executeQuery();


                // Leggi i risultati della query e memorizza i dati nella lista
                while (rs.next()) {
                    int mese = rs.getInt("mese");
                    int totaleDonazioni = rs.getInt("totale_donazioni");
                    Donazione donazione = new Donazione(mese, totaleDonazioni);
                    risultati.add(donazione);
                }

                // Chiudi le risorse del database
                rs.close();
                pstmt.close();
                conn.close();
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }

            // Conversione dei risultati in formato JSON
            Gson gson = new Gson();
            String json = gson.toJson(risultati);

            PrintWriter out = response.getWriter();
            out.print(json);
            out.flush();
            out.close();
        }
    }


}


/*
    CREATE TABLE Donazioni (
    data INT,
    donazioni INT
);

*/



