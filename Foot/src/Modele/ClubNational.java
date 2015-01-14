/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author farida.ouali
 */

package Modele;

import Vue.Observateur;
import java.sql.SQLException;
import java.util.ArrayList;


public class ClubNational implements fight{

    private ArrayList<Equipe> club;
    private String pays;
    private Requetes r;
    private Equipe barrage1, barrage2;

    public ClubNational() throws SQLException {
          //  data= new BD();
        club = new ArrayList<>();
        r = new Requetes();

        club = r.getEquipeByChampionnat("clubs nationals");
        System.out.println(club.size());
    }
    
    public ClubNational(String pays) throws SQLException {
        this.pays = pays;
        //  data= new BD();
        club = new ArrayList<>();
        r = new Requetes();

        club = r.getEquipeByPaysAndChampionnat(pays, "clubs nationals");
        System.out.println(club.size());
    }

      /*public Equipe Barrage() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{

        for(int i=0;i<club.size();i++){
            if(club.get(i).getPays().equals("France") && club.get(i).getClassement()==3){
                barrage1 = new Equipe(club.get(i).getClassement(),club.get(i).getNom(),club.get(i).getPts(),club.get(i).getJ(),club.get(i).getG(),club.get(i).getN(),club.get(i).getP(),club.get(i).getBP(),club.get(i).getBC(),club.get(i).getDiff(),club.get(i).getPays(),club.get(i).getType());
               
            }
            
            if(club.get(i).getPays().equals("Angleterre") && club.get(i).getClassement()==4){
                barrage2 = new Equipe(club.get(i).getClassement(),club.get(i).getNom(),club.get(i).getPts(),club.get(i).getJ(),club.get(i).getG(),club.get(i).getN(),club.get(i).getP(),club.get(i).getBP(),club.get(i).getBC(),club.get(i).getDiff(),club.get(i).getPays(),club.get(i).getType());
            }
            
        }
            fight(barrage1,barrage2);
            
        if(barrage1.getBP()>barrage2.getBP()){
            System.out.println(barrage1.getNom()+" "+barrage1.getPays());
            //LDC barrage1
            //europa barrage2
            r.envoiLDC(barrage1);
            r.envoiEuropa(barrage2);
            
            return barrage1;
        }else{
            System.out.println(barrage2.getNom()+" "+barrage2.getPays());
            //LDC barrage2
            //europa barrage1
            r.envoiLDC(barrage2);
            r.envoiEuropa(barrage1);
            return barrage2;
            
        }
        
    }*/

    public void affichage() {
        for (int i = 0; i < club.size(); i++) {
            System.out.println(club.get(i).getNom() + " " + club.get(i).getClassement() + " " + club.get(i).getPts() + " " + club.get(i).getJ() + " " + club.get(i).getG() + " " + club.get(i).getN() + " " + club.get(i).getP() + " " + club.get(i).getBC() + " " + club.get(i).getBP() + " " + club.get(i).getDiff());
        }
    }

   /* public void echangeD1D2() throws SQLException {
        r.switchEquipeD1D2(pays);
    }*/

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
                
                //MAJ BD pour le classement
                r.MAJBd(e1, "d1");
                r.MAJBd(e2, "d1");

                e1.getDeja_joue().add(e2);
                e2.getDeja_joue().add(e1);

              

          ArrayList<Equipe> tmp = r.Classement(e1.getPays(), "clubs nationals");
         //   club.clear();
             //     club.addAll(tmp);
                for (int j = 0; j < tmp.size(); j++) {
                    for (int i = 0; i < club.size(); i++) {
                        if (club.get(i).getNom().equals(tmp.get(j).getNom())) {
                            club.get(i).setClassement(tmp.get(j).getClassement());
                        }

//                    if (club.contains(e1)) {
//                        club.get(i).setDeja_joue(e1.getDeja_joue());
//                    } else if (club.get(i).getNom().equals(e2.getNom())) {
//                        club.get(i).setDeja_joue(e2.getDeja_joue());
//                    }
                    }
                }
                
            }
        }
//        try {
//            finD1();
//            //System.err.println(r.finSaison("d1", pays));
//            // r.envoiEuropa(e2);
//            // echangeD1D2();
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(D1.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            Logger.getLogger(D1.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            Logger.getLogger(D1.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

 
    public ArrayList<Equipe> getClub() {
        return club;
    }

    public void setClub(ArrayList<Equipe> club) {
        this.club = club;
    }
   
}