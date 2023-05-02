package Queries;

import DTOs.OfferDTO;

public class OfferQueries {

    public static String insertOfferQuery(OfferDTO offer) {
        return ""
                + "INSERT INTO offers "
                + "VALUES ("
                + "'" + offer.getTitle()+ "',"
                + "'" + offer.getState() + "',"
                + "'" + offer.getDescription()+ "',"
                + "'" + offer.getDate()+ "',"
                + "'" + offer.getEmail() + "',"
                + "'" + offer.getRelatedTitle() + "'"
                + ")";
    }

    public static String getOffersByTitleQuery(String title) {
        return ""
                + "SELECT * "
                + "FROM offers "
                + "WHERE title = '" + title + "'";
    }

    public static String getOffersByEmailQuery(String email) {
        return ""
                + "SELECT * "
                + "FROM offers "
                + "WHERE email = '" + email + "'";
    }

    public static String getOffersByRelatedTitleQuery(String relatedTitle) {
        return ""
                + "SELECT * "
                + "FROM offers "
                + "WHERE related_title = '" + relatedTitle + "'";
    }
    
    public static String getUpdateOfferStateQuery(OfferDTO offer, String state) {
        return ""
                + "UPDATE offers "
                + "SET "
                + "state = '" + state + "' "
                + "WHERE title = '" + offer.getTitle()+ "'";
    }

    public static String getOfferByEmailAndRelatedTitleQuery(String email, String relatedTitle) {
        return ""
                + "SELECT * "
                + "FROM offers "
                + "WHERE related_title = '" + relatedTitle + "'"
                + "AND email = '" + email + "'";
    }

    public static String getStatesQuery() {
        return "SELECT unnest(enum_range(NULL::OFFERS_TYPE))";
    }
}
