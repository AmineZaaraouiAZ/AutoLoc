package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.AgenceRepository;
import tn.esprit.autoloc.repository.EquipementRepository;
import tn.esprit.autoloc.repository.MaintenanceRepository;
import tn.esprit.autoloc.repository.VehiculeRepository;
import tn.esprit.autoloc.web.dto.VehiculeDto;
import tn.esprit.autoloc.web.mapper.VehiculeMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class VehiculeServiceImpl implements IVehiculeService {

    private final VehiculeRepository vehiculeRepository;
    private final AgenceRepository agenceRepository;
    private final EquipementRepository equipementRepository;
    private final MaintenanceRepository maintenanceRepository;
    private final VehiculeMapper vehiculeMapper;

    @Override
    public VehiculeDto addVehicule(VehiculeDto dto) {
        Agence agence = dto.getAgenceId() != null ? agenceRepository.findById(dto.getAgenceId()).orElse(null) : null;
        Vehicule vehicule = vehiculeMapper.toEntity(dto, agence);
        return vehiculeMapper.toDto(vehiculeRepository.save(vehicule));
    }

    @Override
    public List<VehiculeDto> getAllVehicules() {
        return vehiculeRepository.findAll().stream().map(vehiculeMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public VehiculeDto getVehiculeById(Long id) {
        return vehiculeRepository.findById(id).map(vehiculeMapper::toDto).orElse(null);
    }

    @Override
    public VehiculeDto updateVehicule(Long id, VehiculeDto dto) {
        if (vehiculeRepository.existsById(id)) {
            Agence agence = dto.getAgenceId() != null ? agenceRepository.findById(dto.getAgenceId()).orElse(null) : null;
            Vehicule vehicule = vehiculeMapper.toEntity(dto, agence);
            vehicule.setIdVehicule(id);
            return vehiculeMapper.toDto(vehiculeRepository.save(vehicule));
        }
        return null;
    }

    @Override
    public void deleteVehicule(Long id) {
        vehiculeRepository.deleteById(id);
    }

    @Override
    public VehiculeDto affecterVehiculeAEquipement(Long idVehicule, Long idEquipement) {
        Vehicule vehicule = vehiculeRepository.findById(idVehicule)
                .orElseThrow(() -> new RuntimeException("Véhicule introuvable avec l'id : " + idVehicule));

        Equipement equipement = equipementRepository.findById(idEquipement)
                .orElseThrow(() -> new RuntimeException("Équipement introuvable avec l'id : " + idEquipement));

        if (vehicule.getEquipements() == null) {
            vehicule.setEquipements(new ArrayList<>());
        }
        if (!vehicule.getEquipements().contains(equipement)) {
            vehicule.getEquipements().add(equipement);
        }

        Vehicule saved = vehiculeRepository.save(vehicule);
        return vehiculeMapper.toDto(saved);
    }

    @Override
    public VehiculeDto affecterMaintenanceAvehicule(String description, String immatriculation) {
        Vehicule vehicule = vehiculeRepository.findByImmatriculation(immatriculation)
                .orElseThrow(() -> new RuntimeException("Véhicule introuvable avec l'immatriculation : " + immatriculation));

        Maintenance maintenance = new Maintenance();
        maintenance.setDescription(description);
        maintenance.setDateDebut(LocalDate.now());
        maintenance.setDateFin(LocalDate.now());
        maintenance.setVehicule(vehicule);
        maintenanceRepository.save(maintenance);

        Vehicule saved = vehiculeRepository.save(vehicule);
        return vehiculeMapper.toDto(saved);
    }
}