package DAOs;

import Convertors.UserConvertor;
import DAOExceptions.UserEmailInUseException;
import DTOs.UserDTO;
import Queries.UserQueries;
import Database.Database;
import Exceptions.UserValidationException;
import Utils.Encriptor;
import Validators.UserValidator;
import java.sql.SQLException;

public class UserDAO {

    public static boolean insertUser(UserDTO user) throws UserEmailInUseException, SQLException, UserValidationException {
        user.setEmail(user.getEmail().trim().toLowerCase());
        System.out.println(UserValidator.validateEmail(user.getEmail()));
        System.out.println(UserValidator.validatePassword(user.getPassword()));
        if(!(UserValidator.validateEmail(user.getEmail()) && UserValidator.validatePassword(user.getPassword()))){
            throw new UserValidationException();
        }
        
        UserDTO alreadyUser = UserDAO.getUserByEmail(user.getEmail());
        if (alreadyUser != null) {
            throw new UserEmailInUseException();
        }

        user.setPassword(Encriptor.encrypt(user.getPassword()));
        String query = UserQueries.insertUserQuery(user);
        return Database.execute(query);
    }

    public static void updateUserByEmail(UserDTO oldUser, UserDTO newUser) throws SQLException {
        newUser.setEmail(newUser.getEmail().trim().toLowerCase());
        String query = UserQueries.updateUserByEmailQuery(oldUser, newUser);
        Database.executeQuery(query);
    }

    public static boolean authenticateUser(UserDTO user) throws SQLException {
        System.out.println(user.getEmail());
        UserDTO queriedUser = getUserByEmail(user.getEmail());
        if (queriedUser != null){
            String encryptedPassword = Encriptor.encrypt(user.getPassword());
            return queriedUser.getPassword().equals(encryptedPassword);
        }
        return false;
    }

    public static UserDTO getUserByEmail(String email) throws SQLException {
        String query = UserQueries.getUsersByEmailQuery(email.trim().toLowerCase());
        return UserConvertor.toUserDTO(Database.executeQuery(query));
    }
    
//    public static int emailInUseQuantity(String email) throws SQLException {
//        String query = UserQueries.getUsersByEmailQuery(email);
//        return UserConvertor.toUserQuantity(Database.executeQuery(query));
//    }
}
