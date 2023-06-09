package Controllers;

import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebFilter(filterName = "LoginFilter")
public class LoginFilter implements Filter {
    private static final String DB_URL = "jdbc:derby://localhost:1527/WEB";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }


    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        Cookie[] cookies = request.getCookies();
        // Controllo della sessione
        HttpSession session = request.getSession();
        String encodedUsername = (String) session.getAttribute("encodedUsername");

        if("logout".equals(request.getParameter("action"))){
            logOut(request,response, cookies);

        } else if(checkLoginStatus(request, cookies, encodedUsername)) {
            UserLoginStatus loginStatus = (UserLoginStatus) request.getAttribute("loginStatus");
            String username = loginStatus.getUsername();
            String type = loginStatus.getType();

            if ("admin".equals(username)) {
                response.sendRedirect("./JSP_pages/amministratore.jsp");
            }
            else if ("Simpatizzante".equals(type)) {
                response.sendRedirect("./JSP_pages/simpatizzante.jsp");
            } else if ("Aderente".equals(type)) {
                response.sendRedirect("./JSP_pages/aderente.jsp");
            } else {
                // errore di qualche tipo
                response.sendRedirect("./JSP_pages/logIn.jsp");
            }

        } else{
            // Procedi con la richiesta
            System.out.println("filter non utilizzato");
            chain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {

    }


    private void logOut(HttpServletRequest request, HttpServletResponse response, Cookie[] cookies) throws IOException {
        String encodedUsername= (String) request.getSession().getAttribute("encodedUsername");

        if(encodedUsername!=null){
            request.getSession().setAttribute("encodedUsername",null);}
        else if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("loginCookie")) {
                        cookie.setValue(null);
                        cookie.setMaxAge(0);
                        cookie.setPath("/");
                        response.addCookie(cookie);
                        break;
                    }
                }
            }
    }

    private boolean checkLoginStatus(HttpServletRequest request, Cookie[]  cookies, String encodedUsername) {
        // Verifica se l'utente ha già effettuato il login tramite cookie o sessione URL-encoded
        // Controllo dei cookie

            // Esegui la tua logica di autenticazione
            if (isValidLogin(encodedUsername)) {
                String usernameSession = decode(encodedUsername);
                String type = getRegistrationType(usernameSession);
                createUser(request, usernameSession, type);
                return true; // L'utente ha già effettuato il login
            } else if (cookies != null) {

                for (Cookie cookie : cookies) {

                    if (cookie.getName().equals("loginCookie")) {

                        // Hai trovato il cookie di login, verifica se i dati di accesso sono presenti
                        String[] loginData = cookie.getValue().split(":");
                        String username = loginData[0];

                        if (isValidLogin(username)) {
                            String type = getRegistrationType(username);
                            createUser(request, username, type);
                            return true; // L'utente ha già effettuato il login
                        }
                    }
                }
            }
        return false;
    }

    private boolean isValidLogin(String username) {
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

    private String getRegistrationType(String username) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL);
            String query = "SELECT registrationtype FROM users WHERE username = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, username);
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

    private void createUser(HttpServletRequest req,String username,String type){
        UserLoginStatus loginStatus = new UserLoginStatus();
        loginStatus.setUsername(username);
        loginStatus.setType(type);
        // Salva l'oggetto UserLoginStatus come attributo della richiesta
        req.setAttribute("loginStatus", loginStatus);
    }
    private class UserLoginStatus {
        private String username;
        private String type;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }


}
