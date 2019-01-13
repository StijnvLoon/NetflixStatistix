package GUI.Listeners;

import GUI.UserInterface;

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
            ui.setPanel1();

        } else if (buttonClicked == 2) {

        } else if (buttonClicked == 3) {

        } else if (buttonClicked == 4) {

        } else if (buttonClicked == 5){

        } else {
        }
    }
}
