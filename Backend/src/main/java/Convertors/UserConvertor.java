package Convertors;

import DTOs.UserDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UserConvertor {

    public static UserDTO toUserDTO(ResultSet userRS) throws SQLException {
        UserDTO user = null;
        if (userRS != null) {
            while (userRS.next()) {
                user = new UserDTO();
                user.setEmail(userRS.getString("email"));
                user.setPassword(userRS.getString("password"));
                user.setName(userRS.getString("name"));
                user.setPhone(userRS.getString("phone"));
                user.setUbication(userRS.getString("ubication"));
            }
        }
        return user;
    }

    public static UserDTO toUserDTO(Map<String, Object> userMap) {
        UserDTO user = new UserDTO();
        user.setEmail((String) userMap.get("email"));
        user.setPassword((String) userMap.get("password"));
        user.setName((String) userMap.get("name"));
        user.setPhone((String) userMap.get("phone"));
        user.setUbication((String) userMap.get("ubication"));
        return user;
    }

    public static Map<String, Object> toUserMap(UserDTO user) {
        Map<String, Object> userMap = new HashMap<>();
        if (user != null) {
            userMap.put("email", user.getEmail());
            userMap.put("password", user.getPassword());
            userMap.put("name", user.getName());
            userMap.put("phone", user.getPhone());
            userMap.put("ubication", user.getUbication());
        }
        return userMap;
    }

//    public static int toUserQuantity(ResultSet userRS) throws SQLException {
//        int i = 0;
//        while (userRS.next()) {
//            i++;
//        }
//        return i;
//    }
}
