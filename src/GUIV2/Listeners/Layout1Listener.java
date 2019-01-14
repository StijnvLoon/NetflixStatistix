package GUIV2.Listeners;

import Database.SqlConnection;
import GUIV2.UserInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Layout1Listener implements ActionListener {

    private UserInterface ui;
    private SqlConnection sqlConnection;

    public Layout1Listener(UserInterface ui) {
        this.ui = ui;
    }

    public Layout1Listener(SqlConnection sqlConnection) {
        this.sqlConnection = sqlConnection;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JComboBox cb = (JComboBox)e.getSource();
        String chosenFilm = (String)cb.getSelectedItem();

        if (chosenFilm.equals("Pirates of the Caribbean: The Curse of the Black Pearl")) {
            ui.changeLayout1(getPirates());

        } else if (chosenFilm.equals("Lord of the rings")) {
            ui.changeLayout1(getRings());

        }
    }

    private String getPirates() {

    }

    private String getRings() {

    }
}
