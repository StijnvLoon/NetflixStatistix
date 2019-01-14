package GUI.Listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextAreaListener implements ActionListener {

    private JTextArea textArea;
    private String text;

    public TextAreaListener(JTextArea textArea, String text) {
        this.textArea = textArea;
        this.text = text;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.textArea.setText(this.text);
    }
}
