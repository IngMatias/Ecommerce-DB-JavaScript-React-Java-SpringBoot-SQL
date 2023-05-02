package Queries;

import DTOs.UserDTO;
import Utils.Encriptor;

public class UserQueries {

    public static String insertUserQuery(UserDTO user) {
        return ""
                + "INSERT INTO users "
                + "VALUES ("
                + "'" + user.getEmail() + "',"
                + "'" + user.getPassword() + "',"
                + "'" + user.getName() + "',"
                + "'" + user.getPhone() + "',"
                + "'" + user.getUbication() + "'"
                + ")";
    }

    public static String updateUserByEmailQuery(UserDTO oldUser, UserDTO newUser) {
        String query = ""
                + "UPDATE users "
                + "SET ";
        if (newUser.getEmail() != null) {
            query += "email = '" + newUser.getEmail() + "', ";
        }
        if (newUser.getPassword() != null) {
            newUser.setPassword(Encriptor.encrypt(newUser.getPassword()));
            query += "password = '" + newUser.getPassword() + "', ";
        }
        if (newUser.getName() != null) {
            query += "name = '" + newUser.getName() + "', ";
        }
        if (newUser.getPhone() != null) {
            query += "phone = '" + newUser.getPhone() + "', ";
        }
        if (newUser.getUbication() != null) {
            query += "ubication = '" + newUser.getUbication() + "', ";
        }
        query = query.substring(0, query.lastIndexOf(','));
        query += "WHERE email = '" + oldUser.getEmail() + "'";
        return query;
    }

    public static String getUsersByEmailQuery(String email) {
        return ""
                + "SELECT * "
                + "FROM users "
                + "WHERE email = '" + email + "'";
    }

}
