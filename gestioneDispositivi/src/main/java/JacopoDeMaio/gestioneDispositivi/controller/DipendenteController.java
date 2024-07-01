package JacopoDeMaio.gestioneDispositivi.controller;

import JacopoDeMaio.gestioneDispositivi.entities.Dipendente;
import JacopoDeMaio.gestioneDispositivi.exceptions.BadRequestException;
import JacopoDeMaio.gestioneDispositivi.payloads.DipendenteDTO;
import JacopoDeMaio.gestioneDispositivi.services.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/dipendenti")
public class DipendenteController {

    @Autowired
    private DipendenteService dipendenteService;

//    REST

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dipendente saveDipendente(@RequestBody @Validated DipendenteDTO payload, BindingResult validationResult){
        if(validationResult.hasErrors()){
            throw  new BadRequestException(validationResult.getAllErrors());
        }else return dipendenteService.saveDipendente(payload);
    }

    @GetMapping
    public Page<Dipendente> getDipendenteList(@RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "10") int size,
                                              @RequestParam(defaultValue = "id") String sortedBy){
        return dipendenteService.getDipendenteList(page,size,sortedBy);
    }

    @GetMapping("/{dipendenteId}")
    public Dipendente findDipendenteById(@PathVariable UUID dipendenteId){
        return dipendenteService.findDipendenteById(dipendenteId);
    }

    @PutMapping("/{dipendenteId}")
    public Dipendente findDipendenteByIdAndUpdate(@PathVariable UUID dipendenteId,@RequestBody Dipendente payload){
        return dipendenteService.findDipendenteByIdAndUpdate(dipendenteId,payload);
    }
    //
    @DeleteMapping("/{dipendenteId}")
    public void findDipendenteByIdAndDelete(@PathVariable UUID dipendenteId){
        dipendenteService.findDipendenteByIdAndDelete(dipendenteId);
    }

    @PatchMapping("/{dipendenteId}/avatar")
    public Dipendente uploadAvatar (@PathVariable UUID dipendenteId, @RequestParam("avatar") MultipartFile file ) throws IOException {
        return dipendenteService.uploadImage(file,dipendenteId);
    }

}
