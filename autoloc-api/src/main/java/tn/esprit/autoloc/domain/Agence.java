package tn.esprit.autoloc.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Agence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAgence;
    private String nom;
    private String ville;
    private String adresse;
    private String telephone;
    @OneToMany(mappedBy = "agence", cascade = CascadeType.ALL)
    @JsonManagedReference // Indique que c'est le côté principal de la relation
    private List<Vehicule> vehicules;

    @OneToMany(mappedBy = "agence", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Employe> employes;
}