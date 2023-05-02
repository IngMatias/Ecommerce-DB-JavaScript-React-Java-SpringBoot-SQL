package DAOs;

import Convertors.PublicationLooksForConvertor;
import DAOExceptions.PublicationNotFoundException;
import DAOExceptions.StickerNotFoundException;
import DTOs.PublicationLooksForDTO;
import DTOs.StickerDTO;
import Database.Database;
import Queries.PublicationLooksForQueries;
import java.sql.SQLException;
import java.util.List;

public class PublicationLooksForDAO {

    public static boolean insertPublicationLooksFor(PublicationLooksForDTO publicationLooksFor) throws PublicationNotFoundException, SQLException, StickerNotFoundException {
        if(PublicationDAO.getPublicationByTitle(publicationLooksFor.getTitle()) == null){
            throw new PublicationNotFoundException();
        }
        
        StickerDTO stickerDTO = StickerDAO.getStickerByCountryAndNumber(publicationLooksFor.getCountry(), publicationLooksFor.getNumber());
        if(stickerDTO == null){
            throw new StickerNotFoundException();
        }
        
        String query = PublicationLooksForQueries.insertPublicationLooksForQuery(publicationLooksFor);
        return Database.execute(query);
    }

    public static List<PublicationLooksForDTO> getPublicationsLooksForByTitle(String title) throws SQLException {
        String query = PublicationLooksForQueries.getPublicationsLooksForByTitleQuery(title);
        return PublicationLooksForConvertor.toPublicationLooksForDTO(Database.executeQuery(query));
    }

    public static List<PublicationLooksForDTO> getPublicationsLooksForByCountryAndNumber(String country, String number) throws SQLException {
        String query = PublicationLooksForQueries.getPublicationsLooksForByCountryAndNumberQuery(country, number);
        return PublicationLooksForConvertor.toPublicationLooksForDTO(Database.executeQuery(query));
    }
}
