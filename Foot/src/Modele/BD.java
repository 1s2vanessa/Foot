package Modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Vanessa
 * 
 * Classe permettant la connexion à la Base de données (la BD est stockée sur phpMyAdmin de Wamp)
 */
public class BD {

    public Statement link;
    public Connection c;
    public String urlJdbc;

    /**
     * Contrsucteur de la classe, contient les éléments nécéssaires à la connexion à la BD
     * @throws SQLException 
     */
    public BD() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (InstantiationException ex) {
            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
        }
        urlJdbc = "jdbc:mysql://localhost/foot";
        c = DriverManager.getConnection(urlJdbc, "root", "");
        link = c.createStatement();
    }

}
