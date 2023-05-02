package Convertors;

import DTOs.UserStickerDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserStickerConvertor {

    public static List<UserStickerDTO> toUserDTO(ResultSet userRS) throws SQLException {
        List<UserStickerDTO> userStickers = new LinkedList<>();
        if (userRS != null) {
            while (userRS.next()) {
                UserStickerDTO user = new UserStickerDTO();
                user.setQuantity(Integer.parseInt(userRS.getString("quantity")));
                user.setEmail(userRS.getString("email"));
                user.setCountry(userRS.getString("country"));
                user.setNumber(userRS.getString("number"));
                userStickers.add(user);
            }
        }

        return userStickers;
    }
}
