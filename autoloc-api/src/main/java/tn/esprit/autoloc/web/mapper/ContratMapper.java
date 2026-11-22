package tn.esprit.autoloc.web.mapper;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.web.dto.ContratDto;

@Component
public class ContratMapper {

    public ContratDto toDto(Contrat contrat) {
        if (contrat == null) {
            return null;
        }
        ContratDto dto = new ContratDto();
        dto.setIdContrat(contrat.getIdContrat());
        dto.setDateSignature(contrat.getDateSignature());
        dto.setMontantTotal(contrat.getMontantTotal());
        if (contrat.getReservation() != null) {
            dto.setIdReservation(contrat.getReservation().getIdReservation());
            dto.setDateDebut(contrat.getReservation().getDateDebut());
            dto.setDateFin(contrat.getReservation().getDateFin());
        }
        return dto;
    }

    public Contrat toEntity(ContratDto dto, Reservation reservation) {
        if (dto == null) {
            return null;
        }
        Contrat contrat = new Contrat();
        contrat.setIdContrat(dto.getIdContrat());
        contrat.setDateSignature(dto.getDateSignature());
        contrat.setMontantTotal(dto.getMontantTotal());
        contrat.setValide(true);
        contrat.setReservation(reservation);
        return contrat;
    }
}