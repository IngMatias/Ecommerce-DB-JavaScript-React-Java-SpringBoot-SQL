package Queries;

import DTOs.PublicationDTO;
import java.util.List;

public class PublicationQueries {

    public static String insertPublicationQuery(PublicationDTO publication) {
        return ""
                + "INSERT INTO publications "
                + "VALUES ("
                + "'" + publication.getTitle() + "',"
                + "'" + publication.getDescription() + "',"
                + "'" + publication.getDate() + "',"
                + "'" + publication.getPhoto() + "',"
                + "'" + publication.getCondition() + "',"
                + "'" + publication.getEmail() + "'"
                + ")";
    }

    public static String getPublicationsQuery(List<String> toNotIncludeTitles) {
        String toNotIncludeTitlesString = null;
        if (!toNotIncludeTitles.isEmpty()) {
            toNotIncludeTitlesString = "(";
            for (String publication : toNotIncludeTitles) {
                toNotIncludeTitlesString += "'" + publication + "',";
            }
            toNotIncludeTitlesString = toNotIncludeTitlesString.substring(0, toNotIncludeTitlesString.length() - 1);
            toNotIncludeTitlesString += ")";
        }

        String query = ""
                + "SELECT * "
                + "FROM publications ";
                if (toNotIncludeTitlesString != null) {
                    query += "WHERE title NOT IN " + toNotIncludeTitlesString + " ";
                }
                query += "ORDER BY date DESC "
                + "LIMIT 40";
                
        return query;
    }

    public static String getPublicationsByPageQuery(int page) {
        int ceroBasedPage = page - 1;
        int publicationsByPage = 40;
        String query = ""
                + "SELECT * "
                + "FROM publications "
                + "OFFSET " + ceroBasedPage * publicationsByPage + " "
                + "LIMIT " + publicationsByPage;
        return query;
    }
    
    public static String getPublicationsByEmailQuery(String email) {
        return ""
                + "SELECT * "
                + "FROM publications "
                + "WHERE email = '" + email + "' "
                + "ORDER BY date DESC";
    }

    public static String getPublicationsByTitleQuery(String title) {
        return ""
                + "SELECT * "
                + "FROM publications "
                + "WHERE title ='" + title + "'";
    }
    
    public static String getConditionsQuery() {
        return "SELECT unnest(enum_range(NULL::stickers_publications_conditions))";
    }
    
}
