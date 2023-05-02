package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnection implements IDatabaseConnection {

    private Connection connection;

    public DatabaseConnection(
            String host,
            String databaseName,
            String user,
            String password) {
        this.connectDb(host, databaseName, user, password);
    }

    private void connectDb(String host, String databaseName, String user, String password) {
        String url = "jdbc:postgresql://" + host + "/" + databaseName;
        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);
        try {
            this.connection = DriverManager.getConnection(url, props);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean execute(String sqlScript) {
        System.out.println("Execute Query:" + sqlScript);
        try {
            Statement statement = this.connection.createStatement();
            statement.execute(sqlScript);
        } catch (SQLException ex) {
            System.out.println("Problem executing: " + sqlScript);
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    @Override
    public Boolean[] execute(String[] sqlScriptsList) {
        Boolean[] result = new Boolean[sqlScriptsList.length];
        for (int i = 0; i < sqlScriptsList.length; i++) {
            result[i] = this.execute(sqlScriptsList[i]);
        }
        return result;
    }

    @Override
    public ResultSet executeQuery(String sqlScript) {
        System.out.println("Execute Query:" + sqlScript);
        ResultSet result = null;
        try {
            Statement statement = this.connection.createStatement();
            result = statement.executeQuery(sqlScript);
        } catch (SQLException ex) {
            System.out.println("Problem Executing: " + sqlScript);
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
