package Database;

import Main.AppConfig;
import Utils.LoadConfig;
import java.sql.ResultSet;
import java.util.Map;

public class Database {

    private static IDatabaseConnection db;

    public static boolean execute(String sqlScript) {
        if (db == null) {
            initDatabase();
        }
        return db.execute(sqlScript);
    }

    public static ResultSet executeQuery(String sqlScript) {
        if (db == null) {
            initDatabase();
        }
        return db.executeQuery(sqlScript);
    }

    public static void initDatabase() {
        Map<String, String> properties = LoadConfig.getConfig(".env");
        System.out.println("PROPS: \n" + properties.toString());
        db = new DatabaseConnection(
                properties.get("databaseHost"),
                properties.get("databaseName"),
                properties.get("databaseUser"),
                properties.get("databasePassword")
        );
        db.execute(getTypeInitialization());
        db.execute(getTableInitialization());
    }

    private static String[] getTypeInitialization() {
        return Type.getInit();
    }

    private static String[] getTableInitialization() {
        return Table.getInit();
    }

}
