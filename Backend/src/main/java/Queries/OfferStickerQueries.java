package Queries;

import DTOs.OfferStickerDTO;
import java.util.List;

public class OfferStickerQueries {

    public static String insertOfferStickerQuery(OfferStickerDTO offerPublication) {
        return ""
                + "INSERT INTO offers_stickers "
                + "VALUES ("
                + "'" + offerPublication.getOfferTitle() + "',"
                + "'" + offerPublication.getCountry() + "',"
                + "'" + offerPublication.getNumber()+ "'"
                + ")";
    }

    public static String insertOfferStickersQuery(List<OfferStickerDTO> offerStickers) {
        String query = ""
                + "INSERT INTO offers_stickers "
                + "VALUES (";
                
        for (OfferStickerDTO offerSticker: offerStickers) {
            query+= "'" + offerSticker.getOfferTitle() + "',";
            query+= "'" + offerSticker.getCountry()+ "',";
            query+= "'" + offerSticker.getNumber()+ "',";
        }
        query = query.substring(0, query.length()-1);
        query += ")";
        return query;
    }
    
    public static String getOfferStickersByOfferTitleQuery(String offerTitle) {
        return ""
                + "SELECT * "
                + "FROM offers_stickers "
                + "WHERE offer_title = '" + offerTitle + "'";
    }

    public static String getOfferStickersByCountryAndNumberQuery(String country, String number) {
        return ""
                + "SELECT * "
                + "FROM offers_stickers "
                + "WHERE country = '" + country + "' AND "
                + "number = '" + number + "'";
    }
}
