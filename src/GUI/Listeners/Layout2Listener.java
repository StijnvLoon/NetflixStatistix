package GUI.Listeners;

import Database.SqlConnection;
import GUI.UserInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Layout2Listener implements ActionListener {

    private UserInterface ui;
    private SqlConnection connection;

    public Layout2Listener(UserInterface ui, SqlConnection connection) {
        this.ui = ui;
        this.connection = connection;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("test");
        if (e.getSource() == ui.createJcomboboxAccounts()) {
            JComboBox accounts = (JComboBox)e.getSource();
        } else {
            JComboBox series = (JComboBox)e.getSource();
        }
    }
}
