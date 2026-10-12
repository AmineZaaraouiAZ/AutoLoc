package tn.esprit.autoloc.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.repository.ContratRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContratServiceImpl implements ContratService {

    private final ContratRepository contratRepository;

    @Override
    public List<Contrat> getAll() {
        return contratRepository.findAll();
    }

    @Override
    public Contrat getById(Long id) {
        return contratRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contrat introuvable : " + id));
    }

    @Override
    public Contrat create(Contrat contrat) {
        return contratRepository.save(contrat);
    }

    // Les paiements sont ignores dans le JSON : on les conserve pour que
    // orphanRemoval ne les supprime pas lors d'une mise a jour.
    @Override
    @Transactional
    public Contrat update(Long id, Contrat contrat) {
        Contrat existant = getById(id);
        contrat.setIdContrat(id);
        contrat.setPaiements(existant.getPaiements());
        return contratRepository.save(contrat);
    }

    @Override
    public void delete(Long id) {
        contratRepository.delete(getById(id));
    }
}
