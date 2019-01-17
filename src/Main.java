import Database.SqlConnection;

public class Main {

    //de Main zorgt ervoor dat de connectie wordt opgemaakt en voor alle classes beschikbaar wordt gesteld. Ook wordt de UserInterface vanaf hier gestart

    public static void main(String[] args){

        //Maak een connectie met de database
        SqlConnection connection = new SqlConnection();
        connection.connectDatabase("jdbc:sqlserver://localhost\\MSSQLSERVER;databaseName=NetflixStatistixMenS;integratedSecurity=true;");

        //Zet de UserInterface op
        GUI.UserInterface ui = new GUI.UserInterface(connection);
        ui.start();
    }
}
