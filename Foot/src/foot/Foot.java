/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package foot;

import Vue.MaFenetre;
import java.sql.SQLException;
import Modele.D1;
import Modele.D2;
import Modele.LdC;
import Modele.Requetes;
/**
 *
 * @author Vanessa
 */
public class Foot {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
      
        Requetes r = new Requetes();
        r.RAZTotale();
        //vider table LDC && europa
        MaFenetre fen = new MaFenetre();
       fen.setVisible(true);
       
      
    }
      
    
    
}
