package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.service.IContratService;
import tn.esprit.autoloc.web.dto.ContratDto;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "Contrats")
@RestController
@RequestMapping("/api/contrats")
@RequiredArgsConstructor
public class ContratController {
    private final IContratService service;

    @PostMapping
    public Contrat addContrat(@RequestBody Contrat contrat) {
        return service.addContrat(contrat);
    }

    @GetMapping
    public List<Contrat> getAllContrats() {
        return service.getAllContrats();
    }

    @GetMapping("/{id}")
    public Contrat getContratById(@PathVariable Long id) {
        return service.getContratById(id);
    }

    @PutMapping("/{id}")
    public Contrat updateContrat(@PathVariable Long id, @RequestBody Contrat contrat) {
        return service.updateContrat(id, contrat);
    }

    @DeleteMapping("/{id}")
    public void deleteContrat(@PathVariable Long id) {
        service.deleteContrat(id);
    }
    @Operation(summary = "Créer et affecter un contrat à une réservation")
    @PostMapping("/affecter-reservation")
    public ResponseEntity<ContratDto> affecterContratAReservation(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateSignature,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDebut,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFin) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.affecterContratAReservation(dateSignature, dateDebut, dateFin));
    }
}