package JacopoDeMaio.gestioneDispositivi.controller;

import JacopoDeMaio.gestioneDispositivi.entities.Dipendente;
import JacopoDeMaio.gestioneDispositivi.exceptions.BadRequestException;
import JacopoDeMaio.gestioneDispositivi.payloads.DipendenteDTO;
import JacopoDeMaio.gestioneDispositivi.payloads.DipendenteLoginDTO;
import JacopoDeMaio.gestioneDispositivi.payloads.DipendenteLoginResponseDTO;
import JacopoDeMaio.gestioneDispositivi.payloads.NewDipendenteResponseDTO;
import JacopoDeMaio.gestioneDispositivi.services.AuthService;
import JacopoDeMaio.gestioneDispositivi.services.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private DipendenteService dipendenteService;

    @PostMapping("/login")
    public DipendenteLoginResponseDTO login(@RequestBody DipendenteLoginDTO payload){
        return new DipendenteLoginResponseDTO(authService.authenticateDipendentiAndGenerateToken(payload));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NewDipendenteResponseDTO saveDipendente(@RequestBody @Validated DipendenteDTO payload, BindingResult validationResult){
        if(validationResult.hasErrors()){
            throw  new BadRequestException(validationResult.getAllErrors());
        }else return new NewDipendenteResponseDTO(dipendenteService.saveDipendente(payload).getId());
    }
}
