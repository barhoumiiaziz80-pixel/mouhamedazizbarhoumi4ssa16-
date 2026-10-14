package tn.esprit.azizspring22.Domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idReservation;

    LocalDate dateDebut;

    LocalDate dateFin;

    @Enumerated(value = EnumType.STRING)
    StatutReservation statut;

    // Association Reservation -> Client
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    Client client;

    // Association Reservation -> Vehicule
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    Vehicule vehicule;

    // Atelier 2 - Association Reservation <-> Contrat
    @OneToOne(
            mappedBy = "reservation",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @ToString.Exclude
    Contrat contrat;

}