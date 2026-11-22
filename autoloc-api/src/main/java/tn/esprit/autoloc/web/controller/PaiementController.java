package tn.esprit.autoloc.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.service.IPaiementService;
import java.util.List;

@RestController
@RequestMapping("/api/paiements")
@RequiredArgsConstructor
public class PaiementController {
    private final IPaiementService service;

    @PostMapping public Paiement addPaiement(@RequestBody Paiement paiement) { return service.addPaiement(paiement); }
    @GetMapping public List<Paiement> getAllPaiements() { return service.getAllPaiements(); }
    @GetMapping("/{id}") public Paiement getPaiementById(@PathVariable Long id) { return service.getPaiementById(id); }
    @PutMapping("/{id}") public Paiement updatePaiement(@PathVariable Long id, @RequestBody Paiement paiement) { return service.updatePaiement(id, paiement); }
    @DeleteMapping("/{id}") public void deletePaiement(@PathVariable Long id) { service.deletePaiement(id); }
}