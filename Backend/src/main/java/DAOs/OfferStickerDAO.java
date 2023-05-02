package DAOs;

import Convertors.OfferStickerConvertor;
import DAOExceptions.OfferNotFoundException;
import DAOExceptions.StickerNotFoundException;
import DTOs.OfferStickerDTO;
import Database.Database;
import Queries.OfferStickerQueries;
import java.sql.SQLException;
import java.util.List;

public class OfferStickerDAO {

    public static boolean insertOfferSticker(OfferStickerDTO offerSticker) throws SQLException, StickerNotFoundException, OfferNotFoundException {
        if(OfferDAO.getOfferByOfferTitle(offerSticker.getOfferTitle()) == null){
            throw new OfferNotFoundException();
        }
        if(StickerDAO.getStickerByCountryAndNumber(offerSticker.getCountry(), offerSticker.getNumber()) == null){
            throw new StickerNotFoundException();
        }
        
        String query = OfferStickerQueries.insertOfferStickerQuery(offerSticker);
        return Database.execute(query);
    }

    public static boolean insertOfferStickers(List<OfferStickerDTO> offerStickers) {
        String query = OfferStickerQueries.insertOfferStickersQuery(offerStickers);
        return Database.execute(query);
    }
    
    public static List<OfferStickerDTO> getOfferPublicationsByOfferTitle(String offerTitle) throws SQLException {
        String query = OfferStickerQueries.getOfferStickersByOfferTitleQuery(offerTitle);
        return OfferStickerConvertor.toOfferStickerDTOs(Database.executeQuery(query));
    }

    public static List<OfferStickerDTO> getOfferStickersByCountryAndNumber(String country, String number) throws SQLException {
        String query = OfferStickerQueries.getOfferStickersByCountryAndNumberQuery(country, number);
        return OfferStickerConvertor.toOfferStickerDTOs(Database.executeQuery(query));
    }
}