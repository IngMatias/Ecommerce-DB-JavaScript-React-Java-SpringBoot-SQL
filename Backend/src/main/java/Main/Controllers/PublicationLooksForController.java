
package Main.Controllers;

import Convertors.UserConvertor;
import DAOs.PublicationDAO;
import DAOs.PublicationLooksForDAO;
import DAOs.StickerDAO;
import DAOs.UserDAO;
import DTOs.PublicationDTO;
import DTOs.PublicationLooksForDTO;
import DTOs.StickerDTO;
import DTOs.UserDTO;
import Exceptions.AuthenticationException;
import DAOExceptions.PublicationNotFoundException;
import DAOExceptions.StickerNotFoundException;
import java.sql.SQLException;
import java.util.Map;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class PublicationLooksForController {
    @PostMapping("/looksFor")
    public boolean addLooksFor(@RequestBody Map<String, Object> body) throws SQLException, AuthenticationException, PublicationNotFoundException, StickerNotFoundException {
        Map<String, Object> userMap = (Map<String, Object>)body.get("user");
        UserDTO user = UserConvertor.toUserDTO(userMap);
        if (!UserDAO.authenticateUser(user)) {
            throw new AuthenticationException();
        }
        Map<String, Object> publicationMap = (Map<String, Object>)body.get("publication");
        PublicationDTO publication = PublicationDAO.getPublicationByTitle((String)publicationMap.get("title"));
        if (!publication.getEmail().equals(user.getEmail())) {
            throw new PublicationNotFoundException();
        }
        Map<String, Object> stickerMap = (Map<String, Object>)body.get("sticker");
        String stickerCountry = (String)stickerMap.get("country");
        String stickerNumber = (String)stickerMap.get("number");
        StickerDTO sticker = StickerDAO.getStickerByCountryAndNumber(stickerCountry, stickerNumber);
        if(sticker == null){
            throw new StickerNotFoundException();
        }
        PublicationLooksForDTO publicationLooksFor = new PublicationLooksForDTO();
        publicationLooksFor.setTitle(publication.getTitle());
        publicationLooksFor.setCountry(sticker.getCountry());
        publicationLooksFor.setNumber(sticker.getNumber());
        return PublicationLooksForDAO.insertPublicationLooksFor(publicationLooksFor);
    }
}
