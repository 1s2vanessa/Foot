/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Modele.CoupeLigue;
import Modele.D1;
import Modele.D2;
import Modele.MonModelTable;
import Modele.Requetes;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author Vanessa
 */
public final class MaFenetre extends JFrame implements ActionListener {

    private Tableau_Score tableauScoreD1, tableauScoreD2;
    static int dejacree = 0;
    private JMenuItem nouvelleSaison, d1Fr, d2Fr, d2Ang, d1Ang;
    private JMenuBar barreMenu;
    private JMenu menu1;
    private MonModelTable mmt;
    private Panno_Choix choix;
    private Panno_Equipe equipe;
    private Requetes r;
    private JButton manu, auto;
    private D1 div1Coupe;
    private D2 div2Coupe;

    // private Tableau_Score score;
    public MaFenetre() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        mmt = new MonModelTable();
        r = new Requetes();
        this.setTitle("Football Manager");// titre fenetre
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//comportement de la fenetre : l'appli se termine quand on ferme la fenetre
        init(); //initialisation du panneau

    }

    public void init() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {

        //menu
        barreMenu = new JMenuBar();
        menu1 = new JMenu("Menu");
        // nouvelleSaison = new JMenuItem("Nouvelle Saison");
        d1Fr = new JMenuItem("Nouvelle D1 Française");
        d2Fr = new JMenuItem("Nouvelle D2 Française");
        d1Ang = new JMenuItem("Nouvelle D1 Anglaise");
        d2Ang = new JMenuItem("Nouvelle D2 Anglaise");
        // menu1.add(nouvelleSaison);
        menu1.add(d1Fr);
        menu1.add(d2Fr);
        menu1.add(d1Ang);
        menu1.add(d2Ang);
        barreMenu.add(menu1);
        this.setJMenuBar(barreMenu);

        //  nouvelleSaison.addActionListener(this);
        tableauScoreD1 = new Tableau_Score();
        tableauScoreD2 = new Tableau_Score();

        mmt.addObs(tableauScoreD1);
        mmt.addObs(tableauScoreD2);

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
        //     equipe.getValiderEquipe().addActionListener(this);

    }

    public void manuAuto() {
        manu = new JButton("Manuel");
        auto = new JButton("Automatique");
        manu.addActionListener(this);
        auto.addActionListener(this);
        add(manu, BorderLayout.WEST);
        add(auto, BorderLayout.EAST);
        this.pack();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == choix.getChoixChampionnat()) {

        } else if (ae.getSource() == choix.getPays()) {

        } else if (ae.getSource() == choix.getValiderChoix()) {
            if (choix.getChoixChampionnat().getSelectedItem().equals("Coupe de la Ligue")) {

                try {

                    afficheCoupeLigue((String) choix.getPays().getSelectedItem());
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(MaFenetre.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    Logger.getLogger(MaFenetre.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(MaFenetre.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(MaFenetre.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                //     manuAuto();
                // }
                // // else if  (ae.getSource() == manu) {
                //     manu.setVisible(false);
                //    auto.setVisible(false);
                panoEquipe();
            }
        } else if (ae.getSource() == equipe.getValiderEquipe()) {

            try {
                affichage_tableau();
            } catch (SQLException ex) {
                Logger.getLogger(MaFenetre.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MaFenetre.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(MaFenetre.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(MaFenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if ((JMenuItem) ae.getSource() == d1Fr) {
            RAZ("France", "d1");
        } else if ((JMenuItem) ae.getSource() == d2Fr) {
            RAZ("France", "d2");
        } else if ((JMenuItem) ae.getSource() == d1Ang) {
            RAZ("Angleterre", "d1");
        } else if ((JMenuItem) ae.getSource() == d2Ang) {
            RAZ("Angleterre", "d2");
        }
//        } else if ((JMenuItem) ae.getSource() == nouvelleSaison) {
//            RAZtout();
//        }

    }

    public void afficheCoupeLigue(String pays) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        div1Coupe = new D1(pays);
        div2Coupe = new D2(pays);

        System.err.println("coupe ligue");
        CoupeLigue coupeLigue = new CoupeLigue(div1Coupe, div2Coupe);
        coupeLigue.match(coupeLigue.getListe());
        //   System.err.println(equipeGagnante);
//        JOptionPane jop1;
//        jop1 = new JOptionPane();
//       jop1.showMessageDialog(null, "Le gagnant de la coupe de la Ligue est : " + equipeGagnante, "Message", JOptionPane.INFORMATION_MESSAGE);

    }

    public void panoEquipe() {
        this.remove(tableauScoreD1);
        this.remove(tableauScoreD2);
        this.remove(equipe);
        try {

            equipe = new Panno_Equipe((String) choix.getPays().getSelectedItem(), (String) choix.getChoixChampionnat().getSelectedItem());
            add(equipe, BorderLayout.CENTER);

            equipe.getValiderEquipe().addActionListener(this);

        } catch (InstantiationException ex) {
            Logger.getLogger(MaFenetre.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(MaFenetre.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MaFenetre.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MaFenetre.class.getName()).log(Level.SEVERE, null, ex);
        }

        //On supprime les données des tableaux
        ((MonModelTable) tableauScoreD1.getTable().getModel()).removeAll();
        ((MonModelTable) tableauScoreD2.getTable().getModel()).removeAll();

        //On modifie les données des tableaux et on les ré-affiche
        if (choix.getChoixChampionnat().getSelectedItem().equals("D1")) {
            for (int i = 0; i < equipe.getDivision1().getDivision1().size(); i++) {
                ((MonModelTable) tableauScoreD1.getTable().getModel()).addLigne(equipe.getDivision1().getDivision1().get(i).getNom(), Integer.toString(equipe.getDivision1().getDivision1().get(i).getClassement()), Integer.toString(equipe.getDivision1().getDivision1().get(i).getPts()), Integer.toString(equipe.getDivision1().getDivision1().get(i).getJ()), Integer.toString(equipe.getDivision1().getDivision1().get(i).getG()), Integer.toString(equipe.getDivision1().getDivision1().get(i).getN()), Integer.toString(equipe.getDivision1().getDivision1().get(i).getP()), Integer.toString(equipe.getDivision1().getDivision1().get(i).getBP()), Integer.toString(equipe.getDivision1().getDivision1().get(i).getBC()), Integer.toString(equipe.getDivision1().getDivision1().get(i).getDiff()));
            }
            add(tableauScoreD1, BorderLayout.SOUTH);

        } else if (choix.getChoixChampionnat().getSelectedItem().equals("D2")) {
            for (int i = 0; i < equipe.getDivision2().getDivision2().size(); i++) {
                ((MonModelTable) tableauScoreD2.getTable().getModel()).addLigne(equipe.getDivision2().getDivision2().get(i).getNom(), Integer.toString(equipe.getDivision2().getDivision2().get(i).getClassement()), Integer.toString(equipe.getDivision2().getDivision2().get(i).getPts()), Integer.toString(equipe.getDivision2().getDivision2().get(i).getJ()), Integer.toString(equipe.getDivision2().getDivision2().get(i).getG()), Integer.toString(equipe.getDivision2().getDivision2().get(i).getN()), Integer.toString(equipe.getDivision2().getDivision2().get(i).getP()), Integer.toString(equipe.getDivision2().getDivision2().get(i).getBP()), Integer.toString(equipe.getDivision2().getDivision2().get(i).getBC()), Integer.toString(equipe.getDivision2().getDivision2().get(i).getDiff()));
            }
            add(tableauScoreD2, BorderLayout.SOUTH);
        }
        pack();
    }

    public void affichage_tableau() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        this.remove(tableauScoreD1);
        this.remove(tableauScoreD2);

        //Si D1 choisit alors fight entre les équipes de la D1
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

        pack();
    }

    public void RAZ(String pays, String championnat) {

        try {
            r.RAZbase(championnat, pays);
        } catch (SQLException ex) {
            Logger.getLogger(MaFenetre.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.remove(equipe);
        this.remove(tableauScoreD1);
        this.remove(tableauScoreD2);
        pack();

    }

    public void RAZtout() {
        try {
            r.RAZTotale();
        } catch (SQLException ex) {
            Logger.getLogger(MaFenetre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
