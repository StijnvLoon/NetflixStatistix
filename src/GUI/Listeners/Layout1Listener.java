package GUI.Listeners;

import Database.SqlConnection;
import GUI.UserInterface;
import Structure.Episode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Layout1Listener implements ActionListener {

    private UserInterface ui;
    private SqlConnection sqlConnection;

    public Layout1Listener(UserInterface ui, SqlConnection connection) {
        this.ui = ui;
        this.sqlConnection = connection;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JComboBox cb = (JComboBox)e.getSource();
        String chosenFilm = (String)cb.getSelectedItem();

        ui.changeLayout(getInfo(chosenFilm));
    }

    private String getInfo(String ChosenSerie) {

        ArrayList<Episode> episodes = new ArrayList<Episode>();

        // van alle afleveringen in serie Pirates, het volgnummer en gemiddeld bekeken percentage

        try {
            ResultSet rs = sqlConnection.executeSql("SELECT Episode.Id, Program.Title, Episode.EpisodeNumber, Program.Duration\n" +
                                                    "FROM Episode JOIN Program on Episode.Id = Program.Id\n" +
                                                    "WHERE Episode.TitleOfSerie = '" + ChosenSerie + "';");
            while (rs.next()) {
                Episode episode = new Episode(rs.getInt("Id"), rs.getString("Title"), rs.getInt("Duration"), rs.getInt("EpisodeNumber"));
                ResultSet watchedDurations = sqlConnection.executeSql("SELECT WatchedDuration FROM Profile_Program JOIN Program on Program.Id = Profile_Program.Id WHERE Program.Title = '" + episode.getTitle() +  " '");
                while (watchedDurations.next()){
                    episode.addWatchedDuration(watchedDurations.getInt("WatchedDuration"));
                }
                episodes.add(episode);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        String endString = "";

        for (Episode episode : episodes) {

            endString += "Aflevering: " + episode.getTitle() + "\nVolgnummer: " + episode.getEpisodeNumber() + "\nGemiddeld " + episode.getAverageWatchedDurationPercentage()+ "% van de afvlevering bekeken.\n\n";
        }

/*        String test = "";
        try {
            ResultSet rs = null;

            rs = this.sqlConnection.executeSql("SELECT TOP 1 Title FROM Program");
            rs.next();
            test += rs.getString("Title");


            System.out.println(test);
        } catch (Exception e) {
            System.out.println(e);
        }*/



        return endString;
    }

    private String getRings() {

        return "Ringon";
    }
}
