package GUIV2.Listeners;

import GUIV2.UserInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuListener implements ActionListener{

    private int buttonClicked;
    private UserInterface ui;

    public MenuListener(int buttonClicked, UserInterface ui) {
        this.buttonClicked = buttonClicked;
        this.ui = ui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (buttonClicked == 1) {
            ui.setPanelLayout1();
            ui.update();

        } else if (buttonClicked == 2) {

        } else if (buttonClicked == 3) {

        } else if (buttonClicked == 4) {

        } else if (buttonClicked == 5){

        } else {
        }
    }
}