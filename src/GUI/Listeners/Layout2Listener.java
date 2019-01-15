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
        String selectedAccount = "";
        String selectedSerie = "";

        if (jcb.getName().equals("accounts")) {
            selectedAccount = (String)jcb.getSelectedItem();
            System.out.println(selectedAccount);
        } else {
            selectedSerie = (String)jcb.getSelectedItem();
            System.out.println(selectedSerie);
        }
    }
}
