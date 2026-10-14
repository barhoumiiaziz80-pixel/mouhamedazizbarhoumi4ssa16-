package tn.esprit.azizspring22;

import jakarta.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.azizspring22.Domain.Contrat;
import tn.esprit.azizspring22.Domain.Paiement;
import tn.esprit.azizspring22.Domain.ModePaiement;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class ContratPaiementCascadeTest {

    @Autowired
    private EntityManager entityManager;

    @Test
    void testCascadePersist() {

        // 1. Créer un contrat
        Contrat contrat = Contrat.builder()
                .dateSignature(LocalDate.now())
                .montantTotal(new BigDecimal("500.00"))
                .valide(true)
                .build();

        // 2. Créer un paiement
        Paiement paiement = Paiement.builder()
                .montant(new BigDecimal("200.00"))
                .datePaiement(LocalDate.now())
                .modePaiement(ModePaiement.CARTE)
                .contrat(contrat)
                .build();

        // 3. Associer le paiement au contrat
        contrat.getPaiements().add(paiement);

        // 4. Enregistrer uniquement le contrat
        entityManager.persist(contrat);

        entityManager.flush();

        // 5. Vérifier que les deux entités sont enregistrées
        assertNotNull(contrat.getIdContrat());
        assertNotNull(paiement.getIdPaiement());

        System.out.println("TEST CASCADE PERSIST REUSSI");
    }
    @Test
    void testOrphanRemoval() {

        // 1. Créer un contrat
        Contrat contrat = Contrat.builder()
                .dateSignature(LocalDate.now())
                .montantTotal(new BigDecimal("500.00"))
                .valide(true)
                .build();

        // 2. Créer un paiement
        Paiement paiement = Paiement.builder()
                .montant(new BigDecimal("200.00"))
                .datePaiement(LocalDate.now())
                .modePaiement(ModePaiement.CARTE)
                .contrat(contrat)
                .build();

        // 3. Associer le paiement au contrat
        contrat.getPaiements().add(paiement);

        // 4. Enregistrer le contrat et son paiement
        entityManager.persist(contrat);
        entityManager.flush();

        Long paiementId = paiement.getIdPaiement();

        assertNotNull(paiementId);

        // 5. Retirer le paiement du contrat
        contrat.getPaiements().remove(paiement);
        paiement.setContrat(null);

        // 6. Appliquer les modifications
        entityManager.flush();
        entityManager.clear();

        // 7. Vérifier la suppression du paiement
        Paiement paiementSupprime =
                entityManager.find(Paiement.class, paiementId);

        assertNull(paiementSupprime);

        System.out.println("TEST ORPHAN REMOVAL REUSSI");
    }
}