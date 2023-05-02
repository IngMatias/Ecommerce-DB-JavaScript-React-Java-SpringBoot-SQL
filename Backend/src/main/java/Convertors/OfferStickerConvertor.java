package Convertors;

import DTOs.OfferStickerDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class OfferStickerConvertor {
    
//    public static OfferStickerDTO toOfferStickerDTO(Object offerStickerObject) {
//        OfferStickerDTO offerSticker = new OfferStickerDTO();
//        Map<String, String> offerStickerMap = (Map<String, String>)offerStickerObject;
//        offerSticker.setOfferTitle(offerStickerMap.get("title"));
//        offerSticker.setNumber(offerStickerMap.get("number"));
//        offerSticker.setCountry(offerStickerMap.get("country"));
//        return offerSticker;
//    }
    
    public static List<OfferStickerDTO> toOfferStickerDTOs(ResultSet offerStickerRS) throws SQLException {
        List<OfferStickerDTO> offerStickers = new LinkedList<>();
        if (offerStickerRS != null) {
            while (offerStickerRS.next()) {
                OfferStickerDTO offerSticker = new OfferStickerDTO();
                offerSticker.setOfferTitle(offerStickerRS.getString("offer_title"));
                offerSticker.setCountry(offerStickerRS.getString("country"));
                offerSticker.setNumber(offerStickerRS.getString("number"));
                offerStickers.add(offerSticker);
            }
        }
        return offerStickers;
    }
    
//    public static List<OfferStickerDTO> toOfferStickerDTOs(List<Object> offerStickerList) {
//        List<OfferStickerDTO> offerStickers = new LinkedList<>();
//        for (Object offerStickerObject: offerStickerList) {
//            offerStickers.add(toOfferStickerDTO(offerStickerObject));
//        }
//        return offerStickers;
//    }
}
