package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.service.IClientService;
import tn.esprit.autoloc.web.dto.ClientDto;

import java.util.List;

@Tag(name = "Gestion des Clients (DTO)")
@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {

    private final IClientService clientService;

    @Operation(summary = "Ajouter un client")
    @PostMapping
    public ClientDto addClient(@Valid @RequestBody ClientDto dto) {
        return clientService.addClient(dto);
    }

    @Operation(summary = "Lister les clients")
    @GetMapping
    public List<ClientDto> getAllClients() {
        return clientService.getAllClients();
    }

    @Operation(summary = "Trouver un client par ID")
    @GetMapping("/{id}")
    public ClientDto getClientById(@PathVariable Long id) {
        return clientService.getClientById(id);
    }

    @Operation(summary = "Mettre à jour un client")
    @PutMapping("/{id}")
    public ClientDto updateClient(@PathVariable Long id, @Valid @RequestBody ClientDto dto) {
        return clientService.updateClient(id, dto);
    }

    @Operation(summary = "Supprimer un client")
    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }
}