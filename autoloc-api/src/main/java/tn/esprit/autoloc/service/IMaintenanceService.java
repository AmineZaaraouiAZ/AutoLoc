package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Maintenance;
import java.util.List;

public interface IMaintenanceService {
    Maintenance addMaintenance(Maintenance maintenance);
    List<Maintenance> getAllMaintenances();
    Maintenance getMaintenanceById(Long id);
    Maintenance updateMaintenance(Long id, Maintenance maintenance);
    void deleteMaintenance(Long id);
}