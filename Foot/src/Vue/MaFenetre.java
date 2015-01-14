/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Modele.ClubNational;
import Modele.CoupeLigue;
import Modele.CoupeNationale;
import Modele.D1;
import Modele.D2;
import Modele.Equipe;
import Modele.MonModelTable;
import Modele.Requetes;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Vanessa
 */
public final class MaFenetre extends JFrame implements ActionListener {

    private Tableau_Score tableauScoreD1, tableauScoreD2, ligue, tableauScore;
    static int dejacree = 0;
    private JMenuItem nouvelleSaison, d1Fr, d2Fr, d2Ang, d1Ang;
    private JMenuBar barreMenu;
    private JMenu menu1;
    private MonModelTable mmt;
    private Panno_Choix choix;
    private Panno_Equipe equipe;
    private Requetes r;
    private JButton manu, auto, semiAuto;
    private D1 div1Coupe;
    private D2 div2Coupe;
    private JPanel pano;
    private ClubNational clubCoupe;

    // private Tableau_Score score;
    public MaFenetre() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        mmt = new MonModelTable();
        r = new Requetes();
        this.setTitle("Football Manager");// titre fenetre
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//comportement de la fenetre : l'appli se termine quand on ferme la fenetre
        init(); //initialisation du panneau

    }

    public void init() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        RAZtout();
        //menu
        barreMenu = new JMenuBar();
        menu1 = new JMenu("Menu");
        nouvelleSaison = new JMenuItem("Nouvelle Saison");
        d1Fr = new JMenuItem("Nouvelle D1 Française");
        d2Fr = new JMenuItem("Nouvelle D2 Française");
        d1Ang = new JMenuItem("Nouvelle D1 Anglaise");
        d2Ang = new JMenuItem("Nouvelle D2 Anglaise");
        menu1.add(nouvelleSaison);
        menu1.add(d1Fr);
        menu1.add(d2Fr);
        menu1.add(d1Ang);
        menu1.add(d2Ang);
        barreMenu.add(menu1);
        this.setJMenuBar(barreMenu);
        pano = new JPanel();

        tableauScore = new Tableau_Score();
        //// tableauScoreD1 = new Tableau_Score();
        //   tableauScoreD2 = new Tableau_Score();
        //     ligue= new Tableau_Score();
        mmt.addObs(tableauScore);
        //   mmt.addObs(tableauScoreD1);
        // mmt.addObs(tableauScoreD2);

        equipe = new Panno_Equipe("", "");
        choix = new Panno_Choix();

        this.setLayout(new BorderLayout());
        add(choix, BorderLayout.NORTH);

        this.pack();

        choix.getChoixChampionnat().addActionListener(this);
        choix.getValiderChoix().addActionListener(this);
        d1Fr.addActionListener(this);
        d2Fr.addActionListener(this);
        d1Ang.addActionListener(this);
        d2Ang.addActionListener(this);
        nouvelleSaison.addActionListener(this);
        //     equipe.getValiderEquipe().addActionListener(this);

    }

    public void manuAuto() {
        pano.setBorder(new TitledBorder("Choix déroulement"));
        pano.setSize(new Dimension(400, 100));
        manu = new JButton("Manuel");
        auto = new JButton("Automatique");
        semiAuto = new JButton("Semi-Automatique");
        manu.addActionListener(this);
        auto.addActionListener(this);
        semiAuto.addActionListener(this);
        pano.add(manu, BorderLayout.WEST);
        pano.add(semiAuto, BorderLayout.CENTER);
        pano.add(auto, BorderLayout.EAST);
        this.add(pano);
        this.pack();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == choix.getChoixChampionnat()) {

        } else if (ae.getSource() == choix.getPays()) {

        } else if (ae.getSource() == choix.getValiderChoix()) {
            this.remove(equipe);
            this.remove(tableauScore);
            pano.removeAll();

            //création des divisions, des clubs nationaux en fonction du pays choisit
            try {
                div1Coupe = new D1((String) choix.getPays().getSelectedItem());
            } catch (SQLException ex) {
                Logger.getLogger(MaFenetre.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                div2Coupe = new D2((String) choix.getPays().getSelectedItem());
            } catch (SQLException ex) {
                Logger.getLogger(MaFenetre.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                clubCoupe = new ClubNational((String) choix.getPays().getSelectedItem());
            } catch (SQLException ex) {
                Logger.getLogger(MaFenetre.class.getName()).log(Level.SEVERE, null, ex);
            }

            //si une division est choisie alors l'utilisateur à le choix entre auto, semiAuto et manuel qui lui permettra de choisir comment faire
            //les matchs
            if (choix.getChoixChampionnat().getSelectedItem().equals("D1") || choix.getChoixChampionnat().getSelectedItem().equals("D2")) {

                manuAuto();

                //si ce n'est pas un division qui est choisie
            } else if (choix.getChoixChampionnat().getSelectedItem().equals("Coupe de la Ligue")) {

                try {
                    afficheCoupeLigue((String) choix.getPays().getSelectedItem());
                } catch (SQLException ex) {
                    Logger.getLogger(MaFenetre.class.getName()).log(Level.SEVERE, null, ex);
                }
                pano.setVisible(false);

            } else if (choix.getChoixChampionnat().getSelectedItem().equals("Coupe Nationale")) {
                System.err.println("ici");
                try {
                    afficheCoupeNationnale((String) choix.getPays().getSelectedItem());
                } catch (SQLException ex) {
                    Logger.getLogger(MaFenetre.class.getName()).log(Level.SEVERE, null, ex);
                }
                pano.setVisible(false);
            }
        } else if (ae.getSource() == manu) {

            try {
                GestionManuelle gestionManuelle=new GestionManuelle(this, "Gestion Manuelle", false, (String) choix.getPays().getSelectedItem(), (String) choix.getChoixChampionnat().getSelectedItem());
            } catch (SQLException ex) {
                Logger.getLogger(MaFenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (ae.getSource() == auto) {
            pano.setVisible(false);
            choixAffiche();

        } else if (ae.getSource() == semiAuto) {

            try {
                pano.setVisible(false);
                panoEquipe();
            } catch (SQLException ex) {
                Logger.getLogger(MaFenetre.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (ae.getSource() == equipe.getValiderEquipe()) {

            try {
                affichage_tableau();
            } catch (SQLException ex) {
            }
        } else if ((JMenuItem) ae.getSource() == d1Fr) {
            RAZ("France", "d1");
        } else if ((JMenuItem) ae.getSource() == d2Fr) {
            RAZ("France", "d2");
        } else if ((JMenuItem) ae.getSource() == d1Ang) {
            RAZ("Angleterre", "d1");
        } else if ((JMenuItem) ae.getSource() == d2Ang) {
            RAZ("Angleterre", "d2");
        } else if ((JMenuItem) ae.getSource() == nouvelleSaison) {
            RAZtout();
             this.remove(equipe);
        // this.remove(tableauScoreD1);
        this.remove(tableauScore);
       ((MonModelTable) tableauScore.getTable().getModel()).removeAll();
        }

    }

    public void choixAffiche() {
        if (choix.getChoixChampionnat().getSelectedItem().equals("D1")) {
            try {

                afficheD1((String) choix.getPays().getSelectedItem());
            } catch (SQLException ex) {
                Logger.getLogger(MaFenetre.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (choix.getChoixChampionnat().getSelectedItem().equals("D2")) {

            try {
                afficheD2((String) choix.getPays().getSelectedItem());
            } catch (SQLException ex) {
                Logger.getLogger(MaFenetre.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public void afficheCoupeLigue(String pays) throws SQLException {

        System.err.println("coupe ligue");
        CoupeLigue coupeLigue = new CoupeLigue(div1Coupe, div2Coupe);
        coupeLigue.match(coupeLigue.getListe(), 8);

        affichage_tableau();
    }

    public void afficheCoupeNationnale(String pays) throws SQLException {

        System.err.println("coupe nationale");
        CoupeNationale coupeNationnale = new CoupeNationale(div1Coupe, div2Coupe, clubCoupe);
        coupeNationnale.match(coupeNationnale.getListe(), 8);

        affichage_tableau();
    }

    public void afficheD1(String pays) throws SQLException {

        System.err.println("D1");
        //execution des matchs
        div1Coupe.match(0);

        //MAJ du tableau des scores
        ((MonModelTable) tableauScore.getTable().getModel()).removeAll();
        ArrayList<Equipe>tmp=r.Classement((String) choix.getPays().getSelectedItem(), "d1");
        div1Coupe.getDivision1().clear();
        div1Coupe.getDivision1().addAll(tmp);
       
        for (int i = 0; i < div1Coupe.getDivision1().size(); i++) {
            ((MonModelTable) tableauScore.getTable().getModel()).addLigne(div1Coupe.getDivision1().get(i).getNom(), Integer.toString(r.classementCoupeByName(div1Coupe.getDivision1().get(i).getNom(), div1Coupe.getDivision1().get(i).getType())), Integer.toString(div1Coupe.getDivision1().get(i).getPts()), Integer.toString(div1Coupe.getDivision1().get(i).getJ()), Integer.toString(div1Coupe.getDivision1().get(i).getG()), Integer.toString(div1Coupe.getDivision1().get(i).getN()), Integer.toString(div1Coupe.getDivision1().get(i).getP()), Integer.toString(div1Coupe.getDivision1().get(i).getBP()), Integer.toString(div1Coupe.getDivision1().get(i).getBC()), Integer.toString(div1Coupe.getDivision1().get(i).getDiff()));
        }

        add(tableauScore, BorderLayout.CENTER);
        this.pack();

    }

    public void afficheD2(String pays) throws SQLException {

        System.err.println("D2");
        div2Coupe.match(0);

        ((MonModelTable) tableauScore.getTable().getModel()).removeAll();

        for (int i = 0; i < div2Coupe.getDivision2().size(); i++) {
            ((MonModelTable) tableauScore.getTable().getModel()).addLigne(div2Coupe.getDivision2().get(i).getNom(), Integer.toString(r.classementCoupeByName(div2Coupe.getDivision2().get(i).getNom(), div2Coupe.getDivision2().get(i).getType())), Integer.toString(div2Coupe.getDivision2().get(i).getPts()), Integer.toString(div2Coupe.getDivision2().get(i).getJ()), Integer.toString(div2Coupe.getDivision2().get(i).getG()), Integer.toString(div2Coupe.getDivision2().get(i).getN()), Integer.toString(div2Coupe.getDivision2().get(i).getP()), Integer.toString(div2Coupe.getDivision2().get(i).getBP()), Integer.toString(div2Coupe.getDivision2().get(i).getBC()), Integer.toString(div2Coupe.getDivision2().get(i).getDiff()));
        }

        add(tableauScore, BorderLayout.CENTER);
        this.pack();

    }

    public void panoEquipe() throws SQLException {

        this.remove(tableauScore);
        this.remove(equipe);

        //permet d'afficher le panneau (omboBox) qui contient le choix des équipes
        equipe = new Panno_Equipe((String) choix.getPays().getSelectedItem(), (String) choix.getChoixChampionnat().getSelectedItem());
        add(equipe, BorderLayout.CENTER);
        equipe.getValiderEquipe().addActionListener(this);

//        //On supprime les données des tableaux
        ((MonModelTable) tableauScore.getTable().getModel()).removeAll();

        //On modifie les données des tableaux et on les ré-affiche en fonction de la division choisie
        if (choix.getChoixChampionnat().getSelectedItem().equals("D1")) {
            for (int i = 0; i < equipe.getDivision1().getDivision1().size(); i++) {
                ((MonModelTable) tableauScore.getTable().getModel()).addLigne(equipe.getDivision1().getDivision1().get(i).getNom(), Integer.toString(equipe.getDivision1().getDivision1().get(i).getClassement()), Integer.toString(equipe.getDivision1().getDivision1().get(i).getPts()), Integer.toString(equipe.getDivision1().getDivision1().get(i).getJ()), Integer.toString(equipe.getDivision1().getDivision1().get(i).getG()), Integer.toString(equipe.getDivision1().getDivision1().get(i).getN()), Integer.toString(equipe.getDivision1().getDivision1().get(i).getP()), Integer.toString(equipe.getDivision1().getDivision1().get(i).getBP()), Integer.toString(equipe.getDivision1().getDivision1().get(i).getBC()), Integer.toString(equipe.getDivision1().getDivision1().get(i).getDiff()));
            }

        } else if (choix.getChoixChampionnat().getSelectedItem().equals("D2")) {
            for (int i = 0; i < equipe.getDivision2().getDivision2().size(); i++) {
                ((MonModelTable) tableauScore.getTable().getModel()).addLigne(equipe.getDivision2().getDivision2().get(i).getNom(), Integer.toString(equipe.getDivision2().getDivision2().get(i).getClassement()), Integer.toString(equipe.getDivision2().getDivision2().get(i).getPts()), Integer.toString(equipe.getDivision2().getDivision2().get(i).getJ()), Integer.toString(equipe.getDivision2().getDivision2().get(i).getG()), Integer.toString(equipe.getDivision2().getDivision2().get(i).getN()), Integer.toString(equipe.getDivision2().getDivision2().get(i).getP()), Integer.toString(equipe.getDivision2().getDivision2().get(i).getBP()), Integer.toString(equipe.getDivision2().getDivision2().get(i).getBC()), Integer.toString(equipe.getDivision2().getDivision2().get(i).getDiff()));
            }

        }

        //ajout au panneau
        add(tableauScore, BorderLayout.SOUTH);
        pack();
    }

    public void affichage_tableau() throws SQLException {
        this.remove(tableauScore);
        pack();

        switch ((String) choix.getChoixChampionnat().getSelectedItem()) {
            case "D1":   //Si D1 choisit alors fight entre les équipes de la D1

                if (equipe.getEquipe1().getSelectedIndex() != 0 && equipe.getEquipe2().getSelectedIndex() != 0) {
                    System.err.println("E1 : " + (String) equipe.getEquipe1().getSelectedItem());
                    System.err.println("E2 : " + (String) equipe.getEquipe2().getSelectedItem());

                    equipe.getDivision1().fight(equipe.getDivision1().getDivision1().get(equipe.getEquipe1().getSelectedIndex() - 1), equipe.getDivision1().getDivision1().get(equipe.getEquipe2().getSelectedIndex() - 1));

                }
                //MAJ des données des tableaux suite au fight
                for (int i = 0; i < equipe.getDivision1().getDivision1().size(); i++) {
                    ((MonModelTable) tableauScore.getTable().getModel()).setLigne(i, equipe.getDivision1().getDivision1().get(i).getNom(), Integer.toString(equipe.getDivision1().getDivision1().get(i).getClassement()), Integer.toString(equipe.getDivision1().getDivision1().get(i).getPts()), Integer.toString(equipe.getDivision1().getDivision1().get(i).getJ()), Integer.toString(equipe.getDivision1().getDivision1().get(i).getG()), Integer.toString(equipe.getDivision1().getDivision1().get(i).getN()), Integer.toString(equipe.getDivision1().getDivision1().get(i).getP()), Integer.toString(equipe.getDivision1().getDivision1().get(i).getBP()), Integer.toString(equipe.getDivision1().getDivision1().get(i).getBC()), Integer.toString(equipe.getDivision1().getDivision1().get(i).getDiff()));
                }
                //MAJ de l'affichage du tableau
                tableauScore.Update();
                add(tableauScore, BorderLayout.SOUTH);

                break;

            case "D2":
                if (equipe.getEquipe1().getSelectedIndex() != 0 && equipe.getEquipe2().getSelectedIndex() != 0) {
                    equipe.getDivision2().fight(equipe.getDivision2().getDivision2().get(equipe.getEquipe1().getSelectedIndex() - 1), equipe.getDivision2().getDivision2().get(equipe.getEquipe2().getSelectedIndex() - 1));
                }
                //MAJ des données des tableaux suite au fight
                for (int i = 0; i < equipe.getDivision2().getDivision2().size(); i++) {
                    ((MonModelTable) tableauScore.getTable().getModel()).setLigne(i, equipe.getDivision2().getDivision2().get(i).getNom(), Integer.toString(equipe.getDivision2().getDivision2().get(i).getClassement()), Integer.toString(equipe.getDivision2().getDivision2().get(i).getPts()), Integer.toString(equipe.getDivision2().getDivision2().get(i).getJ()), Integer.toString(equipe.getDivision2().getDivision2().get(i).getG()), Integer.toString(equipe.getDivision2().getDivision2().get(i).getN()), Integer.toString(equipe.getDivision2().getDivision2().get(i).getP()), Integer.toString(equipe.getDivision2().getDivision2().get(i).getBP()), Integer.toString(equipe.getDivision2().getDivision2().get(i).getBC()), Integer.toString(equipe.getDivision2().getDivision2().get(i).getDiff()));
                }
                tableauScore.Update();
                add(tableauScore, BorderLayout.SOUTH);

                break;

            case "Coupe de la Ligue":
                ArrayList<Equipe> tmp = new ArrayList<>();
                ((MonModelTable) tableauScore.getTable().getModel()).removeAll();

                for (int i = 0; i < div1Coupe.getDivision1().size(); i++) {
                    tmp.add(div1Coupe.getDivision1().get(i));
                }
                for (int j = 0; j < div2Coupe.getDivision2().size(); j++) {
                    tmp.add(div2Coupe.getDivision2().get(j));
                }

                for (int i = 0; i < tmp.size(); i++) {
                    ((MonModelTable) tableauScore.getTable().getModel()).addLigne(tmp.get(i).getNom(), Integer.toString(r.classementCoupeByName(tmp.get(i).getNom(), tmp.get(i).getType())), Integer.toString(tmp.get(i).getPts()), Integer.toString(tmp.get(i).getJ()), Integer.toString(tmp.get(i).getG()), Integer.toString(tmp.get(i).getN()), Integer.toString(tmp.get(i).getP()), Integer.toString(tmp.get(i).getBP()), Integer.toString(tmp.get(i).getBC()), Integer.toString(tmp.get(i).getDiff()));
                }

                add(tableauScore, BorderLayout.SOUTH);
                break;

            case "Coupe Nationnale":
                ArrayList<Equipe> temp = new ArrayList<>();
                ((MonModelTable) tableauScore.getTable().getModel()).removeAll();

                for (int i = 0; i < div1Coupe.getDivision1().size(); i++) {
                    if (div1Coupe.getDivision1().get(i).getJ() > 0) {
                        temp.add(div1Coupe.getDivision1().get(i));
                    }
                }
                for (int j = 0; j < div2Coupe.getDivision2().size(); j++) {
                    if (div2Coupe.getDivision2().get(j).getJ() > 0) {
                        temp.add(div2Coupe.getDivision2().get(j));
                    }
                }
                for (int j = 0; j < clubCoupe.getClub().size(); j++) {
                    if (clubCoupe.getClub().get(j).getJ() > 0) {
                        temp.add(clubCoupe.getClub().get(j));
                    }
                }

                for (int i = 0; i < temp.size(); i++) {
                    ((MonModelTable) tableauScore.getTable().getModel()).addLigne(temp.get(i).getNom(), Integer.toString(r.classementCoupeByName(temp.get(i).getNom(), temp.get(i).getType())), Integer.toString(temp.get(i).getPts()), Integer.toString(temp.get(i).getJ()), Integer.toString(temp.get(i).getG()), Integer.toString(temp.get(i).getN()), Integer.toString(temp.get(i).getP()), Integer.toString(temp.get(i).getBP()), Integer.toString(temp.get(i).getBC()), Integer.toString(temp.get(i).getDiff()));
                }

                add(tableauScore, BorderLayout.SOUTH);
                break;

            case "Europa League":
                break;

            case "Champions League":
                break;

        }
        /*
         if (choix.getChoixChampionnat().getSelectedItem().equals("D1")) {

         System.err.println("E1 : " + (String) equipe.getEquipe1().getSelectedItem());

         System.err.println("E2 : " + (String) equipe.getEquipe2().getSelectedItem());
         if (equipe.getEquipe1().getSelectedIndex() != 0 && equipe.getEquipe2().getSelectedIndex() != 0) {
         // equipe.getDivision1().fight(r.getEquipeByName((String) equipe.getEquipe1().getSelectedItem(),"d1"), r.getEquipeByName((String) equipe.getEquipe2().getSelectedItem(),"d1"));

         equipe.getDivision1().fight(equipe.getDivision1().getDivision1().get(equipe.getEquipe1().getSelectedIndex() - 1), equipe.getDivision1().getDivision1().get(equipe.getEquipe2().getSelectedIndex() - 1));
         //MAJ de l'arrayList des equipes suite au classement de la BD
         //                ArrayList<Equipe> tmp = r.Classement(equipe.getDivision1().getDivision1().get(equipe.getEquipe1().getSelectedIndex() - 1).getPays(), "d1");
         //                equipe.getDivision1().getDivision1().clear();
         //                equipe.getDivision1().getDivision1().addAll(tmp);
         //                equipe.Update();

         }

         //MAJ des données des tableaux suite au fight
         for (int i = 0; i < equipe.getDivision1().getDivision1().size(); i++) {
         ((MonModelTable) tableauScoreD1.getTable().getModel()).setLigne(i, equipe.getDivision1().getDivision1().get(i).getNom(), Integer.toString(equipe.getDivision1().getDivision1().get(i).getClassement()), Integer.toString(equipe.getDivision1().getDivision1().get(i).getPts()), Integer.toString(equipe.getDivision1().getDivision1().get(i).getJ()), Integer.toString(equipe.getDivision1().getDivision1().get(i).getG()), Integer.toString(equipe.getDivision1().getDivision1().get(i).getN()), Integer.toString(equipe.getDivision1().getDivision1().get(i).getP()), Integer.toString(equipe.getDivision1().getDivision1().get(i).getBP()), Integer.toString(equipe.getDivision1().getDivision1().get(i).getBC()), Integer.toString(equipe.getDivision1().getDivision1().get(i).getDiff()));
         }
         //MAJ de l'affichage du tableau
         tableauScoreD1.Update();
         add(tableauScoreD1, BorderLayout.SOUTH);

         } else if (choix.getChoixChampionnat().getSelectedItem().equals("D2")) {
         if (equipe.getEquipe1().getSelectedIndex() != 0 && equipe.getEquipe2().getSelectedIndex() != 0) {
         equipe.getDivision2().fight(equipe.getDivision2().getDivision2().get(equipe.getEquipe1().getSelectedIndex() - 1), equipe.getDivision2().getDivision2().get(equipe.getEquipe2().getSelectedIndex() - 1));

         //MAJ de l'arrayList des equipes suite au classement de la BD
         //                ArrayList<Equipe> tmp = r.Classement(equipe.getDivision2().getDivision2().get(equipe.getEquipe1().getSelectedIndex() - 1).getPays(), "d2");
         //                equipe.getDivision2().getDivision2().clear();
         //                equipe.getDivision2().getDivision2().addAll(tmp);
         }

         //MAJ des données des tableaux suite au fight
         for (int i = 0; i < equipe.getDivision2().getDivision2().size(); i++) {
         ((MonModelTable) tableauScoreD2.getTable().getModel()).setLigne(i, equipe.getDivision2().getDivision2().get(i).getNom(), Integer.toString(equipe.getDivision2().getDivision2().get(i).getClassement()), Integer.toString(equipe.getDivision2().getDivision2().get(i).getPts()), Integer.toString(equipe.getDivision2().getDivision2().get(i).getJ()), Integer.toString(equipe.getDivision2().getDivision2().get(i).getG()), Integer.toString(equipe.getDivision2().getDivision2().get(i).getN()), Integer.toString(equipe.getDivision2().getDivision2().get(i).getP()), Integer.toString(equipe.getDivision2().getDivision2().get(i).getBP()), Integer.toString(equipe.getDivision2().getDivision2().get(i).getBC()), Integer.toString(equipe.getDivision2().getDivision2().get(i).getDiff()));
         }
         tableauScoreD2.Update();
         add(tableauScoreD2, BorderLayout.SOUTH);
         }
         */

        pack();
    }

    public void RAZ(String pays, String championnat) {

        try {
            r.RAZbase(championnat, pays);

        } catch (SQLException ex) {
            Logger.getLogger(MaFenetre.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        this.remove(equipe);
        // this.remove(tableauScoreD1);
        this.remove(tableauScore);
       ((MonModelTable) tableauScore.getTable().getModel()).removeAll();
        pack();

    }

    public void RAZtout() {
        try {
            r.RAZTotale();

        } catch (SQLException ex) {
            Logger.getLogger(MaFenetre.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        
       
        pack();
    }

}
