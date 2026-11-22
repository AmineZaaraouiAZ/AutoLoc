package tn.esprit.autoloc.web.mapper;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.web.dto.AgenceDto;

@Component
public class AgenceMapper {

    public AgenceDto toDto(Agence agence) {
        if (agence == null) return null;
        AgenceDto dto = new AgenceDto();
        dto.setIdAgence(agence.getIdAgence());
        dto.setNom(agence.getNom());
        dto.setVille(agence.getVille());
        dto.setAdresse(agence.getAdresse());
        dto.setTelephone(agence.getTelephone());
        return dto;
    }

    public Agence toEntity(AgenceDto dto) {
        if (dto == null) return null;
        Agence agence = new Agence();
        agence.setIdAgence(dto.getIdAgence());
        agence.setNom(dto.getNom());
        agence.setVille(dto.getVille());
        agence.setAdresse(dto.getAdresse());
        agence.setTelephone(dto.getTelephone());
        return agence;
    }
}