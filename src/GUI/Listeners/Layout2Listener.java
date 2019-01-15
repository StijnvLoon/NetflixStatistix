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
                                                    "JOIN Profile_Program on Program.Id = Profile_Program.Id\n" +
                                                    "JOIN Profile on Profile.ProfileName = Profile_Program.ProfileName\n" +
                                                    "JOIN Subscription on Subscription.Name = Profile.Name\n" +
                                                    "WHERE Episode.TitleOfSerie = '" + this.selectedSerie + "' AND Subscription.Name = '" + this.selectedAccount + "'");
            while (rs.next()) {
                episodes.add(new Episode(rs.getInt("Id"), rs.getString("Title"), rs.getInt("Duration"), rs.getInt("EpisodeNumber")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        String endString = "";

        for (Episode x : episodes) {
            endString += "Aflevering: " + x.getTitle() + "\nVolgnummer: " + x.getEpisodeNumber() + "\nGemiddeld voor: x" + " aantal minuten bekeken\n\n";
        }

        return endString;
    }
}
