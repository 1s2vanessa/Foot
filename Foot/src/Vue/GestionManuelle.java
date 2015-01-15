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
 * 
 * GestionManuelle ouvre une JDialog qui permet de gérer manuellement des matchs
 */
public class GestionManuelle extends JDialog implements ActionListener {
    
    private Panno_Equipe panoEquipe;
    private JButton valider, reset;
    private JLabel e1Label, e1BCLabel, e1BPLabel, e2Label, e2BCLabel, e2BPLabel;
    private JTextField e1BC, e1BP, e2BC, e2BP;
    private String pays, championnat;
    private JPanel info = new JPanel();
    private GridBagConstraints contraintes;
    
    
    /**
     * Constructeur de la classe
     * @param frame : correspond à la fenetre qui est envoyée depuis MaFenetre
     * @param string : nom de la JDialog
     * @param bln : pour autoriser les interactions avec la fenetre principale
     * @param pays : récuperer le pays selectionné
     * @param championnat : récupérer le chmapionnat choisit
     * @throws SQLException 
     */
    public GestionManuelle(Frame frame, String string, boolean bln, String pays, String championnat) throws SQLException {
        super(frame, string, bln);
        this.pays = pays;
        this.championnat = championnat;
        init();
        this.pack();
        this.setVisible(true);
    }
    
    /**
     * Initialisation et placement des éléments de la JDialog
     * @throws SQLException 
     */
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
        
       // contraintes.gridx = 1;
       // contraintes.gridy = 2;
       // info.add(e1BCLabel, contraintes);
        
       // contraintes.gridx = 2;
      //  info.add(e1BC, contraintes);
        
        contraintes.gridx = 0;
        contraintes.gridy = 3;
        info.add(e2Label, contraintes);
        
        contraintes.gridx = 1;
        contraintes.gridy = 4;
        info.add(e2BPLabel, contraintes);
        
        contraintes.gridx = 2;
        info.add(e2BP, contraintes);
        
       // contraintes.gridx = 1;
      //  contraintes.gridy = 5;
       // info.add(e2BCLabel, contraintes);
        
        //contraintes.gridx = 2;
       // info.add(e2BC, contraintes);
        
        contraintes.gridx = 5;
        contraintes.gridy = 1;
        valider = new JButton("Valider");
        info.add(valider, contraintes);
        
        contraintes.gridy = 4;
        reset = new JButton("Annuler");
        info.add(reset, contraintes);
        
        this.add(info, BorderLayout.SOUTH);
        
        valider.addActionListener(this);
        reset.addActionListener(this);
        
    }
    
    /**
     * Pour rafraichir le label Equipe1 qui affiche l'équipe qui a été choisit pour être l'équipe1
     */
    public void labelEquipe1() {
        info.remove(e1Label);
        e1Label = new JLabel("Equipe 1 :  " + panoEquipe.getEquipe1().getSelectedItem());
        contraintes.gridx = 0;
        contraintes.gridy = 0;
        info.add(e1Label, contraintes);
        this.validate();
        this.revalidate();
        
    }
    
      /**
     * Pour rafraichir le label Equipe2 qui affiche l'équipe qui a été choisit pour être l'équipe2
     */
    public void labelEquipe2() {
        info.remove(e2Label);
        e2Label = new JLabel("Equipe 2 :  " + panoEquipe.getEquipe2().getSelectedItem());
        
        contraintes.gridx = 0;
        contraintes.gridy = 3;
        info.add(e2Label, contraintes);
        
        this.validate();
        this.revalidate();
    }
    
    /**
     * permet de gérer les interactions de la JDialog
     * @param ae 
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == reset) {
            //e1BC.setText("");
            e1BP.setText("");
           // e2BC.setText("");
            e2BP.setText("");
        } else if (ae.getSource() == panoEquipe.getEquipe1()) {
            labelEquipe1();
        } else if (ae.getSource() == panoEquipe.getEquipe2()) {
            labelEquipe2();
        }
    }

    /**
     * retourne le Panno_Equipe
     * @return le panneau qui contient les 2 comboBox pour le chois des équipe
     */
    public Panno_Equipe getPanoEquipe() {
        return panoEquipe;
    }

    /**
     * retourne le contenu des buts marqués pa l'équipe 1
     * @return BC de l'équipe 1
     */
    public JTextField getE1BP() {
        return e1BP;
    }


     /**
     * retourne le contenu des buts marqués pa l'équipe 2
     * @return BC de l'équipe 2
     */
    public JTextField getE2BP() {
        return e2BP;
    }


    /**
     * Permet de récupérer le button de la JDialog ^pour pouvoir l'utiliser sur MaFenetre
     * @return le bouton valider de la JDialog
     */
    public JButton getValider() {
        return valider;
    }
    
    
    
    
}
