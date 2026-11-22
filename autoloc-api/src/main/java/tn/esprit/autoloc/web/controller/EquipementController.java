package tn.esprit.autoloc.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.service.IEquipementService;
import java.util.List;

@RestController
@RequestMapping("/api/equipements")
@RequiredArgsConstructor
public class EquipementController {
    private final IEquipementService service;

    @PostMapping public Equipement addEquipement(@RequestBody Equipement equipement) { return service.addEquipement(equipement); }
    @GetMapping public List<Equipement> getAllEquipements() { return service.getAllEquipements(); }
    @GetMapping("/{id}") public Equipement getEquipementById(@PathVariable Long id) { return service.getEquipementById(id); }
    @PutMapping("/{id}") public Equipement updateEquipement(@PathVariable Long id, @RequestBody Equipement equipement) { return service.updateEquipement(id, equipement); }
    @DeleteMapping("/{id}") public void deleteEquipement(@PathVariable Long id) { service.deleteEquipement(id); }
}