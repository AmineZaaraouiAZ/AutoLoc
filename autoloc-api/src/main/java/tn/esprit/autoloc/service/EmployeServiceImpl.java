package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.repository.EmployeRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeServiceImpl implements IEmployeService {
    private final EmployeRepository repository;

    @Override public Employe addEmploye(Employe employe) { return repository.save(employe); }
    @Override public List<Employe> getAllEmployes() { return repository.findAll(); }
    @Override public Employe getEmployeById(Long id) { return repository.findById(id).orElse(null); }

    @Override
    public Employe updateEmploye(Long id, Employe employe) {
        if(repository.existsById(id)) {
            employe.setIdEmploye(id);
            return repository.save(employe);
        }
        return null;
    }

    @Override public void deleteEmploye(Long id) { repository.deleteById(id); }
}