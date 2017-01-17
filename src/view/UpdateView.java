package view;

import model.DataWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by John on 1/18/2017.
 */
public class UpdateView extends JFrame {

    private JTextField groupTF, firstNameTF, surnameTF,
            addressTF, baptismTF, birthdayTF, kapisananTF;

    private JButton updateBtn;

    public UpdateView() {

        updateBtn = new JButton("UPDATE");

        groupTF = newTF();
        firstNameTF = newTF();
        surnameTF = newTF();
        addressTF = newTF();
        baptismTF = newTF();
        birthdayTF = newTF();
        kapisananTF = newTF();

        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(0);
        flowLayout.setHgap(10);

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(flowLayout);

        headerPanel.add(groupTF);
        headerPanel.add(firstNameTF);
        headerPanel.add(surnameTF);
        headerPanel.add(addressTF);
        headerPanel.add(baptismTF);
        headerPanel.add(birthdayTF);
        headerPanel.add(kapisananTF);
        headerPanel.add(updateBtn);

        add(headerPanel, BorderLayout.CENTER);

        setSize(1360, 100);
        setVisible(true);
    }

    public JTextField getGroupTF() {
        return groupTF;
    }

    public JTextField getFirstNameTF() {
        return firstNameTF;
    }

    public JTextField getSurnameTF() {
        return surnameTF;
    }

    public JTextField getAddressTF() {
        return addressTF;
    }

    public JTextField getBaptismTF() {
        return baptismTF;
    }

    public JTextField getBirthdayTF() {
        return birthdayTF;
    }

    public JTextField getKapisananTF() {
        return kapisananTF;
    }

    public JButton getUpdateBtn() {
        return updateBtn;
    }

    private JTextField newTF() {
        JTextField tf = new JTextField(13);
        return  tf;
    }

}
