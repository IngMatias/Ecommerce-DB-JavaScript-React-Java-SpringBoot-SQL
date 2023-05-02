package Database;

public class Type {

    public static String[] getInit() {
        String init = ""
                + "CREATE TYPE OFFERS_TYPE AS ENUM ("
                + "    'InOffer',"
                + "    'CounterOffer',"
                + "    'Accepted',"
                + "    'Declined'"
                + ");"
                + "CREATE TYPE STICKERS_PUBLICATIONS_CONDITIONS AS ENUM ("
                + "    'Excelent',"
                + "    'VeryGood',"
                + "    'Good',"
                + "    'Acceptable'"
                + ");";
        return init.split(";");
    }
}
