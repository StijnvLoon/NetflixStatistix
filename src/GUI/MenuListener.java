package GUI;

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
            System.out.println("werkt1");
            ui.setPanel1();

        } else if (buttonClicked == 2) {
            System.out.println("werkt2");

        } else if (buttonClicked == 3) {
            System.out.println("werkt3");

        } else if (buttonClicked == 4) {
            System.out.println("werkt4");

        } else if (buttonClicked == 5){
            System.out.println("werkt5");

        } else {
            System.out.println("werkt6");
        }
    }
}
