package tn.esprit.azizspring22.Domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Equipement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idEquipement;

    String libelle;

    // Atelier 2 - Association Equipement <-> Vehicule
    @ManyToMany(
            mappedBy = "equipements",
            fetch = FetchType.LAZY
    )
    @Builder.Default
    @ToString.Exclude
    Set<Vehicule> vehicules = new HashSet<>();

}