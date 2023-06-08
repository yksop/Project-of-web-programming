package Controllers;

import com.models.UserLoginStatus;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;


@WebFilter(filterName = "LoginFilter")
public class LoginFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
        // Inizializzazione del filtro
    }
    private static final String DB_URL = "jdbc:derby://localhost:1527/WEB";
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        if(checkLoginStatus(request)) {
            UserLoginStatus loginStatus = (UserLoginStatus) request.getAttribute("loginStatus");
            String username = loginStatus.getUsername();
            String password = loginStatus.getPassword();
            String type = loginStatus.getType();

            if ("admin".equals(username) && "2Adm1n!".equals(password)) {
                response.sendRedirect("./JSP_pages/amministratore.jsp");
            }
            else if ("Simpatizzante".equals(type)) {
                response.sendRedirect("./JSP_pages/simpatizzante.jsp");
            } else if ("Aderente".equals(type)) {
                response.sendRedirect("./JSP_pages/aderente.jsp");
            } else {
                // errore di qualche tipo
                response.sendRedirect("./JSP_pages/login.jsp");
            }

        } else {
            // Procedi con la richiesta
            chain.doFilter(request, response);
        }

    }

    public void destroy() {
        // Distruzione del filtro
    }

    private boolean checkLoginStatus(HttpServletRequest request) {
        // Verifica se l'utente ha già effettuato il login tramite cookie o sessione URL-encoded
        // Controllo dei cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("loginCookie")) {
                    // Hai trovato il cookie di login, verifica se i dati di accesso sono presenti
                    String[] loginData = cookie.getValue().split(":");
                    String username = loginData[0];
                    String password = loginData[1];

                    if (isValidLogin(username, password)) {
                        String type= getRegistrationType(username,password);
                        redirect(request,username,password,type);
                        return true; // L'utente ha già effettuato il login
                    }
                }
            }
        }

        // Controllo della sessione
        HttpSession session = request.getSession();
        String encodedUsername = (String) session.getAttribute("encodedUsername");
        String encodedPassword = (String) session.getAttribute("encodedPassword");
        String username= decode(encodedUsername);
        String password= decode(encodedPassword);

        // Esegui la tua logica di autenticazione
        if (encodedUsername != null && encodedPassword != null && isValidLogin(decode(encodedUsername), decode(encodedPassword))) {
            String type=getRegistrationType(decode(encodedUsername),decode(encodedPassword));
            redirect(request,username,password,type);
            return true; // L'utente ha già effettuato il login
        }

        return false; // L'utente non ha ancora effettuato il login
    }

    private boolean isValidLogin(String username, String password) {
        if (username != null && password != null) {
            return true;
        }
         else {return false;}
    }

    private String decode(String encodedValue) {
        try {
            return java.net.URLDecoder.decode(encodedValue, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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

    private void redirect(HttpServletRequest req,String username, String password,String type){
        UserLoginStatus loginStatus = new UserLoginStatus();
        loginStatus.setUsername(username);
        loginStatus.setPassword(password);
        loginStatus.setType(type);
        // Salva l'oggetto UserLoginStatus come attributo della richiesta
        req.setAttribute("loginStatus", loginStatus);

    }

}
