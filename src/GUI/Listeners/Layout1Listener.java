package GUI.Listeners;

import Database.SqlConnection;
import GUI.UserInterface;
import Structure.Episode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;

//Wanneer door de gebruiker in de GUI met layout1 een item in de combobox wordt geselecteerd, wordt er vanaf deze class een String gereturned
//naar de changeLayout() method in UserInterface.

public class Layout1Listener implements ActionListener {

    private UserInterface ui;
    private SqlConnection connection;

    public Layout1Listener(UserInterface ui, SqlConnection connection) {
        this.ui = ui;
        this.connection = connection;
    }

    //Deze method haalt het geselecteerde item uit de combobox op, en zet die om naar een String
    //Ook wordt het tekstveld aangepast, aan de hand van het geselecteerde item.
    @Override
    public void actionPerformed(ActionEvent e) {

        JComboBox cb = (JComboBox)e.getSource();
        String chosenSerie = (String)cb.getSelectedItem();

        ui.changeLayout(getInfo(chosenSerie));
    }

    //In deze method wordt alle informatie die horen bij de gekozen serie en als één grote String gereturned.
    private String getInfo(String ChosenSerie) {

        ArrayList<Episode> episodes = new ArrayList<Episode>();

        //Opvragen van informatie
        try {
            ResultSet rs = connection.executeSql("SELECT Episode.Id, Program.Title, Episode.EpisodeNumber, Program.Duration\n" +
                                                    "FROM Episode JOIN Program on Episode.Id = Program.Id\n" +
                                                    "WHERE Episode.TitleOfSerie = '" + ChosenSerie + "';");

            // elke episode word aan de ArrayList episodes toegevoegd.
            while (rs.next()) {
                Episode episode = new Episode(rs.getInt("Id"), rs.getString("Title"), rs.getInt("Duration"), rs.getInt("EpisodeNumber"));
                ResultSet watchedDurations = connection.executeSql("SELECT WatchedDuration " +
                                                                        "FROM Profile_Program JOIN Program on Program.Id = Profile_Program.Id " +
                                                                        "WHERE Program.Title = '" + episode.getTitle() +  " '");
                while (watchedDurations.next()){
                    episode.addWatchedDuration(watchedDurations.getInt("WatchedDuration"));
                }
                episodes.add(episode);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        String endString = "";

        //alle benodigde informatie van de aflevering wordt uit de ArrayList gehaald en in dezelfde String gestopt, met behulp van \n blijft alles gescheiden.
        for (Episode episode : episodes) {

            endString += "Volgnummer: " + episode.getEpisodeNumber() + "\nTitle van de aflevering: " + episode.getTitle() + "\nGemiddeld is " + episode.getAverageWatchedDurationPercentage()+ "% van de aflevering bekeken.\n\n";
        }
        return endString;
    }
}
