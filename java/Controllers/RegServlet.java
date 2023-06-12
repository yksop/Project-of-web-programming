package Controllers;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.http.*;


@WebServlet(name = "RegServlet", value = "/RegServlet")
public class RegServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:derby://localhost:1527/WEB";

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
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            if (checkExistingUsername(username)) {
                // Reindirizza alla pagina di signIn
                request.setAttribute("errorMessage", "2: Username già presente. Si prega di sceglierne un altro.");
                response.sendRedirect("./JSP_pages/signIn.jsp");

            }

        }
        catch (SQLException e) {
            e.printStackTrace();
           response.sendRedirect("./JSP_pages/signIn.jsp");
            return;
        }


        try{
            Connection connection = DriverManager.getConnection(DB_URL);
            // Inserisci i dati dell'utente nel database
            String insertQuery = "INSERT INTO Users (FirstName, Lastname, Dateofbirth, Email, Phonenumber, RegistrationType, Username, Password) "
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
            System.out.println("problemi con database");
           response.sendRedirect("./JSP_pages/signIn.jsp");
            return;
        }

        // Reindirizza alla pagina regConf
        response.sendRedirect("./JSP_pages/regConf.jsp");
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


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/plain");
        String action = request.getParameter("action");

        String username = null;


        HttpSession session = request.getSession();
        String encodedUsername = (String) session.getAttribute("encodedUsername");

        Cookie[] cookies = request.getCookies();

        if (action != null) {

            if (isValid(encodedUsername)) {
                username=decode(encodedUsername);
            } else if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("loginCookie")) {
                        String[] loginData = cookie.getValue().split(":");
                        if(isValid(loginData[0])){
                        username = loginData[0];
                        break;
                        }
                    }
                }
            }

            if (action.equals("welcome")){
                try {
                    response.setContentType("application/json");
                    String sql = "SELECT FIRSTNAME FROM users WHERE username = ?";
                    Connection conn = DriverManager.getConnection(DB_URL);
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1, username);
                    stmt.executeQuery();
                    ResultSet res = stmt.executeQuery();

                    PrintWriter out = response.getWriter();
                    if (res.next()) {
                        String name = res.getString("firstname");
                        String user_name= username+":"+name;
                        Gson gson = new Gson();
                        String json = gson.toJson(user_name);

                        out.print(json);
                        out.flush();
                    }
                    response.setStatus(HttpServletResponse.SC_OK);
                    conn.close();
                    stmt.close();
                    res.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                }


            } else if (action.equals("delete")) {
                try {
                    Connection conn = DriverManager.getConnection(DB_URL);
                    String sql = "DELETE FROM users WHERE username = ?";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1, username);
                    stmt.executeUpdate();

                    conn.close();
                    stmt.close();

                    response.setStatus(HttpServletResponse.SC_OK);
                    PrintWriter out = response.getWriter();
                    out.println("Registrazione cancellata.\n\nVerrà reindirizzato alla pagina di signIn a breve,\nla preghiamo di attendere\n");
                } catch (SQLException e) {
                    e.printStackTrace();

                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    PrintWriter out = response.getWriter();
                    out.println("Registrazione non cancellata correttamente.\n\nSi prega di provare più tardi,\nSe il problema sussiste, chiamare i proprietari del sito\n");
                }
            }

        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private boolean isValid(String username) {
        return username != null;
    }
    private String decode(String encodedValue) {
        try {
            return java.net.URLDecoder.decode(encodedValue, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

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