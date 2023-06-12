package Controllers;

import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "SingleElencoServlet", value = "/SingleElencoServlet")
public class SingleElencoServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:derby://localhost:1527/WEB";


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ciao");
        response.setContentType("text/html");

        String username = null;

        HttpSession session = request.getSession();
        String encodedUsername = (String) session.getAttribute("encodedUsername");

        Cookie[] cookies = request.getCookies();


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
                try {

                    String sql = "SELECT FirstName, LastName, Email, Username, PASSWORD, PhoneNumber, DateOfBirth FROM Users WHERE username = ?";
                    Connection conn = DriverManager.getConnection(DB_URL);
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1, username);
                    stmt.executeQuery();
                    ResultSet res = stmt.executeQuery();

                    PrintWriter out = response.getWriter();

                    out.println("<ul>");

                    while (res.next()) {
                        String firstName = res.getString("FirstName");
                        String lastName = res.getString("LastName");
                        String email = res.getString("Email");
                        String user = res.getString("Username");
                        String pass = res.getString("Password");
                        String phoneNumber = res.getString("PhoneNumber");
                        String dateOfBirth = res.getString("DateOfBirth");

                        out.println("<li>Nome:              " + firstName + "</li>");
                        out.println("<li>Cognome:           " + lastName + "</li>");
                        out.println("<li>Data di nascita:   " + dateOfBirth + "</li>");
                        out.println("<li>Email:             " + email + "</li>");
                        out.println("<li>Telefono:          " + phoneNumber + "</li>");
                        out.println("<li>Username:          " + user + "</li>");
                        out.println("<li>Password:          " + pass + "</li>");

                    }

                    out.println("</ul>");

                    out.close();
                    conn.close();
                    stmt.close();
                    res.close();
                } catch (SQLException e) {
                    e.printStackTrace();
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
