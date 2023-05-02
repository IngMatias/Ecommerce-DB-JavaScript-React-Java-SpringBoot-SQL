package DAOs;

import DTOs.UserStickerDTO;
import Database.Database;
import Queries.UserStickerQueries;
import java.util.List;
import Convertors.UserStickerConvertor;
import DAOExceptions.StickerNotFoundException;
import DAOExceptions.UserNotFoundException;
import java.sql.SQLException;

public class UserStickerDAO {

    public static boolean insertUserSticker(UserStickerDTO userSticker) throws UserNotFoundException, SQLException, StickerNotFoundException {
        String query = UserStickerQueries.insertUserStickerQuery(userSticker);
        if(UserDAO.getUserByEmail(userSticker.getEmail())== null){
            throw new UserNotFoundException();
        }
        if(StickerDAO.getStickerByCountryAndNumber(userSticker.getCountry(), userSticker.getNumber()) == null){
            throw new StickerNotFoundException();
        }
        return Database.execute(query);
    }

    static Object getUserStickerByEmailCountryAndNumber(String email, String country, String number) throws SQLException {
        String query = UserStickerQueries.getUserStickerByEmailCountryAndNumberQuery(email, country, number);
        return UserStickerConvertor.toUserDTO(Database.executeQuery(query));
    }

    public List<UserStickerDTO> getUserStickersByEmail(String email) throws SQLException {
        String query = UserStickerQueries.getUserStickersByEmailQuery(email);
        return UserStickerConvertor.toUserDTO(Database.executeQuery(query));
    }

//    public List<UserStickerDTO> getUserStickersByPlayer(String country, String number) throws SQLException {
//        String query = UserStickerQueries.getUserStickersByCountryAndNumberQuery(country, number);
//        return UserStickerConvertor.toUserDTO(Database.executeQuery(query));
//    }
}
