package GUI.Listeners;

import Database.SqlConnection;
import GUI.UserInterface;
import Structure.Episode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;

//Wanneer door de gebruiker in de GUI met layout2 een item in de combobox wordt geselecteerd, wordt er vanaf deze class een String gereturned
//naar de changeLayout() method in UserInterface.

public class Layout2Listener implements ActionListener {

    private UserInterface ui;
    private SqlConnection connection;
    private String selectedAccount;
    private String selectedSerie;

    public Layout2Listener(UserInterface ui, SqlConnection connection) {
        this.ui = ui;
        this.connection = connection;
    }

    //Deze method haalt de geselecteerde items uit de comboboxen op, en zet die om naar een String
    //Ook wordt het tekstveld aangepast, aan de hand van de geselecteerde items.
    //Omdat er in desbetreffende layout 2 comboboxen waren, moesten we er iets op verzinnen om ze uit elkaar te halen.
    //Dit hebben we gedaan door de comboboxen een naam te geven in de UserInterface, en hier die naam op te vragen.
    //Zo konden we onderscheid maken tussen de verschillende geselecteerde items, en die aan een variable binnen de class binden.
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

    //In deze method wordt alle informatie die horen bij de gekozen account en serie als één grote String gereturned.
    public String getInfo() {

        ArrayList<Episode> episodes = new ArrayList<Episode>();

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
