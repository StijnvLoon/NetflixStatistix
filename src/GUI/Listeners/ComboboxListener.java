package GUI.Listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComboboxListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        JComboBox cb = (JComboBox)e.getSource();
        String chosenItem = (String)cb.getSelectedItem();

        if (chosenItem == "kaas") {
            System.out.println("kaas");

        } else {
            System.out.println("zoogdier");
        }



    }
}
