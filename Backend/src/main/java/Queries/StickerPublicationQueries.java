package Queries;

import DTOs.StickerPublicationDTO;

public class StickerPublicationQueries {

    public static String insertStickerPublicationQuery(StickerPublicationDTO stickerPublication) {
        return ""
                + "INSERT INTO sticker_publications "
                + "VALUES ("
                + "'" + stickerPublication.getTitle() + "',"
                + "'" + stickerPublication.getCountry()+ "',"
                + "'" + stickerPublication.getNumber()+ "'"
                + ")";
    }

    public static String getStickerPublicationByTitleQuery(String title) {
        return ""
                + "SELECT * "
                + "FROM sticker_publications "
                + "WHERE title = '" + title + "'";
    }
    
    public static String getStickerPublicationByCountryAndNumber(String country, String number) {
        return ""
                + "SELECT * "
                + "FROM sticker_publications "
                + "WHERE "
                + "country = '" + country + "' AND "
                + "number = '" + number + "'";
    }
}
