
package Main.Controllers;

import Convertors.UserConvertor;
import DAOs.UserDAO;
import DAOs.StickerDAO;
import DAOs.UserStickerDAO;
import DTOs.UserDTO;
import DTOs.UserStickerDTO;
import Exceptions.AuthenticationException;
import DAOExceptions.StickerNotFoundException;
import DAOExceptions.UserNotFoundException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StickerController {
    @PostMapping("/user/sticker")
    public boolean addStickerToUser(@RequestBody Map<String, Object> body) throws SQLException, AuthenticationException, StickerNotFoundException, UserNotFoundException {
        Map<String, Object> userMap = (Map<String, Object>)body.get("user");
        UserDTO user = UserConvertor.toUserDTO(userMap);
        if (!UserDAO.authenticateUser(user)) {
            throw new AuthenticationException();
        }
        
        Map<String, Object> stickerMap = (Map<String, Object>)body.get("sticker");
        UserStickerDTO userSticker = new UserStickerDTO();
        userSticker.setEmail(user.getEmail());
        userSticker.setCountry((String) stickerMap.get("country"));
        userSticker.setNumber((String) stickerMap.get("number"));
        String quantity = (String) stickerMap.get("quantity");
        if(quantity != null){
            userSticker.setQuantity(Integer.parseInt(quantity) + 1);
        } else {
            userSticker.setQuantity(1);
        }
        
        return UserStickerDAO.insertUserSticker(userSticker);
    }
    
    @GetMapping("/sticker/countries")
    public List<String> getCountries() throws SQLException {
        return StickerDAO.getCountries();
    }
    
    @GetMapping("/stickers/numbers/{country}")
    public List<String> getNumbersByCountry(@PathVariable String country) throws SQLException {
        return StickerDAO.getNumbersByCountry(country);
    }
}
