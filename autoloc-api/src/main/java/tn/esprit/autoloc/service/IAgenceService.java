package tn.esprit.autoloc.service;

import tn.esprit.autoloc.web.dto.AgenceDto;
import java.util.List;

public interface IAgenceService {
    AgenceDto addAgence(AgenceDto agenceDto);
    List<AgenceDto> getAllAgences();
    AgenceDto getAgenceById(Long id);
    AgenceDto updateAgence(Long id, AgenceDto agenceDto);
    void deleteAgence(Long id);
}