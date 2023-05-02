package Queries;

import DTOs.PublicationLooksForDTO;

public class PublicationLooksForQueries {

    public static String insertPublicationLooksForQuery(PublicationLooksForDTO publicationLooksFor) {
        return ""
                + "INSERT INTO publication_looksfor "
                + "VALUES ("
                + "'" + publicationLooksFor.getTitle() + "',"
                + "'" + publicationLooksFor.getCountry() + "',"
                + "'" + publicationLooksFor.getNumber() + "'"
                + ")";
    }

    public static String getPublicationsLooksForByTitleQuery(String title) {
        return ""
                + "SELECT * "
                + "FROM publication_looksfor "
                + "WHERE title = '" + title + "'";
    }

    public static String getPublicationsLooksForByCountryAndNumberQuery(String country, String number) {
        return ""
                + "SELECT * "
                + "FROM publication_looksfor "
                + "WHERE "
                + "country = '" + country + "'"
                + "number = '" + number + "'";
    }
}
