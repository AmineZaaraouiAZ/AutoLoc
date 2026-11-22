package tn.esprit.autoloc.web.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.web.dto.ReservationResponseDto;

@Component
@RequiredArgsConstructor
public class ReservationMapper {

    private final ContratMapper contratMapper;

    public ReservationResponseDto toResponseDto(Reservation reservation) {
        if (reservation == null) {
            return null;
        }
        ReservationResponseDto dto = new ReservationResponseDto();
        dto.setIdReservation(reservation.getIdReservation());
        dto.setDateDebut(reservation.getDateDebut());
        dto.setDateFin(reservation.getDateFin());
        if (reservation.getStatut() != null) {
            dto.setStatut(reservation.getStatut().name());
        }
        if (reservation.getClient() != null) {
            dto.setIdClient(reservation.getClient().getIdClient());
            dto.setNomClient(reservation.getClient().getNom());
            dto.setPrenomClient(reservation.getClient().getPrenom());
        }
        if (reservation.getVehicule() != null) {
            dto.setIdVehicule(reservation.getVehicule().getIdVehicule());
            dto.setImmatriculationVehicule(reservation.getVehicule().getImmatriculation());
        }
        if (reservation.getContrat() != null) {
            dto.setContrat(contratMapper.toDto(reservation.getContrat()));
        }
        return dto;
    }
}