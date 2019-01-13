package GUI.Listeners;

import GUI.Layouts.FirstLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComboboxListener implements ActionListener {

    private FirstLayout fl;
    private String text;

    public ComboboxListener(FirstLayout fl) {
        this.fl = fl;
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

        } else {
            System.out.println("zoogdier");
        }



    }
}
