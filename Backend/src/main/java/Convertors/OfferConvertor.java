package Convertors;

import DTOs.OfferDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class OfferConvertor {

    public static OfferDTO toOfferDTO(ResultSet offersRS) throws SQLException {
        OfferDTO offer = null;
        if (offersRS != null) {
            while (offersRS.next()) {
                offer = new OfferDTO();
                offer.setTitle(offersRS.getString("title"));
                offer.setState(offersRS.getString("state"));
                offer.setDescription(offersRS.getString("description"));
                offer.setDate(offersRS.getString("date"));
                offer.setEmail(offersRS.getString("email"));
                offer.setRelatedTitle(offersRS.getString("related_title"));
            }
        }
        return offer;
    }

    public static OfferDTO toOfferDTO(Map<String, Object> offerMap) {
        OfferDTO offer = new OfferDTO();
        offer.setTitle((String) offerMap.get("title"));
        offer.setState((String) offerMap.get("state"));
        offer.setDescription((String) offerMap.get("description"));
        offer.setDate((String) offerMap.get("date"));
        offer.setEmail((String) offerMap.get("email"));
        offer.setRelatedTitle((String) offerMap.get("relatedTitle"));
        return offer;
    }

    public static List<OfferDTO> toOfferDTOs(ResultSet offersRS) throws SQLException {
        List<OfferDTO> offers = new LinkedList<>();
        System.out.println("Hola Mundo1");
        if (offersRS != null) {
            while (offersRS.next()) {
                OfferDTO offer = new OfferDTO();
                offer.setTitle(offersRS.getString("title"));
                offer.setState(offersRS.getString("state"));
                offer.setDescription(offersRS.getString("description"));
                offer.setDate(offersRS.getString("date"));
                offer.setEmail(offersRS.getString("email"));
                offer.setRelatedTitle(offersRS.getString("related_title"));
                offers.add(offer);
            }
        }
        return offers;
    }

    public static List<String> toStates(ResultSet statesRS) throws SQLException {
        List<String> states = new LinkedList<>();
        if (statesRS != null) {
            while (statesRS.next()) {
                states.add(statesRS.getString("unnest"));
            }
        }
        return states;
    }

}
