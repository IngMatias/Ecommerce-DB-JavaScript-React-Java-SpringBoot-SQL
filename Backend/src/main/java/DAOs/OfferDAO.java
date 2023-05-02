package DAOs;

import Convertors.OfferConvertor;
import DAOExceptions.PublicationNotFoundException;
import DAOExceptions.UserNotFoundException;
import DTOs.OfferDTO;
import DTOs.PublicationDTO;
import DTOs.UserDTO;
import Database.Database;
import DAOExceptions.CannotOfferYourPublicationException;
import DAOExceptions.OfferNotFoundException;
import DAOExceptions.OfferStateInvalidException;
import DAOExceptions.OfferTitleInUseException;
import DAOExceptions.PublicationNotActiveException;
import Queries.OfferQueries;
import Utils.Date;
import java.sql.SQLException;
import java.util.List;

public class OfferDAO {

    public static boolean insertOffer(OfferDTO offer) throws SQLException, UserNotFoundException, PublicationNotFoundException, PublicationNotActiveException, CannotOfferYourPublicationException, OfferTitleInUseException  {
        UserDTO userDTO = UserDAO.getUserByEmail(offer.getEmail());
        if(userDTO == null){
            throw new UserNotFoundException();
        }
        
        PublicationDTO publicationDTO = PublicationDAO.getPublicationByTitle(offer.getRelatedTitle());
        if(publicationDTO == null){
            throw new PublicationNotFoundException();
        }
        if(7 < Date.getDaysFrom(publicationDTO.getDate())){
            throw new PublicationNotActiveException();
        }
//        if (publicationDTO.getEmail().equals(offer.getEmail())) {
//            throw new CannotOfferYourPublicationException();
//        }
        
        OfferDTO alreadyOffer = OfferDAO.getOfferByOfferTitle(offer.getTitle());
        if (alreadyOffer != null) {
            throw new OfferTitleInUseException();
        }

        String query = OfferQueries.insertOfferQuery(offer);
        return Database.execute(query);
    }

    public static OfferDTO getOfferByOfferTitle(String offerTitle) throws SQLException {
        String query = OfferQueries.getOffersByTitleQuery(offerTitle);
        return OfferConvertor.toOfferDTO(Database.executeQuery(query));
    }

    public static List<OfferDTO> getOffersByEmail(String email) throws SQLException {
        String query = OfferQueries.getOffersByEmailQuery(email);
        return OfferConvertor.toOfferDTOs(Database.executeQuery(query));
    }

    public static List<OfferDTO> getOffersByRelatedTitle(String title) throws SQLException {
        String query = OfferQueries.getOffersByRelatedTitleQuery(title);
        return OfferConvertor.toOfferDTOs(Database.executeQuery(query));
    }
    
    public static boolean updateOfferState(OfferDTO offer, String state) throws OfferNotFoundException, SQLException, PublicationNotActiveException, PublicationNotFoundException, OfferStateInvalidException {
        OfferDTO queriedOffer = OfferDAO.getOfferByOfferTitle(offer.getTitle());
        if (queriedOffer == null) {
            throw new OfferNotFoundException();
        }
        
        PublicationDTO publication = PublicationDAO.getPublicationByTitle(queriedOffer.getRelatedTitle());
        if(Date.getDaysFrom(publication.getDate())>7){
            throw new PublicationNotActiveException();
        }
        
        if (!((queriedOffer.getState().equals("InOffer") && (state.equals("Accepted") || state.equals("Declined") || state.equals("CounterOffer"))) ||
                (queriedOffer.getState().equals("CounterOffer") && (state.equals("Accepted") || state.equals("Declined"))))){
            throw new OfferStateInvalidException();
        }
        
        String query = OfferQueries.getUpdateOfferStateQuery(offer, state);
        return Database.execute(query);
    }

    public static List<String> getStates() throws SQLException {
        String query = OfferQueries.getStatesQuery();
        return OfferConvertor.toStates(Database.executeQuery(query));
    }
}
