package tn.esprit.autoloc.service;

import tn.esprit.autoloc.web.dto.VehiculeDto;
import java.util.List;

public interface IVehiculeService {
    VehiculeDto addVehicule(VehiculeDto vehiculeDto);
    List<VehiculeDto> getAllVehicules();
    VehiculeDto getVehiculeById(Long id);
    VehiculeDto updateVehicule(Long id, VehiculeDto vehiculeDto);
    void deleteVehicule(Long id);

    VehiculeDto affecterVehiculeAEquipement(Long idVehicule, Long idEquipement);
    VehiculeDto affecterMaintenanceAvehicule(String description, String immatriculation);
}