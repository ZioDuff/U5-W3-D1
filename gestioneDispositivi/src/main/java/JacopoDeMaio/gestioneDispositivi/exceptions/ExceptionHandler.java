package JacopoDeMaio.gestioneDispositivi.exceptions;

import JacopoDeMaio.gestioneDispositivi.payloads.ErrorsDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorsDTO handleNotFound(NotFoundException ex){
        return new ErrorsDTO(ex.getMessage(), LocalDateTime.now());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsDTO handleBadRequest(BadRequestException ex ){
//        controllo necessario se vogliamo stampare gli errori presenti nell nostri DTO e farli tornare in sottoforma di lista
        if(ex.getErrorList() != null){
            String message = ex.getErrorList().stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.joining());
            return new ErrorsDTO(message, LocalDateTime.now());
        }else {

            return  new ErrorsDTO(ex.getMessage(),LocalDateTime.now());
        }
    }
}
