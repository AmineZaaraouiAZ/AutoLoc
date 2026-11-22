package tn.esprit.autoloc.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.service.IEmployeService;
import java.util.List;

@RestController
@RequestMapping("/api/employes")
@RequiredArgsConstructor
public class EmployeController {
    private final IEmployeService service;

    @PostMapping public Employe addEmploye(@RequestBody Employe employe) { return service.addEmploye(employe); }
    @GetMapping public List<Employe> getAllEmployes() { return service.getAllEmployes(); }
    @GetMapping("/{id}") public Employe getEmployeById(@PathVariable Long id) { return service.getEmployeById(id); }
    @PutMapping("/{id}") public Employe updateEmploye(@PathVariable Long id, @RequestBody Employe employe) { return service.updateEmploye(id, employe); }
    @DeleteMapping("/{id}") public void deleteEmploye(@PathVariable Long id) { service.deleteEmploye(id); }
}