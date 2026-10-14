package tn.esprit.azizspring22.Domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
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
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idClient;

    String nom;

    String prenom;

    String email;

    String telephone;

    String numPermis;

    LocalDate dateInscription;

    // Atelier 2 - Association Client <-> Reservation
    @OneToMany(
            mappedBy = "client",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY
    )
    @Builder.Default
    @ToString.Exclude
    List<Reservation> reservations = new ArrayList<>();

}