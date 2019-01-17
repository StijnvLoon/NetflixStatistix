package GUI.Listeners;

import Database.SqlConnection;
import GUI.UserInterface;
import Structure.Episode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Layout2Listener implements ActionListener {

    private UserInterface ui;
    private SqlConnection connection;
    private String selectedAccount;
    private String selectedSerie;

    public Layout2Listener(UserInterface ui, SqlConnection connection) {
        this.ui = ui;
        this.connection = connection;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JComboBox jcb = (JComboBox)e.getSource();

        if (jcb.getName().equals("accounts")) {
            this.selectedAccount = (String)jcb.getSelectedItem();

        } else {
            this.selectedSerie = (String)jcb.getSelectedItem();
        }

        ui.changeLayout(getInfo());
    }

    public String getInfo() {

        ArrayList<Episode> episodes = new ArrayList<Episode>();

        // van alle afleveringen in serie Pirates, het volgnummer en gemiddeld bekeken percentage

        try {
            ResultSet rs = connection.executeSql("SELECT Episode.Id, Program.Title, Episode.EpisodeNumber, Program.Duration\n" +
                                                    "FROM Episode JOIN Program on Episode.Id = Program.Id\n" +
                                                    "WHERE Episode.TitleOfSerie = '" + this.selectedSerie + "';");
            while (rs.next()) {
                Episode episode = new Episode(rs.getInt("Id"), rs.getString("Title"), rs.getInt("Duration"), rs.getInt("EpisodeNumber"));
                ResultSet watchedDurations = connection.executeSql("SELECT WatchedDuration " +
                                                                        "FROM Profile_Program JOIN Program on Program.Id = Profile_Program.Id " +
                                                                        "JOIN Profile on Profile.ProfileName = Profile_Program.ProfileName " +
                                                                        "JOIN Subscription on Subscription.Name = Profile.Name " +
                                                                        "WHERE Program.Title = '" + episode.getTitle() +  "' AND Subscription.Name = '" + this.selectedAccount + "';" );
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
            endString += "Volgnummer: " + episode.getEpisodeNumber() + "\nTitle van de aflevering: " + episode.getTitle() + "\nGemiddeld is " + episode.getAverageWatchedDurationPercentage()+ "% van de aflevering bekeken.\n\n";
        }

        return endString;
    }
}
