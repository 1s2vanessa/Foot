/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Vanessa
 */
public class Panno_Choix extends JPanel {

    private JComboBox pays, choixChampionnat;
    private JButton validerChoix;
 

    public Panno_Choix() {
        this.setBorder(new TitledBorder("Championnat"));
        this.setSize(new Dimension(400, 100));
        init();
    }

    public void init() {

        JLabel label = new JLabel("Choisir une pays: ");
        pays = new JComboBox();
        choixChampionnat = new JComboBox();
        validerChoix = new JButton("Valider");

        pays.addItem("---");
        pays.addItem("France");
        pays.addItem("Angleterre");

        choixChampionnat.addItem("---");
        choixChampionnat.addItem("D1");
        choixChampionnat.addItem("D2");
        choixChampionnat.addItem("Coupe de la Ligue");
        choixChampionnat.addItem("Coupe Nationale");
        choixChampionnat.addItem("Ligue des Champions");
        choixChampionnat.addItem("Europa league");
        
        this.setLayout(new GridBagLayout());
        //création des contraintes de placement
        GridBagConstraints contraintes = new GridBagConstraints();
        contraintes.fill = GridBagConstraints.BOTH;

        //placement des composants
        contraintes.gridx = 0;
        contraintes.gridy = 0;
        add(new JLabel("Choix Championnat : "), contraintes);

        contraintes.gridx = 1;
        add(choixChampionnat, contraintes);

        contraintes.gridx = 0;
        contraintes.gridy = 1;
        add(label, contraintes);

        contraintes.gridx = 1;
        add(pays, contraintes);

        contraintes.gridx = 3;
        add(validerChoix, contraintes);

        //this.setContentPane(pano);

    }

   

    public JComboBox getPays() {
        return pays;
    }

    public JComboBox getChoixChampionnat() {
        return choixChampionnat;
    }

    public JButton getValiderChoix() {
        return validerChoix;
    }
    
    
}
