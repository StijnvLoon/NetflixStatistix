package GUI.Listeners;

import Database.SqlConnection;
import GUI.UserInterface;
import Structure.Subscription;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.*;

//Wanneer door de gebruiker in de GUI met layout5 een item in de combobox wordt geselecteerd, wordt er vanaf deze class een String gereturned
//naar de changeLayout() method in UserInterface.

public class Layout5Listener implements ActionListener {

    private UserInterface ui;
    private SqlConnection connection;
    private JTextArea number;

    public Layout5Listener(UserInterface ui, SqlConnection connection, JTextArea number) {
        this.ui = ui;
        this.connection = connection;
        this.number = number;
    }


    //Hier wordt de layout aangepast aan de hand van de opgevraagde informatie
    @Override
    public void actionPerformed(ActionEvent e) {

        playSound("popsound.wav");
        ui.changeLayout(getInfo());

    }

    //geluidje wanneer je op de knop drukt
    public void playSound(String soundName)
    {
        try
        {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src\\Sounds\\popsound.wav").getAbsoluteFile( ));
            Clip clip = AudioSystem.getClip( );
            clip.open(audioInputStream);
            FloatControl gainControl =
                    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(6.0f);
            clip.start( );
        }
        catch(Exception ex)
        {
            System.out.println("Error with playing sound.");
            ex.printStackTrace( );
        }
    }

    //In deze method wordt alle informatie die horen bij de doorgegeven String en als één grote String gereturned.
    //Omdat er meerdere soorten informatie kan worden doorgegeven door de gebruiker, moet daarmee rekening worden gehouden.
    //Dit hebben we gedaan doormiddel van if-statements.
    public String getInfo() {

        if (this.number.getText().matches("[0-5]")) {

            ArrayList<Subscription> subscriptions = new ArrayList<Subscription>();

            try {
                ResultSet rs = connection.executeSql("SELECT Subscription.*\n" +
                                                        "FROM Subscription JOIN Profile ON Subscription.Name = Profile.Name\n" +
                                                        "GROUP BY Subscription.Name, Subscription.Address, Subscription.City\n" +
                                                        "HAVING COUNT(*) = " + this.number.getText() + ";");
                while (rs.next()) {
                    Subscription subscription = new Subscription(rs.getString("Name"), rs.getString("Address"));
                    subscriptions.add(subscription);
                }

            } catch (Exception e) {
                System.out.println(e);
            }

            String endString = "";

            for (Subscription subscription : subscriptions) {

                endString += "Naam: " + subscription.getName() + "\nAdres: " + subscription.getAddress() + "\n\n";
            }

            if (endString.equals("")) {
                endString = "Er is helaas niks gevonden onder voorwaarden.";
            }
            return endString;

        } else if (this.number.getText().matches("[0-9][0-9]*")) {
            return "Een abbonement kan maar maximaal 5 profielen hebben.";
        } else {
            return "'" + this.number.getText() + "' is geen nummer!";
        }
    }
}
