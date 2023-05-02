package Main.Controllers;

import Convertors.PublicationConvertor;
import Convertors.StickerPublicationConvertor;
import Convertors.UserConvertor;
import DAOs.OfferDAO;
import DAOs.PublicationDAO;
import DAOs.StickerPublicationDAO;
import DAOs.UserDAO;
import DTOs.OfferDTO;
import DTOs.PublicationDTO;
import DTOs.StickerPublicationDTO;
import DTOs.UserDTO;
import Exceptions.AuthenticationException;
import DAOExceptions.PublicationLimitExceededException;
import DAOExceptions.PublicationConditionInvalidException;
import DAOExceptions.PublicationTitleInUseException;
import DAOExceptions.StickerNotFoundException;
import DAOExceptions.UserNotFoundException;
import Utils.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicationController {

    @PostMapping("/publication")
    public PublicationDTO addPublication(@RequestBody Map<String, Object> bodyMap) throws AuthenticationException, SQLException, PublicationLimitExceededException, StickerNotFoundException, PublicationTitleInUseException, UserNotFoundException, PublicationConditionInvalidException {
        Map<String, Object> userMap = (Map<String, Object>) bodyMap.get("user");
        UserDTO user = UserConvertor.toUserDTO(userMap);
        if (!UserDAO.authenticateUser(user)) {
            throw new AuthenticationException();
        }
        
        Map<String, Object> publicationMap = (Map<String, Object>) bodyMap.get("publication");
        System.out.println(userMap.get("email"));
        publicationMap.put("email", userMap.get("email"));
        System.out.println(publicationMap.get("email"));
        publicationMap.put("date", Date.getDate());
        PublicationDTO publication = PublicationConvertor.toPublicationDTO(publicationMap);
        PublicationDAO.insertPublication(publication);
        
        Map<String, Object> stickerMap = (Map<String, Object>) bodyMap.get("sticker");
        stickerMap.put("title", publicationMap.get("title"));
        StickerPublicationDTO stickerPublication = StickerPublicationConvertor.toStickerPublicationDTO(stickerMap);
        StickerPublicationDAO.insertStickerPublication(stickerPublication);
        
        return PublicationDAO.getPublicationByTitle(publication.getTitle());
    }

//    @PostMapping("/publications")
//    public List<PublicationDTO> getPublications(@RequestBody List<String> toNotIncludeTitles) throws SQLException {
//        return PublicationDAO.getPublications(toNotIncludeTitles);
//    }
    
    @PostMapping("/publications")
    public List<PublicationDTO> getPublicationsByPage(@RequestBody Map<String, Object> pageMap) throws SQLException {
        int page = (int)pageMap.get("page");
        return PublicationDAO.getPublicationsByPage(page);
    }

    @PostMapping("/user/publications")
    public List<PublicationDTO> getPublicationsByUser(@RequestBody Map<String, Object> userMap) throws SQLException, AuthenticationException {
        UserDTO user = UserConvertor.toUserDTO(userMap);
        if (!UserDAO.authenticateUser(user)) {
            throw new AuthenticationException();
        }
        return PublicationDAO.getPublicationsByEmail(user.getEmail());
    }
    
    @GetMapping("/publication/{title}")
    public Map<String, Object> getPublication(@PathVariable String title) throws SQLException {
        Map<String, Object> result = new HashMap<>();

        PublicationDTO publication = PublicationDAO.getPublicationByTitle(title);
        result.put("publication", publication);
        System.out.println("H "+title);
        List<OfferDTO> offers = OfferDAO.getOffersByRelatedTitle(title);
        result.put("offers", offers);

        return result;
    }

    @GetMapping("/publications/conditions")
    public List<String> getConditions() throws SQLException {
        return PublicationDAO.getConditions();
    }
}
