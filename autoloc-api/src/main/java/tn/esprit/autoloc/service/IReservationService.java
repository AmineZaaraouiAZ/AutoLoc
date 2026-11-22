package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.web.dto.ReservationResponseDto;
import tn.esprit.autoloc.web.dto.ReservationWithContratRequestDto;

import java.util.List;

public interface IReservationService {
    Reservation addReservation(Reservation reservation);
    List<Reservation> getAllReservations();
    Reservation getReservationById(Long id);
    Reservation updateReservation(Long id, Reservation reservation);
    void deleteReservation(Long id);

    ReservationResponseDto affecterReservationAClient(Long idReservation, String nom, String prenom);
    ReservationResponseDto creerReservationEtContratAssocie(ReservationWithContratRequestDto requestDto);
}