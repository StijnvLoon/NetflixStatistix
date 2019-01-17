package GUI.Listeners;

import Database.SqlConnection;
import GUI.UserInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Layout6Listener implements ActionListener {

    private UserInterface ui;
    private SqlConnection sqlConnection;

    public Layout6Listener(UserInterface ui, SqlConnection connection) {
        this.ui = ui;
        this.sqlConnection = connection;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox)e.getSource();
        String chosenFilm = (String)cb.getSelectedItem();

        ui.changeLayout(getInfo(chosenFilm));
    }

    public String getInfo(String chosenFilm) {

        int viewCount = 0;

        try {
            ResultSet rs = sqlConnection.executeSql("SELECT COUNT(Profile.Name) AS WatchedAmount\n" +
                                                        "FROM Profile JOIN Profile_Program ON Profile.ProfileName = Profile_Program.ProfileName\n" +
                                                        "JOIN Program ON Program.Id = Profile_Program.Id\n" +
                                                        "WHERE Program.Title = '"+ chosenFilm + "';");
            while (rs.next()) {
                viewCount = rs.getInt("WatchedAmount");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return "\n" + viewCount + " gebruikers hebben de film\n'" + chosenFilm + "' bekeken";


    }
}
