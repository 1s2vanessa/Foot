package Vue;

import Modele.MonModelTable;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Vanessa
 */
public class Tableau_Score extends JPanel implements Observateur {

    private JTable table;
    private MonModelTable model;

    public Tableau_Score() {
        this.setBorder(new TitledBorder("Résultat"));
        this.setSize(new Dimension(400, 100));
        model = new MonModelTable();
        table = new JTable(model);
        init();
    }

    public void init() {
        // model.removeAll();
        JScrollPane scroll = new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(590, 400));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.add(scroll);
    }

    public JTable getTable() {
        return table;
    }

    @Override
    public void Update() {
        this.removeAll();
        init();
        validate();
        revalidate();

    }

}
