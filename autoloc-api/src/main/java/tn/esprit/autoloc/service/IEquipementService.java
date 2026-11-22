package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Equipement;
import java.util.List;

public interface IEquipementService {
    Equipement addEquipement(Equipement equipement);
    List<Equipement> getAllEquipements();
    Equipement getEquipementById(Long id);
    Equipement updateEquipement(Long id, Equipement equipement);
    void deleteEquipement(Long id);
}