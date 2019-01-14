package GUI.Listeners;

import GUI.Layouts.FirstLayout;
import GUI.UserInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComboboxListener implements ActionListener {

    private FirstLayout fl;
    private String text;
    private UserInterface ui;

    public ComboboxListener(FirstLayout fl, UserInterface ui) {
        this.fl = fl;
        this.ui = ui;
    }

    public void setText(String text) {
        this.text = "blablabla";
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JComboBox cb = (JComboBox)e.getSource();
        String chosenItem = (String)cb.getSelectedItem();

        if (chosenItem == "kaas") {
            System.out.println("kaas");
            fl.setText(this.text);
            ui.update();

        } else {
            System.out.println("zoogdier");
            fl.setText("testestestest");

        }



    }
}
