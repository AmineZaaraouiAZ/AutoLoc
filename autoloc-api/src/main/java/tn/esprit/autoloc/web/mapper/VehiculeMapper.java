package tn.esprit.autoloc.web.mapper;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.web.dto.VehiculeDto;

@Component
public class VehiculeMapper {

    public VehiculeDto toDto(Vehicule vehicule) {
        if (vehicule == null) return null;
        VehiculeDto dto = new VehiculeDto();
        dto.setIdVehicule(vehicule.getIdVehicule());
        dto.setImmatriculation(vehicule.getImmatriculation());
        dto.setMarque(vehicule.getMarque());
        dto.setModele(vehicule.getModele());
        dto.setCategorie(vehicule.getCategorie());
        dto.setTarifJournalier(vehicule.getTarifJournalier());
        dto.setStatut(vehicule.getStatut());
        if (vehicule.getAgence() != null) {
            dto.setAgenceId(vehicule.getAgence().getIdAgence());
        }
        return dto;
    }

    public Vehicule toEntity(VehiculeDto dto, Agence agence) {
        if (dto == null) return null;
        Vehicule vehicule = new Vehicule();
        vehicule.setIdVehicule(dto.getIdVehicule());
        vehicule.setImmatriculation(dto.getImmatriculation());
        vehicule.setMarque(dto.getMarque());
        vehicule.setModele(dto.getModele());
        vehicule.setCategorie(dto.getCategorie());
        vehicule.setTarifJournalier(dto.getTarifJournalier());
        vehicule.setStatut(dto.getStatut());
        vehicule.setAgence(agence);
        return vehicule;
    }
}