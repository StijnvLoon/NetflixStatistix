package GUI.Listeners;

import Database.SqlConnection;
import GUI.UserInterface;
import Structure.Episode;
import Structure.Film;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Layout3Listener implements ActionListener {

    private UserInterface ui;
    private SqlConnection connection;

    public Layout3Listener(UserInterface ui, SqlConnection connection) {
        this.ui = ui;
        this.connection = connection;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JComboBox cb = (JComboBox)e.getSource();
        String chosenFilm = (String)cb.getSelectedItem();

        ui.changeLayout(getInfo(chosenFilm));
    }

    public String getInfo(String chosenFilm) {

        ArrayList<String> films = new ArrayList<String>();

        try {
            ResultSet rs = connection.executeSql("SELECT DISTINCT Program.Title\n" +
                                                "FROM Program JOIN Film ON Program.Id = Film.Id\n" +
                                                "JOIN Profile_Program ON Profile_Program.Id = Program.Id\n" +
                                                "JOIN Profile ON Profile.ProfileName = Profile_Program.ProfileName\n" +
                                                "JOIN Subscription ON Subscription.Name = Profile.Name\n" +
                                                "WHERE Subscription.Name = '" + chosenFilm + "'");
            while (rs.next()) {
                String filmTitle = rs.getString("Title");
                films.add(filmTitle);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        String endString = "";

        for (String film : films) {

            endString += "Titel: " + film + "\n";
        }

        return endString;
    }
}
