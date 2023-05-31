package Controllers;

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
    private static final String DB_URL = "jdbc:derby://localhost:1527/WEB;create=true";

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
                // Reindirizza alla pagina di signIn
                request.setAttribute("errorMessage", "2: Username già presente. Si prega di sceglierne un altro.");
                response.sendRedirect("SignIn.html");
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
           response.sendRedirect("SignIn.html");
            return;
        }


        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        try (Connection connection = DriverManager.getConnection(DB_URL)) {
            // Inserisci i dati dell'utente nel database
            String insertQuery = "INSERT INTO Users (Name, Lastname, Dateofbirth, Email, Phonenumber, RegistrationType, Username, Password) "
                                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
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
            e.printStackTrace();
            System.out.println("problemi conn database");
           response.sendRedirect("SignIn.html");
            return;
        }

        // Reindirizza alla pagina regConf
        response.sendRedirect("regConf.html");
    }
    private boolean checkExistingUsername(String username) throws SQLException {
        String query = "SELECT COUNT(*) FROM Users WHERE Username = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL);
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