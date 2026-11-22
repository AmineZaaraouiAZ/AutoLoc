package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.service.IReservationService;
import tn.esprit.autoloc.web.dto.ReservationResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.web.dto.ReservationWithContratRequestDto;
import java.util.List;

@Tag(name = "Réservations")
@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {
    private final IReservationService service;

    @PostMapping
    public Reservation addReservation(@RequestBody Reservation reservation) {
        return service.addReservation(reservation);
    }

    @GetMapping
    public List<Reservation> getAllReservations() {
        return service.getAllReservations();
    }

    @GetMapping("/{id}")
    public Reservation getReservationById(@PathVariable Long id) {
        return service.getReservationById(id);
    }

    @PutMapping("/{id}")
    public Reservation updateReservation(@PathVariable Long id, @RequestBody Reservation reservation) {
        return service.updateReservation(id, reservation);
    }

    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable Long id) {
        service.deleteReservation(id);
    }

    @Operation(summary = "Affecter une réservation existante à un client par nom et prénom")
    @PutMapping("/{idReservation}/affecter-client")
    public ResponseEntity<ReservationResponseDto> affecterReservationAClient(
            @PathVariable Long idReservation,
            @RequestParam String nom,
            @RequestParam String prenom) {
        return ResponseEntity.ok(service.affecterReservationAClient(idReservation, nom, prenom));
    }

    @Operation(summary = "Créer une réservation et son contrat associé")
    @PostMapping("/avec-contrat")
    public ResponseEntity<ReservationResponseDto> creerReservationEtContratAssocie(
            @Valid @RequestBody ReservationWithContratRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.creerReservationEtContratAssocie(requestDto));
    }
}