# stam-api

docker compose down -v
docker compose up -d

- API: http://localhost:8080
- Swagger: http://localhost:8080/swagger-ui/index.html
- Kafka UI: http://localhost:8090

./mvnw spring-boot:run -Dspring-boot.run.profiles=dev

communication via un topic Kafka pour importer des jeux en asynchrone.

### Topic

- Topic d'import catalogue: `stam.catalog.import`

Le producer publie des messages JSON sur ce topic, et le consumer les consomme pour insérer en base.


Le producer est la partie qui envoie un message dans Kafka.
prend un jeu (un `GameRequestDTO`), le transforme en JSON, puis l'envoie sur le topic `stam.catalog.import`.
le producer dépose des messages, sans se soucier de quand ils seront traités.


Le consumer est la partie qui reçoit les messages depuis Kafka.
écoute le topic `stam.catalog.import`, lit le JSON, le reconvertit en `GameRequestDTO`, puis appelle le service (`gameService.createGame`) pour créer le jeu en base de données.
L'intérêt principal est de traiter l'import en arrière-plan (asynchrone).

### Schéma (simplifié)

Producteur (partenaire)  --->  Kafka topic stam.catalog.import  --->  stam-api (consumer)  --->  Postgres

### Exemple de message

{
	"title": "Stam Racer 2026",
	"description": "Un jeu de course arcade.",
	"releaseDate": "2026-03-01",
	"price": 39.99,
	"imageUrl": "https://cdn.example.com/images/stam-racer-2026.jpg",
	"genreId": 1
}


 Ouvrir Kafka UI (http://localhost:8090)
 Publier un message sur le topic `stam.catalog.import`
 Vérifier l'insertion via l'API: `GET /api/games`
