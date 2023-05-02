
package Main.Controllers;

import Convertors.UserConvertor;
import DAOs.UserDAO;
import DTOs.UserDTO;
import Exceptions.AuthenticationException;
import DAOExceptions.UserEmailInUseException;
import Exceptions.UserValidationException;
import java.sql.SQLException;
import java.util.Map;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @PostMapping("/user/register")
    public UserDTO registerUser(@RequestBody Map<String, Object> userMap) throws SQLException, UserEmailInUseException, UserValidationException {
        System.out.println("registerUser executed");
        UserDTO user = UserConvertor.toUserDTO(userMap);
        UserDAO.insertUser(user);
        return UserDAO.getUserByEmail(user.getEmail());
    }
    @PostMapping("/user/login")
    public UserDTO loginUser(@RequestBody Map<String, Object> userMap) throws SQLException, AuthenticationException, UserValidationException {        
        UserDTO user = UserConvertor.toUserDTO(userMap);
        if (!UserDAO.authenticateUser(user)) {
            throw new AuthenticationException();
        }
        
        return UserDAO.getUserByEmail(user.getEmail());
    }
    @PostMapping("/user/update")
    public UserDTO updateUser(@RequestBody Map<String, Object> userMap) throws SQLException, AuthenticationException, UserValidationException {
        UserDTO oldUser = UserConvertor.toUserDTO((Map<String, Object>)userMap.get("oldUser"));
        UserDTO newUser = UserConvertor.toUserDTO((Map<String, Object>)userMap.get("newUser"));
        if (!UserDAO.authenticateUser(oldUser)) {
            throw new AuthenticationException();
        }
        
        UserDAO.updateUserByEmail(oldUser, newUser);
        return UserDAO.getUserByEmail(newUser.getEmail());
    }
}
