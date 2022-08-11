package christmasRaces.entities.cars;

import static christmasRaces.common.ExceptionMessages.INVALID_HORSE_POWER;

public class SportsCar extends BaseCar{

    private static final double MUSCLE_CAR_CUBIC_CENTIMETERS = 3000;


    public SportsCar(String model, int horsePower) {
        super(model, horsePower, MUSCLE_CAR_CUBIC_CENTIMETERS);
    }

}
