package Convertors;

import DTOs.PublicationDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PublicationConvertor {

    public static PublicationDTO toPublicationDTO(ResultSet publicationsRS) throws SQLException {
        PublicationDTO publication = null;
        if (publicationsRS != null) {
            while (publicationsRS.next()) {
                publication = new PublicationDTO();
                publication.setTitle(publicationsRS.getString("title"));
                publication.setDescription(publicationsRS.getString("description"));
                publication.setDate(publicationsRS.getString("date"));
                publication.setPhoto(publicationsRS.getString("photo"));
                publication.setCondition(publicationsRS.getString("condition"));
                publication.setEmail(publicationsRS.getString("email"));
            }
        }

        return publication;
    }

    public static PublicationDTO toPublicationDTO(Map<String, Object> publicationMap) {
        PublicationDTO publication = new PublicationDTO();
        publication.setTitle((String) publicationMap.get("title"));
        publication.setDescription((String) publicationMap.get("description"));
        publication.setDate((String) publicationMap.get("date"));
        publication.setPhoto((String) publicationMap.get("photo"));
        publication.setCondition((String) publicationMap.get("condition"));
        publication.setEmail((String) publicationMap.get("email"));
        return publication;
    }
    
    public static List<PublicationDTO> toPublicationDTOs(ResultSet publicationsRS) throws SQLException {
        List<PublicationDTO> publicationsDTO = new LinkedList<>();
        if (publicationsRS != null) {
            while (publicationsRS.next()) {
                PublicationDTO publication = new PublicationDTO();
                publication.setTitle(publicationsRS.getString("title"));
                publication.setDescription(publicationsRS.getString("description"));
                publication.setDate(publicationsRS.getString("date"));
                publication.setPhoto(publicationsRS.getString("photo"));
                publication.setCondition(publicationsRS.getString("condition"));
                publication.setEmail(publicationsRS.getString("email"));
                publicationsDTO.add(publication);
            }
        }
        return publicationsDTO;
    }

    public static List<String> toConditions(ResultSet conditionsRS) throws SQLException {
        List<String> conditions = new LinkedList<>();
        if (conditionsRS != null) {
            while (conditionsRS.next()) {
                conditions.add(conditionsRS.getString("unnest"));
            }
        }
        return conditions;
    }
}
