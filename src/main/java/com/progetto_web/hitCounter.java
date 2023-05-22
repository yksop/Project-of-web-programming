package com.progetto_web;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "hitCounter", value = "/hitCounter")
public class hitCounter extends HttpServlet {

    Counter counter=new Counter();
    String myFile="fileHit.ser";

    public void log( String msg) {getServletContext().log(msg);}
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html; charset=UTF-8");
        try(PrintWriter out =response.getWriter()){
            /*request.getRequestDispatcher("/header.html")
                    .include(request,response);*/

                int temp=counter.getValue();
                temp++;
                counter.setValue(temp);

            out.println(counter);

           /* request.getRequestDispatcher("/footer.html")
                    .include(request,response);*/

            // Parametro che identifica la pagina visitata
            String pagina = request.getParameter("pagina");

            // Connessione al database Derby
            String jdbcURL = "jdbc:derby://localhost:1527/WEB";
            Connection conn = null;

            try {
                conn = DriverManager.getConnection(jdbcURL);

                // Controllo se la pagina esiste già nel database
                String CheckPage = "SELECT * FROM VISITS WHERE PAGE = ?";
                PreparedStatement statCheckPage = conn.prepareStatement(CheckPage);
                statCheckPage.setString(1, pagina);
                ResultSet res = statCheckPage.executeQuery();

                if (res.next()) {
                    // La pagina esiste già, aggiorno il numero delle visite
                    int visitePrecedenti = res.getInt("VISITS");
                    int visiteAggiornate = visitePrecedenti + 1;


                    String queryUpdateVisite = "UPDATE VISITS SET NUMVISITS = ? WHERE PAGE = ?";
                    PreparedStatement stmtUpdateVisite = conn.prepareStatement(queryUpdateVisite);
                    stmtUpdateVisite.setInt(1, visiteAggiornate);
                    stmtUpdateVisite.setString(2, pagina);
                    stmtUpdateVisite.executeUpdate();
                } else {
                    // La pagina non esiste, la inserisco nel database con una visita
                    String queryInsertPage = "INSERT INTO VISITS (PAGE, NUMVISITS) VALUES (?, 1)";
                    PreparedStatement stmtInsertPage = conn.prepareStatement(queryInsertPage);
                    stmtInsertPage.setString(1, pagina);
                    stmtInsertPage.executeUpdate();
                }

                // Invia una risposta al client per confermare l'aggiornamento delle visite
                response.setStatus(HttpServletResponse.SC_OK);
            } catch (SQLException e) {
                e.printStackTrace();
                // Invia una risposta di errore al client
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            } finally {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }



        }


    }

    public void init(ServletConfig config) throws ServletException{
        super.init(config);
        ObjectInputStream oi=null;
        try{
            oi=new ObjectInputStream(new FileInputStream(myFile));
        counter=(Counter) oi.readObject();
        } catch (FileNotFoundException ignored){
            log("The file does not exist");
        }
        catch (IOException ignored){
            log("I cannot read the file properly");
        }
        catch (NumberFormatException ignored){
            log("The file seems corrupted");
        }
        catch (ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }


    public void destroy(){
                File myFile=new File("fileHit.ser");
                ObjectOutputStream oi=null;
                try{
                    oi= new ObjectOutputStream(new FileOutputStream(myFile));
                    oi.writeObject(counter);
                } catch (IOException e){
                    throw new RuntimeException(e);
                }
            super.destroy();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {}


}



