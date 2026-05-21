# Parking Management System - Spring Boot Microservices

## Description
Système de gestion de parking basé sur une architecture microservices avec Spring Boot.
Le système est composé de 3 services indépendants qui communiquent entre eux via RestTemplate.

---

## Architecture

- parking-service (port 8081) : Gestion des places et des réservations
- payment-service (port 8082) : Gestion des paiements et factures
- access-control-service (port 8083) : Gestion des entrées et sorties (IoT)

---

## Technologies utilisées

- Java 17
- Spring Boot 4.0.6
- Spring Data JPA
- Spring Web (REST API)
- H2 Database (in-memory)
- Lombok
- Maven
- RestTemplate (communication entre services)

---

## Structure du projet
parking-management-system/
├── parking-service/
│   ├── model/          ParkingSpot, Reservation
│   ├── repository/     ParkingSpotRepository, ReservationRepository
│   ├── service/        ParkingSpotService, ReservationService
│   └── controller/     ParkingSpotController, ReservationController
│
├── payment-service/
│   ├── model/          Payment
│   ├── repository/     PaymentRepository
│   ├── service/        PaymentService
│   └── controller/     PaymentController
│
└── access-control-service/
├── model/          AccessLog
├── repository/     AccessLogRepository
├── service/        AccessControlService
└── controller/     AccessControlController

---

## Lancer le projet

### Prérequis
- Java 17
- Maven
- IntelliJ IDEA (recommandé)

### Etapes
1. Cloner le repository
git clone https://github.com/oumame05/parking-management-system.git

2. Ouvrir chaque service dans votre IDE comme projet Maven séparé

3. Lancer les 3 services dans cet ordre :
   - access-control-service (port 8083)
   - payment-service (port 8082)
   - parking-service (port 8081)

---

## API Endpoints

### parking-service (port 8081)

| Method | URL | Description |
|--------|-----|-------------|
| GET | /api/spots | Lister toutes les places |
| POST | /api/spots | Ajouter une place |
| DELETE | /api/spots/{id} | Supprimer une place |
| GET | /api/reservations | Lister toutes les réservations |
| POST | /api/reservations | Créer une réservation |
| DELETE | /api/reservations/{id} | Annuler une réservation |
| GET | /api/reservations/spot/{spotId} | Réservations par place |
| GET | /api/reservations/owner/{ownerName} | Réservations par propriétaire |

### payment-service (port 8082)

| Method | URL | Description |
|--------|-----|-------------|
| GET | /api/payments | Lister tous les paiements |
| POST | /api/payments | Créer une facture |
| PUT | /api/payments/{id}/confirm | Confirmer un paiement |
| PUT | /api/payments/{id}/cancel | Annuler un paiement |
| GET | /api/payments/owner/{ownerName} | Paiements par propriétaire |

### access-control-service (port 8083)

| Method | URL | Description |
|--------|-----|-------------|
| GET | /api/access | Lister tous les logs |
| POST | /api/access/entry | Enregistrer une entrée |
| POST | /api/access/exit | Enregistrer une sortie |
| GET | /api/access/car/{carPlate} | Logs par plaque |

---

## Exemple d'utilisation

### 1. Ajouter une place
POST http://localhost:8081/api/spots
{
"spotNumber": "A1",
"available": true,
"type": "NORMAL"
}

### 2. Créer une réservation
Lors de la création d'une réservation, le système:
- Marque la place comme non disponible
- Crée automatiquement une facture dans payment-service
- Enregistre automatiquement une entree dans access-control-service

### 3. Annuler une réservation
DELETE http://localhost:8081/api/reservations/1

Lors de l'annulation, le système:
- Remet la place comme disponible
- Enregistre automatiquement une sortie dans access-control-service

---

## Etapes de construction

1. Création de parking-service avec ParkingSpot (CRUD) et Reservation
2. Création de payment-service avec gestion des statuts PENDING, PAID, CANCELLED
3. Création de access-control-service avec logs ENTRY et EXIT
4. Communication entre services via RestTemplate
5. Tests avec Postman

---

## Auteur
Groupe G10 - Matricules : 24034, 24246, 24214, 24206, 24205
