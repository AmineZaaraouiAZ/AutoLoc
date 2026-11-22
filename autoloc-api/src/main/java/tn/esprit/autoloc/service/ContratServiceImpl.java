package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.repository.ContratRepository;
import tn.esprit.autoloc.repository.ReservationRepository;
import tn.esprit.autoloc.web.dto.ContratDto;
import tn.esprit.autoloc.web.mapper.ContratMapper;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ContratServiceImpl implements IContratService {

    private final ContratRepository contratRepository;
    private final ReservationRepository reservationRepository;
    private final ContratMapper contratMapper;

    @Override
    public Contrat addContrat(Contrat contrat) {
        return contratRepository.save(contrat);
    }

    @Override
    public List<Contrat> getAllContrats() {
        return contratRepository.findAll();
    }

    @Override
    public Contrat getContratById(Long id) {
        return contratRepository.findById(id).orElse(null);
    }

    @Override
    public Contrat updateContrat(Long id, Contrat contrat) {
        if (contratRepository.existsById(id)) {
            contrat.setIdContrat(id);
            return contratRepository.save(contrat);
        }
        return null;
    }

    @Override
    public void deleteContrat(Long id) {
        contratRepository.deleteById(id);
    }

    @Override
    public ContratDto affecterContratAReservation(LocalDate dateSignature, LocalDate dateDebut, LocalDate dateFin) {
        Reservation reservation = reservationRepository.findTopByOrderByIdReservationDesc()
                .orElseThrow(() -> new RuntimeException("Aucune réservation disponible pour affecter le contrat"));

        Contrat contrat = new Contrat();
        contrat.setDateSignature(dateSignature);
        contrat.setValide(true);
        contrat.setReservation(reservation);

        Contrat saved = contratRepository.save(contrat);
        return contratMapper.toDto(saved);
    }
}