package GUI.Layouts;

import GUI.Listeners.ComboboxListener;

import javax.swing.*;
import java.awt.*;

public class FirstLayout {

    private String[] comboBoxWords;
    private String text;

    public FirstLayout() {
    }

    public void setComboBoxWords(String[] comboBoxWords) {
        this.comboBoxWords = comboBoxWords;
    }

    public void setText(String text) {
        this.text = text;
    }

    public JPanel createPanel() {
        JPanel panel = new JPanel(new BorderLayout());


        String[] testwords = {"kaas", "zoogdier"};              //DIT IS VOOR DE TEST
        JComboBox comboBox = new JComboBox(testwords);
        JLabel text = new JLabel(this.text);

        comboBox.addActionListener(new ComboboxListener());

        panel.add(comboBox, BorderLayout.NORTH);
        panel.add(text, BorderLayout.CENTER);

        return panel;
    }
}
