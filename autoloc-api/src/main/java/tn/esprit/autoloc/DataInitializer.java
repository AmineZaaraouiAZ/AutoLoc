package tn.esprit.autoloc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.CategorieVehicule;
import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.AgenceRepository;
import tn.esprit.autoloc.repository.VehiculeRepository;

import java.math.BigDecimal;
import java.util.List;

@Configuration
@Profile("dev")
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(VehiculeRepository vehiculeRepository, AgenceRepository agenceRepository) {
        return args -> {
            // On vérifie si la base est vide
            if (agenceRepository.count() == 0) {

                // 1. Création et sauvegarde de l'Agence
                Agence agence = new Agence();
                agence.setNom("AutoLoc Centrale Tunis");
                agence.setVille("Tunis");
                agence.setAdresse("Centre Urbain Nord");
                agence.setTelephone("71123456");
                agence = agenceRepository.save(agence); // On récupère l'instance avec l'ID généré

                // 2. Création des véhicules en les liant à l'agence
                Vehicule v1 = new Vehicule();
                v1.setImmatriculation("123-TU-4567");
                v1.setMarque("Peugeot");
                v1.setModele("208");
                v1.setCategorie(CategorieVehicule.CITADINE);
                v1.setTarifJournalier(new BigDecimal("80.00"));
                v1.setStatut(StatutVehicule.DISPONIBLE);
                v1.setAgence(agence); // Association !

                Vehicule v2 = new Vehicule();
                v2.setImmatriculation("987-TU-6543");
                v2.setMarque("Renault");
                v2.setModele("Clio");
                v2.setCategorie(CategorieVehicule.CITADINE);
                v2.setTarifJournalier(new BigDecimal("75.00"));
                v2.setStatut(StatutVehicule.DISPONIBLE);
                v2.setAgence(agence); // Association !

                Vehicule v3 = new Vehicule();
                v3.setImmatriculation("111-TU-2222");
                v3.setMarque("Volkswagen");
                v3.setModele("Tiguan");
                v3.setCategorie(CategorieVehicule.SUV);
                v3.setTarifJournalier(new BigDecimal("150.00"));
                v3.setStatut(StatutVehicule.LOUE);
                v3.setAgence(agence); // Association !

                // 3. Sauvegarde des véhicules
                vehiculeRepository.saveAll(List.of(v1, v2, v3));

                System.out.println("==========================================================");
                System.out.println("=> 1 Agence et 3 véhicules ont été insérés en base !");
                System.out.println("==========================================================");
            }
        };
    }
}