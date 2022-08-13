package spaceStation.core;

import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.astronauts.Biologist;
import spaceStation.models.astronauts.Geodesist;
import spaceStation.models.astronauts.Meteorologist;
import spaceStation.models.mission.Mission;
import spaceStation.models.mission.MissionImpl;
import spaceStation.models.planets.Planet;
import spaceStation.models.planets.PlanetImpl;
import spaceStation.repositories.AstronautRepository;
import spaceStation.repositories.PlanetRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static spaceStation.common.ConstantMessages.*;
import static spaceStation.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private AstronautRepository astronautRepository;
    private PlanetRepository planetRepository;
    private int exploredPlanets;

    public ControllerImpl() {
        this.astronautRepository = new AstronautRepository();
        this.planetRepository = new PlanetRepository();
        this.exploredPlanets = 0;
    }

    @Override
    public String addAstronaut(String type, String astronautName) {
        Astronaut astronaut = null;
        switch (type) {
            case "Biologist":
                astronaut = new Biologist(astronautName);
                break;
            case "Geodesist":
                astronaut = new Geodesist(astronautName);
                break;
            case "Meteorologist":
                astronaut = new Meteorologist(astronautName);
                break;
            default:
                throw new IllegalArgumentException(ASTRONAUT_INVALID_TYPE);
        }

        astronautRepository.add(astronaut);
        return String.format(ASTRONAUT_ADDED, type, astronautName);
    }

    @Override
    public String addPlanet(String planetName, String... items) {
        Planet planet = new PlanetImpl(planetName);
        planet.getItems().addAll(Arrays.asList(items));
        planetRepository.add(planet);
        return String.format(PLANET_ADDED, planetName);
    }

    @Override
    public String retireAstronaut(String astronautName) {
        Astronaut astronautToRetire = astronautRepository.findByName(astronautName);
        if (astronautToRetire == null) {
            throw new IllegalArgumentException(String.format(ASTRONAUT_DOES_NOT_EXIST, astronautName));
        }
        astronautRepository.remove(astronautToRetire);

        return String.format(ASTRONAUT_RETIRED, astronautName);
    }

    @Override
    public String explorePlanet(String planetName) {

        List<Astronaut> eligibleAstronauts = astronautRepository.getModels()
                .stream().filter(a -> a.getOxygen() > 60).collect(Collectors.toList());

        if (eligibleAstronauts.isEmpty()) {
            throw new IllegalArgumentException(PLANET_ASTRONAUTS_DOES_NOT_EXISTS);
        }

        Planet planet = planetRepository.findByName(planetName);
        Mission mission = new MissionImpl();
        mission.explore(planet, eligibleAstronauts);
        exploredPlanets++;

        long deadAstronauts = eligibleAstronauts.stream().filter(a -> !a.canBreath()).count();
        return String.format(PLANET_EXPLORED, planetName, deadAstronauts);
    }

    @Override
    public String report() {

       return String.format(REPORT_PLANET_EXPLORED, exploredPlanets) + System.lineSeparator()
                + String.format(REPORT_ASTRONAUT_INFO) + System.lineSeparator()
                + astronautRepository.getModels()
                .stream().map(Objects::toString).collect(Collectors.joining(System.lineSeparator()));


//        StringBuilder info = new StringBuilder();
//
//        info.append(String.format(REPORT_PLANET_EXPLORED, this.exploredPlanets)).append(System.lineSeparator());
//        info.append(REPORT_ASTRONAUT_INFO).append(System.lineSeparator());
//
//        this.astronautRepository
//                .getModels()
//                .forEach(astronaut -> info
//                        .append(astronaut.toString())
//                        .append(System.lineSeparator()));
//
//        return info.toString().trim();
    }
}
