package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.repository.PaiementRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaiementServiceImpl implements IPaiementService {
    private final PaiementRepository repository;

    @Override public Paiement addPaiement(Paiement paiement) { return repository.save(paiement); }
    @Override public List<Paiement> getAllPaiements() { return repository.findAll(); }
    @Override public Paiement getPaiementById(Long id) { return repository.findById(id).orElse(null); }

    @Override
    public Paiement updatePaiement(Long id, Paiement paiement) {
        if(repository.existsById(id)) {
            paiement.setIdPaiement(id);
            return repository.save(paiement);
        }
        return null;
    }

    @Override public void deletePaiement(Long id) { repository.deleteById(id); }
}