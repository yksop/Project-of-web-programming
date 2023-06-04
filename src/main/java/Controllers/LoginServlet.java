package Controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.*;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:derby://localhost:1527/WEB";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("Username");
        String password = request.getParameter("Password");

        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            if (checkExistingUsername(username)) {
                if(checkExistingPassword(password)) {

                    boolean useCookie = isCookieAccepted(request);
                    if (useCookie) {
                        // Imposta il cookie di login
                        setLoginCookie(response, username, password);
                    } else {
                        // Imposta i parametri nella sessione URL-encoded
                        setLoginSession(request.getSession(), username, password);
                    }


                    String registrationType = getRegistrationType(username, password);
                    if ("admin".equals(username) && "2Adm1n!".equals(password)) {
                        response.sendRedirect("./JSP_pages/amministratore.jsp");
                    } else if ("Simpatizzante".equals(registrationType)) {
                        response.sendRedirect("./JSP_pages/simpatizzante.jsp");
                    } else if ("Aderente".equals(registrationType)) {
                        response.sendRedirect("./JSP_pages/aderente.jsp");
                    } else {
                        // errore di qualche tipo
                        response.sendRedirect("./JSP_pages/login.jsp");
                    }

                } else {
                    request.setAttribute("errorMessage", "2: Password non presente. Si prega di riprovare o effettuare il signIn.");
                    response.sendRedirect("./JSP_pages/logIn.jsp");
                }

            }
             else {
                request.setAttribute("errorMessage", "2: Utente non presente. Si prega di riprovare o effettuare il signIn.");
                response.sendRedirect("./JSP_pages/logIn.jsp");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("./JSP_pages/logIn.jsp");
        }


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


    private boolean checkExistingPassword(String password) throws SQLException {
        String query = "SELECT COUNT(*) FROM Users WHERE PASSWORD = ?";
        try (Connection connect = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connect.prepareStatement(query)) {
            statement.setString(1, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        }
        return false;
    }

    private String getRegistrationType(String username, String password) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL);
            String query = "SELECT registrationtype FROM users WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getString("registrationtype");
            }
            rs.close();
            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    private boolean isCookieAccepted(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("useCookie") && cookie.getValue().equals("true")) {
                    return true; // I cookie sono accettati
                }
            }
        }

        return false; // I cookie sono disattivati
    }

    private void setLoginCookie(HttpServletResponse response, String username, String password) {
        // Imposta il cookie di login
        // Puoi utilizzare il nome e il valore del cookie per memorizzare i dati di accesso in modo sicuro
        // In questo esempio, i dati di accesso sono separati da due punti (:)

        String loginData = username + ":" + password;
        Cookie cookie = new Cookie("loginCookie", loginData);
        cookie.setPath("/");
        response.addCookie(cookie);

    }

    private void setLoginSession(HttpSession session, String username, String password) throws UnsupportedEncodingException {
        // Imposta i parametri nella sessione URL-encoded
        // Puoi utilizzare il metodo URLEncoder.encode() per codificare i dati di accesso in modo sicuro

        String encodedUsername = java.net.URLEncoder.encode(username, "UTF-8");
        String encodedPassword = java.net.URLEncoder.encode(password, "UTF-8");

        session.setAttribute("encodedUsername", encodedUsername);
        session.setAttribute("encodedPassword", encodedPassword);
    }
}

