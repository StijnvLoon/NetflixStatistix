package GUI.Layouts;

import GUI.Listeners.ComboboxListener;
import GUI.Listeners.TextAreaListener;
import GUI.UserInterface;

import javax.swing.*;
import java.awt.*;

public class FirstLayout {

    private String[] comboBoxWords;
    private String text;
    private UserInterface ui;
    private JTextArea textArea;
    private TextAreaListener tal;

    public FirstLayout(UserInterface ui) {
        this.ui = ui;
        this.textArea = new JTextArea();

    }

    public void setComboBoxWords(String[] comboBoxWords) {
        this.comboBoxWords = comboBoxWords;
    }

    public void setText(String text) {
        this.text = text;
        this.tal = new TextAreaListener(this.textArea, this.text);
    }

    public JPanel createPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        String[] testwords = {"kaas", "zoogdier"};              //DIT IS VOOR DE TEST
        JComboBox comboBox = new JComboBox(testwords);

        comboBox.addActionListener(new ComboboxListener(this, this.ui));

        panel.add(comboBox, BorderLayout.NORTH);
        panel.add(this.textArea, BorderLayout.CENTER);

        return panel;
    }
}
