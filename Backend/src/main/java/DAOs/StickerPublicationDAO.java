package DAOs;

import Convertors.StickerPublicationConvertor;
import DAOExceptions.StickerNotFoundException;
import DAOExceptions.StickerNotOwnedByUserException;
import DAOExceptions.UserNotFoundException;
import DTOs.StickerPublicationDTO;
import Database.Database;
import Queries.StickerPublicationQueries;
import java.sql.SQLException;
import java.util.List;

public class StickerPublicationDAO {

    public static boolean insertStickerPublication(StickerPublicationDTO stickerPublication) throws SQLException, UserNotFoundException, StickerNotFoundException {
        if(PublicationDAO.getPublicationByTitle(stickerPublication.getTitle()) == null){
            throw new UserNotFoundException();
        }
        if(StickerDAO.getStickerByCountryAndNumber(stickerPublication.getCountry(), stickerPublication.getNumber()) == null){
            throw new StickerNotFoundException();
        }
        
        String query = StickerPublicationQueries.insertStickerPublicationQuery(stickerPublication);
        return Database.execute(query);
    }

    public static List<StickerPublicationDTO> getStickerPublicationByTitle(String title) throws SQLException {
        String query = StickerPublicationQueries.getStickerPublicationByTitleQuery(title);
        return StickerPublicationConvertor.toStickerPublicationDTOs(Database.executeQuery(query));
    }

    public static List<StickerPublicationDTO> getStickerPublicationByCountryAndNumber(String country, String number) throws SQLException {
        String query = StickerPublicationQueries.getStickerPublicationByCountryAndNumber(country, number);
        return StickerPublicationConvertor.toStickerPublicationDTOs(Database.executeQuery(query));
    }
}
