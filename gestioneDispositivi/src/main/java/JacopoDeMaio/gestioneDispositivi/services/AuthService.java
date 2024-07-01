package JacopoDeMaio.gestioneDispositivi.services;

import JacopoDeMaio.gestioneDispositivi.entities.Dipendente;
import JacopoDeMaio.gestioneDispositivi.exceptions.UnauthorizedException;
import JacopoDeMaio.gestioneDispositivi.payloads.DipendenteLoginDTO;
import JacopoDeMaio.gestioneDispositivi.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private DipendenteService dipendenteService;

    @Autowired
    private JWTTools jwtTools;

    public String authenticateDipendentiAndGenerateToken(DipendenteLoginDTO payload) {

        Dipendente dipendente = this.dipendenteService.findByEmail(payload.email());

        if (dipendente.getPassword().equals(payload.password())) {
            return jwtTools.createToken(dipendente);
        } else {
            throw new UnauthorizedException("credenziali non corrette");
        }
    }
}
