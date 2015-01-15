package Modele;

import Vue.Observateur;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;


public class MonModelTable extends DefaultTableModel implements Observable {

    private class Ligne {

        String nom, classement, pts, J, G, N, P, BP, BC, Diff;

        public Ligne(String nom, String classement, String pts, String J, String G, String N, String P, String BP, String BC, String Diff) {
            this.nom = nom;
            this.classement = classement;
            this.pts = pts;
            this.J = J;
            this.G = G;
            this.N = N;
            this.P = P;
            this.BP = BP;
            this.BC = BC;
            this.Diff = Diff;
        }

    }

    private String[] entetes = {"Nom_Equipe", "Classement", "Pts", "J", "G", "N", "P", "BP", "BC", "Diff"};
    private ArrayList<Ligne> liste = new ArrayList();
    private ArrayList<Observateur> observateur;

    public MonModelTable() {
        super();
        observateur = new ArrayList<>();
    }

    public void removeAll() {
        int max = this.getRowCount();
        for (int i = 0; i < max; i++) {
            removeLigne(0);
        }
    }

    @Override
    public Object getValueAt(int row, int column) {

        switch (column) {
            case 0:
                return liste.get(row).nom;
            case 1:
                return liste.get(row).classement;

            case 2:
                return liste.get(row).pts;

            case 3:
                return liste.get(row).J;
            case 4:
                return liste.get(row).G;
            case 5:
                return liste.get(row).N;
            case 6:
                return liste.get(row).P;
            case 7:
                return liste.get(row).BP;
            case 8:
                return liste.get(row).BC;
            case 9:
                return liste.get(row).Diff;
        }
        return null;
    }

    @Override
    public int getRowCount() {
        try {
            return liste.size();
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int getColumnCount() {
        return entetes.length;
    }

    @Override
    public String getColumnName(int column) {
        return entetes[column];
    }

    public void addLigne(String nom, String classement, String pts, String J, String G, String N, String P, String BP, String BC, String Diff) {
        addLigne(new Ligne(nom, classement, pts, J, G, N, P, BP, BC, Diff));
        notifyObs();
    }

    private void addLigne(Ligne l) {
        liste.add(l);
        fireTableRowsInserted(liste.size() - 1, liste.size() - 1);
        notifyObs();
    }

    public void removeLigne(int index) {
        liste.remove(index);
        fireTableRowsDeleted(index, index);
        notifyObs();
    }

    public void removeAllLigne() {
        for (int i = liste.size() - 1; i > -1; i--) {
            liste.remove(i);
            fireTableRowsDeleted(i, i);
        }
        notifyObs();
    }

    public void setLigne(int index, String nom, String classement, String pts, String J, String G, String N, String P, String BP, String BC, String Diff) {
        liste.get(index).nom = nom;
        liste.get(index).classement = classement;
        liste.get(index).pts = pts;
        liste.get(index).J = J;
        liste.get(index).G = G;
        liste.get(index).N = N;
        liste.get(index).P = P;
        liste.get(index).BP = BP;
        liste.get(index).BC = BC;
        liste.get(index).Diff = Diff;
        //fireTableRowsDeleted(index, index);
        notifyObs();
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
            obs.update();
        }
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

}
