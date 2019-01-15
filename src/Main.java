import Database.SqlConnection;
import GUI.UserInterface;

public class Main {

    public static void main(String[] args) throws NullPointerException {
        SqlConnection connection = new SqlConnection();
        connection.connectDatabase("jdbc:sqlserver://localhost\\MSSQLSERVER;databaseName=NetflixStatistixMenS;integratedSecurity=true;");

        //NetflixStatistixMenS

        GUIV2.UserInterface ui = new GUIV2.UserInterface(connection);
        ui.start();
    }
}
