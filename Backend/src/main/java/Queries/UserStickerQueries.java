package Queries;

import DTOs.UserStickerDTO;

public class UserStickerQueries {

    public static String insertUserStickerQuery(UserStickerDTO userSricker) {
        return ""
                + "INSERT INTO users_stickers "
                + "VALUES ("
                + "'" + userSricker.getQuantity() + "',"
                + "'" + userSricker.getEmail() + "',"
                + "'" + userSricker.getCountry()+ "',"
                + "'" + userSricker.getNumber()+ "'"
                + ")";
    }

    public static String getUserStickersByEmailQuery(String email) {
        return ""
                + "SELECT * "
                + "FROM users_stickers "
                + "WHERE email = '" + email + "'";
    }

//    public static String getUserStickersByCountryAndNumberQuery(String country, String number) {
//        return ""
//                + "SELECT * "
//                + "FROM users_stickers "
//                + "WHERE "
//                + "country = '" + country + "' AND "
//                + "number = '" + number + "'";
//    }

    public static String getUserStickerByEmailCountryAndNumberQuery(String email, String country, String number) {
        return ""
                + "SELECT * "
                + "FROM users_stickers "
                + "WHERE "
                + "email = '" + email + "' AND "
                + "country = '" + country + "' AND "
                + "number = '" + number + "'";
    }
}
