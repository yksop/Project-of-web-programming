package Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Visite", value = "/Visite")
public class Visite extends HttpServlet {


    private static final String DB_URL = "jdbc:derby://localhost:1527/WEB;create=true";

    public int getTotalVisits() throws SQLException {
        int totalVisits=0;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection conn = DriverManager.getConnection(DB_URL);
            PreparedStatement pstmt = conn.prepareStatement("SELECT SUM(VISITS) as tot_visits FROM VISITS");
            ResultSet res = pstmt.executeQuery();

            // Leggi i risultati della query e memorizza i dati nella lista
            if (res.next()) {
                totalVisits = res.getInt("tot_visits");
            }

            // Chiudi le risorse del database
            closeResources(conn, pstmt, res);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return totalVisits;
    }

    public Map<String, Integer> getVisitsByPage() throws SQLException {
        Map<String, Integer> visitsByPage = new HashMap<>();

        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection conn = DriverManager.getConnection(DB_URL);
            PreparedStatement pstmt = conn.prepareStatement("SELECT PAGE, VISITS FROM VISITS");
            ResultSet res = pstmt.executeQuery();
            while (res.next()) {

                String page = res.getString("PAGE");
                int visits = res.getInt("VISITS");
                visitsByPage.put(page, visits);
            }
            closeResources(conn, pstmt, res);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return visitsByPage;
    }

    public void resetVisits() throws SQLException {

        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection conn = DriverManager.getConnection(DB_URL);
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE VISITS SET VISITS = 0");
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Number of rows affected: " + rowsAffected);
            conn.close();
            preparedStatement.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
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
        String action = request.getParameter("action");


        if (action != null && !action.isEmpty()) {
            // Gestione della richiesta per ottenere il numero totale di visite
            if (action.equals("totale")) {
                int totalVisits = 0;
                try {
                    totalVisits = getTotalVisits();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                String json = new Gson().toJson(totalVisits);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
            }

            // Gestione della richiesta per ottenere l'istogramma delle visite per ogni pagina
            else if (action.equals("visite")) {
                Map<String, Integer> visitsByPage = null;
                try {
                    visitsByPage = getVisitsByPage();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                String json = new Gson().toJson(visitsByPage);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
            }

            // Gestione della richiesta per reimpostare il conto delle visite
            else if (action.equals("reset")) {
                try {
                    resetVisits();
                    String messageJson = new Gson().toJson("0");
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(messageJson);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            // Se il percorso non corrisponde a nessuna delle route sopra definite, restituisci un errore 404
            else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                System.out.println("problemi con path");
            }


        }
    }

}

/*
CREATE TABLE Visits (
  page VARCHAR(50),
  visits INT
);
*/