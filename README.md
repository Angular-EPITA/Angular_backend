# stam-api

docker compose down -v
docker compose up -d

- API: http://localhost:8080
- Swagger: http://localhost:8080/swagger-ui/index.html
- Kafka UI: http://localhost:8090

## Lancer l'API en local

Prérequis: démarrer Postgres + Kafka

./mvnw -DskipTests package
docker compose up -d

### Dev

Le profil `dev` est le profil par défaut, donc:

./mvnw spring-boot:run


### Prod

Le profil `prod` attend des variables d'environnement (datasource + kafka):

SPRING_PROFILES_ACTIVE=prod \
SPRING_DATASOURCE_URL='jdbc:postgresql://localhost:5432/stam_db' \
SPRING_DATASOURCE_USERNAME='stam_admin' \
SPRING_DATASOURCE_PASSWORD='stam_password' \
SPRING_KAFKA_BOOTSTRAP_SERVERS='localhost:29092' \
./mvnw spring-boot:run
