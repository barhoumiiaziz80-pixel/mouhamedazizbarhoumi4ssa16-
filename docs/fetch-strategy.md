# Atelier 2 — Stratégies Fetch et Cascade JPA

## Projet AutoLoc

### 1. Introduction

Dans le cadre de l'Atelier 2, nous avons implémenté les
associations JPA entre les entités de l'application AutoLoc.

L'objectif est de gérer les relations entre les entités,
les stratégies de chargement des données et les opérations
de persistance en cascade.

### 2. Stratégies de chargement

#### FetchType.LAZY

La stratégie LAZY permet de différer le chargement des
données associées jusqu'à leur utilisation.

Avantages :
- Réduction des chargements inutiles.
- Meilleure maîtrise des requêtes SQL.
- Amélioration potentielle des performances.

#### FetchType.EAGER

La stratégie EAGER demande le chargement de l'association
en même temps que l'entité principale.

Avantages :
- Les données associées sont directement disponibles.

Inconvénients :
- Chargement potentiellement inutile de certaines données.
- Risque d'augmentation du nombre de requêtes SQL.

### 3. Associations réalisées

| Association | Type | Fetch | Cascade |
|---|---|---|---|
| Contrat → Paiement | OneToMany | LAZY | ALL |
| Paiement → Contrat | ManyToOne | LAZY | Aucune |
| Agence → Vehicule | OneToMany | LAZY | Aucune |
| Vehicule → Agence | ManyToOne | LAZY | Aucune |
| Agence → Employe | OneToMany | LAZY | Aucune |
| Employe → Agence | ManyToOne | LAZY | Aucune |
| Vehicule ↔ Equipement | ManyToMany | LAZY | Aucune |
| Client → Reservation | OneToMany | LAZY | PERSIST |
| Reservation → Client | ManyToOne | LAZY | Aucune |
| Reservation → Vehicule | ManyToOne | LAZY | Aucune |
| Reservation → Contrat | OneToOne | LAZY | ALL |
| Contrat → Reservation | OneToOne | LAZY | Aucune |
| Vehicule → Maintenance | OneToMany | LAZY | PERSIST |
| Maintenance → Vehicule | ManyToOne | LAZY | Aucune |

### 4. Gestion des cascades

#### CascadeType.ALL

Utilisée entre Contrat et Paiement ainsi qu'entre
Reservation et Contrat.

Elle propage les principales opérations JPA vers
les entités associées.

#### CascadeType.PERSIST

Utilisée entre Client et Reservation ainsi qu'entre
Vehicule et Maintenance.

Elle permet de propager l'opération de persistance
aux nouvelles entités associées.

#### orphanRemoval = true

Utilisée dans l'association Contrat → Paiement.

Elle permet de supprimer un paiement devenu orphelin
lorsqu'il est retiré de la collection gérée.

### 5. Vérification du schéma

Les associations ont été configurées avec les
annotations JPA et Hibernate.

Le démarrage de Spring Boot et les instructions SQL
générées ont permis de vérifier la création des
tables et des clés étrangères.

Les tests fonctionnels de chargement LAZY et
des cascades restent à réaliser.

### 6. Conclusion

L'utilisation des associations JPA permet de
structurer le modèle de données AutoLoc.

Le choix de FetchType.LAZY contribue à limiter
les chargements automatiques inutiles.

Les cascades sont définies selon les besoins de
chaque relation afin de maîtriser les opérations
de persistance et de suppression.