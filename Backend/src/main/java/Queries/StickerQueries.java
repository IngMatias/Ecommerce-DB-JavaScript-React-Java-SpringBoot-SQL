package Queries;

import DTOs.StickerDTO;

public class StickerQueries {

    public static String insertStickerQuery(StickerDTO sticker) {
        return ""
                + "INSERT INTO stickers "
                + "VALUES ("
                + "'" + sticker.getPlayer() + "',"
                + "'" + sticker.getCountry() + "',"
                + "'" + sticker.getNumber() + "',"
                + "'" + sticker.getPhoto() + "'"
                + ")";
    }

    public static String getStickersQuery() {
        return ""
                + "SELECT * "
                + "FROM stickers "
                + "GROUP BY country";
    }
    
    public static String getStickerByCountryAndNumberQuery(String country, String number) {
        return ""
                + "SELECT * "
                + "FROM stickers "
                + "WHERE "
                + "country = '" + country + "' AND "
                + "number = '" + number + "'";
    }
    
    public static String getCountriesQuery() {
        return ""
                + "SELECT DISTINCT country "
                + "FROM stickers";
    }
    
    public static String getNumberByCountry(String country) {
        return ""
                + "SELECT number "
                + "FROM stickers " 
                + "WHERE country = '"+ country +"'";
                 
    }
}
