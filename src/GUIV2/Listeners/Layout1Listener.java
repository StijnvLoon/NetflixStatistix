package GUIV2.Listeners;

import Database.SqlConnection;
import GUIV2.UserInterface;
import Structure.Episode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Layout1Listener implements ActionListener {

    private UserInterface ui;
    private SqlConnection sqlConnection;

    public Layout1Listener(UserInterface ui) {
        this.ui = ui;
    }

    public Layout1Listener(SqlConnection sqlConnection) {
        this.sqlConnection = sqlConnection;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JComboBox cb = (JComboBox)e.getSource();
        String chosenFilm = (String)cb.getSelectedItem();

        if (chosenFilm.equals("Pirates of the Caribbean")) {
            ui.changeLayout1(getNCIS());

        } else if (chosenFilm.equals("Lord of the rings")) {
            ui.changeLayout1(getRings());

        }
    }

    private String getNCIS() {

//        ArrayList<Episode> episodes = new ArrayList<Episode>();
//
        // van alle afleveringen in serie Pirates, het volgnummer en gemiddeld bekeken percentage
//
//        try {
//            ResultSet rs = sqlConnection.executeSql("SELECT * FROM Serie WHERE Title ");
//            while (rs.next()) {
//                episodes.add(new Episode(rs.getInt("Id"), rs.getString("Title"), rs.getInt("Duration"), rs.getInt("EpisodeNumber")));
//            }
//
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//
//        String endString = "";
//
//        for (Episode x : episodes) {
//
//
//            endString += "Serie: " + x.getTitle() + "\nVolgnummer: " + x.getEpisodeNumber() + "\nGemiddeld voor: ";
//        }

        String test = "";
        try {
            sqlConnection.connectDatabase("jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=NetflixStatistixMenS;integratedSecurity=true");
            ResultSet rs = null;

            rs = sqlConnection.executeSql("SELECT TOP 1 Title FROM Program");
            test += rs.getString("Title");


            System.out.println(test);
        } catch (Exception e) {
            System.out.println(e);
        }



        return test;
    }

    private String getRings() {

        return "Ringon";
    }
}
