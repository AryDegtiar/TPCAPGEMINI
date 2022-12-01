package TPBASE.tpBase.entidades.superclases;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ApplicationExceptionHandler {
/*
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInavalidArgument( MethodArgumentNotValidException ex ){
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> errorMap.put(fieldError.getField(), fieldError.getDefaultMessage()));
    }


 */

}
