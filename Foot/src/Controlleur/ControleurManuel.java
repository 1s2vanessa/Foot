/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlleur;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Vanessa
 * 
 * Cette classe permet de gérer les informations qui sont rentrées par l'utilisateur dans la base
 */
public class ControleurManuel {

    public ControleurManuel() {
    }

    /**
     * Cette fonction récupère et traite les informations rentrées pas l'utilisateur
     * @param info liste d'information rentrées par l'utilisateur
     * @return un nombre et en fonction de celui ci la base pourra se mettre à jour
     */
    public int control(ArrayList<String> info) {
        int retour=0;
        if (info.get(0).startsWith("-") || info.get(0).matches(".*[a-zA-Z].*") || info.get(0).equals("")) {
            JOptionPane.showMessageDialog(null, "Erreur dans le contenu BP de l'équipe 1", "Message", JOptionPane.INFORMATION_MESSAGE);
            retour= 1;
        } 
        if (info.get(1).startsWith("-") || info.get(1).matches(".*[a-zA-Z].*") || info.get(1).equals("")) {

            JOptionPane.showMessageDialog(null, "Erreur dans le contenu BP de l'équipe 2", "Message", JOptionPane.INFORMATION_MESSAGE);
            retour= 1;
        }
        if (info.get(2).equals("---")|| info.get(3).equals("---")){
            JOptionPane.showMessageDialog(null, "Une des équipes n'est pas renseigné", "Message", JOptionPane.INFORMATION_MESSAGE);
            retour= 1;
        }
        if (info.get(2).equals(info.get(3))){
            JOptionPane.showMessageDialog(null, "Même Equipe", "Message", JOptionPane.INFORMATION_MESSAGE);
            retour=1;
        }
        return retour;
    }
}
