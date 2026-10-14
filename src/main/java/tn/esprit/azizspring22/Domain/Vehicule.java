package tn.esprit.azizspring22.Domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
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
public class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idVehicule;

    String immatricule;

    String marque;

    String modele;

    @Enumerated(value = EnumType.STRING)
    CategorieVehicule categorie;

    BigDecimal tarifJournalier;

    @Enumerated(value = EnumType.STRING)
    StatutVehicule statut;

    // Atelier 2 - Association Vehicule -> Agence
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    Agence agence;

    // Atelier 2 - Association Vehicule <-> Equipement
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "vehicule_equipement",
            joinColumns = @JoinColumn(name = "id_vehicule"),
            inverseJoinColumns = @JoinColumn(name = "id_equipement")
    )
    @Builder.Default
    @ToString.Exclude
    Set<Equipement> equipements = new HashSet<>();

    // Atelier 2 - Association Vehicule <-> Maintenance
    @OneToMany(
            mappedBy = "vehicule",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY
    )
    @Builder.Default
    @ToString.Exclude
    List<Maintenance> maintenances = new ArrayList<>();

}