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
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        urlJdbc= "jdbc:mysql://localhost/foot";
        c= DriverManager.getConnection(urlJdbc, "root","");
        link=c.createStatement();        
       }

}
