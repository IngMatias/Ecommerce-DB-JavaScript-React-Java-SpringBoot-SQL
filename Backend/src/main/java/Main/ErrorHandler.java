
package Main;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleCustomException(Exception exception) {
        Map<String, String> errorMap = new HashMap<>();
        String msg = exception.getMessage();
        if (msg == null) {
            msg = "Error";
        }
        errorMap.put("error", msg);
        exception.printStackTrace();
        return errorMap;
    }

} 