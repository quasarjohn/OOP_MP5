package view;


import javax.swing.*;
import java.awt.*;

/**
 * Created by John on 1/18/2017.
 */
public class ViewHome extends JFrame {
    private JButton addBtn;

    private JTextField groupTF, firstNameTF, surnameTF,
            addressTF, baptismTF, birthdayTF, kapisananTF;

    private JTable table;
    public ViewHome() {
        table = new JTable();

        addBtn = new JButton("ADD");

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
        headerPanel.add(addBtn);

        setLayout(new BorderLayout());


        add(new JScrollPane(table), BorderLayout.CENTER);
        add(headerPanel, BorderLayout.NORTH);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    private JTextField newTF() {
        JTextField tf = new JTextField(13);
        return  tf;
    }

    public JTable getTable() {
        return table;
    }

    public JButton getAddBtn() {
        return addBtn;
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
}
