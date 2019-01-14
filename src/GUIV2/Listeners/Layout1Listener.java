package GUIV2.Listeners;

import GUIV2.UserInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Layout1Listener implements ActionListener {

    private UserInterface ui;
    private String text;

    public Layout1Listener(UserInterface ui) {
        this.ui = ui;
    }

    public Layout1Listener(String text) {
        this.text = text;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JComboBox cb = (JComboBox)e.getSource();
        String chosenFilm = (String)cb.getSelectedItem();

        ui.changeLayout1(this.text);
    }
}
