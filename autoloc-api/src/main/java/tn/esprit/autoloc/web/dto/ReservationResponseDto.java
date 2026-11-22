package tn.esprit.autoloc.web.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservationResponseDto {
    private Long idReservation;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String statut;
    private Long idClient;
    private String nomClient;
    private String prenomClient;
    private Long idVehicule;
    private String immatriculationVehicule;
    private ContratDto contrat;
}