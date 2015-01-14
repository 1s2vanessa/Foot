package Modele;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class CoupeLigue implements fight {

    private D1 d1;
    private D2 d2;
    private ArrayList<Equipe> gagnant;
    private ArrayList<Equipe> liste;
    private ArrayList<Equipe> perdant;
    private Requetes r;

    public CoupeLigue(D1 d1, D2 d2) throws SQLException {
        r = new Requetes();
        this.d1 = d1;
        this.d2 = d2;

        for (int i = 0; i < d1.getDivision1().size(); i++) {
            d1.getDivision1().get(i).setClassement(r.classementCoupeByName(d1.getDivision1().get(i).getNom(), "d1"));
        }

        for (int i = 0; i < d2.getDivision2().size(); i++) {
            d2.getDivision2().get(i).setClassement(r.classementCoupeByName(d2.getDivision2().get(i).getNom(), "d2"));
        }
        gagnant = new ArrayList<>();
        liste = new ArrayList<>();
        perdant = new ArrayList<>();

        listeEquipe();
    }

    public void listeEquipe() throws SQLException {
        for (int i = 0; i < 16; i++) {
            getListe().add(d1.getDivision1().get(i));
            getListe().add(d2.getDivision2().get(i));

            // System.err.println(liste.get(i).getNom());
        }

        //       match(liste);
    }

    public void match(ArrayList<Equipe> list, int test) throws SQLException {

        System.out.println();
        if (test != 1) {
            System.out.println("******* 1/" + test + " de finale ********");
        } else {
            System.out.println("******** FINALE ********");
        }
        System.out.println();

        test = test / 2;

        if (list.size() < 3) {
            System.out.println("Le gagnant est= " + list.get(0).getNom());
            JOptionPane jop1;
            jop1 = new JOptionPane();
            jop1.showMessageDialog(null, "Le gagnant de la coupe de la Ligue est : " + list.get(0).getNom(), "Message", JOptionPane.INFORMATION_MESSAGE);

            //return list.get(0).getNom();
        } else {
            for (int i = 0; i < list.size() / 2; i++) {
                int j = (int) (Math.random() * list.size());
                int h = (int) (Math.random() * list.size());

                if (j != h && list.get(j) != list.get(h)) {
                    //if(!list.get(j).getDeja_joue().contains(list.get(h))){
                    //      test++;
                    // }
                    System.out.println("j= " + j + " h= " + h);
                    fight(list.get(j), list.get(h));
                    System.out.println();
                    affichage();
                } else {
                  //  if (test>10){

                    //     break;
                    // }
                    i--;

                }
            }

            ArrayList<Equipe> gagnant1 = new ArrayList<>();
            for (int i = 0; i < gagnant.size(); i++) {
                //Equipe e = gagnant.get(i);
                gagnant1.add(gagnant.get(i));
            }

            gagnant.removeAll(gagnant);
            match(gagnant1, test);
        }
        //  return null;
    }

    public void affichage() {
        for (int i = 0; i < gagnant.size(); i++) {
            System.out.println(gagnant.get(i).getNom());
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
                if (getGagnant().size() < 3) {
                    if (e1.getPts() > e2.getPts()) {
                        getGagnant().add(e1);
                        getPerdant().add(e2);
                    } else {
                        getGagnant().add(e2);
                        getPerdant().add(e1);
                    }
                    getListe().remove(e1);
                    getListe().remove(e2);
                }
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
                    System.err.println("Perdante : " + e2.getNom());

                    if (!getGagnant().contains(e1)) {
                        getGagnant().add(e1);
                    }

                    getPerdant().add(e2);
                    getListe().remove(e1);
                    getListe().remove(e2);
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
                    System.err.println("Perdante : " + e1.getNom());
                    if (!getGagnant().contains(e2)) {
                        getGagnant().add(e2);
                    }
                    getPerdant().add(e1);
                    getListe().remove(e1);
                    getListe().remove(e2);
                }

                // System.err.println(e1.toString());
                //   System.err.println(e2.toString());
                // MAJ BD pour le classement
                r.MAJBd(e1, e1.getType());
                r.MAJBd(e2, e2.getType());
                e1.getDeja_joue().add(e2);
                e2.getDeja_joue().add(e1);

                ArrayList<Equipe> tmpd1 = r.ClassementCoupe(e1.getPays());
                ArrayList<Equipe> tmpd2 = r.ClassementCoupe(e2.getPays());
                //   division1.clear();
                //     division1.addAll(tmp);
//                for (int j = 0; j < tmp.size(); j++) {
//                    for (int i = 0; i < division1.size(); i++) {
//                        if (division1.get(i).getNom().equals(tmp.get(j).getNom())) {
//                            division1.get(i).setClassement(tmp.get(j).getClassement());
//                        }
//
////                    if (division1.contains(e1)) {
////                        division1.get(i).setDeja_joue(e1.getDeja_joue());
////                    } else if (division1.get(i).getNom().equals(e2.getNom())) {
////                        division1.get(i).setDeja_joue(e2.getDeja_joue());
////                    }
//                    }
//                }

            }
        }
    }

    public ArrayList<Equipe> getPerdant() {
        return perdant;
    }

    public ArrayList<Equipe> getGagnant() {
        return gagnant;
    }

    public ArrayList<Equipe> getListe() {
        return liste;
    }

}
