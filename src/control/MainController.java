package control;

/**
 * Created by John on 1/18/2017.
 */

import javafx.scene.control.Tab;
import model.DataReader;
import model.DataWriter;
import model.TableRow;
import view.UpdateView;
import view.ViewHome;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class MainController implements ActionListener{

    private ViewHome viewHome;

    private DefaultTableModel dm;

    private JTable table;

    static String[][] rows;

    public MainController(ViewHome viewHome) {
        this.viewHome = viewHome;
        table = viewHome.getTable();
        dm = new DefaultTableModel();
        table.setModel(dm);
        setListeners();
    }

    public void renderTable() {
        SwingUtilities.invokeLater(() -> {
            dm.setDataVector(new Object[][]{null}, new Object[]{"Group", "First Name",
                    "Surname", "Address", "Baptism", "Birthday", "Kapisanan", "UPDATE", "DELETE"});

            table.getColumn("UPDATE").setCellRenderer(new ButtonRenderer());
            table.getColumn("UPDATE").setCellEditor(
                    new ButtonEditor(new JCheckBox()));

            table.getColumn("DELETE").setCellRenderer(new ButtonRenderer());

            ButtonEditor buttonEditor = new ButtonEditor(new JCheckBox());
            buttonEditor.setController(this);
            table.getColumn("DELETE").setCellEditor(
                    buttonEditor);

            rows = new DataReader().getData();

            for (String[] data :rows ) {
                dm.addRow(data);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewHome.getAddBtn()) {
            TableRow row = new TableRow();
            row.setGroup(viewHome.getGroupTF().getText());
            row.setFirstName(viewHome.getFirstNameTF().getText());
            row.setSurname(viewHome.getSurnameTF().getText());
            row.setAddress(viewHome.getAddressTF().getText());
            row.setBaptism(viewHome.getBaptismTF().getText());
            row.setBirthday(viewHome.getBirthdayTF().getText());
            row.setKapisanan(viewHome.getKapisananTF().getText());

            if(new DataWriter().addRow(row)) {
                JOptionPane.showMessageDialog(null, "Row added");
                renderTable();
            }
            else {
                JOptionPane.showMessageDialog(null, "Error adding row");
            }
        }
    }

    private void setListeners() {
        viewHome.getAddBtn().addActionListener(this);
    }
}

class ButtonRenderer extends JButton implements TableCellRenderer {

    public ButtonRenderer() {
        setOpaque(true);
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            setForeground(table.getSelectionForeground());
            setBackground(table.getSelectionBackground());
        } else {
            setForeground(table.getForeground());
            setBackground(UIManager.getColor("Button.background"));
        }
        setText((value == null) ? "" : value.toString());
        return this;
    }
}


class ButtonEditor extends DefaultCellEditor {
    protected JButton button;

    private String label;
    private int rowIndex;

    private boolean isPushed;

    public ButtonEditor(JCheckBox checkBox) {
        super(checkBox);
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });
    }

    private MainController mainController;
    public void setController(MainController mainController) {
        this.mainController = mainController;
    }

    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {

        if (isSelected) {
            button.setForeground(table.getSelectionForeground());
            button.setBackground(table.getSelectionBackground());
        } else {
            button.setForeground(table.getForeground());
            button.setBackground(table.getBackground());
        }
        label = "DELETE";
        isPushed = true;
        rowIndex = row;

        //DO UPDATE
        if(column == 7) {
            label = "UPDATE";
            isPushed = true;
            rowIndex = row;

        }
        //DO DELETE
        else {
            label = "DELETE";
            isPushed = true;
            rowIndex = row;
        }
        return button;
    }

    public Object getCellEditorValue() {
        if (isPushed) {
            if(label.equals("DELETE")) {
                String firstName, lastName;

                firstName = MainController.rows[rowIndex - 1][1];
                lastName = MainController.rows[rowIndex - 1][2];

                JOptionPane.showMessageDialog(button,
                        firstName + " " +
                                 lastName+ " " +
                                " has been deleted.");

                if(new DataWriter().deleteRow(firstName, lastName)) {
                    mainController.renderTable();
                }
            }
            else if(label.equals("UPDATE")) {
                UpdateView view = new UpdateView();

                UpdateController updateController = new UpdateController(view, MainController.rows[rowIndex - 1], mainController);
            }
        }
        isPushed = false;
        return new String(label);
    }

    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }

    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}