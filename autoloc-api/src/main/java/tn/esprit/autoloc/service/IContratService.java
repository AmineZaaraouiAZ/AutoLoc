package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.web.dto.ContratDto;

import java.util.List;
import java.time.LocalDate;

public interface IContratService {
    Contrat addContrat(Contrat contrat);
    List<Contrat> getAllContrats();
    Contrat getContratById(Long id);
    Contrat updateContrat(Long id, Contrat contrat);
    void deleteContrat(Long id);
    ContratDto affecterContratAReservation(LocalDate dateSignature, LocalDate dateDebut, LocalDate dateFin);
}