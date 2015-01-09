package Modele;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Vanessa
 */
public class BD{
    public Statement link;
    public Connection c;
    public String urlJdbc;
    
    public BD()  throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
//        Class.forName("com.mysql.jdbc.Driver").newInstance();
//        urlJdbc= "jdbc:mysql://localhost/foot"; // J'ai pas le bon lien
//        c= DriverManager.getConnection(urlJdbc, "root",""); // pareil
//        
//        link=c.createStatement();        
      
     
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        urlJdbc= "jdbc:mysql://09iutabourglnx.iutbourg.univ-lyon1.fr/p1209090";
        c= DriverManager.getConnection(urlJdbc, "p1209090","11209090");
        link=c.createStatement();
    
    
    }

}
