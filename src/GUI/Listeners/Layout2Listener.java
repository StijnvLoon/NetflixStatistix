package GUI.Listeners;

import Database.SqlConnection;
import GUI.UserInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Layout2Listener implements ActionListener {

    private UserInterface ui;
    private SqlConnection connection;
    private String selectedAccount;
    private String selectedSerie;

    public Layout2Listener(UserInterface ui, SqlConnection connection) {
        this.ui = ui;
        this.connection = connection;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JComboBox jcb = (JComboBox)e.getSource();

        if (jcb.getName().equals("accounts")) {
            this.selectedAccount = (String)jcb.getSelectedItem();

        } else {
            this.selectedSerie = (String)jcb.getSelectedItem();
        }
    }

    public void getInfo() {

    }
}
