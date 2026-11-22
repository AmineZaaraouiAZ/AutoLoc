package tn.esprit.autoloc.web.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservationWithContratRequestDto {
    @NotNull(message = "L'identifiant du client est obligatoire")
    private Long idClient;

    @NotNull(message = "L'identifiant du véhicule est obligatoire")
    private Long idVehicule;

    @NotNull(message = "La date de début est obligatoire")
    @FutureOrPresent(message = "La date de début ne peut pas être dans le passé")
    private LocalDate dateDebut;

    @NotNull(message = "La date de fin est obligatoire")
    private LocalDate dateFin;

    @NotNull(message = "La date de signature du contrat est obligatoire")
    private LocalDate dateSignature;
}