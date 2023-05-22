package com.progetto_web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name = "ElencoRegServlet", value = "/ElencoRegServlet")
public class ElencoRegServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tipoElenco = request.getParameter("type"); // Ottieni il tipo di elenco richiesto

        Connection conn = null;
        Statement stmt = null;
        ResultSet res = null;

        try {
            // Connessione al database Derby
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/WEB");

            stmt = conn.createStatement();

            String query;
            if ("simpatizzanti".equalsIgnoreCase(tipoElenco)) {
                query = "SELECT FirstName, LastName, Email, Username, PhoneNumber, DateOfBirth FROM Users WHERE RegistrationType = 'Simpatizzante'";
            } else if ("aderenti".equalsIgnoreCase(tipoElenco)) {
                query = "SELECT FirstName, LastName, Email, Username, PhoneNumber, DateOfBirth FROM Users WHERE RegistrationType = 'Aderente'";
            } else {
                query = "SELECT FirstName, LastName, Email, Username, PhoneNumber, DateOfBirth FROM Users";
            }

            res = stmt.executeQuery(query);

            // Genera la risposta in formato HTML
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<h2>Elenco Utenti</h2>");
            out.println("<table>");
            out.println("<tr><th>Nome</th><th>Cognome</th><th>Email</th><th>Username</th><th>Numero di Telefono</th><th>Data di Nascita</th></tr>");
            while (res.next()) {
                String firstName = res.getString("FirstName");
                String lastName = res.getString("LastName");
                String email = res.getString("Email");
                String username = res.getString("Username");
                String phoneNumber = res.getString("PhoneNumber");
                String dateOfBirth = res.getString("DateOfBirth");

                out.println("<tr><td>" + firstName + "</td><td>" + lastName + "</td><td>" + email + "</td><td>"
                        + username + "</td><td>" + phoneNumber + "</td><td>" + dateOfBirth + "</td></tr>");
            }
            out.println("</table>");
            out.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // Chiusura delle risorse
            try {
                if (res != null) {
                    res.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
