package Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
//import com.google.gson.Gson;
//import com.google.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Visite", value = "/Visite")
public class Visite extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // Override del metodo doGet per gestire le richieste GET

    private static final String DB_URL = "jdbc:derby://localhost:1527/WEB";
    public int getTotalVisits() throws SQLException {
        Connection conn = null;
        Statement statement = null;
        ResultSet res = null;
        int totalVisits = 0;

        try {
            conn = DriverManager.getConnection(DB_URL);
            statement = conn.createStatement();
            res = statement.executeQuery("SELECT SUM(NUMVISITS) FROM VISITS");

            if (res.next()) {
                totalVisits = res.getInt(1);
            }
        } finally {
            closeResources(conn, statement, res);
        }

        return totalVisits;
    }

    public Map<String, Integer> getVisitsByPage() throws SQLException {
        Connection conn = null;
        Statement statement = null;
        ResultSet res = null;
        Map<String, Integer> visitsByPage = new HashMap<>();

        try {
            conn = DriverManager.getConnection(DB_URL);
            statement = conn.createStatement();
            res = statement.executeQuery("SELECT PAGE, NUMVISITS FROM VISITS");

            while (res.next()) {
                String page = res.getString("PAGE");
                int visits = res.getInt("NUMVISITS");
                visitsByPage.put(page, visits);
            }
        } finally {
            closeResources(conn, statement, res);
        }

        return visitsByPage;
    }

    public void resetVisits() throws SQLException {
        Connection conn = null;
        Statement stmt = null;

        try {
            conn = DriverManager.getConnection(DB_URL);
            stmt = conn.createStatement();
            stmt.executeUpdate("UPDATE VISITS SET NUMVISITS = 0");
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    private void closeResources(Connection conn, Statement stmt, ResultSet rs) throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (stmt != null) {
            stmt.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String pathInfo = request.getPathInfo();

        // Gestione della richiesta per ottenere il numero totale di visite
        if (pathInfo != null && pathInfo.equals("/visualizzareVisiteTot")) {
            int totalVisits = 0;
            try {
                totalVisits = getTotalVisits();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            sendJsonResponse(response, "{\"totalVisits\": " + totalVisits + "}");
        }

        // Gestione della richiesta per ottenere l'istogramma delle visite per ogni pagina
        else if (pathInfo != null && pathInfo.equals("/visualizzareVisite")) {
            Map<String, Integer> visitsByPage = null;
            try {
                visitsByPage = getVisitsByPage();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            sendJsonResponse(response, convertMapToJson(visitsByPage));
        }

        // Gestione della richiesta per reimpostare il conto delle visite
        else if (pathInfo != null && pathInfo.equals("/resetVisite")) {
            try {
                resetVisits();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            sendJsonResponse(response, "{\"message\": \"Visits reset successfully\"}");
        }

        // Se il percorso non corrisponde a nessuna delle route sopra definite, restituisci un errore 404
        else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            sendJsonResponse(response, "{\"error\": \"Route not found\"}");
        }
    }

    // Metodo per inviare una risposta JSON al browser
    private void sendJsonResponse(HttpServletResponse response, String json) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }

    // Metodi di utilità per la conversione di una mappa in formato JSON
    private String convertMapToJson(Map<String, Integer> map) {
        StringBuilder json = new StringBuilder("{");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            json.append("\"").append(entry.getKey()).append("\": ").append(entry.getValue()).append(",");
        }
        if (!map.isEmpty()) {
            json.deleteCharAt(json.length() - 1); // Rimuovi l'ultima virgola
        }
        json.append("}");
        return json.toString();
    }
}


/*
CREATE TABLE Visits (
  id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  page VARCHAR(50),
  visits INT
);
INSERT INTO Visits (page, visits) VALUES ('pagina1', 0);
INSERT INTO Visits (page, visits) VALUES ('pagina2', 0);
...
Connessione al database Derby: Nel tuo progetto Java su IntelliJ, aggiungi la dipendenza Derby nel tuo file pom.xml (se stai utilizzando Maven) o nel tuo file di configurazione delle dipendenze.
xml
Copy code
<dependency>
    <groupId>org.apache.derby</groupId>
    <artifactId>derby</artifactId>
    <version>10.15.2.0</version>
</dependency>
 */


/*
import com.google.gson.Gson;

public class Visite extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String DB_URL = "jdbc:derby://localhost:1527/WEB";
    private Gson gson = new Gson();

    // Resto del codice...

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String pathInfo = request.getPathInfo();

        // Gestione della richiesta per ottenere il numero totale di visite
        if (pathInfo != null && pathInfo.equals("/visualizzareVisiteTot")) {
            int totalVisits = 0;
            try {
                totalVisits = getTotalVisits();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            sendJsonResponse(response, gson.toJson(new TotalVisitsResponse(totalVisits)));
        }

        // Gestione della richiesta per ottenere l'istogramma delle visite per ogni pagina
        else if (pathInfo != null && pathInfo.equals("/visualizzareVisite")) {
            Map<String, Integer> visitsByPage = null;
            try {
                visitsByPage = getVisitsByPage();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            sendJsonResponse(response, gson.toJson(visitsByPage));
        }

        // Gestione della richiesta per reimpostare il conto delle visite
        else if (pathInfo != null && pathInfo.equals("/resetVisite")) {
            try {
                resetVisits();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            sendJsonResponse(response, gson.toJson(new ResetVisitsResponse("Visits reset successfully")));
        }

        // Se il percorso non corrisponde a nessuna delle route sopra definite, restituisci un errore 404
        else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            sendJsonResponse(response, gson.toJson(new ErrorResponse("Route not found")));
        }
    }

    // Resto del codice...

    // Classe di utilità per la risposta del numero totale di visite
    private class TotalVisitsResponse {
        private int totalVisits;

        public TotalVisitsResponse(int totalVisits) {
            this.totalVisits = totalVisits;
        }

        public int getTotalVisits() {
            return totalVisits;
        }
    }

    // Classe di utilità per la risposta del reset delle visite
    private class ResetVisitsResponse {
        private String message;

        public ResetVisitsResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    // Classe di utilità per la risposta di errore
    private class ErrorResponse {
        private String error;

        public ErrorResponse(String error) {
            this.error = error;
        }

        public String getError() {
            return error;
        }
    }
}

 */