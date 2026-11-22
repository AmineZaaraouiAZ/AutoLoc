package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.ClientRepository;
import tn.esprit.autoloc.repository.ContratRepository;
import tn.esprit.autoloc.repository.ReservationRepository;
import tn.esprit.autoloc.repository.VehiculeRepository;
import tn.esprit.autoloc.web.dto.ReservationResponseDto;
import tn.esprit.autoloc.web.dto.ReservationWithContratRequestDto;
import tn.esprit.autoloc.web.mapper.ReservationMapper;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReservationServiceImpl implements IReservationService {

    private final ReservationRepository repository;
    private final ClientRepository clientRepository;
    private final VehiculeRepository vehiculeRepository;
    private final ContratRepository contratRepository;
    private final ReservationMapper reservationMapper;

    @Override
    public Reservation addReservation(Reservation reservation) {
        return repository.save(reservation);
    }

    @Override
    public List<Reservation> getAllReservations() {
        return repository.findAll();
    }

    @Override
    public Reservation getReservationById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Reservation updateReservation(Long id, Reservation reservation) {
        if (repository.existsById(id)) {
            reservation.setIdReservation(id);
            return repository.save(reservation);
        }
        return null;
    }

    @Override
    public void deleteReservation(Long id) {
        repository.deleteById(id);
    }

    @Override
    public ReservationResponseDto affecterReservationAClient(Long idReservation, String nom, String prenom) {
        Reservation reservation = repository.findById(idReservation)
                .orElseThrow(() -> new RuntimeException("Réservation introuvable avec l'id : " + idReservation));

        Client client = clientRepository.findByNomIgnoreCaseAndPrenomIgnoreCase(nom, prenom)
                .orElseThrow(() -> new RuntimeException("Client introuvable : " + nom + " " + prenom));

        reservation.setClient(client);
        Reservation saved = repository.save(reservation);
        return reservationMapper.toResponseDto(saved);
    }

    @Override
    public ReservationResponseDto creerReservationEtContratAssocie(ReservationWithContratRequestDto requestDto) {
        Client client = clientRepository.findById(requestDto.getIdClient())
                .orElseThrow(() -> new RuntimeException("Client introuvable avec l'id : " + requestDto.getIdClient()));

        Vehicule vehicule = vehiculeRepository.findById(requestDto.getIdVehicule())
                .orElseThrow(() -> new RuntimeException("Véhicule introuvable avec l'id : " + requestDto.getIdVehicule()));

        Reservation reservation = new Reservation();
        reservation.setClient(client);
        reservation.setVehicule(vehicule);
        reservation.setDateDebut(requestDto.getDateDebut());
        reservation.setDateFin(requestDto.getDateFin());
        Reservation savedReservation = repository.save(reservation);

        long jours = Math.max(1, ChronoUnit.DAYS.between(requestDto.getDateDebut(), requestDto.getDateFin()));
        BigDecimal tarif = vehicule.getTarifJournalier() != null ? vehicule.getTarifJournalier() : BigDecimal.ZERO;
        BigDecimal montantTotal = tarif.multiply(BigDecimal.valueOf(jours));

        Contrat contrat = new Contrat();
        contrat.setDateSignature(requestDto.getDateSignature());
        contrat.setMontantTotal(montantTotal);
        contrat.setValide(true);
        contrat.setReservation(savedReservation);
        Contrat savedContrat = contratRepository.save(contrat);

        savedReservation.setContrat(savedContrat);
        return reservationMapper.toResponseDto(savedReservation);
    }
}