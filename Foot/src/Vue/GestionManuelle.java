/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Vanessa
 */
public class GestionManuelle extends JDialog implements ActionListener {

    private Panno_Equipe panoEquipe;
    private JButton valider, reset;
    private JLabel e1Label, e1BCLabel, e1BPLabel, e2Label, e2BCLabel, e2BPLabel;
    private JTextField e1BC, e1BP, e2BC, e2BP;
    private String pays, championnat;
    private JPanel info = new JPanel();
    private GridBagConstraints contraintes;

    public GestionManuelle(Frame frame, String string, boolean bln, String p, String c) throws SQLException {
        super(frame, string, bln);
        pays = p;
        championnat = c;
        init();
        this.pack();
        this.setVisible(true);
    }

    public void init() throws SQLException {
        panoEquipe = new Panno_Equipe(pays, championnat);
        panoEquipe.getValiderEquipe().setVisible(false);
        this.add(panoEquipe, BorderLayout.NORTH);
        panoEquipe.getEquipe1().addActionListener(this);
        panoEquipe.getEquipe2().addActionListener(this);

        e1Label = new JLabel("Equipe 1 :  " + panoEquipe.getEquipe1().getSelectedItem());
        e1BCLabel = new JLabel("BC");
        e1BPLabel = new JLabel("BP");

        e2Label = new JLabel("Equipe 2 :  " + panoEquipe.getEquipe2().getSelectedItem());
        e2BCLabel = new JLabel("BC");
        e2BPLabel = new JLabel("BP");

        e1BC = new JTextField();
        e1BC.setColumns(10);

        e1BP = new JTextField();
        e1BP.setColumns(10);

        e2BC = new JTextField();
        e2BC.setColumns(10);

        e2BP = new JTextField();
        e2BP.setColumns(10);

        info.setBorder(new TitledBorder("Choix informations"));
        info.setLayout(new GridBagLayout());
        //création des contraintes de placement
        contraintes = new GridBagConstraints();
        contraintes.fill = GridBagConstraints.BOTH;

        contraintes.gridx = 0;
        contraintes.gridy = 0;
        info.add(e1Label, contraintes);

        contraintes.gridx = 1;
        contraintes.gridy = 1;
        info.add(e1BPLabel, contraintes);

        contraintes.gridx = 2;
        info.add(e1BP, contraintes);

        contraintes.gridx = 1;
        contraintes.gridy = 2;
        info.add(e1BCLabel, contraintes);
        
         contraintes.gridx = 2;
        info.add(e1BC, contraintes);

        contraintes.gridx = 0;
        contraintes.gridy = 3;
        info.add(e2Label, contraintes);

        contraintes.gridx = 1;
        contraintes.gridy = 4;
        info.add(e2BPLabel, contraintes);
        
        contraintes.gridx = 2;
        info.add(e2BP, contraintes);

        contraintes.gridx = 1;
        contraintes.gridy = 5;
        info.add(e2BCLabel, contraintes);
        
        contraintes.gridx = 2;
        info.add(e2BC, contraintes);
        

        this.add(info, BorderLayout.SOUTH);

    }

    public void labelEquipe1() {
        info.remove(e1Label);
        e1Label = new JLabel("Equipe 1 :  " + panoEquipe.getEquipe1().getSelectedItem());
        contraintes.gridx = 0;
        contraintes.gridy = 0;
        info.add(e1Label, contraintes);
        this.validate();
        this.revalidate();

    }

    public void labelEquipe2() {
        info.remove(e2Label);
        e2Label = new JLabel("Equipe 2 :  " + panoEquipe.getEquipe2().getSelectedItem());

        contraintes.gridx = 0;
        contraintes.gridy = 3;
        info.add(e2Label, contraintes);

        this.validate();
        this.revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == valider) {
            //maj bd en récupérant info
            //fight e1 et e2
        } else if (ae.getSource() == reset) {
            e1BC.setText("");
            e1BP.setText("");
            e2BC.setText("");
            e2BP.setText("");
        } else if (ae.getSource() == panoEquipe.getEquipe1()) {
            labelEquipe1();
        } else if (ae.getSource() == panoEquipe.getEquipe2()) {
            labelEquipe2();
        }
    }

}
