package Modele;


import Vue.Observateur;

/**
 *
 * @author Vanessa
 */
public interface Observable {
    void addObs(Observateur ob);
    void suppObs(Observateur ob);
    void notifyObs();
}
