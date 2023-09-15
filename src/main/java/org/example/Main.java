package org.example;

import org.example.entities.Client;
import org.example.entities.Planet;
import org.example.services.ClientCrudService;
import org.example.services.PlanetCrudService;
import org.example.util.DatabaseMigration;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DatabaseMigration migration = new DatabaseMigration();
        migration.initDb();

        // Testing Client
//        ClientCrudService clientCrudService = new ClientCrudService();
//        Client client = new Client();
//        client.setName("Young Sheldon");
//        long clientId = clientCrudService.create(client);
//
//        Client justAddedClient = clientCrudService.getById(clientId);
//        System.out.println("We just added the new client: " + justAddedClient);
//
//        List<Client> allClients = clientCrudService.listAll();
//        System.out.println("Full list of clients: " + allClients);

        // Testing Planet
        PlanetCrudService planetCrudService = new PlanetCrudService();
        Planet planet = new Planet();
        planet.setId("URN7");
        planet.setName("Uranus");
        String planetId = planetCrudService.create(planet);

        Planet justAddedPlanet = planetCrudService.getById(planetId);
        System.out.println("\"We just added the new planet: " + justAddedPlanet);

        List<Planet> allPlanets = planetCrudService.listAll();
        System.out.println("Full list of planets: " + allPlanets);

    }
}