package DAOs;

import Convertors.StickerConvertor;
import DAOExceptions.StickerInUseException;
import DTOs.StickerDTO;
import Database.Database;
import Queries.StickerQueries;
import java.sql.SQLException;
import java.util.List;

public class StickerDAO {

    public static boolean insertSticker(StickerDTO sticker) throws SQLException, StickerInUseException {
        StickerDTO alreadySticker = StickerDAO.getStickerByCountryAndNumber(sticker.getCountry(), sticker.getNumber());
        if(alreadySticker != null) {
            throw new StickerInUseException();
        }
        String query = StickerQueries.insertStickerQuery(sticker);
        return Database.execute(query);
    }

    public static List<StickerDTO> getStickers() throws SQLException {
        String query = StickerQueries.getStickersQuery();
        return StickerConvertor.toStickerDTOs(Database.executeQuery(query));
    }

    public static StickerDTO getStickerByCountryAndNumber(String country, String number) throws SQLException {
        String query = StickerQueries.getStickerByCountryAndNumberQuery(country, number);
        return StickerConvertor.toStickerDTO(Database.executeQuery(query));
    }
    
    public static List<String> getCountries() throws SQLException {
        String query = StickerQueries.getCountriesQuery();
        return StickerConvertor.toCountries(Database.executeQuery(query));
    }
    
    public static List<String> getNumbersByCountry(String country) throws SQLException {
        String query = StickerQueries.getNumberByCountry(country);
        return StickerConvertor.toNumbers(Database.executeQuery(query));
    }
}
