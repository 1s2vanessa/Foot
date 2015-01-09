package Modele;

import Vue.Observateur;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Vanessa
 */
public class D2 implements fight {

    private ArrayList<Equipe> division2;
    //private BD data;
    private String pays;
    private Requetes r;
      private Equipe barrage1, barrage2;
      private ArrayList<Observateur> observateur;

    public D2(String pays) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        this.pays = pays;
        //  data= new BD();
        division2 = new ArrayList<>();
        r = new Requetes();

        division2 = r.getEquipeByPaysAndChampionnat(this.pays, "d2");

        /*String cmd="SELECT * FROM d2 where Pays ='"+this.pays+"'";
         ResultSet rs=data.link.executeQuery(cmd);
        
         while (rs.next()){
            
         //division2.add(new Equipe(rs.getString("Nom_Equipe"), i, "D2"));
         division2.add(new Equipe(rs.getInt("Classement"), rs.getString("Nom_Equipe"), rs.getInt("Pts"), rs.getInt("J"), rs.getInt("G"), rs.getInt("N"), rs.getInt("P"), rs.getInt("BP"), rs.getInt("BC"), rs.getInt("Diff"), rs.getString("Pays")));
         System.out.println("Equipe : "+rs.getString("Nom_Equipe")+" "+rs.getInt("Classement")+" "+ rs.getInt("Pts")+" "+ rs.getInt("J")+" "+ rs.getInt("G")+" "+ rs.getInt("N")+" "+rs.getInt("P")+" "+rs.getInt("BP")+" "+rs.getInt("BC")+" "+rs.getInt("Diff"));
         //  i++;
         }*/
        System.out.println(division2.size());

        /*  fight(division2.get(0), division2.get(2));
         affichage();
         fight(division2.get(0), division2.get(1));
         affichage();
         fight(division2.get(2), division2.get(0));
         */
    }
    
     public D2() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
          //  data= new BD();
        division2 = new ArrayList<>();
        r = new Requetes();

        division2 = r.getEquipeByChampionnat("d1");
        observateur = new ArrayList<>();
        System.out.println(division2.size());
    }

   public Equipe Barrage() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{

        for(int i=0;i<division2.size();i++){
            if(division2.get(i).getPays().equals("France") && division2.get(i).getClassement()==3){
                barrage1 = new Equipe(division2.get(i).getClassement(),division2.get(i).getNom(),division2.get(i).getPts(),division2.get(i).getJ(),division2.get(i).getG(),division2.get(i).getN(),division2.get(i).getP(),division2.get(i).getBP(),division2.get(i).getBC(),division2.get(i).getDiff(),division2.get(i).getPays(),division2.get(i).getType());
               
            }
            
            if(division2.get(i).getPays().equals("Angleterre") && division2.get(i).getClassement()==4){
                barrage2 = new Equipe(division2.get(i).getClassement(),division2.get(i).getNom(),division2.get(i).getPts(),division2.get(i).getJ(),division2.get(i).getG(),division2.get(i).getN(),division2.get(i).getP(),division2.get(i).getBP(),division2.get(i).getBC(),division2.get(i).getDiff(),division2.get(i).getPays(),division2.get(i).getType());
            }
            
        }
            fight(barrage1,barrage2);
        if(barrage1.getBP()>barrage2.getBP()){
            System.out.println(barrage1.getNom()+" "+barrage1.getPays());
            return barrage1;
        }else{
            System.out.println(barrage2.getNom()+" "+barrage2.getPays());
            return barrage2;
            
        }
        
    }

    public void affichage() {
        for (int i = 0; i < division2.size(); i++) {
            System.out.println(division2.get(i).getNom() + " " + division2.get(i).getClassement() + " " + division2.get(i).getPts() + " " + division2.get(i).getJ() + " " + division2.get(i).getG() + " " + division2.get(i).getN() + " " + division2.get(i).getP() + " " + division2.get(i).getBC() + " " + division2.get(i).getBP() + " " + division2.get(i).getDiff());
        }
    }

    @Override
    public void fight(Equipe e1, Equipe e2) throws SQLException {

      double gagnant = Math.random();
        int nbButMarque = (int) (Math.random() * 100) % 5;
        if (nbButMarque == 0) {
            nbButMarque = 1;
        }
        int nbButEcaisse = (int) (Math.random() * 100) % 5;
        if (nbButEcaisse >= nbButMarque) {
            nbButEcaisse = nbButMarque - 1;
        }

        if (e1 == e2) {
            System.out.println("Erreur même equipe");

        } else if (e1 != e2) {
            if (e1.getDeja_joue().contains(e2)) {
                System.out.println("Equipe déjà affrontée");
                gagnant = -1;
            }
            
            if (gagnant != -1) {
                if (gagnant > -1 && gagnant < 0.5) {
                    //Equipe gagnante
                    e1.setJ(e1.getJ() + 1);
                    e1.setG(e1.getG() + 1);
                    e1.setPts(e1.getPts() + 3);
                    e1.setBP(e1.getBP() + nbButMarque);
                    e1.setBC(e1.getBC() + nbButEcaisse);
                    e1.setDiff(e1.getBP() - e1.getBC());

                    //Equipe perdante
                    e2.setJ(e2.getJ() + 1);
                    e2.setP(e2.getP() + 1);
                    e2.setPts(e2.getPts() - 1);
                    e2.setBP(e2.getBP() + nbButEcaisse);
                    e2.setBC(e2.getBC() + nbButMarque);
                    e2.setDiff(e2.getBP() - e2.getBC());

                    System.err.println("Gagnante : " + e1.getNom());
                } else if (gagnant == 0.5) {
                    nbButEcaisse = nbButMarque;

                    //Equipe 2
                    e2.setJ(e2.getJ() + 1);
                    e2.setN(e2.getN() + 1);
                    e2.setPts(e2.getPts() + 1);
                    e2.setBP(e2.getBP() + nbButMarque);
                    e2.setBC(e2.getBC() + nbButEcaisse);
                    e2.setDiff(e2.getBP() - e2.getBC());

                    //Equipe 1
                    e1.setJ(e1.getJ() + 1);
                    e1.setP(e1.getP() + 1);
                    e1.setPts(e1.getPts() + 1);
                    e1.setBP(e1.getBP() + nbButEcaisse);
                    e1.setBC(e1.getBC() + nbButMarque);
                    e1.setDiff(e1.getBP() - e1.getBC());

                    System.err.println("Match Nul");
                } else {
                    //Equipe gagnante
                    e2.setJ(e2.getJ() + 1);
                    e2.setG(e2.getG() + 1);
                    e2.setPts(e2.getPts() + 3);
                    e2.setBP(e2.getBP() + nbButMarque);
                    e2.setBC(e2.getBC() + nbButEcaisse);
                    e2.setDiff(e2.getBP() - e2.getBC());

                    //Equipe perdante
                    e1.setJ(e1.getJ() + 1);
                    e1.setP(e1.getP() + 1);
                    e1.setPts(e1.getPts() - 1);
                    e1.setBP(e1.getBP() + nbButEcaisse);
                    e1.setBC(e1.getBC() + nbButMarque);
                    e1.setDiff(e1.getBP() - e1.getBC());

                    System.err.println("Gagnante : " + e2.getNom());
                }
            r.MAJBd(e1, "d2");
            r.MAJBd(e2, "d2");
            e1.getDeja_joue().add(e2);
            e2.getDeja_joue().add(e1);

              

          ArrayList<Equipe> tmp = r.Classement(e1.getPays(), "d2");
         //   division2.clear();
             //     division2.addAll(tmp);
                for (int j = 0; j < tmp.size(); j++) {
                    for (int i = 0; i < division2.size(); i++) {
                        if (division2.get(i).getNom().equals(tmp.get(j).getNom())) {
                            division2.get(i).setClassement(tmp.get(j).getClassement());
                        }

//                    if (division2.contains(e1)) {
//                        division2.get(i).setDeja_joue(e1.getDeja_joue());
//                    } else if (division2.get(i).getNom().equals(e2.getNom())) {
//                        division2.get(i).setDeja_joue(e2.getDeja_joue());
//                    }
                    }
                }
                
            }
        }
         // r.envoiEuropa(e2);
       // echangeD1D2();
    }

    public ArrayList<Equipe> getDivision2() {
        return division2;
    }

    public void setDivision2(ArrayList<Equipe> division2) {
        this.division2 = division2;
    }

   

    public JScrollPane tableau() {
        Object[][] donnees = {{division2.get(0).getNom(),
            division2.get(0).getClassement(),
            division2.get(0).getPts(),
            division2.get(0).getJ(),
            division2.get(0).getG(),
            division2.get(0).getN(),
            division2.get(0).getP(),
            division2.get(0).getBP(),
            division2.get(0).getBC(),
            division2.get(0).getDiff(),},
        {division2.get(1).getNom(),
            division2.get(1).getClassement(),
            division2.get(1).getPts(),
            division2.get(1).getJ(),
            division2.get(1).getG(),
            division2.get(1).getN(),
            division2.get(1).getP(),
            division2.get(1).getBP(),
            division2.get(1).getBC(),
            division2.get(1).getDiff(),},
        {division2.get(2).getNom(),
            division2.get(2).getClassement(),
            division2.get(2).getPts(),
            division2.get(2).getJ(),
            division2.get(2).getG(),
            division2.get(2).getN(),
            division2.get(2).getP(),
            division2.get(2).getBP(),
            division2.get(2).getBC(),
            division2.get(2).getDiff(),},
        {division2.get(3).getNom(),
            division2.get(3).getClassement(),
            division2.get(3).getPts(),
            division2.get(3).getJ(),
            division2.get(3).getG(),
            division2.get(3).getN(),
            division2.get(3).getP(),
            division2.get(3).getBP(),
            division2.get(3).getBC(),
            division2.get(3).getDiff(),},
        {division2.get(4).getNom(),
            division2.get(4).getClassement(),
            division2.get(4).getPts(),
            division2.get(4).getJ(),
            division2.get(4).getG(),
            division2.get(4).getN(),
            division2.get(4).getP(),
            division2.get(4).getBP(),
            division2.get(4).getBC(),
            division2.get(4).getDiff(),},
        {division2.get(5).getNom(),
            division2.get(5).getClassement(),
            division2.get(5).getPts(),
            division2.get(5).getJ(),
            division2.get(5).getG(),
            division2.get(5).getN(),
            division2.get(5).getP(),
            division2.get(5).getBP(),
            division2.get(5).getBC(),
            division2.get(5).getDiff(),},
        {division2.get(6).getNom(),
            division2.get(6).getClassement(),
            division2.get(6).getPts(),
            division2.get(6).getJ(),
            division2.get(6).getG(),
            division2.get(6).getN(),
            division2.get(6).getP(),
            division2.get(6).getBP(),
            division2.get(6).getBC(),
            division2.get(6).getDiff(),},
        {division2.get(7).getNom(),
            division2.get(7).getClassement(),
            division2.get(7).getPts(),
            division2.get(7).getJ(),
            division2.get(7).getG(),
            division2.get(7).getN(),
            division2.get(7).getP(),
            division2.get(7).getBP(),
            division2.get(7).getBC(),
            division2.get(7).getDiff(),},
        {division2.get(8).getNom(),
            division2.get(8).getClassement(),
            division2.get(8).getPts(),
            division2.get(8).getJ(),
            division2.get(8).getG(),
            division2.get(8).getN(),
            division2.get(8).getP(),
            division2.get(8).getBP(),
            division2.get(8).getBC(),
            division2.get(8).getDiff(),},
        {division2.get(9).getNom(),
            division2.get(9).getClassement(),
            division2.get(9).getPts(),
            division2.get(9).getJ(),
            division2.get(9).getG(),
            division2.get(9).getN(),
            division2.get(9).getP(),
            division2.get(9).getBP(),
            division2.get(9).getBC(),
            division2.get(9).getDiff(),},
        {division2.get(10).getNom(),
            division2.get(10).getClassement(),
            division2.get(10).getPts(),
            division2.get(10).getJ(),
            division2.get(10).getG(),
            division2.get(10).getN(),
            division2.get(10).getP(),
            division2.get(10).getBP(),
            division2.get(10).getBC(),
            division2.get(10).getDiff(),},
        {division2.get(11).getNom(),
            division2.get(11).getClassement(),
            division2.get(11).getPts(),
            division2.get(11).getJ(),
            division2.get(11).getG(),
            division2.get(11).getN(),
            division2.get(11).getP(),
            division2.get(11).getBP(),
            division2.get(11).getBC(),
            division2.get(11).getDiff(),},
        {division2.get(12).getNom(),
            division2.get(12).getClassement(),
            division2.get(12).getPts(),
            division2.get(12).getJ(),
            division2.get(12).getG(),
            division2.get(12).getN(),
            division2.get(12).getP(),
            division2.get(12).getBP(),
            division2.get(12).getBC(),
            division2.get(12).getDiff(),},
        {division2.get(13).getNom(),
            division2.get(13).getClassement(),
            division2.get(13).getPts(),
            division2.get(13).getJ(),
            division2.get(13).getG(),
            division2.get(13).getN(),
            division2.get(13).getP(),
            division2.get(13).getBP(),
            division2.get(13).getBC(),
            division2.get(13).getDiff(),},
        {division2.get(14).getNom(),
            division2.get(14).getClassement(),
            division2.get(14).getPts(),
            division2.get(14).getJ(),
            division2.get(14).getG(),
            division2.get(14).getN(),
            division2.get(14).getP(),
            division2.get(14).getBP(),
            division2.get(14).getBC(),
            division2.get(14).getDiff(),},
        {division2.get(15).getNom(),
            division2.get(15).getClassement(),
            division2.get(15).getPts(),
            division2.get(15).getJ(),
            division2.get(15).getG(),
            division2.get(15).getN(),
            division2.get(15).getP(),
            division2.get(15).getBP(),
            division2.get(15).getBC(),
            division2.get(15).getDiff(),},
        {division2.get(16).getNom(),
            division2.get(16).getClassement(),
            division2.get(16).getPts(),
            division2.get(16).getJ(),
            division2.get(16).getG(),
            division2.get(16).getN(),
            division2.get(16).getP(),
            division2.get(16).getBP(),
            division2.get(16).getBC(),
            division2.get(16).getDiff(),},
        {division2.get(17).getNom(),
            division2.get(17).getClassement(),
            division2.get(17).getPts(),
            division2.get(17).getJ(),
            division2.get(17).getG(),
            division2.get(17).getN(),
            division2.get(17).getP(),
            division2.get(17).getBP(),
            division2.get(17).getBC(),
            division2.get(17).getDiff(),},
        {division2.get(18).getNom(),
            division2.get(18).getClassement(),
            division2.get(18).getPts(),
            division2.get(18).getJ(),
            division2.get(18).getG(),
            division2.get(18).getN(),
            division2.get(18).getP(),
            division2.get(18).getBP(),
            division2.get(18).getBC(),
            division2.get(18).getDiff(),},
        {division2.get(19).getNom(),
            division2.get(19).getClassement(),
            division2.get(19).getPts(),
            division2.get(19).getJ(),
            division2.get(19).getG(),
            division2.get(19).getN(),
            division2.get(19).getP(),
            division2.get(19).getBP(),
            division2.get(19).getBC(),
            division2.get(19).getDiff(),},};
        String[] entetes = {"Nom_Equipe", "Classement", "Pts", "J", "G", "N", "P", "BP", "BC", "Diff"};
        JTable tableauScoreD1 = new JTable(donnees, entetes);

        return new JScrollPane(tableauScoreD1);

    }

}
