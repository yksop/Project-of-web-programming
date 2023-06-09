package Controllers;

import javax.jms.Session;
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
                if(checkExistingUser(username,password)) {

                    boolean useCookie = isCookieAccepted(request);
                    if (useCookie) {
                        setLoginCookie(response, username);
                    } else {
                        // Imposta i parametri nella sessione URL-encoded
                        setLoginSession(request.getSession(), username);
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
                        response.sendRedirect("./JSP_pages/logIn.jsp");
                    }

                } else {
                    request.setAttribute("errorMessage", "2: Utente non presente. Si prega di riprovare o effettuare il signIn.");
                    request.getRequestDispatcher("./JSP_pages/logIn.jsp").forward(request, response);

                }
        }
        catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("./JSP_pages/logIn.jsp");
        }

    }


    private boolean checkExistingUser(String username, String password) throws SQLException {
        String query = "SELECT COUNT(*) FROM Users WHERE Username = ? AND Password = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
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

    private void setLoginCookie(HttpServletResponse response, String username) {
        String loginData = username;
        Cookie cookie = new Cookie("loginCookie", loginData);
        cookie.setPath("/");
        response.addCookie(cookie);
    }




    private void setLoginSession(HttpSession session, String username) throws UnsupportedEncodingException {
        String encodedUsername = java.net.URLEncoder.encode(username, "UTF-8");
        session.setAttribute("encodedUsername", encodedUsername);
    }
}

