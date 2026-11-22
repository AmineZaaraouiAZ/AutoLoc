package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.repository.MaintenanceRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceServiceImpl implements IMaintenanceService {
    private final MaintenanceRepository repository;

    @Override public Maintenance addMaintenance(Maintenance maintenance) { return repository.save(maintenance); }
    @Override public List<Maintenance> getAllMaintenances() { return repository.findAll(); }
    @Override public Maintenance getMaintenanceById(Long id) { return repository.findById(id).orElse(null); }

    @Override
    public Maintenance updateMaintenance(Long id, Maintenance maintenance) {
        if(repository.existsById(id)) {
            maintenance.setIdMaintenance(id);
            return repository.save(maintenance);
        }
        return null;
    }

    @Override public void deleteMaintenance(Long id) { repository.deleteById(id); }
}