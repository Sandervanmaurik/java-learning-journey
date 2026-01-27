## Java Learning Journey

Small Spring Boot project that exercises core Java and Spring concepts.

### Run the app
1) Create a `.env` file (copy from `.env-example`) and fill in:
   - `OMDB_API_KEY`
   - `DB_USERNAME`
   - `DB_PASSWORD`
2) Start MySQL:
```bash
docker compose up -d
```
3) Start the app:
```bash
./mvnw clean spring-boot:run
```
