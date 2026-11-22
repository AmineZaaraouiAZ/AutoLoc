package tn.esprit.autoloc.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.service.IMaintenanceService;
import java.util.List;

@RestController
@RequestMapping("/api/maintenances")
@RequiredArgsConstructor
public class MaintenanceController {
    private final IMaintenanceService service;

    @PostMapping public Maintenance addMaintenance(@RequestBody Maintenance maintenance) { return service.addMaintenance(maintenance); }
    @GetMapping public List<Maintenance> getAllMaintenances() { return service.getAllMaintenances(); }
    @GetMapping("/{id}") public Maintenance getMaintenanceById(@PathVariable Long id) { return service.getMaintenanceById(id); }
    @PutMapping("/{id}") public Maintenance updateMaintenance(@PathVariable Long id, @RequestBody Maintenance maintenance) { return service.updateMaintenance(id, maintenance); }
    @DeleteMapping("/{id}") public void deleteMaintenance(@PathVariable Long id) { service.deleteMaintenance(id); }
}