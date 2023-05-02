package Convertors;

import DTOs.PublicationLooksForDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class PublicationLooksForConvertor {

    public static List<PublicationLooksForDTO> toPublicationLooksForDTO(ResultSet publicationLooksForRS) throws SQLException {
        List<PublicationLooksForDTO> publicationLooksFors = new LinkedList<>();
        if (publicationLooksForRS != null) {
            while (publicationLooksForRS.next()) {
                PublicationLooksForDTO publicationLooksFor = new PublicationLooksForDTO();
                publicationLooksFor.setTitle(publicationLooksForRS.getString("title"));
                publicationLooksFor.setCountry(publicationLooksForRS.getString("country"));
                publicationLooksFor.setNumber(publicationLooksForRS.getString("number"));
                publicationLooksFors.add(publicationLooksFor);
            }
        }
        return publicationLooksFors;
    }
}
