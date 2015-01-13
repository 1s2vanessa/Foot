package Modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Vanessa
 */
public class Requetes {

    private BD data;

    public Requetes() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        data = new BD();
    }

    public ArrayList<Equipe> getEquipeByPaysAndChampionnat(String pays, String Championnat) throws SQLException {

        String cmd = "SELECT DISTINCT * FROM " + Championnat + " where Pays ='" + pays + "' ORDER BY CLassement";
        ResultSet rs = data.link.executeQuery(cmd);
        ArrayList list = new ArrayList();
        while (rs.next()) {
            //division1.add(new Equipe(rs.getString("Nom_Equipe"), i, "D1"));
            list.add(new Equipe(rs.getInt("Classement"), rs.getString("Nom_Equipe"), rs.getInt("Pts"), rs.getInt("J"), rs.getInt("G"), rs.getInt("N"), rs.getInt("P"), rs.getInt("BP"), rs.getInt("BC"), rs.getInt("Diff"), rs.getString("Pays"), Championnat));
            // System.out.println("Equipe : " + rs.getString("Nom_Equipe") + " " + rs.getInt("Classement") + " " + rs.getInt("Pts") + " " + rs.getInt("J") + " " + rs.getInt("G") + " " + rs.getInt("N") + " " + rs.getInt("P") + " " + rs.getInt("BP") + " " + rs.getInt("BC") + " " + rs.getInt("Diff"));
            //  i++;
        }
        return list;
    }

    public ArrayList<Equipe> getEquipeByChampionnat(String championnat) throws SQLException {

        String cmd = "SELECT DISTINCT * FROM " + championnat + " ORDER BY CLassement";
        ResultSet rs = data.link.executeQuery(cmd);
        ArrayList list = new ArrayList();
        while (rs.next()) {
            //division1.add(new Equipe(rs.getString("Nom_Equipe"), i, "D1"));
            list.add(new Equipe(rs.getInt("Classement"), rs.getString("Nom_Equipe"), rs.getInt("Pts"), rs.getInt("J"), rs.getInt("G"), rs.getInt("N"), rs.getInt("P"), rs.getInt("BP"), rs.getInt("BC"), rs.getInt("Diff"), rs.getString("Pays"), championnat));
            //  System.out.println("Equipe : " + rs.getString("Nom_Equipe") + " " + rs.getInt("Classement") + " " + rs.getInt("Pts") + " " + rs.getInt("J") + " " + rs.getInt("G") + " " + rs.getInt("N") + " " + rs.getInt("P") + " " + rs.getInt("BP") + " " + rs.getInt("BC") + " " + rs.getInt("Diff"));
            //  i++;
        }
        return list;
    }

    public Equipe getEquipeByName(String nom, String Championnat) throws SQLException {

        String cmd = "SELECT DISTINCT * FROM " + Championnat + " where Nom_Equipe='" + nom + "'";
        ResultSet rs = data.link.executeQuery(cmd);
        Equipe equipe = null;
        while (rs.next()) {
            //division1.add(new Equipe(rs.getString("Nom_Equipe"), i, "D1"));
            equipe = new Equipe(rs.getInt("Classement"), rs.getString("Nom_Equipe"), rs.getInt("Pts"), rs.getInt("J"), rs.getInt("G"), rs.getInt("N"), rs.getInt("P"), rs.getInt("BP"), rs.getInt("BC"), rs.getInt("Diff"), rs.getString("Pays"), Championnat);

        }
        return equipe;
    }

    public void MAJBd(Equipe e1, String Championnat) throws SQLException {
        String cmd = "UPDATE `" + Championnat + "` SET `Pts`='" + e1.getPts() + "',`J`='" + e1.getJ() + "',`G`='" + e1.getG() + "',`N`='" + e1.getN() + "',`P`='" + e1.getP() + "',`BP`='" + e1.getBP() + "',`BC`='" + e1.getBC() + "',`Diff`='" + e1.getDiff() + "' WHERE Nom_Equipe ='" + e1.getNom() + "'";
        data.link.executeUpdate(cmd);
    }

    public ArrayList<Equipe> Classement(String pays, String Championnat) throws SQLException {
        //Pour classer les équipes
        String cmd = "SELECT * FROM " + Championnat + " where Pays ='" + pays + "' ORDER BY Pts DESC";
        ResultSet rs = data.link.executeQuery(cmd);
        ArrayList<Equipe> list = new ArrayList();
        int i = 1;
        String cmd2;
        while (rs.next()) {

            //division1.add(new Equipe(rs.getString("Nom_Equipe"), i, "D1"));
            list.add(new Equipe(i, rs.getString("Nom_Equipe"), rs.getInt("Pts"), rs.getInt("J"), rs.getInt("G"), rs.getInt("N"), rs.getInt("P"), rs.getInt("BP"), rs.getInt("BC"), rs.getInt("Diff"), rs.getString("Pays"), Championnat));
            // System.out.println("Equipe : " + rs.getString("Nom_Equipe") + " " + rs.getInt("Classement") + " " + rs.getInt("Pts") + " " + rs.getInt("J") + " " + rs.getInt("G") + " " + rs.getInt("N") + " " + rs.getInt("P") + " " + rs.getInt("BP") + " " + rs.getInt("BC") + " " + rs.getInt("Diff"));

            i++;
        }

        for (int j = 0; j < list.size(); j++) {
            list.get(j).setClassement(j + 1);
            cmd2 = "UPDATE `" + Championnat + "` SET `Classement`='" + (j + 1) + "' WHERE Nom_Equipe ='" + list.get(j).getNom() + "'";
            data.link.executeUpdate(cmd2);
//            list.get(j).toString();
        }
        return list;
    }

    public ArrayList<Equipe> ClassementCoupe(String pays) throws SQLException {
        //Pour classer les équipes
        String cmd = "SELECT * FROM d1 where Pays ='" + pays + "' ORDER BY Pts DESC";
        String cmd1 = "SELECT * FROM d2 where Pays ='" + pays + "' ORDER BY Pts DESC";
        ResultSet rs = data.link.executeQuery(cmd);
        ArrayList<Equipe> list = new ArrayList();
        int i = 1;
        String cmd2, cmd3;
        while (rs.next()) {
            list.add(new Equipe(i, rs.getString("Nom_Equipe"), rs.getInt("Pts"), rs.getInt("J"), rs.getInt("G"), rs.getInt("N"), rs.getInt("P"), rs.getInt("BP"), rs.getInt("BC"), rs.getInt("Diff"), rs.getString("Pays"), "d1"));
            i++;
        }

        ResultSet rs2 = data.link.executeQuery(cmd1);
        while (rs2.next()) {
            list.add(new Equipe(i, rs2.getString("Nom_Equipe"), rs2.getInt("Pts"), rs2.getInt("J"), rs2.getInt("G"), rs2.getInt("N"), rs2.getInt("P"), rs2.getInt("BP"), rs2.getInt("BC"), rs2.getInt("Diff"), rs2.getString("Pays"), "d2"));

            i++;
        }

        for (int j = 0; j < list.size(); j++) {
            list.get(j).setClassement(j + 1);
            //chercher dans la d1
            cmd2 = "UPDATE `" + list.get(j).getType() + "` SET `ClassementCoupe`='" + (j + 1) + "' WHERE Nom_Equipe ='" + list.get(j).getNom() + "'";
            data.link.executeUpdate(cmd2);
            //chercher dans la d2

        }
        return list;
    }
    
    
    public ArrayList<Equipe> ClassementCoupeNationnale(String pays) throws SQLException {
        //Pour classer les équipes
        String cmd = "SELECT * FROM d1 where Pays ='" + pays + "' ORDER BY Pts DESC";
        String cmd1 = "SELECT * FROM d2 where Pays ='" + pays + "' ORDER BY Pts DESC";
        String cmd4 = "SELECT * FROM `clubs nationals` where Pays ='" + pays + "' ORDER BY Pts DESC";
        ResultSet rs = data.link.executeQuery(cmd);
        ArrayList<Equipe> list = new ArrayList();
        int i = 1;
        String cmd2, cmd3;
        while (rs.next()) {
            list.add(new Equipe(i, rs.getString("Nom_Equipe"), rs.getInt("Pts"), rs.getInt("J"), rs.getInt("G"), rs.getInt("N"), rs.getInt("P"), rs.getInt("BP"), rs.getInt("BC"), rs.getInt("Diff"), rs.getString("Pays"), "d1"));
            i++;
        }

        ResultSet rs2 = data.link.executeQuery(cmd1);
        while (rs2.next()) {
            list.add(new Equipe(i, rs2.getString("Nom_Equipe"), rs2.getInt("Pts"), rs2.getInt("J"), rs2.getInt("G"), rs2.getInt("N"), rs2.getInt("P"), rs2.getInt("BP"), rs2.getInt("BC"), rs2.getInt("Diff"), rs2.getString("Pays"), "d2"));

            i++;
        }
        
         ResultSet rs3 = data.link.executeQuery(cmd4);
        while (rs2.next()) {
            list.add(new Equipe(i, rs3.getString("Nom_Equipe"), rs3.getInt("Pts"), rs3.getInt("J"), rs3.getInt("G"), rs3.getInt("N"), rs3.getInt("P"), rs3.getInt("BP"), rs3.getInt("BC"), rs3.getInt("Diff"), rs3.getString("Pays"), "clubs nationals"));

            i++;
        }

        for (int j = 0; j < list.size(); j++) {
            list.get(j).setClassement(j + 1);
            //chercher dans la d1
            cmd2 = "UPDATE `" + list.get(j).getType() + "` SET `ClassementCoupe`='" + (j + 1) + "' WHERE Nom_Equipe ='" + list.get(j).getNom() + "'";
            data.link.executeUpdate(cmd2);
            //chercher dans la d2

        }
        return list;
    }
    

    public int classementCoupeByName(String nom, String Championnat) throws SQLException {
        String cmd = "SELECT `ClassementCoupe` FROM " + Championnat + " where Nom_Equipe ='" + nom + "'";
        ResultSet rs = data.link.executeQuery(cmd);
        int resultat = -1;
        while (rs.next()) {
            resultat = rs.getInt("ClassementCoupe");
        }
        return resultat;
    }

    public void RAZbase(String championnat, String pays) throws SQLException {
        int i = 0;
        String cmd;
        String selection = "SELECT * FROM " + championnat + " where Pays ='" + pays + "'";
        ResultSet rs = data.link.executeQuery(selection);

        while (rs.next()) {
            i++;
            System.err.println(i);
        }

        for (int j = 1; j < i + 1; j++) {
            cmd = "UPDATE `" + championnat + "` SET `Pts`='0',`J`='0',`G`='0',`N`='0',`P`='0',`BP`='0',`BC`='0',`Diff`='0' WHERE Pays= '" + pays + "' AND Classement ='" + j + "'";
            data.link.executeUpdate(cmd);

            System.err.println(j);
        }
    }

    public void RAZTotale() throws SQLException {
        int i = 0;
        String cmd;
        String selection = "SELECT * FROM d1";
        ResultSet rs = data.link.executeQuery(selection);

        while (rs.next()) {
            i++;
            // System.err.println(i);
        }

        for (int j = 1; j < i + 1; j++) {
            cmd = "UPDATE `d1` SET `Pts`='0',`J`='0',`G`='0',`N`='0',`P`='0',`BP`='0',`BC`='0',`Diff`='0'";
            data.link.executeUpdate(cmd);

            //System.err.println(j);
        }

        selection = "SELECT * FROM d2";
        rs = data.link.executeQuery(selection);
        i = 0;
        while (rs.next()) {
            i++;
            // System.err.println(i);
        }

        for (int j = 1; j < i + 1; j++) {
            cmd = "UPDATE `d2` SET `Pts`='0',`J`='0',`G`='0',`N`='0',`P`='0',`BP`='0',`BC`='0',`Diff`='0'";
            data.link.executeUpdate(cmd);

            //System.err.println(j);
        }
    }

    public ArrayList<Equipe> dernieresEquipeD1(String pays) throws SQLException {
        ArrayList<Equipe> lastEquipe = new ArrayList<>();
        String selection = "SELECT * FROM d1 where Pays ='" + pays + "' AND Classement > '17' ORDER BY Classement";
        ResultSet rs = data.link.executeQuery(selection);
        int i = 18;
        int j = 0;
        while (rs.next()) {
            lastEquipe.add(new Equipe(i, rs.getString("Nom_Equipe"), rs.getInt("Pts"), rs.getInt("J"), rs.getInt("G"), rs.getInt("N"), rs.getInt("P"), rs.getInt("BP"), rs.getInt("BC"), rs.getInt("Diff"), rs.getString("Pays"), "d1"));
            System.err.println(lastEquipe.get(j).toString());
            j++;
            i++;
        }
        return lastEquipe;
    }

    public void switchEquipeD1D2(String pays) throws SQLException {
        String selectionD1 = "SELECT * FROM d1 where Pays ='" + pays + "' AND Classement > '17' ORDER BY Classement";
        ResultSet rsD1 = data.link.executeQuery(selectionD1);
        ArrayList<Equipe> lastEquipe = new ArrayList<>();
        int i = 1;
        int j = 0;
        while (rsD1.next()) {
            lastEquipe.add(new Equipe(i, rsD1.getString("Nom_Equipe"), rsD1.getInt("Pts"), rsD1.getInt("J"), rsD1.getInt("G"), rsD1.getInt("N"), rsD1.getInt("P"), rsD1.getInt("BP"), rsD1.getInt("BC"), rsD1.getInt("Diff"), rsD1.getString("Pays"), "d1"));
            System.err.println(lastEquipe.get(j).toString());
            j++;
            i++;
        }

        String selectionD2 = "SELECT * FROM d2 where Pays ='" + pays + "' AND Classement < '4' ORDER BY Classement";
        ResultSet rsD2 = data.link.executeQuery(selectionD2);
        ArrayList<Equipe> firstEquipe = new ArrayList<>();
        i = 18;
        j = 0;
        while (rsD2.next()) {
            firstEquipe.add(new Equipe(i, rsD2.getString("Nom_Equipe"), rsD2.getInt("Pts"), rsD2.getInt("J"), rsD2.getInt("G"), rsD2.getInt("N"), rsD2.getInt("P"), rsD2.getInt("BP"), rsD2.getInt("BC"), rsD2.getInt("Diff"), rsD2.getString("Pays"), "d2"));
            System.err.println(firstEquipe.get(j).toString());
            j++;
            i++;
        }

        //MAJ D1
        String majD1, majD2;

        for (i = 0; i < firstEquipe.size(); i++) {
            majD1 = "UPDATE `d1` SET `Nom_Equipe`= '" + firstEquipe.get(i).getNom() + "', `Pts`='0',`J`='0',`G`='0',`N`='0',`P`='0',`BP`='0',`BC`='0',`Diff`='0' WHERE Pays='" + pays + "' AND  Classement= '" + firstEquipe.get(i).getClassement() + "'";
            data.link.executeUpdate(majD1);

        }

        //MAJ D2
        for (i = 0; i < lastEquipe.size(); i++) {
            majD2 = "UPDATE `d2` SET `Nom_Equipe`= '" + lastEquipe.get(i).getNom() + "', `Pts`='0',`J`='0',`G`='0',`N`='0',`P`='0',`BP`='0',`BC`='0',`Diff`='0'WHERE Pays='" + pays + "' AND  Classement= '" + lastEquipe.get(i).getClassement() + "'";
            data.link.executeUpdate(majD2);

        }
    }

    public void envoiEuropa(Equipe e) throws SQLException {

        String nbLigne = "SELECT * FROM `europa league`";
        ResultSet rs = data.link.executeQuery(nbLigne);
        int row = 1;
        while (rs.next()) {
            row++;
        }
        System.err.println(row);
        String ajout = "INSERT INTO `europa league` (Classement, Nom_Equipe, Pts, J, G, N, P, BP, BC, Diff, Pays) VALUES ('" + row + "' ,'" + e.getNom() + "', 0, 0, 0, 0, 0, 0, 0, 0, '" + e.getPays() + "'  )";
        data.link.executeUpdate(ajout);
    }

    public void envoiLDC(Equipe e) throws SQLException {

        String nbLigne = "SELECT * FROM `champions league`";
        ResultSet rs = data.link.executeQuery(nbLigne);
        int row = 1;
        while (rs.next()) {
            row++;
        }
        System.err.println(row);
        String ajout = "INSERT INTO `champions league` (Classement, Nom_Equipe, Pts, J, G, N, P, BP, BC, Diff, Pays) VALUES ('" + row + "' ,'" + e.getNom() + "', 0, 0, 0, 0, 0, 0, 0, 0, '" + e.getPays() + "'  )";
        data.link.executeUpdate(ajout);
    }

    public boolean finSaison(String championnat, String pays) throws SQLException {
        String cmd = "SELECT * FROM " + championnat + " WHERE Pays='" + pays + "'";
        ResultSet rs = data.link.executeQuery(cmd);
        while (rs.next()) {
            if (championnat.equals("d1") || championnat.equals("d2")) {
                if (rs.getInt("J") < 19) {
                    return false;
                }
            }
        }

        return true;
    }

//    public void creationBDCoupeLigue(ArrayList<Equipe> listeEquipe) throws SQLException{
//        String cmd="DROP TABLE IF EXISTS `coupe de la ligue`";
//          ResultSet rs = data.link.executeQuery(cmd);
//          
//          for (int i=0; i<listeEquipe.size(); i++){
//              
//          }
//    }
}
