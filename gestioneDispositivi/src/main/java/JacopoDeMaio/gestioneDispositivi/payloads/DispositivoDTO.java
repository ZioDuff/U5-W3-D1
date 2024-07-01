package JacopoDeMaio.gestioneDispositivi.payloads;

import JacopoDeMaio.gestioneDispositivi.enums.StatoDispositivo;
import JacopoDeMaio.gestioneDispositivi.enums.TipoDispositivo;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record DispositivoDTO(
        @NotNull(message = "Il campo tipo di dispositivo è obbligatorio ")
        TipoDispositivo tipoDispositivo,
        @NotNull(message = "il cmapo stato di dispositivo è obbligatorio")
        StatoDispositivo statoDispositivo,
        UUID dipendenteId

) {
}
