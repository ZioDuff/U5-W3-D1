package JacopoDeMaio.gestioneDispositivi.payloads;

import java.time.LocalDateTime;

public record ErrorsDTO(
        String message,
        LocalDateTime timeStamp
) {
}
