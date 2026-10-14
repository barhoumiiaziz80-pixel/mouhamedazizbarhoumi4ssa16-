package tn.esprit.azizspring22.Domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
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
public class Contrat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idContrat;

    LocalDate dateSignature;

    BigDecimal montantTotal;

    boolean valide;

    // Association Contrat <-> Paiement
    @OneToMany(
            mappedBy = "contrat",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @Builder.Default
    @ToString.Exclude
    List<Paiement> paiements = new ArrayList<>();

    // Atelier 2 - Association Contrat -> Reservation
    @OneToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    Reservation reservation;

}