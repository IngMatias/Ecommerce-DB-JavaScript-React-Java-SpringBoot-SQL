
package Main.Controllers;

import Convertors.OfferConvertor;
import Convertors.StickerConvertor;
import Convertors.UserConvertor;
import DAOs.OfferDAO;
import DAOs.OfferStickerDAO;
import DAOs.UserDAO;
import DTOs.OfferDTO;
import DTOs.OfferStickerDTO;
import DTOs.StickerDTO;
import DTOs.UserDTO;
import Exceptions.AuthenticationException;
import DAOExceptions.CannotOfferYourPublicationException;
import DAOExceptions.OfferNotFoundException;
import DAOExceptions.OfferStateInvalidException;
import DAOExceptions.OfferTitleInUseException;
import DAOExceptions.PublicationNotActiveException;
import DAOExceptions.PublicationNotFoundException;
import DAOExceptions.UserNotFoundException;
import Utils.Date;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OfferController {
    @PostMapping("/offer")
    public boolean addOffer(@RequestBody Map<String, Object> body) throws SQLException, AuthenticationException, CannotOfferYourPublicationException, PublicationNotFoundException, PublicationNotActiveException, UserNotFoundException, OfferTitleInUseException {
        Map<String, Object> userMap = (Map<String, Object>)body.get("user");
        UserDTO user = UserConvertor.toUserDTO(userMap);
        if (!UserDAO.authenticateUser(user)) {
            throw new AuthenticationException();
        }
        
        Map<String, Object> publicationMap = (Map<String, Object>)body.get("publication");
        Map<String, Object> offerMap = (Map<String, Object>)body.get("offer");
        offerMap.put("state", "InOffer");
        offerMap.put("date", Date.getDate());
        offerMap.put("email", userMap.get("email"));
        offerMap.put("relatedTitle", publicationMap.get("title"));
        OfferDTO offer = OfferConvertor.toOfferDTO(offerMap);
        
        List<Object> stickers = (List<Object>)offerMap.get("stickers");
        List<StickerDTO> stickersList = StickerConvertor.toStickerList(stickers);
        List<OfferStickerDTO> offerStickers = new LinkedList<>();
        for (StickerDTO sticker: stickersList) {
            OfferStickerDTO offerSticker = new OfferStickerDTO();
            offerSticker.setOfferTitle(offer.getTitle());
            offerSticker.setNumber(sticker.getNumber());
            offerSticker.setCountry(sticker.getCountry());
            offerStickers.add(offerSticker);   
        }

        return OfferDAO.insertOffer(offer) && OfferStickerDAO.insertOfferStickers(offerStickers);
    }
    
    
    @PostMapping("/offer/update")
    public boolean updateOffer(@RequestBody Map<String, Object> body) throws SQLException, AuthenticationException, OfferNotFoundException, PublicationNotFoundException, PublicationNotActiveException, OfferStateInvalidException {
        Map<String, Object> userMap = (Map<String, Object>)body.get("user");
        UserDTO user = UserConvertor.toUserDTO(userMap);
        if (!UserDAO.authenticateUser(user)) {
            throw new AuthenticationException();
        }
        
        Map<String, Object> offerMap = (Map<String, Object>)body.get("offer");
        OfferDTO offer = OfferConvertor.toOfferDTO(offerMap);
        
	String offerNewState = (String)body.get("offerNewState");
        return OfferDAO.updateOfferState(offer, offerNewState);
    }
    
    @GetMapping("/offers/states")
    public List<String> getStates() throws SQLException, AuthenticationException, OfferNotFoundException, PublicationNotFoundException, PublicationNotActiveException, OfferStateInvalidException {
        return OfferDAO.getStates();
    }
}
