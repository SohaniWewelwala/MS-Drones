# MS-Drones

## Project Background
- This project uses SpringBoot framework along with H2 in-memory database.
- Required data has been preloaded to the project.
- Run the project using `./mvnw spring-boot:run` in the project root.
- To build the JAR file run `mvn clean install` (or skip tests using `mvn clean install -DskipTests`) in the project root.

## Assumptions
- When adding medications, they are added to a drone.
- When a drone is added to the database, its initial state is IDLE.
- Drone has a weight limit, and when adding medications that weight limit get reduced. When weight limit comes to 0 the state of the drone is LOADED (since it's fully loaded.)
- If the weight limit is greater than 0 then its in LOADING state after medication is added.
  `