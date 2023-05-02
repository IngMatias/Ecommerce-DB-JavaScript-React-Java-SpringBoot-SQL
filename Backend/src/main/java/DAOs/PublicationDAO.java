package DAOs;

import Convertors.PublicationConvertor;
import DAOExceptions.PublicationConditionInvalidException;
import DAOExceptions.PublicationTitleInUseException;
import DAOExceptions.UserNotFoundException;
import DTOs.PublicationDTO;
import DTOs.UserDTO;
import Database.Database;
import DAOExceptions.PublicationLimitExceededException;
import Queries.PublicationQueries;
import java.sql.SQLException;
import java.util.List;

public class PublicationDAO {

    public static boolean insertPublication(PublicationDTO publication) throws SQLException, UserNotFoundException, PublicationTitleInUseException, PublicationLimitExceededException, PublicationConditionInvalidException {
        UserDTO userDTO = UserDAO.getUserByEmail(publication.getEmail());
        if(userDTO == null){
            throw new UserNotFoundException();
        }
        
        if (PublicationDAO.getPublicationsByEmail(publication.getEmail()).size() > 3) {
            throw new PublicationLimitExceededException();
        }
        
        PublicationDTO alreadyPublication = PublicationDAO.getPublicationByTitle(publication.getTitle());
        if(alreadyPublication != null){
            throw new PublicationTitleInUseException();
        }
        
        if(!(publication.getCondition().equals("Excelent") || publication.getCondition().equals("Very_Good") || 
                publication.getCondition().equals("Good") || publication.getCondition().equals("Acceptable"))) {
            throw new PublicationConditionInvalidException();
        }
        String query = PublicationQueries.insertPublicationQuery(publication);
        return Database.execute(query);
    }

    public static List<PublicationDTO> getPublications(List<String> toNotIncludeTitles) throws SQLException {
        String query = PublicationQueries.getPublicationsQuery(toNotIncludeTitles);
        return PublicationConvertor.toPublicationDTOs(Database.executeQuery(query));
    }
    
    public static List<PublicationDTO> getPublicationsByPage(int page) throws SQLException {
        String query = PublicationQueries.getPublicationsByPageQuery(page);
        return PublicationConvertor.toPublicationDTOs(Database.executeQuery(query));
    }

    public static List<PublicationDTO> getPublicationsByEmail(String email) throws SQLException {
        String query = PublicationQueries.getPublicationsByEmailQuery(email);
        return PublicationConvertor.toPublicationDTOs(Database.executeQuery(query));
    }
    
    public static PublicationDTO getPublicationByTitle(String title) throws SQLException {
        String query = PublicationQueries.getPublicationsByTitleQuery(title);
        return PublicationConvertor.toPublicationDTO(Database.executeQuery(query));
    }

    public static List<String> getConditions() throws SQLException {
        String query = PublicationQueries.getConditionsQuery();
        return PublicationConvertor.toConditions(Database.executeQuery(query));
    }
}
