package GUI.Listeners;

import Database.SqlConnection;
import GUI.UserInterface;
import Structure.Subscription;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Layout5Listener implements ActionListener {

    private UserInterface ui;
    private SqlConnection connection;
    private JTextArea number;

    public Layout5Listener(UserInterface ui, SqlConnection connection, JTextArea number) {
        this.ui = ui;
        this.connection = connection;
        this.number = number;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        ui.changeLayout(getInfo());

    }

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

                endString += "Naam: " + subscription.getName() + "\nAdres: " + subscription.getAaddress() + "\n\n";
            }

            if (endString.equals("")) {
                endString = "Er is helaas niks gevonden onder voorwaarden.";
            }
            return endString;

        } else if (this.number.getText().matches("[6-9]*")) {
            return "Een abbonement kan maar maximaal 5 profielen hebben.";
        } else {
            return "'" + this.number.getText() + "' IS GEEN NUMMER!!";
        }
    }
}
