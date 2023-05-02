package Convertors;

import DTOs.StickerPublicationDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class StickerPublicationConvertor {

    public static StickerPublicationDTO toStickerPublicationDTO(Map<String, Object> stickerMap) {
        StickerPublicationDTO stickerPublication = new StickerPublicationDTO();
        if(stickerMap != null) {
            stickerPublication.setTitle((String) stickerMap.get("title"));
            stickerPublication.setCountry((String) stickerMap.get("country"));
            stickerPublication.setNumber((String) stickerMap.get("number"));
        }
        return stickerPublication;
    }
    
    public static List<StickerPublicationDTO> toStickerPublicationDTOs(ResultSet userRS) throws SQLException {
        List<StickerPublicationDTO> stickerPublications = new LinkedList<>();
        if(userRS != null) {
            while (userRS.next()) {
                StickerPublicationDTO stickerPublication = new StickerPublicationDTO();
                stickerPublication.setTitle(userRS.getString("title"));
                stickerPublication.setCountry(userRS.getString("country"));
                stickerPublication.setNumber(userRS.getString("number"));
                stickerPublications.add(stickerPublication);
            }
        }
        return stickerPublications;
    }

    
}
