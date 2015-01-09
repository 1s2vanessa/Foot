package Modele;

import Vue.Observateur;
import java.util.ArrayList;

/**
 *
 * @author Vanessa
 */
public class Equipe implements Observable {

    private String nom;
    private int classement;
    private String type, Pays;
    private int Pts, J, G, N, P, BP, BC, Diff;
    private ArrayList<Equipe> deja_joue;

    private ArrayList<Observateur> observateur;

    public Equipe(int classement, String nom, int Pts, int J, int G, int N, int P, int BP, int BC, int Diff, String Pays, String type) {
        this.nom = nom;
        this.classement = classement;
        this.Pays = Pays;
        this.Pts = Pts;
        this.J = J;
        this.G = G;
        this.N = N;
        this.P = P;
        this.BP = BP;
        this.BC = BC;
        this.Diff = Diff;
        this.type=type;
        deja_joue = new ArrayList<>();
        observateur=new ArrayList<>();
    }

    public Equipe(String type) {
        this.type=type;
    }

    public ArrayList<Equipe> getDeja_joue() {
        return deja_joue;
    }

    public void setDeja_joue(ArrayList<Equipe> deja_joue) {
        this.deja_joue = deja_joue;
          notifyObs();
    }

    public int getJ() {
        return J;
    }

    public void setJ(int J) {
        this.J = J;
          notifyObs();
    }

    public String getPays() {
        return Pays;
    }

    public void setPays(String Pays) {
        this.Pays = Pays;
          notifyObs();
    }

    public int getPts() {
        return Pts;
    }

    public void setPts(int Pts) {
        this.Pts = Pts;
          notifyObs();
    }

    public int getG() {
        return G;
    }

    public void setG(int G) {
        this.G = G;
          notifyObs();
    }

    public int getN() {
        return N;
    }

    public void setN(int N) {
        this.N = N;
          notifyObs();
    }

    public int getP() {
        return P;
    }

    public void setP(int P) {
        this.P = P;
          notifyObs();
    }

    public int getBP() {
        return BP;
    }

    public void setBP(int BP) {
        this.BP = BP;
          notifyObs();
    }

    public int getBC() {
        return BC;
    }

    public void setBC(int BC) {
        this.BC = BC;
          notifyObs();
    }

    public int getDiff() {
        return Diff;
    }

    public void setDiff(int Diff) {
        this.Diff = Diff;
        notifyObs();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
          notifyObs();
    }

    public int getClassement() {
        return classement;
    }

    public void setClassement(int classement) {
        this.classement = classement;
          notifyObs();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return ("Equipe : " + nom + " " + classement + " " + Pts + " " + J + " " + G + " " + N + " " + P + " " + BP + " " + BC + " " + Diff);

    }

    @Override
    public void addObs(Observateur ob) {
        observateur.add(ob);
    }

    @Override
    public void suppObs(Observateur ob) {
        observateur.remove(ob);
    }

    @Override
    public void notifyObs() {
        for (Observateur obs : observateur) {
            obs.Update();
        }
    }

}
