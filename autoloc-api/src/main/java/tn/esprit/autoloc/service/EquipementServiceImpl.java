package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.repository.EquipementRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipementServiceImpl implements IEquipementService {
    private final EquipementRepository repository;

    @Override public Equipement addEquipement(Equipement equipement) { return repository.save(equipement); }
    @Override public List<Equipement> getAllEquipements() { return repository.findAll(); }
    @Override public Equipement getEquipementById(Long id) { return repository.findById(id).orElse(null); }

    @Override
    public Equipement updateEquipement(Long id, Equipement equipement) {
        if(repository.existsById(id)) {
            equipement.setIdEquipement(id);
            return repository.save(equipement);
        }
        return null;
    }

    @Override public void deleteEquipement(Long id) { repository.deleteById(id); }
}