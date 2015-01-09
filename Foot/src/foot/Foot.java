/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package foot;

import Modele.D1;
import Modele.D2;
import Modele.LdC;
import Vue.MaFenetre;
import java.sql.SQLException;

/**
 *
 * @author Vanessa
 */
public class Foot {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
//       MaFenetre fen = new MaFenetre();
//       fen.setVisible(true);
   
     D1 div1= new D1();
       D2 div2 = new D2();
      LdC lea=new LdC(div1.getDivision1(),div1.getDivision1());
      
      lea.melange_array(div1.getDivision1());
      lea.faire_8_groupes_de_4();
      lea.melange_array(lea.getListe_LDC());
      for (int i=0;i<8;i++)
      {
        lea.affiche_groupe(i);  
        System.out.println();
        System.out.println();
        System.out.println();
      }
    
    
    }
      
    
    
}
