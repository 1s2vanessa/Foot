package Modele;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Vanessa 
 * 
 * La classe D1 permet la création d'une liste d'équipe appartenant à la division1
 */
public class D1 implements fight, fightManuel {

    private ArrayList<Equipe> division1;
    private String pays;
    private Requetes r;
    private Equipe barrage1, barrage2;
    private ArrayList<Equipe> echange = new ArrayList<Equipe>();

    /**
     * Création de la division1
     *
     * @throws SQLException
     */
    public D1() throws SQLException {
        division1 = new ArrayList<>();
        r = new Requetes();
        division1 = r.getEquipeByChampionnat("d1");
    }

    /**
     * Création de la division1 en fonction d'un pays
     *
     * @param pays pays
     * @throws SQLException
     */
    public D1(String pays) throws SQLException {
        this.pays = pays;
        division1 = new ArrayList<>();
        r = new Requetes();
        division1 = r.getEquipeByPaysAndChampionnat(pays, "d1");
        //System.out.println(division1.size());
    }

    /**
     * Permet de gerer le barrage entre 2 équipes pour savoir qui va en Ligue
     * des Champions ou en Europa League
     *
     * @return l'équipe qui a gagné le barrage
     * @throws SQLException
     */
    public Equipe Barrage() throws SQLException {

        for (int i = 0; i < division1.size(); i++) {
            if (division1.get(i).getPays().equals("France") && division1.get(i).getClassement() == 3) {
                barrage1 = new Equipe(division1.get(i).getClassement(), division1.get(i).getNom(), division1.get(i).getPts(), division1.get(i).getJ(), division1.get(i).getG(), division1.get(i).getN(), division1.get(i).getP(), division1.get(i).getBP(), division1.get(i).getBC(), division1.get(i).getDiff(), division1.get(i).getPays(), division1.get(i).getType());

            }

            if (division1.get(i).getPays().equals("Angleterre") && division1.get(i).getClassement() == 4) {
                barrage2 = new Equipe(division1.get(i).getClassement(), division1.get(i).getNom(), division1.get(i).getPts(), division1.get(i).getJ(), division1.get(i).getG(), division1.get(i).getN(), division1.get(i).getP(), division1.get(i).getBP(), division1.get(i).getBC(), division1.get(i).getDiff(), division1.get(i).getPays(), division1.get(i).getType());
            }

        }
        fight(barrage1, barrage2);

        if (barrage1.getBP() > barrage2.getBP()) {
            System.out.println(barrage1.getNom() + " " + barrage1.getPays());
            //LDC barrage1
            //europa barrage2
            r.envoiLDC(barrage1);
            r.envoiEuropa(barrage2);

            return barrage1;
        } else {
            System.out.println(barrage2.getNom() + " " + barrage2.getPays());
            //LDC barrage2
            //europa barrage1
            r.envoiLDC(barrage2);
            r.envoiEuropa(barrage1);
            return barrage2;

        }

    }

    /**
     * Cette fonction permet un affichage console des valeurs contenues dans la
     * liste des équipes de la division1
     */
    public void affichage() {
        for (int i = 0; i < division1.size(); i++) {
            System.out.println(division1.get(i).getNom() + " " + division1.get(i).getClassement() + " " + division1.get(i).getPts() + " " + division1.get(i).getJ() + " " + division1.get(i).getG() + " " + division1.get(i).getN() + " " + division1.get(i).getP() + " " + division1.get(i).getBC() + " " + division1.get(i).getBP() + " " + division1.get(i).getDiff());
        }
    }

    public void echangeD1D2() throws SQLException {
        r.switchEquipeD1D2(pays);
    }

    /**
     * permet de générer des résultats aléatoires de match entre 2 équipes et 
     * permet la MAJ de la BD
     * @param e1 correspond à l'équipe qui va jouer contre e2
     * @param e2 correspond à l'équipe qui va jouer contre e1
     * @throws SQLException
     */
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
                    e1.setN(e1.getN() + 1);
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

                ArrayList<Equipe> tmp = r.Classement(e1.getPays(), "d1");
                //   division1.clear();
                //     division1.addAll(tmp);
                for (int j = 0; j < tmp.size(); j++) {
                    for (int i = 0; i < division1.size(); i++) {
                        if (division1.get(i).getNom().equals(tmp.get(j).getNom())) {
                            division1.get(i).setClassement(tmp.get(j).getClassement());
                        }

//                    if (division1.contains(e1)) {
//                        division1.get(i).setDeja_joue(e1.getDeja_joue());
//                    } else if (division1.get(i).getNom().equals(e2.getNom())) {
//                        division1.get(i).setDeja_joue(e2.getDeja_joue());
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

    
    /**
     * Cette fonction permet de vérifié si la D1 est finie
     * @throws SQLException 
     */
    public void finD1() throws SQLException {

        if (r.finSaison("d1", pays) == true) {
            Equipe tmp = Barrage();
            tmp.toString();
            r.switchEquipeD1D2(pays);
        }
    }

    public ArrayList<Equipe> getDivision1() {
        return division1;
    }

    public void setDivision1(ArrayList<Equipe> division1) {
        this.division1 = division1;
    }

    /**
     * Fonction récursive qui permet de générer tous les matchs de la D1 automatiquement
     * @param nbMatch correspond au nombre de match
     * @throws SQLException 
     */
    public void match(int nbMatch) throws SQLException {
        if (nbMatch == 19) {
            JOptionPane.showMessageDialog(null, "Fin de D1 pour  : " + pays, "Message", JOptionPane.INFORMATION_MESSAGE);

        } else {
            for (int i = 0; i < division1.size(); i++) {
                if (nbMatch != i) {
                    fight(division1.get(i), division1.get(nbMatch));
                }
            }
            nbMatch++;
            match(nbMatch);
        }

    }

    /**
     * permet le match entre 2 équipes et la MAJ de la BD avec les paramètres de la fonction
     * @param e1 correspond à l'équipe qui va jouer contre e2
     * @param e2 correspond à l'équipe qui va jouer contre e1
     * @param e1BP correspond au nombre de buts marqués par e1
     * @param e2BP correspond au nombre de buts marqués par e2
     * @throws SQLException
     */
    @Override
    public void fightManuel(Equipe e1, Equipe e2, int e1BP, int e2BP) throws SQLException {
        int gagnant = 0;
        if (e1 == e2) {
            System.out.println("Erreur même equipe");

        } else if (e1 != e2) {
            if (e1.getDeja_joue().contains(e2)) {
                System.out.println("Equipe déjà affrontée");
                gagnant = -1;
            }

            if (gagnant != -1) {
                if (e1BP > e2BP) {
                    //Equipe gagnante
                    e1.setJ(e1.getJ() + 1);
                    e1.setG(e1.getG() + 1);
                    e1.setPts(e1.getPts() + 3);
                    e1.setBP(e1.getBP() + e1BP);
                    e1.setBC(e1.getBC() + e2BP);
                    e1.setDiff(e1.getBP() - e1.getBC());

                    //Equipe perdante
                    e2.setJ(e2.getJ() + 1);
                    e2.setP(e2.getP() + 1);
                    e2.setPts(e2.getPts() - 1);
                    e2.setBP(e2.getBP() + e2BP);
                    e2.setBC(e2.getBC() + e1BP);
                    e2.setDiff(e2.getBP() - e2.getBC());

                    System.err.println("Gagnante : " + e1.getNom());
                } else if (e1BP == e2BP) {
                    //Equipe 2
                    e2.setJ(e2.getJ() + 1);
                    e2.setN(e2.getN() + 1);
                    e2.setPts(e2.getPts() + 1);
                    e2.setBP(e2.getBP() + e2BP);
                    e2.setBC(e2.getBC() + e1BP);
                    e2.setDiff(e2.getBP() - e2.getBC());

                    //Equipe 1
                    e1.setJ(e1.getJ() + 1);
                    e1.setN(e1.getN() + 1);
                    e1.setPts(e1.getPts() + 1);
                    e1.setBP(e1.getBP() + e1BP);
                    e1.setBC(e1.getBC() + e2BP);
                    e1.setDiff(e1.getBP() - e1.getBC());

                    System.err.println("Match Nul");
                } else {
                    //Equipe gagnante
                    e2.setJ(e2.getJ() + 1);
                    e2.setG(e2.getG() + 1);
                    e2.setPts(e2.getPts() + 3);
                    e2.setBP(e2.getBP() + e2BP);
                    e2.setBC(e2.getBC() + e1BP);
                    e2.setDiff(e2.getBP() - e2.getBC());

                    //Equipe perdante
                    e1.setJ(e1.getJ() + 1);
                    e1.setP(e1.getP() + 1);
                    e1.setPts(e1.getPts() - 1);
                    e1.setBP(e1.getBP() + e1BP);
                    e1.setBC(e1.getBC() + e2BP);
                    e1.setDiff(e1.getBP() - e1.getBC());

                    System.err.println("Gagnante : " + e2.getNom());
                }

                //MAJ BD pour le classement
                r.MAJBd(e1, "d1");
                r.MAJBd(e2, "d1");

                e1.getDeja_joue().add(e2);
                e2.getDeja_joue().add(e1);

                ArrayList<Equipe> tmp = r.Classement(e1.getPays(), "d1");
                for (int j = 0; j < tmp.size(); j++) {
                    for (int i = 0; i < division1.size(); i++) {
                        if (division1.get(i).getNom().equals(tmp.get(j).getNom())) {
                            division1.get(i).setClassement(tmp.get(j).getClassement());
                        }

                    }
                }

            }
        }

    }
}
