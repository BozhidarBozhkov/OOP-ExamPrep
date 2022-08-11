package christmasRaces.core;

import christmasRaces.core.interfaces.Controller;
import christmasRaces.entities.cars.Car;
import christmasRaces.entities.cars.MuscleCar;
import christmasRaces.entities.cars.SportsCar;
import christmasRaces.entities.drivers.Driver;
import christmasRaces.entities.drivers.DriverImpl;
import christmasRaces.entities.races.Race;
import christmasRaces.entities.races.RaceImpl;
import christmasRaces.repositories.interfaces.Repository;


import java.util.List;
import java.util.stream.Collectors;

import static christmasRaces.common.ExceptionMessages.*;
import static christmasRaces.common.OutputMessages.*;

public class ControllerImpl implements Controller {

    private Repository<Driver> driverRepository;
    private Repository<Car> carRepository;
    private Repository<Race> raceRepository;

    public ControllerImpl(Repository<Driver> driverRepository, Repository<Car> carRepository, Repository<Race> raceRepository) {
        this.driverRepository = driverRepository;
        this.carRepository = carRepository;
        this.raceRepository = raceRepository;
    }

    @Override
    public String createDriver(String driver) {
        if (driverRepository.getByName(driver) != null) {
            throw new IllegalArgumentException(String.format(DRIVER_EXISTS, driver));
        }
        Driver driver1 = new DriverImpl(driver);
        driverRepository.add(driver1);
        return String.format(DRIVER_CREATED, driver);
    }

    @Override
    public String createCar(String type, String model, int horsePower) {
        Car car = null;
        String typeOfCar = null;

        if (carRepository.getByName(model) != null) {
            throw new IllegalArgumentException(String.format(CAR_EXISTS, model));
        }

        if ("Muscle".equals(type)) {
            car = new MuscleCar(model, horsePower);
            typeOfCar = "MuscleCar";
        }

        if ("Sports".equals(type)) {
            car = new SportsCar(model, horsePower);
            typeOfCar = "SportsCar";
        }


//        switch (type) {
//            case "MuscleCar":
//                car = new MuscleCar(model, horsePower);
//                break;
//            case "SportsCar":
//                car = new SportsCar(model, horsePower);
//                break;
//            default:
//                throw new IllegalArgumentException(String.format(CAR_EXISTS, model));
//        }
        carRepository.add(car);
        return String.format(CAR_CREATED, typeOfCar, model);
    }

    @Override
    public String addCarToDriver(String driverName, String carModel) {
        Driver driver = driverRepository.getByName(driverName);
        if (driver == null) {
            throw new IllegalArgumentException(String.format(DRIVER_NOT_FOUND, driverName));
        }

        Car car = carRepository.getByName(carModel);
        if (car == null) {
            throw new IllegalArgumentException(String.format(CAR_NOT_FOUND, carModel));
        }

        driver.addCar(car);
        return String.format(CAR_ADDED, driverName, carModel);
    }

    @Override
    public String addDriverToRace(String raceName, String driverName) {

        Race race = raceRepository.getByName(raceName);
        if (race == null) {
            throw new IllegalArgumentException(String.format(RACE_NOT_FOUND, raceName));
        }

        Driver driver = driverRepository.getByName(driverName);
        if (driver == null) {
            throw new IllegalArgumentException(String.format(DRIVER_NOT_FOUND, driverName));
        }

        race.addDriver(driver);
        return String.format(DRIVER_ADDED, driverName, raceName);
    }

    @Override
    public String startRace(String raceName) {
        Race race = raceRepository.getByName(raceName);
        if (race == null) {
            throw new IllegalArgumentException(String.format(RACE_NOT_FOUND, raceName));
        }
        if (race.getDrivers().stream().filter(Driver::getCanParticipate).count() < 3) {
            throw new IllegalArgumentException(String.format(RACE_INVALID, raceName, 3));
        }

        List<Driver> fastestDrivers = race.getDrivers().stream()
                .sorted((d1, d2) -> Double.compare(d2.getCar().calculateRacePoints(race.getLaps()), d1.getCar().calculateRacePoints(race.getLaps()))).
                collect(Collectors.toList());

        raceRepository.remove(race);

//        "Driver {first driver name} wins {race name} race."
//        "Driver {second driver name} is second in {race name} race."
//        "Driver {third driver name} is third in {race name} race."

        return String.format(DRIVER_FIRST_POSITION, fastestDrivers.get(0).getName(), raceName) + System.lineSeparator()
                + String.format(DRIVER_SECOND_POSITION, fastestDrivers.get(1).getName(), raceName) + System.lineSeparator()
                + String.format(DRIVER_THIRD_POSITION, fastestDrivers.get(2).getName(), raceName);


    }

    @Override
    public String createRace(String name, int laps) {
        Race race = raceRepository.getByName(name);
        if (race != null) {
            throw new IllegalArgumentException(String.format(RACE_EXISTS, name));
        }

        Race newRace = new RaceImpl(name, laps);
        raceRepository.add(newRace);
        return String.format(RACE_CREATED, name);
    }
}
