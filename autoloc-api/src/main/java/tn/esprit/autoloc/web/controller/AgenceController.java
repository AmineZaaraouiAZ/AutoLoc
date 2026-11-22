package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.service.IAgenceService;
import tn.esprit.autoloc.web.dto.AgenceDto;

import java.util.List;

@Tag(name = "Gestion des Agences (DTO)")
@RestController
@RequestMapping("/api/agences")
@RequiredArgsConstructor
public class AgenceController {

    private final IAgenceService agenceService;

    @Operation(summary = "Ajouter une agence")
    @PostMapping
    public AgenceDto addAgence(@Valid @RequestBody AgenceDto dto) {
        return agenceService.addAgence(dto);
    }

    @Operation(summary = "Lister les agences")
    @GetMapping
    public List<AgenceDto> getAllAgences() {
        return agenceService.getAllAgences();
    }

    @Operation(summary = "Trouver une agence par ID")
    @GetMapping("/{id}")
    public AgenceDto getAgenceById(@PathVariable Long id) {
        return agenceService.getAgenceById(id);
    }

    @Operation(summary = "Mettre à jour une agence")
    @PutMapping("/{id}")
    public AgenceDto updateAgence(@PathVariable Long id, @Valid @RequestBody AgenceDto dto) {
        return agenceService.updateAgence(id, dto);
    }

    @Operation(summary = "Supprimer une agence")
    @DeleteMapping("/{id}")
    public void deleteAgence(@PathVariable Long id) {
        agenceService.deleteAgence(id);
    }
}