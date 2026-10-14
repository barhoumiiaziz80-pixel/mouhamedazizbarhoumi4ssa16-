package tn.esprit.azizspring22.Domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Agence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idAgence;

    String nom;

    String ville;

    String adresse;

    String telephone;

    // Atelier 2 - Association Agence <-> Vehicule
    @OneToMany(
            mappedBy = "agence",
            fetch = FetchType.LAZY
    )
    @Builder.Default
    @ToString.Exclude
    List<Vehicule> vehicules = new ArrayList<>();

    // Atelier 2 - Association Agence <-> Employe
    @OneToMany(
            mappedBy = "agence",
            fetch = FetchType.LAZY
    )
    @Builder.Default
    @ToString.Exclude
    List<Employe> employes = new ArrayList<>();

}