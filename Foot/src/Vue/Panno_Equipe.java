/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Modele.D1;
import Modele.D2;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Vanessa
 */
public class Panno_Equipe extends JPanel{

    private String pays, championnat;
    private D1 division1;
    private D2 division2;
    private JComboBox equipe1, equipe2;
    private GridBagConstraints contraintes;
    private JButton validerEquipe;
    private JLabel e1, e2;

    public Panno_Equipe(String pays, String championnat) throws SQLException {

        this.pays = pays;
        this.championnat = championnat;
        this.setBorder(new TitledBorder("Equipe"));
        this.setSize(new Dimension(400, 100));

        init();
    }

    public void init() throws  SQLException {

        equipe1 = new JComboBox();
        equipe2 = new JComboBox();
        equipe1.addItem("---");
        equipe2.addItem("---");

        validerEquipe = new JButton("Valider");

        this.setLayout(new GridBagLayout());
        //création des contraintes de placement
        contraintes = new GridBagConstraints();
        contraintes.fill = GridBagConstraints.BOTH;

        switch (championnat) {
            case "":
                break;
            case "D1":
                division1 = new D1(pays);
                for (int i = 0; i < division1.getDivision1().size(); i++) {
                    equipe1.addItem(division1.getDivision1().get(i).getNom());
                    equipe2.addItem(division1.getDivision1().get(i).getNom());
                    //((MonModelTable)tableauScoreD1.getModel()).addLigne(division1.getDivision1().get(i).getNom(),Integer.toString(division1.getDivision1().get(i).getClassement()),Integer.toString(division1.getDivision1().get(i).getPts()), Integer.toString(division1.getDivision1().get(i).getJ()), Integer.toString(division1.getDivision1().get(i).getG()),Integer.toString(division1.getDivision1().get(i).getN()),Integer.toString(division1.getDivision1().get(i).getP()),Integer.toString(division1.getDivision1().get(i).getBP()),Integer.toString(division1.getDivision1().get(i).getBC()),Integer.toString(division1.getDivision1().get(i).getDiff()));
                }
                break;
            case "D2":
                division2 = new D2(pays);
                for (int i = 0; i < division2.getDivision2().size(); i++) {
                    equipe1.addItem(division2.getDivision2().get(i).getNom());
                    equipe2.addItem(division2.getDivision2().get(i).getNom());

                }
                
                break;
        }

        e1 = new JLabel("Equipe 1 : ");
        e2 = new JLabel("VS equipe 2 : ");
        contraintes.gridx = 0;
        contraintes.gridy = 2;
        add(e1, contraintes);

        contraintes.gridx = 1;
        add(equipe1, contraintes);

        contraintes.gridx = 2;
        add(e2, contraintes);

        contraintes.gridx = 3;
        add(equipe2, contraintes);

        contraintes.gridx = 4;
        contraintes.gridy = 3;
        add(validerEquipe, contraintes);

    }

    public JComboBox getEquipe1() {
        return equipe1;
    }

    public JComboBox getEquipe2() {
        return equipe2;
    }

    public JButton getValiderEquipe() {
        return validerEquipe;
    }

    public D1 getDivision1() {
        return division1;
    }

    public D2 getDivision2() {
        return division2;
    }

    
    public void Update() {
    this.removeAll();
        try {
            init();
        } catch (SQLException ex) {
            Logger.getLogger(Panno_Equipe.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        validate();
        revalidate();
    
    }

}
