package com.example.progweb;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
@WebServlet(name = "RegServlet", value = "/RegServlet")
public class RegServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:derby://localhost:1527/DB;create=true";
    private static final String DB_USERNAME = "Jacopo";
    private static final String DB_PASSWORD = "Jcp";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("Name");
        String lastname = request.getParameter("LastName");
        String dateofbirth = request.getParameter("Date");
        String email = request.getParameter("Email");
        String phonenumber = request.getParameter("Tel");
        String registrationType = request.getParameter("Choice");
        String username = request.getParameter("Username");
        String password = request.getParameter("Password");

        try {
            if (checkExistingUsername(username)) {
                // Reindirizza alla pagina di errore username duplicato
                request.setAttribute("errorMessage", "Username già presente. Si prega di sceglierne un altro.");
                System.out.println("FINOA QUA VA 1");
               // response.sendRedirect("SignIn.html");
                return;
            }
        } catch (SQLException e) {
            // Gestisci l'eccezione in caso di errore di connessione al database
            e.printStackTrace();
            // Reindirizza alla pagina di errore
           // response.sendRedirect("SignIn.html");
            return;
        }

        // Connessione al database Derby
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            // Inserisci i dati dell'utente nel database
            System.out.println("FINOA QUA VA 2");
            String insertQuery = "INSERT INTO Users (Name, Lastname, Dateofbirth, Email, Phonenumber, RegistrationType, Username, Password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
                statement.setString(1, name);
                statement.setString(2, lastname);
                statement.setString(3, dateofbirth);
                statement.setString(4, email);
                statement.setString(5, phonenumber);
                statement.setString(6, registrationType);
                statement.setString(7, username);
                statement.setString(8, password);

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("FINOA QUA VA 3");
            // Gestisci l'eccezione in caso di errore di connessione al database o inserimento dei dati
            e.printStackTrace();
            // Reindirizza alla pagina di errore
           // response.sendRedirect("SignIn.html");
            return;
        }

        // Reindirizza alla pagina di conferma registrazione
        response.sendRedirect("regConf.html");
    }

    private boolean checkExistingUsername(String username) throws SQLException {
        System.out.println("FINOA QUA VA 4");
        String query = "SELECT COUNT(*) FROM Users WHERE Username = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        }
        return false;
    }

}



/*
  CREATE TABLE Users (
  Id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  FirstName VARCHAR(255) NOT NULL,
  LastName VARCHAR(255) NOT NULL,
  DateOfBirth VARCHAR(10) NOT NULL,
  Email VARCHAR(255) NOT NULL,
  PhoneNumber VARCHAR(20) NOT NULL,
  RegistrationType VARCHAR(20) NOT NULL,
  Username VARCHAR(50) NOT NULL,
  Password VARCHAR(50) NOT NULL
);

*/