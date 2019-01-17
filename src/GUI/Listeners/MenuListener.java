package GUI.Listeners;

import GUI.UserInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Het menu kijkt welk id de knop heeft die ingedrukt wordt, en voert aan de hand van dat id acties uit.

public class MenuListener implements ActionListener{

    private int buttonClicked;
    private UserInterface ui;

    public MenuListener(int buttonClicked, UserInterface ui) {
        this.buttonClicked = buttonClicked;
        this.ui = ui;
    }

    //Aan de hand van het id van de knop, wordt de container gereset, de nieuwe layout neergezet en daarna het frame geupdate.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (buttonClicked == 1) {
            ui.resetContainer();
            ui.setPanelLayout1();
            ui.update();

        } else if (buttonClicked == 2) {
            ui.resetContainer();
            ui.setPanelLayout2();
            ui.update();

        } else if (buttonClicked == 3) {
            ui.resetContainer();
            ui.setPanelLayout3();
            ui.update();

        } else if (buttonClicked == 4) {
            ui.resetContainer();
            ui.setPanelLayout4();
            ui.update();

        } else if (buttonClicked == 5){
            ui.resetContainer();
            ui.setPanelLayout5();
            ui.update();

        } else {
            ui.resetContainer();
            ui.setPanelLayout6();
            ui.update();

        }
    }
}
