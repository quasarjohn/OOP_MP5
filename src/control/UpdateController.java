package control;

import model.DataWriter;
import model.TableRow;
import view.UpdateView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by John on 1/18/2017.
 */
public class UpdateController implements ActionListener{

    private UpdateView view;
    private String[] row;
    private MainController mainController;
    public UpdateController(UpdateView updateView, String[] row, MainController context) {
        this.row = row;
        this.view = updateView;
        this.mainController = context;

        populateFields();
        view.getUpdateBtn().addActionListener(this);
    }

    private void populateFields() {
        view.getGroupTF().setText(row[0]);
        view.getFirstNameTF().setText(row[1]);
        view.getSurnameTF().setText(row[2]);
        view.getAddressTF().setText(row[3]);
        view.getBaptismTF().setText(row[4]);
        view.getBirthdayTF().setText(row[5]);
        view.getKapisananTF().setText(row[6]);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == view.getUpdateBtn()) {
            TableRow newRow = new TableRow();

            newRow.setGroup(view.getGroupTF().getText());
            newRow.setFirstName(view.getFirstNameTF().getText());
            newRow.setSurname(view.getSurnameTF().getText());
            newRow.setAddress(view.getAddressTF().getText());
            newRow.setBaptism(view.getBaptismTF().getText());
            newRow.setBirthday(view.getBirthdayTF().getText());
            newRow.setKapisanan(view.getKapisananTF().getText());

            if(new DataWriter().updateRow(row[1], row[2], newRow)) {
                JOptionPane.showMessageDialog(null, row[1] + " has been updated");
                view.dispose();
                mainController.renderTable();
            }
            else {
                JOptionPane.showMessageDialog(null, "ERROR UPDATING");
            }
        }
    }
}
