import Database.SqlConnection;

public class Main {

    public static void main(String[] args) throws NullPointerException {
        SqlConnection connection = new SqlConnection();
        connection.connectDatabase("jdbc:sqlserver://localhost\\MSSQLSERVER;databaseName=NetflixStatistixMenS;integratedSecurity=true;");

        //NetflixStatistixMenS

        GUI.UserInterface ui = new GUI.UserInterface(connection);
        ui.start();
    }
}
